package com.codinginflow.bigots;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.lang.ref.WeakReference;

public class UnifiedService extends android.app.Service {
    private MediaPlayerManager mediaPlayerManager;
    private Handler handler;
    private Runnable updateRunnable;
    private MainActivity mainActivity;
    private BtcTurk btcTurkActivity;
    private boolean isRunning = false;
    private static boolean isMainServiceRunning = false;
    private static boolean isBtcTurkServiceRunning = false;
    private static final int NOTIFICATION_ID_PARIBU = 1;
    private static final int NOTIFICATION_ID_BTCTURK = 2;
    private PowerManager.WakeLock wakeLock;
    private NotificationManager notificationManager;
    public static WeakReference<MainActivity> mainActivityRef;
    public static WeakReference<BtcTurk> btcTurkActivityRef;

    @Override
    public void onCreate() {
        super.onCreate();
        MainActivity.initSoundPrefs(this);
        mediaPlayerManager = MediaPlayerManager.getInstance(this);
        handler = new Handler(Looper.getMainLooper());
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            stopService();
            return START_NOT_STICKY;
        }

        String serviceType = intent.getStringExtra("service_type");
        if (serviceType == null) {
            stopService();
            return START_NOT_STICKY;
        }

        switch (serviceType) {
            case "main":
                if (!isMainServiceRunning) {
                    startMainService();
                }
                break;
            case "btcturk":
                if (!isMainServiceRunning) {
                    // Main servis çalışmıyorsa BTCTurk servisi başlatılamaz
                    stopBtcTurkService();
                    return START_NOT_STICKY;
                }
                if (!isBtcTurkServiceRunning) {
                    startBtcTurkService();
                }
                break;
            default:
                stopService();
                return START_NOT_STICKY;
        }

        if (!isRunning && isMainServiceRunning) {
            startPeriodicUpdates();
        }

        return START_STICKY;
    }

    private void startMainService() {
        isMainServiceRunning = true;
        createMainNotification();
        acquireWakeLock();
    }

    private void startBtcTurkService() {
        isBtcTurkServiceRunning = true;
        createBtcTurkNotification();
    }

    private void startPeriodicUpdates() {
        if (isRunning) return;

        isRunning = true;
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (!isMainServiceRunning) {
                        stopService();
                        return;
                    }

                    // Ana aktiviteyi güncelle
                    MainActivity activity = MainActivity.getInstance();
                    if (activity != null && !activity.isFinishing()) {
                        activity.runOnUiThread(() -> {
                            try {
                                activity.Dot();
                            } catch (Exception e) {
                                Log.e("UnifiedService", "Error updating MainActivity: " + e.getMessage());
                            }
                        });
                    }

                    // BTCTurk aktivitesini güncelle
                    if (isBtcTurkServiceRunning) {
                        BtcTurk btcTurk = BtcTurk.getInstance();
                        if (btcTurk != null && !btcTurk.isFinishing() && BtcTurk.calistiMi) {
                            btcTurk.runOnUiThread(() -> {
                                try {
                                    btcTurk.Dot();
                                } catch (Exception e) {
                                    Log.e("UnifiedService", "Error updating BtcTurk: " + e.getMessage());
                                }
                            });
                        }
                    }

                    handler.postDelayed(this, (long)Sesler.saniyed);
                } catch (Exception e) {
                    Log.e("UnifiedService", "Critical error in update loop: " + e.getMessage());
                    handleError(e);
                }
            }
        };
        handler.post(updateRunnable);
    }

    private void createMainNotification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANNEL_ID)
                    .setContentTitle("BigotsPlus")
                    .setContentText("Paribu servisi çalışıyor")
                    .setSmallIcon(R.drawable.paribu_amblem)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setCategory(NotificationCompat.CATEGORY_SERVICE)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setAutoCancel(false)
                    .setOngoing(true)
                    .setUsesChronometer(true)
                    .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
                    .setWhen(System.currentTimeMillis());

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startForeground(NOTIFICATION_ID_PARIBU, builder.build(),
                        ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC);
            } else {
                startForeground(NOTIFICATION_ID_PARIBU, builder.build());
            }
        }
    }

    private void createBtcTurkNotification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App2.CHANNEL_ID)
                    .setContentTitle("BigotsPlus")
                    .setContentText("BTCTurk servisi çalışıyor")
                    .setSmallIcon(R.drawable.btcturk)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setCategory(NotificationCompat.CATEGORY_SERVICE)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setAutoCancel(false)
                    .setOngoing(true)
                    .setUsesChronometer(true)
                    .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
                    .setWhen(System.currentTimeMillis());

            notificationManager.notify(NOTIFICATION_ID_BTCTURK, builder.build());
        }
    }

    private void stopMainService() {
        isMainServiceRunning = false;

        // Cancel both notifications when main service stops
        if (notificationManager != null) {
            notificationManager.cancel(NOTIFICATION_ID_PARIBU);
            notificationManager.cancel(NOTIFICATION_ID_BTCTURK);
        }

        stopBtcTurkService(); // Stop BTCTurk service
        stopService(); // Stop the entire service
    }

    private void stopBtcTurkService() {
        if (isBtcTurkServiceRunning) {
            isBtcTurkServiceRunning = false;
            if (notificationManager != null) {
                notificationManager.cancel(NOTIFICATION_ID_BTCTURK);
            }
        }
    }

    private void stopService() {
        try {
            isRunning = false;
            isMainServiceRunning = false;
            isBtcTurkServiceRunning = false;

            if (handler != null && updateRunnable != null) {
                handler.removeCallbacks(updateRunnable);
                handler = null;
                updateRunnable = null;
            }

            if (mediaPlayerManager != null) {
                mediaPlayerManager.releaseAll();
                mediaPlayerManager = null;
            }

            releaseWakeLock();

            if (notificationManager != null) {
                notificationManager.cancel(NOTIFICATION_ID_PARIBU);
                notificationManager.cancel(NOTIFICATION_ID_BTCTURK);
                notificationManager = null;
            }

            stopForeground(true);
            stopSelf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void acquireWakeLock() {
        if (wakeLock == null) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    "BigotsPlus:WakeLock");
            if (!wakeLock.isHeld()) {
                wakeLock.acquire(30*60*1000L); // 30 dakika sonra otomatik release
            }
        }
    }

    private void releaseWakeLock() {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
            wakeLock = null;
        }
    }

    @Override
    public void onDestroy() {
        try {
            isRunning = false;
            if (handler != null && updateRunnable != null) {
                handler.removeCallbacks(updateRunnable);
            }

            if (mediaPlayerManager != null) {
                mediaPlayerManager.releaseAll();
            }

            // Cancel both notifications in onDestroy as well
            if (notificationManager != null) {
                notificationManager.cancel(NOTIFICATION_ID_PARIBU);
                notificationManager.cancel(NOTIFICATION_ID_BTCTURK);
            }

            releaseWakeLock();
            stopForeground(true);
        } catch (Exception e) {
            Log.e("UnifiedService", "Error in onDestroy: " + e.getMessage());
        } finally {
            super.onDestroy();
        }}
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static boolean isMainServiceRunning() {
        return isMainServiceRunning;
    }

    public static boolean isBtcTurkServiceRunning() {
        return isBtcTurkServiceRunning;
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        // Uygulama arkaplanda kapatıldığında çağrılır
        stopCompletelyAndKill();
    }

    private void stopCompletelyAndKill() {
        try {
            // Tüm servisleri durdur
            stopService();

            // Tüm aktiviteleri kapat
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            // Servis ve uygulamayı tamamen sonlandır
            stopSelf();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  private void handleError(Exception e) {
        try {
            // Hata logla
            Log.e("UnifiedService", "Error occurred: " + e.getMessage());

            // Mevcut durumu temizle
            releaseResources();

            // Servisi yeniden başlat
            restartService();
        } catch (Exception ex) {
            // En kötü durumda servisi durdur
            stopSelf();
        }
    }

    private void releaseResources() {
        if (mediaPlayerManager != null) {
            mediaPlayerManager.releaseAll();
        }
        // Diğer kaynakları temizle
    }

    private void restartService() {
        Intent intent = new Intent(this, UnifiedService.class);
        stopSelf();
        startService(intent);
    }

}

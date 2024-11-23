package com.codinginflow.bigots;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import androidx.core.app.NotificationCompat;

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

    @Override
    public void onCreate() {
        super.onCreate();
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
                if (!isMainServiceRunning) {
                    stopService();
                    return;
                }

                // Ana aktiviteyi güncelle
                if (mainActivity == null) {
                    mainActivity = MainActivity.getInstance();
                }

                if (mainActivity != null) {
                    mainActivity.runOnUiThread(() -> {
                        mainActivity.Dot();

                        // BtcTurk aktivitesini güncelle
                        if (isBtcTurkServiceRunning) {
                            if (btcTurkActivity == null) {
                                btcTurkActivity = BtcTurk.getInstance();
                            }

                            if (btcTurkActivity != null && BtcTurk.calistiMi) {
                                btcTurkActivity.runOnUiThread(() -> {
                                    btcTurkActivity.Dot();
                                });
                            }
                        }
                    });
                }

                handler.postDelayed(this, (long)Sesler.saniyed);
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
        stopBtcTurkService(); // Main servis durduğunda BTCTurk servisi de durmalı
        // stopService() çağrısını kaldırdık
    }

    private void stopBtcTurkService() {
        if (isBtcTurkServiceRunning) {
            isBtcTurkServiceRunning = false;
            notificationManager.cancel(NOTIFICATION_ID_BTCTURK);
        }
    }

    private void stopService() {
        isRunning = false;
        isMainServiceRunning = false;
        isBtcTurkServiceRunning = false;

        if (handler != null && updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }

        if (mediaPlayerManager != null) {
            mediaPlayerManager.releaseAll();
        }

        releaseWakeLock();
        notificationManager.cancelAll();
        stopForeground(true);
        // stopMainService() ve stopBtcTurkService() çağrılarını kaldırdık
        stopSelf();
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
        stopService();
        super.onDestroy();
    }

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
}

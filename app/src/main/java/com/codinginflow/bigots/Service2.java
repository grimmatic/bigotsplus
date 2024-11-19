package com.codinginflow.bigots;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;

import androidx.core.app.NotificationCompat;

public class Service2 extends android.app.Service {
    private MediaPlayerManager mediaPlayerManager;
    private Handler handler;
    private Runnable updateRunnable;
    private BtcTurk btcTurkActivity;
    private boolean isRunning = false;
    private static boolean isServiceRunning = false;
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayerManager = MediaPlayerManager.getInstance(this);
        handler = new Handler(Looper.getMainLooper());
        isServiceRunning = true;
        createInitialNotification();
        isServiceRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isRunning) {
            acquireWakeLock();
            startPeriodicUpdates();
            isRunning = true;
        }
        return START_STICKY;
    }

    private void startPeriodicUpdates() {
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                if (btcTurkActivity == null) {
                    btcTurkActivity = BtcTurk.getInstance();
                }

                if (btcTurkActivity != null) {
                    btcTurkActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btcTurkActivity.Dot();
                        }
                    });
                }

                handler.postDelayed(this, (long)Sesler.saniyed);
            }
        };
        handler.post(updateRunnable);
    }

    private void createInitialNotification() {
        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            builder = new NotificationCompat.Builder(this, App2.CHANNEL_ID)
                    .setContentTitle("BigotsPlus")
                    .setContentText("BTCTurk servisi çalışıyor")
                    .setSmallIcon(R.drawable.btcturk)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setCategory(NotificationCompat.CATEGORY_SERVICE)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setAutoCancel(false)
                    .setOngoing(true)
                    .setUsesChronometer(true)
                    .setWhen(System.currentTimeMillis())
                    .setChronometerCountDown(false)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);

            Notification notification = builder.build();
            notification.flags |= Notification.FLAG_NO_CLEAR |
                    Notification.FLAG_ONGOING_EVENT |
                    Notification.FLAG_FOREGROUND_SERVICE;

            startForeground(2, notification);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public static boolean isRunning() {
        return isServiceRunning;
    }

    @Override
    public void onDestroy() {
        isServiceRunning = false;
        isRunning = false;
        if (handler != null && updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }
        if (mediaPlayerManager != null) {
            mediaPlayerManager.releaseAll();
        }
        stopForeground(true);
        stopSelf();
        super.onDestroy();
    }
    private PowerManager.WakeLock wakeLock;

    private void acquireWakeLock() {
        if (wakeLock == null) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    "BigotsPlus:WakeLock");
            wakeLock.acquire();
        }
    }

    private void releaseWakeLock() {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
            wakeLock = null;
        }
    }
}
package com.codinginflow.bigots;

import android.app.Notification;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.core.app.NotificationCompat;

public class Service extends android.app.Service {
    private Handler handler;
    private Runnable updateRunnable;
    private MainActivity mainActivity;
    private boolean isRunning = false;
    private static boolean isServiceRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
        createInitialNotification();
        isServiceRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isRunning) {
            startPeriodicUpdates();
            isRunning = true;
        }
        return START_STICKY;
    }

    private void startPeriodicUpdates() {
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                if (mainActivity == null) {
                    mainActivity = MainActivity.getInstance();
                }

                if (mainActivity != null) {
                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.Dot();
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
            builder = new NotificationCompat.Builder(this, App.CHANNEL_ID)
                    .setContentTitle("BigotsPlus")
                    .setContentText("Paribu servisi çalışıyor")
                    .setSmallIcon(R.drawable.paribu_amblem)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setCategory(NotificationCompat.CATEGORY_SERVICE)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setAutoCancel(false)
                    .setOngoing(true)
                    .setUsesChronometer(true)
                    .setWhen(System.currentTimeMillis())
                    .setChronometerCountDown(false)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);
        }

        startForeground(1, builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        isServiceRunning = false;
        isRunning = false;
        if (handler != null && updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }

        stopForeground(true);
        stopSelf();
        super.onDestroy();
    }

    public void setMainActivity(MainActivity activity) {
        this.mainActivity = activity;
    }
    public static boolean isRunning() {
        return isServiceRunning;
    }
}
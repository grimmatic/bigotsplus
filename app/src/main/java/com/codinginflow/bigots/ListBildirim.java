package com.codinginflow.bigots;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import java.util.Timer;


public class ListBildirim extends android.app.Service {
    private NotificationCompat.Builder builder;
    String contentText = "";
    Notification notification;
    private NotificationManager notificationManager;
    private Timer timer;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        contentText=MainActivity.binanceDuyuruText;
        builder.setContentText(contentText);
        notificationManager.notify(1, builder.build());
        startForeground(1, builder.build());
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) getSystemService(ns);
        nMgr.cancelAll();
        super.onDestroy();

    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }
}
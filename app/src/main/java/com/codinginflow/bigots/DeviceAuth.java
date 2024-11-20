package com.codinginflow.bigots;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

public class DeviceAuth {

    // İzin verilen cihaz ID'leri
    private static final String[] ALLOWED_DEVICE_IDS = {
            "a9bd872eefe5e52c", //BEN
            "57bb7be0dfef4866", //PC
            "091d1522b86ae1dc"//ABİM
    };

    public static boolean isDeviceAuthorized(Context context) {
        try {

            String androidId = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ANDROID_ID
            );

            // Debug için ID'yi göster
            if (androidId != null) {
                Toast.makeText(context, "Cihaz ID: " + androidId, Toast.LENGTH_LONG).show();
            }

            // ID'yi kontrol et
            if (androidId != null) {
                for (String allowedId : ALLOWED_DEVICE_IDS) {
                    if (androidId.equals(allowedId)) {
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Hata: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return false;
    }
}
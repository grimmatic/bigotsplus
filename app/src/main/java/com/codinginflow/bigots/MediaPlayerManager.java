package com.codinginflow.bigots;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.AudioAttributes;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class MediaPlayerManager {
    private static MediaPlayerManager instance;
    private Context context;
    private Map<Integer, MediaPlayer> playerPool;
    private static final String TAG = "MediaPlayerManager";

    private MediaPlayerManager(Context context) {
        this.context = context.getApplicationContext();
        this.playerPool = new HashMap<>();
    }

    public static synchronized MediaPlayerManager getInstance(Context context) {
        if (instance == null) {
            instance = new MediaPlayerManager(context);
        }
        return instance;
    }
    public void updateVolume(int rawResourceId, float volume) {
        MediaPlayer player = playerPool.get(rawResourceId);
        if (player != null) {
            try {
                player.setVolume(volume, volume);
            } catch (Exception e) {
                Log.e(TAG, "Error updating volume: " + e.getMessage());
            }
        }
    }
    public MediaPlayer getPlayer(int rawResourceId) {
        try {
            MediaPlayer player = playerPool.get(rawResourceId);

            if (player == null || !player.isPlaying()) {
                // Eğer player null ise veya çalmıyorsa yeni bir player oluştur
                if (player != null) {
                    player.release();
                }

                player = MediaPlayer.create(context, rawResourceId);

                if (player != null) {
                    player.setAudioAttributes(
                            new AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_ALARM)
                                    .build()
                    );

                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                            mp.release();
                            playerPool.remove(rawResourceId);
                        }
                    });

                    playerPool.put(rawResourceId, player);
                }
            }
            return player;
        } catch (Exception e) {
            Log.e(TAG, "Error creating/getting MediaPlayer: " + e.getMessage());
            return null;
        }
    }

    public void playSound(int rawResourceId, float volume) {
        try {
            MediaPlayer player = getPlayer(rawResourceId);
            if (player != null) {
                player.setVolume(volume, volume);
                if (!player.isPlaying()) {
                    player.start();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error playing sound: " + e.getMessage());
        }
    }

    public void releaseAll() {
        for (MediaPlayer player : playerPool.values()) {
            try {
                if (player != null) {
                    player.release();
                }
            } catch (Exception e) {
                Log.e(TAG, "Error releasing player: " + e.getMessage());
            }
        }
        playerPool.clear();
    }
}
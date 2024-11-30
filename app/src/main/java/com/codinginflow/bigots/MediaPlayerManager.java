package com.codinginflow.bigots;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.AudioAttributes;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class MediaPlayerManager {
    private static MediaPlayerManager instance;
    private Map<Integer, Float> savedVolumes = new HashMap<>();
    private Context context;
    private Map<Integer, MediaPlayer> playerPool;
    private Map<Integer, Float> volumeLevels; // Ses seviyelerini saklamak için
    private static final int MAX_POOL_SIZE = 10;
    private static final String TAG = "MediaPlayerManager";

    private MediaPlayerManager(Context context) {
        this.context = context.getApplicationContext();
        this.playerPool = new HashMap<>();
        this.volumeLevels = new HashMap<>();
    }

    public static synchronized MediaPlayerManager getInstance(Context context) {
        if (instance == null) {
            instance = new MediaPlayerManager(context);
        }
        return instance;
    }
    public void updateVolume(int rawResourceId, float volume) {
        savedVolumes.put(rawResourceId, volume);
        MediaPlayer player = playerPool.get(rawResourceId);
        if (player != null) {
            try {
                player.setVolume(volume, volume);
            } catch (Exception e) {
                Log.e(TAG, "Error updating volume: " + e.getMessage());
            }
        }
    }
    @SuppressLint("NewApi")
    public MediaPlayer getPlayer(int rawResourceId) {
        if (playerPool.size() >= MAX_POOL_SIZE) {
            releaseOldestPlayer();
        }
        try {
            MediaPlayer player = playerPool.get(rawResourceId);

            if (player == null || !player.isPlaying()) {
                if (player != null) {
                    player.release();
                }

                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build();

                player = new MediaPlayer();
                player.setAudioAttributes(audioAttributes);
                player.setDataSource(context.getResources().openRawResourceFd(rawResourceId));
                player.prepare();

                // Kaydedilmiş ses seviyesini uygula
                float savedVolume = savedVolumes.getOrDefault(rawResourceId, 1.0f);
                player.setVolume(savedVolume, savedVolume);

                player.setOnCompletionListener(mp -> {
                    mp.reset();
                    mp.release();
                    playerPool.remove(rawResourceId);
                });

                playerPool.put(rawResourceId, player);
            }
            return player;
        } catch (Exception e) {
            Log.e(TAG, "Error creating/getting MediaPlayer: " + e.getMessage());
            return null;
        }
    }
    private void releaseOldestPlayer() {
        if (!playerPool.isEmpty()) {
            Map.Entry<Integer, MediaPlayer> oldest = playerPool.entrySet().iterator().next();
            MediaPlayer player = oldest.getValue();
            if (player != null) {
                player.release();
            }
            playerPool.remove(oldest.getKey());
        }
    }
    public void playSound(int rawResourceId, float volume) {
        try {
            volumeLevels.put(rawResourceId, volume); // Ses seviyesini kaydet
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

    public synchronized void releaseAll() {
        try {
            for (Map.Entry<Integer, MediaPlayer> entry : playerPool.entrySet()) {
                MediaPlayer player = entry.getValue();
                if (player != null) {
                    if (player.isPlaying()) {
                        player.stop();
                    }
                    player.release();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in releaseAll: " + e.getMessage());
        } finally {
            playerPool.clear();
            // volumeLevels'ı temizleme - ses seviyelerini saklamak istiyoruz
        }
    }
    @Override
    protected void finalize() throws Throwable {
        try {
            releaseAll();
        } finally {
            super.finalize();
        }
    }
}
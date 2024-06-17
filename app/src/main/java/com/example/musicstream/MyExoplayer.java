package np.com.bimalkafle.musicstream;

import android.content.Context;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import com.example.musicstream.models.SongModel;

//import np.com.bimalkafle.musicstream.models.SongModel;

public enum MyExoplayer {
    INSTANCE;

    private ExoPlayer exoPlayer = null;
    private SongModel currentSong = null;

    public SongModel getCurrentSong() {
        return currentSong;
    }

    public ExoPlayer getInstance() {
        return exoPlayer;
    }

    public void startPlaying(Context context, SongModel song) {
        if (exoPlayer == null) {
            exoPlayer = new ExoPlayer.Builder(context).build();
        }

        if (currentSong != song) {
            // It's a new song so start playing
            currentSong = song;

            if (currentSong.getUrl() != null) {
                MediaItem mediaItem = MediaItem.fromUri(currentSong.getUrl());
                exoPlayer.setMediaItem(mediaItem);
                exoPlayer.prepare();
                exoPlayer.play();
            }
        }
    }
}

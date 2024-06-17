package com.example.musicstream;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.musicstream.databinding.ActivityPlayerBinding;
import com.example.musicstream.models.SongModel;

//import np.com.bimalkafle.musicstream.databinding.ActivityPlayerBinding;
//import np.com.bimalkafle.musicstream.models.SongModel;

public class PlayerActivity extends AppCompatActivity {

    private ActivityPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SongModel currentSong = np.com.bimalkafle.musicstream.MyExoplayer.INSTANCE.getCurrentSong();
        if (currentSong != null) {
            binding.songsTitleTextView.setText(currentSong.getTitle());
            binding.songsSubtitleTextView.setText(currentSong.getSubtitle());
            Glide.with(this)
                    .load(currentSong.getCoverUrl())
                    .into(binding.songsCoverImageView);
        }
    }
}

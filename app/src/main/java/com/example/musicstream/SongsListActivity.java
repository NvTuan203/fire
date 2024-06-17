package com.example.musicstream;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

//import com.example.musicstream.adapter.Songslist;
import com.example.musicstream.databinding.ActivitySongsListBinding;
import com.example.musicstream.models.CategoryModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import np.com.bimalkafle.musicstream.adapter.SongsListAdapter;

public class SongsListActivity extends AppCompatActivity {

    public static CategoryModel category;

    private ActivitySongsListBinding binding;
    private np.com.bimalkafle.musicstream.adapter.SongsListAdapter songsListAdapter;
    static {
        category = new CategoryModel("Default Category", "default_cover_url", new ArrayList<>());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using ViewBinding
        binding = ActivitySongsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the text of nameTextView
        binding.nameTextView.setText(category.getName());


        Glide.with(binding.coverImageView.getContext())
                .load(category.getCoverUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(32)))
                .into(binding.coverImageView);

        setupSongsListRecyclerView();
    }
    public static void setCategory(CategoryModel newCategory) {
        category = newCategory;
    }
    private void setupSongsListRecyclerView() {
        songsListAdapter = new SongsListAdapter(category.getSongs());
        binding.songsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.songsListRecyclerView.setAdapter(songsListAdapter);
    }
}
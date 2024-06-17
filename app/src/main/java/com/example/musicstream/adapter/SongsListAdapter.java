package np.com.bimalkafle.musicstream.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
//import com.example.musicstream.MyExoplayer;
import com.example.musicstream.PlayerActivity;
import com.example.musicstream.databinding.SongListItemRecyclerRowBinding;
import com.example.musicstream.models.SongModel;
import com.google.firebase.firestore.FirebaseFirestore;
//import np.com.bimalkafle.musicstream.databinding.SongListItemRecyclerRowBinding;
//import np.com.bimalkafle.musicstream.models.SongModel;
import android.content.Context;
import android.content.Intent;
import java.util.List;

public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.MyViewHolder> {

    private final List<String> songIdList;

    public SongsListAdapter(List<String> songIdList) {
        this.songIdList = songIdList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final SongListItemRecyclerRowBinding binding;

        public MyViewHolder(SongListItemRecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Bind data with view
        public void bindData(String songId) {
            FirebaseFirestore.getInstance().collection("songs")
                    .document(songId).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        SongModel song = documentSnapshot.toObject(SongModel.class);
                        if (song != null) {
                            binding.songsTitleTextView.setText(song.getTitle());
                            binding.songsSubtitleTextView.setText(song.getSubtitle());
                            Glide.with(binding.songsCoverImageView.getContext())
                                    .load(song.getCoverUrl())
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(32)))
                                    .into(binding.songsCoverImageView);
                            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    np.com.bimalkafle.musicstream.MyExoplayer.INSTANCE.startPlaying(binding.getRoot().getContext(), song);
                                    v.getContext().startActivity(new Intent(v.getContext(), PlayerActivity.class));

                                }
                            });

                        }
                    });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SongListItemRecyclerRowBinding binding = SongListItemRecyclerRowBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return songIdList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(songIdList.get(position));
    }
}

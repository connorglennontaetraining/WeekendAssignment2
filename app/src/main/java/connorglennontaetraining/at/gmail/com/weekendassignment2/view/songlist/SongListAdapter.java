package connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.util.List;

import connorglennontaetraining.at.gmail.com.weekendassignment2.MyApp;
import connorglennontaetraining.at.gmail.com.weekendassignment2.R;
import connorglennontaetraining.at.gmail.com.weekendassignment2.Utils.IMedia;
import connorglennontaetraining.at.gmail.com.weekendassignment2.Utils.Media;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Song;

public class SongListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Song> mSongList;
    private int mLayoutId;

    SongListAdapter(Context mContext, List<Song> mSongList, int mLayoutId) {
        this.mContext = mContext;
        this.mSongList = mSongList;
        this.mLayoutId = mLayoutId;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Song currentSong = mSongList.get(position);
        SongViewHolder songViewHolder = (SongViewHolder) holder;
        songViewHolder.mTrackName.setText(currentSong.getTrackName());
        songViewHolder.mArtistName.setText(currentSong.getArtistName());
        //TODO handle warning to do with poor setting of this text field.
        Log.i("Id", "" + currentSong.getTrackId());
        if(currentSong.getTrackPrice() != null) songViewHolder.mTrackPrice.setText(Double.toString(currentSong.getTrackPrice()) + " USD");

        Uri uri = Uri.parse(currentSong.getArtworkUrl100());
        songViewHolder.mArtwork.setImageURI(uri);

        holder.itemView.setOnClickListener(view -> {
            Media.getMedia().playSound(currentSong.getPreviewUrl());
        });
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mTrackName;
        private TextView mArtistName;
        private TextView mTrackPrice;

        private SimpleDraweeView mArtwork;

        SongViewHolder(View itemView) {
            super(itemView);

            mTrackName = itemView.findViewById(R.id.trackName);
            mArtistName = itemView.findViewById(R.id.artistName);
            mTrackPrice = itemView.findViewById(R.id.trackPrice);

            mArtwork = itemView.findViewById(R.id.artwork);
        }
    }
}

package connorglennontaetraining.at.gmail.com.weekendassignment2.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import connorglennontaetraining.at.gmail.com.weekendassignment2.R;
import connorglennontaetraining.at.gmail.com.weekendassignment2.model.Song;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public class SongListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Song> mSongList;
    private int mLayoutId;

    public SongListAdapter(Context mContext, List<Song> mSongList, int mLayoutId) {
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
        //TODO bind Song fields to view holder views.
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

        private ImageView mArtwork;

        SongViewHolder(View itemView) {
            super(itemView);

            mTrackName = itemView.findViewById(R.id.trackName);
            mArtistName = itemView.findViewById(R.id.artistName);
            mTrackPrice = itemView.findViewById(R.id.trackPrice);

            mArtwork = itemView.findViewById(R.id.artwork);
        }
    }
}

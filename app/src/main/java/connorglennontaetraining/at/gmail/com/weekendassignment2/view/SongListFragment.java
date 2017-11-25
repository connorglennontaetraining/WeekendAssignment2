package connorglennontaetraining.at.gmail.com.weekendassignment2.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import connorglennontaetraining.at.gmail.com.weekendassignment2.R;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.IRequest;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.ServerConnection;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Song;
import icepick.State;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class SongListFragment extends Fragment implements ISongListFragment{

    RecyclerView mRvSongList;
    int mLayoutId;

    public SongListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRvSongList(view);
        mLayoutId = R.layout.card_song;
    }

    /**
     * initialises the RecyclerView that displays the list of songs.
     * @param view the view representing the fragment.
     */
    private void initRvSongList(View view)
    {
        mRvSongList = view.findViewById(R.id.rvSongList);
        mRvSongList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onFetchDataSuccess(List<Song> songList) {
        mRvSongList.setAdapter(new SongListAdapter(getActivity(), songList, mLayoutId));
    }

    @Override
    public void onFetchDataError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}

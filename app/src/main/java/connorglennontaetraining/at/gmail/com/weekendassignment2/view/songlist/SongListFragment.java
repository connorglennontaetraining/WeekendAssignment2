package connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import connorglennontaetraining.at.gmail.com.weekendassignment2.R;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Song;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.CommonUtils;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.KeyboardUtils;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.NetworkUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class SongListFragment extends Fragment implements ISongListFragment{

    RecyclerView mRvSongList;
    int mLayoutId;
    List<Song> mFailedToFetchDataList;
    SwipeRefreshLayout mSwipeRefreshLayout;

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
        mSwipeRefreshLayout = getActivity().findViewById(R.id.swipeRefresh);
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

    private void initFailedToLoadDataList(){
        mFailedToFetchDataList = new ArrayList<>();
        Song song = new Song();
        song.setTrackName("Error loading data");
        song.setArtistName("Unfortunately we couldn't find the data you are looking for.");
        song.setArtworkUrl100("");
        mFailedToFetchDataList.add(song);
    }

    @Override
    public void onFetchDataSuccess(List<Song> songList) {
        mRvSongList.setAdapter(new SongListAdapter(getActivity(), songList, mLayoutId));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchDataError(String message) {
        if(mFailedToFetchDataList == null){initFailedToLoadDataList();}
        mRvSongList.setAdapter(new SongListAdapter(getActivity(), mFailedToFetchDataList, mLayoutId));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        CommonUtils.showLoadingDialog(getActivity());
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getContext());
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hideSoftInput(getActivity());
    }
}

package connorglennontaetraining.at.gmail.com.weekendassignment2.view;

import java.util.List;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Song;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.ui.base.MvpView;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public interface ISongListFragment extends MvpView{
    void onFetchDataSuccess(List<Song> songList);
    void onFetchDataError(String message);
}

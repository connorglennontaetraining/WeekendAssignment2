package connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist;

import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.ui.base.MvpPresenter;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public interface ISongListPresenter<V extends ISongListFragment> extends MvpPresenter<V> {
    void onCallGetClassicSongList();
    void onCallGetRockSongList();
    void onCallGetPopSongList();

}

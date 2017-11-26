package connorglennontaetraining.at.gmail.com.weekendassignment2;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.DataManager;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.rx2.AppSchedulerProvider;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.ISongListPresenter;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.SongListFragment;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.SongListPresenter;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.navbar.INavbarEvent;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.navbar.NavbarFragment;
import icepick.State;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements INavbarEvent{

    FragmentManager mFragmentManager;
    ISongListPresenter<SongListFragment> presenter;
    int mCurrentMenuId;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SongListFragment songListFragment = new SongListFragment();

        if(savedInstanceState == null){
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, songListFragment)
                    .commit();

            mFragmentManager.beginTransaction()
                    .add(R.id.fragmentNavbar, new NavbarFragment())
                    .commit();
        } else {
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, songListFragment)
                    .commit();

            mFragmentManager.beginTransaction()
                    .replace(R.id.fragmentNavbar, new NavbarFragment())
                    .commit();
        }

        presenter = new SongListPresenter<>(new DataManager(),
                new AppSchedulerProvider(),
                new CompositeDisposable());

        presenter.onAttach(songListFragment);

        if(savedInstanceState == null)
        {
            presenter.onCallGetClassicSongList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void onItemClick(int itemId) {
        mCurrentMenuId = itemId;
        switch (itemId){
            case R.id.navigation_classic:
                presenter.onCallGetClassicSongList();
                break;
            case  R.id.navigation_rock:
                presenter.onCallGetRockSongList();
                break;
            case R.id.navigation_pop:
                presenter.onCallGetPopSongList();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> onItemClick(mCurrentMenuId));
    }
}

package connorglennontaetraining.at.gmail.com.weekendassignment2;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.DataManager;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.rx2.AppSchedulerProvider;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.ISongListPresenter;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.SongListFragment;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.SongListPresenter;
import icepick.State;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity{

    FragmentManager mFragmentManager;
    @State int mMenuItemId;
    MenuItem mSelectedItem;
    BottomNavigationView mNavigationView;
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    ISongListPresenter<SongListFragment> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*
        Initialise the bottom navigation
         */
        initBottomNavigation();

        SongListFragment songListFragment = new SongListFragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, songListFragment)
                .addToBackStack("")
                .commit();


        presenter = new SongListPresenter<>(new DataManager(),
                new AppSchedulerProvider(),
                new CompositeDisposable());
        presenter.onAttach(songListFragment);

        if(savedInstanceState == null)
        {
            presenter.onCallGetClassicSongList();
        }

    }

    private void initBottomNavigation()
    {
        mOnNavigationItemSelectedListener = item -> {
            mSelectedItem = item;
            mMenuItemId = item.getItemId();
            switch (item.getItemId()) {
                case R.id.navigation_classic:
                    presenter.onCallGetClassicSongList();
                    return true;
                case R.id.navigation_rock:
                    presenter.onCallGetRockSongList();
                    return true;
                case R.id.navigation_pop:
                    presenter.onCallGetPopSongList();
                    return true;
            }
            return false;
        };
        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSelectedItem = mNavigationView.getMenu().getItem(mMenuItemId);
        mOnNavigationItemSelectedListener.onNavigationItemSelected(mSelectedItem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}

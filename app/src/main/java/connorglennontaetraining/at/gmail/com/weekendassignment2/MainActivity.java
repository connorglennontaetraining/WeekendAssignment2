package connorglennontaetraining.at.gmail.com.weekendassignment2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.DataManager;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.rx2.AppSchedulerProvider;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.ISongListFragment;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.ISongListPresenter;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.SongListFragment;
import connorglennontaetraining.at.gmail.com.weekendassignment2.view.SongListPresenter;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity{

    FragmentManager mFragmentManager;
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
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}

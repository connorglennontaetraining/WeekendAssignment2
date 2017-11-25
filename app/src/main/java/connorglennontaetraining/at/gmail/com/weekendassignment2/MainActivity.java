package connorglennontaetraining.at.gmail.com.weekendassignment2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import connorglennontaetraining.at.gmail.com.weekendassignment2.view.SongListFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Initialise the bottom navigation
         */
        initBottomNavigation();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, new SongListFragment())
                .addToBackStack("")
                .commit();

    }

    private void initBottomNavigation()
    {
        mOnNavigationItemSelectedListener = item -> {
            switch (item.getItemId()) {
                case R.id.navigation_classic:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, new SongListFragment())
                            .addToBackStack("")
                            .commit();
                    return true;
                case R.id.navigation_rock:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, new SongListFragment())
                            .addToBackStack("")
                            .commit();
                    return true;
                case R.id.navigation_pop:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, new SongListFragment())
                            .addToBackStack("")
                            .commit();
                    return true;
            }
            return false;
        };
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}

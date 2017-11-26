package connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist.navbar;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import connorglennontaetraining.at.gmail.com.weekendassignment2.MainActivity;
import connorglennontaetraining.at.gmail.com.weekendassignment2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavbarFragment extends Fragment {

    int mMenuItemId;
    MenuItem mSelectedItem;
    BottomNavigationView mNavigationView;
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;


    public NavbarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        Initialise the bottom navigation
         */
        initBottomNavigation();

        if(savedInstanceState != null) {
            Log.i("NavbarFragment", "Loaded mMenuItemId: " + savedInstanceState.getInt("mMenuItemId"));
            mMenuItemId = savedInstanceState.getInt("mMenuItemId");
            mNavigationView.setSelectedItemId(mMenuItemId);
        }
        mMenuItemId = mNavigationView.getSelectedItemId();
        mNavigationView.setSelectedItemId(mMenuItemId);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mMenuItemId", mMenuItemId);
        Log.i("NavbarFragment", "Saving mMenuItemId: " + mMenuItemId);
    }

    private void initBottomNavigation()
    {
        mOnNavigationItemSelectedListener = item -> {
            Log.i("NavbarFragment", "Item: " + item.getItemId());
            INavbarEvent navbarEvent = (INavbarEvent) getActivity();
            mSelectedItem = item;
            mMenuItemId = item.getItemId();
            navbarEvent.onItemClick(mMenuItemId);
            return true;
        };
        mNavigationView = getActivity().findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}

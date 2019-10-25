package com.example.navigation_ui_java;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    NavHostFragment navHostFragment;
    BottomNavigationView bottomNavigationView = null;
    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        //--------------------------------------------------------------------------------------------------------------
        // We can get NavController directly like this.
        // navController = Navigation.findNavController(this,R.id.my_nav_host_fragment);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        //--------------------------------------------------------------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //NavigationUI.setupWithNavController(toolbar, NavHostFragment.findNavController(navHostFragment));

        //--------------------------------------------------------------------------------------------------------------
//        ActionBar actionBar = getSupportActionBar();
//        assert actionBar != null;
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
//        //actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_background);


        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        // Add Bottom view into Navigation UI
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav_view);
        if( bottomNavigationView != null) {
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }
        //--------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------
        // Set up Action Bar
        Set<Integer> topLevelDests = new HashSet<>();
        topLevelDests.add(R.id.home_dest);
        topLevelDests.add(R.id.deeplink_dest);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(topLevelDests)
                .setDrawerLayout(mDrawerLayout)
                .build();

        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(toolbar,navController,mAppBarConfiguration);

        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        // Option Menu -
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            NavigationUI.setupWithNavController(navigationView, navController);
        }
        //--------------------------------------------------------------------------------------------------------------

    }

    /*
        Add onCreateOptionsMenu (...) method for attaching Shopping Cart Image on the right of the Action Bar
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Boolean retValue = super.onCreateOptionsMenu(menu);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);

        if (navigationView == null) {
            getMenuInflater().inflate(R.menu.overflow_menu, menu);
            return true;
        }
        return retValue;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        NavOptions.Builder builder = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right);

        NavOptions options = builder.build();

        if (item.getItemId() == R.id.home_dest) {
            navController.navigate(R.id.home_dest, null, options);
            return true;
        } else if (item.getItemId() == R.id.deeplink_dest) {
            navController.navigate(R.id.deeplink_dest, null, options);
            return true;
        } else if (item.getItemId() == R.id.settings_dest) {
            navController.navigate(R.id.settings_dest, null, options);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}

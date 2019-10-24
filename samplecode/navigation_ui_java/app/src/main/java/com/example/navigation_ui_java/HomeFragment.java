package com.example.navigation_ui_java;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;



public class HomeFragment extends Fragment {

    private NavController navController;
    private NavOptions navOptions;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
//        final Toolbar toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(nv -> navController.navigateUp());

        NavOptions.Builder builder = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right);

        NavOptions options = builder.build();

        view.findViewById(R.id.navigate_destination_button).setOnClickListener(v -> {
            navController.navigate(R.id.flow_step_one_dest, null, options);
        });



        /* -----------------------------------------------------------------------
            Using Action Button with Action id
        */

        view.findViewById(R.id.navigate_action_button).setOnClickListener(v -> {
            NavDirections directions = HomeFragmentDirections.nextAction();
            navController.navigate(directions);
        });

//        /* Call Fragment Directly through NavController */
//        view.findViewById(R.id.navigate_action_button).setOnClickListener(v -> {
//            navController.navigate(R.id.flow_step_one_dest, null);
//        });

    }

    /* Add onCreateOptionsMenu (...) method for attaching Shopping Cart Image on the right of the Action Bar */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

}

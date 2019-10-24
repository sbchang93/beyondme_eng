package com.example.navigation_ui_java;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;


public class FlowStepFragment extends Fragment {
    private NavController navController;
    private NavOptions navOptions;
    private int flowStepNumber;

    public FlowStepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            flowStepNumber = getArguments().getInt("flowStepNumber");
        }

        if( flowStepNumber == 2 ){
            return inflater.inflate(R.layout.flow_step_two_fragment, container, false);
        }
        return inflater.inflate(R.layout.flow_step_one_fragment, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        NavOptions.Builder builder = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right);

        NavOptions options = builder.build();

        // Click Next Button
        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            NavDirections directions = FlowStepFragmentDirections.nextAction();
            navController.navigate(directions);
        });

    }


}

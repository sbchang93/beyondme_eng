package com.example.navigation_ui_java;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class DeepLinkFragment extends Fragment {
    private NavController navController;
    private NavOptions navOptions;

    public DeepLinkFragment() {
        // Required empty public constructor
    }

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.deeplink_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        TextView textView = view.findViewById(R.id.text);
        if (getArguments() != null) {
            textView.setText(getArguments().getString("myarg", ""));
        }

        Button notificationButton = (Button) view.findViewById(R.id.send_notification_button);
        notificationButton.setOnClickListener(v -> {
            EditText editArgs = (EditText) view.findViewById(R.id.args_edit_text);
            Bundle args = new Bundle();
            args.putString("myarg", editArgs.getText().toString());

            PendingIntent deeplink = navController.createDeepLink()
                    .setDestination(R.id.deeplink_dest)
                    .setArguments(args)
                    .createPendingIntent();

            assert getContext() != null;

            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(new NotificationChannel(
                        "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH));
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "deeplink")
                    .setContentTitle("Navigation")
                    .setContentText("Deep link to Android")
                    .setSmallIcon(R.drawable.ic_android)
                    .setContentIntent(deeplink)
                    .setAutoCancel(true);
            notificationManager.notify(0, builder.build());

        });

//        // Click Next Button
//        view.findViewById(R.id.send_notification_button).setOnClickListener(v -> {
//            NavDirections directions = FlowStepFragmentDirections.nextAction();
//            navController.navigate(directions);
//        });

        // adb commnad
        // adb shell am start -a android.intent.action.VIEW -d "http://www.example.com/urlTest"
    }

}

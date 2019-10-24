package com.example.navigation_ui_java;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.navigation.NavDeepLinkBuilder;


public class DeepLinkAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews( context.getPackageName(), R.layout.deep_link_appwidget);
        //Resources resources = context.getResources();

        Bundle args = new Bundle();
        args.putString("myarg", "From Widget");

        PendingIntent pendingIntent = new NavDeepLinkBuilder(context)
                .setGraph(R.navigation.mobile_navigation)
                .setDestination(R.id.deeplink_dest)
                .setArguments(args)
                .createPendingIntent();

        remoteViews.setOnClickPendingIntent(R.id.deep_link_button, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
}


package com.example.fragmentexample.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fragmentexample.R;
import com.example.fragmentexample.Utils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


// reference link
// : https://github.com/marcoRS/rxjava-essentials/blob/master/app/src/main/java/com/packtpub/apps/rxjava_essentials/example1/FirstExampleFragment.java

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstExampleFragment extends Fragment {

    //@InjectView(R.id.fragment_first_example_list)
    RecyclerView mRecyclerView;

    //@InjectView(R.id.fragment_first_example_swipe_container)
//    SwipeRefreshLayout mSwipeRefreshLayout;

    private ApplicationAdapter mAdapter;
    private File mFilesDir;

    public FirstExampleFragment() {
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.fragment_first_example, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ButterKnife.inject(this, view);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_first_example_recycler_view_list) ;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mAdapter = new ApplicationAdapter(new ArrayList<>(), R.layout.applications_list_item);
        mRecyclerView.setAdapter(mAdapter);

//        mSwipeRefreshLayout.findViewById(R.id.fragment_first_example_swipe_container) ;
//        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.myPrimaryColor));
//        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
//                        getResources().getDisplayMetrics()));
//
//        // Progress
//        mSwipeRefreshLayout.setEnabled(false);
//        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.setVisibility(View.GONE);

        getFileDir().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(file -> {
                    mFilesDir = file;
                    //refreshTheList();
                });
    }

    private Observable<File> getFileDir() {
        return Observable.create(subscriber -> {
            subscriber.onNext(getContext().getFilesDir());
            subscriber.onComplete();
        });
    }

    private void refreshTheList() {
        getApps().toSortedList().subscribe(new Observer<List<AppInfo>>() {
            @Override public void onComplete() {
                Toast.makeText(getActivity(), "Here is the list!", Toast.LENGTH_LONG).show();
            }

            @Override public void onError(Throwable e) {
                Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                //mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override public void onNext(List<AppInfo> appInfos) {
                mRecyclerView.setVisibility(View.VISIBLE);
                mAdapter.addApplications(appInfos);
                //mSwipeRefreshLayout.setRefreshing(false);
                storeList(appInfos);
            }
        });
    }

    private void storeList(List<AppInfo> appInfos) {
        ApplicationsList.getInstance().setList(appInfos);

        Schedulers.io().createWorker().schedule(() -> {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            Type appInfoType = new TypeToken<List<AppInfo>>() {
            }.getType();
            sharedPref.edit().putString("APPS", new Gson().toJson(appInfos, appInfoType)).apply();
        });
    }

    private Observable<AppInfo> getApps() {
        return Observable.create(subscriber -> {
            List<AppInfoRich> apps = new ArrayList<>();

            final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> infos =
                    getActivity().getPackageManager().queryIntentActivities(mainIntent, 0);
            for (ResolveInfo info : infos) {
                apps.add(new AppInfoRich(getActivity(), info));
            }

            for (AppInfoRich appInfo : apps) {
                Bitmap icon = Utils.drawableToBitmap(appInfo.getIcon());
                String name = appInfo.getName();
                String iconPath = mFilesDir + "/" + name;
                Utils.storeBitmap(getContext(), icon, name);

//                if (subscriber.isUnsubscribed()) {
//                    return;
//                }
                subscriber.onNext(new AppInfo(name, iconPath, appInfo.getLastUpdateTime()));
            }
//            if (!subscriber.isUnsubscribed()) {
                subscriber.onComplete();
//            }
        });
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
    }
}


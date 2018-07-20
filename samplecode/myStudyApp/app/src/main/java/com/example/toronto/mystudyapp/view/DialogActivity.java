package com.example.toronto.mystudyapp.view;

// 참조 링크
// http://webnautes.tistory.com/1094

import android.annotation.SuppressLint;
import android.app.Activity;
//import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.common.WeakReferenceHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DialogActivity extends Activity {

    protected ProgressDialog mDialog;

    /* Message Type */
    public static final int SHOW_COMMON_DIALOG = 100;
    public static final int UPDATE_UI_ON_SUCCESS = 101;
    public static final int UPDATE_UI_ON_FAIL = 102;

    public static final String DIALOG_MSG = "DIALOG_MSG";
    public static final String DIALOG_TITLE = "DIALOG_TITLE";
    public static final String DIALOG_BUTTON = "DIALOG_BUTTON";
    public static final String DIALOG_BACK_TO_MAIN = "DIALOG_BACK_TO_MAIN";
    public static final String UI_UPDATE_TASK = "UI_UPDATE_TASK";
    public static final String UI_UPDATE_STATUS = "UI_UPDATE_STATUS";
    public static final String UI_UPDATE_DATA = "UI_UPDATE_DATA";

    protected Handler mBaseUIHanlder;

    @BindView(R.id.button1)    Button button1;
    private Disposable mDisposable;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        mUnbinder = ButterKnife.bind(this);

        mBaseUIHanlder = new UIHandler(this);

        mDisposable = Observable.create(e ->  button1.setOnClickListener(e::onNext))
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    showDialog();
                });


        // 참조 링크
        // http://webnautes.tistory.com/1094
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
    }

    void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog Title (제목)");
        builder.setMessage("Content (표시하고 싶은 내용들!!!)");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    private void showDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setItems(new String[]{"Gumi(구미역)", "Suwon(수원역)", "Seoul(서울역)"}, (dialog1, position) -> {
            switch (position) {
                case 0:
                    showProgressDialog();
                    Observable.just("")
                            .delay(3, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(s -> {
                                dismissProgressDialog();
                                Toast.makeText(DialogActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                            });
                    break;
                case 1:
                    showGenericDialog(new String("No Ticket - 수원역 차표없음."));
                    break;
                case 2:
                    showGenericDialog(new String("No Ticket - 서울역 차표없음."));
                    break;
            }
        }).create().show();

    }

    protected void onResume() {
        super.onResume();

    }

    public void showProgressDialog() {
        if ((mDialog != null && mDialog.isShowing()) || isFinishing()) {
            return;
        }
        mDialog = new ProgressDialog(this, R.style.AppAlertDialogStyle);
        mDialog.setMessage("Loading ...");
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    public void dismissProgressDialog() {
        if (mDialog == null || !mDialog.isShowing() || isFinishing()) {
            return;
        }
        try {
            mDialog.dismiss();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void showGenericDialog(String dialogMsg) {
        //showDialogOnUi(null, dialogMsg, getResources().getString(R.string.common_ok), false);
        showDialogOnUi(null, dialogMsg, new String("확인(OK)"), false);
    }

    private void showDialogOnUi(String dialogTitle, String dialogMsg, String dialogButton, boolean backToMain) {
        if (TextUtils.isEmpty(dialogMsg)) {
            Log.d("test", "showDialogOnUi - empty msg");
            return;
        }

        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_MSG, dialogMsg);
        if (!TextUtils.isEmpty(dialogTitle)) {
            bundle.putString(DIALOG_TITLE, dialogTitle);
        }
        bundle.putString(DIALOG_BUTTON, dialogButton);
        bundle.putBoolean(DIALOG_BACK_TO_MAIN, backToMain);
        message.setData(bundle);
        message.what = SHOW_COMMON_DIALOG;
        mBaseUIHanlder.sendMessage(message);

    }


    @SuppressLint("HandlerLeak")
    class UIHandler extends WeakReferenceHandler<DialogActivity> {
        UIHandler(DialogActivity object) {
            super(object);
        }

        @Override
        public void handleMessage(final DialogActivity activity, Message msg) {
            Bundle bundle;
            switch (msg.what) {
                case SHOW_COMMON_DIALOG:
                    bundle = msg.getData();
                    if (!activity.isFinishing() && !activity.isDestroyed()) {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
                        if (!TextUtils.isEmpty(bundle.getString(DIALOG_TITLE))) {
                            builder.setTitle(bundle.getString(DIALOG_TITLE));
                        }
                        builder.setMessage(bundle.getString(DIALOG_MSG));
                        builder.setCancelable(true);
                        final boolean backToMain = bundle.getBoolean(DIALOG_BACK_TO_MAIN);
                        if (backToMain) {
                            builder.setNeutralButton(bundle.getString(DIALOG_BUTTON), (dialog, which) -> activity.finish());
                            builder.setOnCancelListener(dialogInterface -> activity.finish());
                        } else {
                            builder.setNeutralButton(bundle.getString(DIALOG_BUTTON), null);
                            builder.setOnCancelListener(null);
                            //notifyUIOnFailCallback(REDEEM_AND_GET_CLIP, UI_UPDATE_DEAL_DATA);
                        }
                        builder.show();
                    }
                    break;

                case UPDATE_UI_ON_SUCCESS:
                    bundle = msg.getData();
                    int successTaskIndex = bundle.getInt(UI_UPDATE_TASK);
                    String successStatus = bundle.getString(UI_UPDATE_STATUS);
                    String successData = bundle.getString(UI_UPDATE_DATA);
                    updateUIOnSuccessCallback(successTaskIndex, successStatus, successData);
                    break;

                case UPDATE_UI_ON_FAIL:
                    bundle = msg.getData();
                    int failTaskIndex = bundle.getInt(UI_UPDATE_TASK);
                    String failStatus = bundle.getString(UI_UPDATE_STATUS);
                    updateUIOnFailCallback(failTaskIndex, failStatus);
                    break;
            }
        }
    }

    protected void updateUIOnSuccessCallback(int taskIndex, String status, String data) {
    }

    protected void updateUIOnFailCallback(int taskIndex, String status) {
    }

}


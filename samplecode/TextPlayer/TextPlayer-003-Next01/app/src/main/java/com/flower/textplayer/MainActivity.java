package com.flower.textplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

//Reference Homepage URL : https://zion830.tistory.com/51
//Reference Homepage URL : https://gist.github.com/zion830/d045325ec84b7cb1a545f31f65ff966c
//Reference Homepage URL : https://channelg.co.kr/entry/%EC%A7%A7%EC%9D%80-%EC%98%81%EC%96%B4-%EB%AA%85%EC%96%B8-%EB%AA%A8%EC%9D%8C%EC%A7%91-75%EA%B0%9C-%EB%A0%88%ED%84%B0%EB%A7%81-%EB%AC%B8%EA%B5%AC-%EC%B6%94%EC%B2%9C

//// Sample Code
//private static final int DEFAULT_VALUE_OFFSET = 0;
//public static final int VERSION_CODE = 1;
//private static int nextId = 0;
//public static final boolean DEBUG = Boolean.parseBoolean("true");
//public static final String APPLICATION_ID = "com.flower.textplayer";
//public static final String BUILD_TYPE = "debug";
//public static final String VERSION_NAME = "1.0";
//private final static String TAG = "MainActivity";
//private static final String TAG = CanvasSaveProxy.class.getSimpleName();
//private static final String METHOD_NAME = "save";
//private static final String FIELD_NAME = "CLIP_SAVE_FLAG";
//private static final int MY_PERMISSION_CAMERA = 1111;
//private static final int REQUEST_TAKE_PHOTO = 2222;
//private static final int REQUEST_TAKE_ALBUM = 3333;
//private static final int REQUEST_IMAGE_CROP = 4444;


public class MainActivity extends AppCompatActivity implements TextPlayer, View.OnClickListener {
    private final static String TAG = "MainActivity-TextPlayer";
    private static final int PLAY_1_TIME = 1;
    private static final int PLAY_N_TIME = 2;
    private static final int PLAY_FOR_5_MINUTES = 3;

    private final Bundle params = new Bundle();
    private final BackgroundColorSpan colorSpan = new BackgroundColorSpan(Color.YELLOW);
    private TextToSpeech tts;
    private Button playBtn;
    private Button pauseBtn;
    private Button stopBtn;
    private Button clearBtn;
    private Button languageBtn;
    private Button previousBtn;
    private Button nextBtn;
    private Button repeatBtn;
    private EditText inputEditText;
    private TextView contentTextView;
    private PlayState playState = PlayState.STOP;
    private Spannable spannable;
    private int standbyIndex = 0;
    private int lastPlayIndex = 0;
    private boolean isLanguageToggle = false; // false => English Language
    private int mRepeatMode = PLAY_1_TIME;
    private int mPreviousRepeatMode = PLAY_1_TIME;
    private boolean isRunningFor5Minutes = false;
    //private boolean isRepeatSentence = false;
    private long mStartTime = 0;

    public final static String APP_LOGIN_INFO_PREF = "APP_LOGIN_INFO_PREF";
    public static final String INFO_LOGIN_STATUS = "INFO_LOGIN_STATUS";
    private Context mContext =  null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
//        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTTS();
        initView();

        mContext = getApplicationContext();
        boolean bCheckLoginStatus = getLoginStatusInfo(MainActivity.this, APP_LOGIN_INFO_PREF, INFO_LOGIN_STATUS);
        if (bCheckLoginStatus == false) {
            showLoginDialog();
        }
    }

    private void initView() {
        playBtn = findViewById(R.id.btn_play);
        pauseBtn = findViewById(R.id.btn_pause);
        stopBtn = findViewById(R.id.btn_stop);
        clearBtn = findViewById(R.id.btn_clear);
        languageBtn = findViewById(R.id.btn_language);
        previousBtn = findViewById(R.id.btn_previous);
        nextBtn = findViewById(R.id.btn_next);
        repeatBtn = findViewById(R.id.btn_repeat);
        inputEditText = findViewById(R.id.et_input);
        contentTextView = findViewById(R.id.tv_content);

        playBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        languageBtn.setOnClickListener(this);
        previousBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        repeatBtn.setOnClickListener(this);

        String content = inputEditText.getText().toString();
        contentTextView.setText(content, TextView.BufferType.SPANNABLE);
    }

    private void initTTS() {
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, null);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int state) {
                if (state == TextToSpeech.SUCCESS) {
                    //ENGLISH
                    tts.setLanguage(Locale.ENGLISH);
                    isLanguageToggle = false;
                } else {
                    showState("TTS 객체 초기화 중 문제가 발생했습니다.");
                }
            }
        });

        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {

            }

            @Override
            public void onDone(String s) {
                Log.i(TAG, "setOnUtteranceProgressListener onDone(..) => mRepeatMode = " + mRepeatMode + ", isRunningFor5Minutes = " + isRunningFor5Minutes);
                clearAll();
                if((mRepeatMode == PLAY_N_TIME) || (mRepeatMode == PLAY_FOR_5_MINUTES && isRunningFor5Minutes == true)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            startPlay();
                        }
                    });
                }
            }

            @Override
            public void onError(String s) {
                showState("재생 중 에러가 발생했습니다.");
            }

            @Override
            public void onRangeStart(String utteranceId, int start, int end, int frame) {
                changeHighlight(standbyIndex + start, standbyIndex + end);
                lastPlayIndex = start;
            }
        });
    }

    public void showLoginDialog() {
        Log.i(TAG, "showLoginDialog");
        final EditText etPassword = new EditText(mContext);

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("Login Dialog"); // Title
        dlg.setMessage("Enter Login Password!"); // Message
        dlg.setIcon(android.R.mipmap.sym_def_app_icon); // Icon
        dlg.setView(etPassword);
        dlg.setPositiveButton("Confirm",new DialogInterface.OnClickListener(){ // Button Click Operation
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "showLoginDialog - Confirm‎");
                String value = etPassword.getText().toString();
                if("love12345".equals(value)) {
                    setLoginStatusInfo(mContext, APP_LOGIN_INFO_PREF, INFO_LOGIN_STATUS, true);
                    Toast.makeText(MainActivity.this,"Success !",Toast.LENGTH_SHORT).show();
                } else {
                    setLoginStatusInfo(mContext, APP_LOGIN_INFO_PREF, INFO_LOGIN_STATUS, false);
                    finishAffinity();
                }
            }
        });
        dlg.show();
    }

    public boolean getLoginStatusInfo(Context context, String perference, String key){
        return context.getSharedPreferences(perference, MODE_PRIVATE).getBoolean(key, false);
    }

    public void setLoginStatusInfo(Context context, String perference, String key, boolean value){
        context.getSharedPreferences(perference, MODE_PRIVATE).edit().putBoolean(key, value).apply();
    }

    @Override
    public void onClick(View view) {
        String content;

        switch (view.getId()) {
            case R.id.btn_play:
                startPlay();
                break;

            case R.id.btn_pause:
                pausePlay();
                showState(playState.getState());
                break;

            case R.id.btn_stop:
                stopPlay();
                showState(playState.getState());
                break;

            case R.id.btn_clear:
                clearInputData();
                break;

            case R.id.btn_language:

                if (isLanguageToggle == true) {
                    tts.setLanguage(Locale.ENGLISH);
                    languageBtn.setText("Eng");
                    isLanguageToggle = false;
                }
                else {
                    tts.setLanguage(Locale.KOREAN);
                    languageBtn.setText("Kor");
                    isLanguageToggle = true;
                }
                pausePlay();
                startPlay();
                break;

            case R.id.btn_previous:
                if(!playState.isPlaying()) return;
                tts.stop();
                content = inputEditText.getText().toString();
                standbyIndex += lastPlayIndex;
                lastPlayIndex = 0;
                standbyIndex -= 20;
                if (standbyIndex <= 0) standbyIndex = 0;
                startSpeak(content.substring(standbyIndex));
                //startSpeak(content.substring(standbyIndex, standbyIndex+1));
                break;

            case R.id.btn_next:
                if(!playState.isPlaying()) return;
                tts.stop();
                content = inputEditText.getText().toString();
                standbyIndex += lastPlayIndex;
                lastPlayIndex = 0;
                standbyIndex += 20;
                if(standbyIndex >= content.length()) standbyIndex -= 20;
                startSpeak(content.substring(standbyIndex));
                //startSpeak(content.substring(standbyIndex, standbyIndex+1));
                break;

            case R.id.btn_repeat:
                mPreviousRepeatMode = mRepeatMode;
                isRunningFor5Minutes = false;
                if (mRepeatMode  == PLAY_1_TIME) {
                    repeatBtn.setText("N");
                    mRepeatMode = PLAY_N_TIME;
                } else if (mRepeatMode  == PLAY_N_TIME) {
                    repeatBtn.setText("5M");
                    mRepeatMode = PLAY_FOR_5_MINUTES;

                    Log.i(TAG, "Set => isRunningFor5Minutes : true");
                    isRunningFor5Minutes = true;
                    mStartTime = System.currentTimeMillis();
                } else if (mRepeatMode  == PLAY_FOR_5_MINUTES) {
                    repeatBtn.setText("1");
                    mRepeatMode = PLAY_1_TIME;
                }  else { // This syntax is defensive code for Exceptional Cases. But it doesn't happen.
                    repeatBtn.setText("1");
                    mRepeatMode = PLAY_1_TIME;
                }
                Log.i(TAG, "Change RepeatMode => mRepeatMode = " + mRepeatMode + ", mPreviousRepeatMode = " + mPreviousRepeatMode + ", isRunningFor5Minutes = " + isRunningFor5Minutes);
                break;

        }
    }

    @Override
    public void startPlay() {
        Log.i(TAG, "startPlay() - start : playState = " + playState + ", mRepeatMode = " + mRepeatMode);

        String content = inputEditText.getText().toString();
        if (playState.isStopping() && !tts.isSpeaking()) {
            setContentFromEditText(content);
            startSpeak(content);
        } else if (playState.isWaiting()) {
            standbyIndex += lastPlayIndex;
            startSpeak(content.substring(standbyIndex));
        }

        if(mRepeatMode == PLAY_FOR_5_MINUTES) {
            if (isRunningFor5Minutes == false) {
                Log.i(TAG, "Set => isRunningFor5Minutes : true");
                isRunningFor5Minutes = true;
                mStartTime = System.currentTimeMillis();
            } else {
                Log.i(TAG, "Running this for 5 minutes. --- isRunningFor5Minutes : true --- Running Time : " + (System.currentTimeMillis() - mStartTime));
                // in case of " isRunningFor5Minutes == true "
                if (System.currentTimeMillis() - mStartTime > 1000 * 60 * 5) {
                    // Stop it !!! // more than 1000*60*5 ( more than 5 Minutes )
                    Log.i(TAG, "Stop it !!!, Set => isRunningFor5Minutes : false");
                    isRunningFor5Minutes = false;
                    mStartTime = 0;
                    playState = PlayState.STOP;
                }
            }
        } else {
            playState = PlayState.PLAY;
        }

        Log.i(TAG, "startPlay() - End  : playState = " + playState + ", mRepeatMode = " + mRepeatMode);

//        // There are some timing issue when using Observable Thread. Maybe we need to use protecting ways.
//        if(mRepeatMode == PLAY_FOR_5_MINUTES) {
//            if(isRunningFor5Minutes == false) {
//                isRunningFor5Minutes = true;
//                Observable.timer(5, TimeUnit.MINUTES)
//                        //.observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(s -> {
//                            isRunningFor5Minutes = false;
//                        }, t -> t.getMessage());
//            }
//        }
    }

    @Override
    public void pausePlay() {
        if (playState.isPlaying()) {
            playState = PlayState.WAIT;
            tts.stop();
        }
    }

    @Override
    public void stopPlay() {
        tts.stop();
        clearAll();
    }

    public void clearInputData() {
        inputEditText.setText(null);
        contentTextView.setText(null);
    }

    private void changeHighlight(final int start, final int end) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spannable.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        });
    }

    private void setContentFromEditText(String content) {
        contentTextView.setText(content, TextView.BufferType.SPANNABLE);
        spannable = (SpannableString) contentTextView.getText();
    }

    private void startSpeak(final String text) {
        tts.speak(text, TextToSpeech.QUEUE_ADD, params, text);
    }

    private void clearAll() {
        playState = PlayState.STOP;
        standbyIndex = 0;
        lastPlayIndex = 0;

        if (spannable != null) {
            changeHighlight(0, 0); // remove highlight
        }
    }

    private void showState(final String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        if (playState.isPlaying()) {
            pausePlay();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (playState.isWaiting()) {
            startPlay();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        tts.stop();
        tts.shutdown();
        super.onDestroy();
    }
}
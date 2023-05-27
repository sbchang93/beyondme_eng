package com.flower.textplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

//Reference Homepage URL : https://zion830.tistory.com/51
//Reference Homepage URL : https://gist.github.com/zion830/d045325ec84b7cb1a545f31f65ff966c
//Reference Homepage URL : https://channelg.co.kr/entry/%EC%A7%A7%EC%9D%80-%EC%98%81%EC%96%B4-%EB%AA%85%EC%96%B8-%EB%AA%A8%EC%9D%8C%EC%A7%91-75%EA%B0%9C-%EB%A0%88%ED%84%B0%EB%A7%81-%EB%AC%B8%EA%B5%AC-%EC%B6%94%EC%B2%9C

public class MainActivity extends AppCompatActivity implements TextPlayer, View.OnClickListener {

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
    private boolean languageToggle = false; // false => English Language

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTTS();
        initView();
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
                    languageToggle = false;
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
                clearAll();
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

    @Override
    public void onClick(View view) {
        String content;

        switch (view.getId()) {
            case R.id.btn_play:
                startPlay();
                break;

            case R.id.btn_pause:
                pausePlay();
                break;

            case R.id.btn_stop:
                stopPlay();
                break;

            case R.id.btn_clear:
                clearInputData();
                break;

            case R.id.btn_language:

                if (languageToggle == true) {
                    tts.setLanguage(Locale.ENGLISH);
                    languageBtn.setText("Eng");
                    languageToggle = false;
                }
                else {
                    tts.setLanguage(Locale.KOREAN);
                    languageBtn.setText("Kor");
                    languageToggle = true;
                }
                pausePlay();
                startPlay();
                break;

            case R.id.btn_previous:
                tts.stop();
                content = inputEditText.getText().toString();
                standbyIndex += lastPlayIndex;
                lastPlayIndex = 0;
                standbyIndex -= 20;
                startSpeak(content.substring(standbyIndex));
                //startSpeak(content.substring(standbyIndex, standbyIndex+1));
                break;

            case R.id.btn_next:
                tts.stop();
                content = inputEditText.getText().toString();
                standbyIndex += lastPlayIndex;
                lastPlayIndex = 0;
                standbyIndex += 20;
                startSpeak(content.substring(standbyIndex));
                //startSpeak(content.substring(standbyIndex, standbyIndex+1));
                break;

            case R.id.btn_repeat:
                break;

        }
        showState(playState.getState());
    }

    @Override
    public void startPlay() {
        String content = inputEditText.getText().toString();
        if (playState.isStopping() && !tts.isSpeaking()) {
            setContentFromEditText(content);
            startSpeak(content);
        } else if (playState.isWaiting()) {
            standbyIndex += lastPlayIndex;
            startSpeak(content.substring(standbyIndex));
        }
        playState = PlayState.PLAY;
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
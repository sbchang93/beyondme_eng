package com.example.swipeup.view.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.swipeup.R;

// Reference Home URL : https://gun0912.tistory.com/38

public class CustomLoginButton extends LinearLayout {
    private static final String TAG = "CustomLoginButton";

    LinearLayout bg;
    ImageView symbol;
    TextView text;

    public CustomLoginButton(Context context) {
        super(context);
        initView();
    }

    public CustomLoginButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public CustomLoginButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        getAttrs(attrs, defStyleAttr);
    }

//    public CustomLoginButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void initView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.welcome_login_button, this, false);
        addView(view);
        //inflate(getContext(), R.layout.welcome_login_button, this);

        bg = findViewById(R.id.bg);
        symbol = findViewById(R.id.symbol);
        text = findViewById(R.id.text);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginButton);
        setTypedArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defSyleAttr) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginButton, defSyleAttr, 0);
        setTypedArray(typedArray);
    }

    private void setTypedArray(TypedArray typedArray) {
        int bg_resID = typedArray.getResourceId(R.styleable.LoginButton_bg, R.drawable.login_naver_bg);
        bg.setBackgroundResource(bg_resID);

        int symbol_resID = typedArray.getResourceId(R.styleable.LoginButton_symbol, R.drawable.login_naver_symbol);
        symbol.setImageResource(symbol_resID);

        int textColor = typedArray.getColor(R.styleable.LoginButton_textColor, 0);
        text.setTextColor(textColor);

        String text_string = typedArray.getString(R.styleable.LoginButton_text);
        text.setText(text_string);

        typedArray.recycle();
    }

    void setBg(int bg_resID) {

        bg.setBackgroundResource(bg_resID);
    }

    void setSymbol(int symbol_resID) {
        symbol.setImageResource(symbol_resID);
    }

    void setTextColor(int color) {

        text.setTextColor(color);
    }

    void setText(String text_string) {
        text.setText(text_string);
    }

    void setText(int text_resID) {
        text.setText(text_resID);
    }

}

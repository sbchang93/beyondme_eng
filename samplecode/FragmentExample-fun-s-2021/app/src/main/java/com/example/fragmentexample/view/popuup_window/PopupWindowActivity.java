package com.example.fragmentexample.view.popuup_window;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.BidiFormatter;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fragmentexample.R;

import java.util.Locale;

// reference homepage : https://arabiannight.tistory.com/331

public class PopupWindowActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    private Button btn_Popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        btn_Popup = (Button) findViewById(R.id.btn_click);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_click:

                View popupView = getLayoutInflater().inflate(R.layout.popup_window, null);

                mPopupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setAnimationStyle(-1); // 애니메이션 설정(-1:설정, 0:설정안함)
                // 중앙에 Popup Window를 띄워줌
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                //mPopupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 0, 0);
                //mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, -200);

                // btn_Popup의 (50, 50) 위치에 PopupWindow를 띄워줌
    			//mPopupWindow.showAsDropDown(btn_Popup, 50, 50);

   			    //mPopupWindow.update(anchor, xoff, yoff, width, height)(width, height);
                break;

            case R.id.btn_close:
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                break;

            case R.id.btn_bottom:

                boolean isRTL = true; // isRTL(); // TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL);
                String appName = BidiFormatter.getInstance(isRTL).unicodeWrap(getString(R.string.app_name));
                String Added = new String("%s added in App.");
                String Removed = new String("%s removed from App.");
                String toastString = String.format(Locale.getDefault(), BidiFormatter.getInstance(isRTL).unicodeWrap(true ? Added : Removed), appName);
                Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();

                Toast.makeText(this, "Bottom UI 입니다.", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }
    }
}

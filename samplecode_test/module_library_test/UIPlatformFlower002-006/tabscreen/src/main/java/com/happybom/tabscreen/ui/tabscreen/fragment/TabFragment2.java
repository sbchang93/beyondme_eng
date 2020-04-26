package com.happybom.tabscreen.ui.tabscreen.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.happybom.tabscreen.R;

// Reference Homepage : https://duckssi.tistory.com/9 (activity)
// Reference Homepage : https://gun0912.tistory.com/23 (fragment)
public class TabFragment2 extends Fragment {
    private static final String TAG = "TabFragment2";
    private WebView webView;
    private String url = "https://google.com";
    //private String url = "https://www.naver.com";

    public TabFragment2 newInstance(int index) {
        TabFragment2 f = new TabFragment2();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment2, container, false);

        webView = (WebView) view.findViewById(R.id.tab2_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new Tab2WebViewClient());

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != KeyEvent.ACTION_DOWN)
                    return true;

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        getActivity().onBackPressed();
                    }
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    private class Tab2WebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /*
    // Activity??Í≤ΩÏö∞?êÎäî onKeyDown(...)?®ÏàòÎ•?overrideÍ∞Ä?•ÌïòÍ∏??åÎ¨∏???ÑÎûò???®Ïàò?êÏÑú backkeyÎ•?Ï≤òÎ¶¨??Ï§????àÏùå.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
     */

}

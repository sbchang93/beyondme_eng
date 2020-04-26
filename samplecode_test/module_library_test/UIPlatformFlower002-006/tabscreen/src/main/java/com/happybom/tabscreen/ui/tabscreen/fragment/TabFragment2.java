package com.happybom.tabscreen.ui.tabscreen.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.happybom.tabscreen.R;

// Reference Homepage : https://duckssi.tistory.com/9 (activity)
// Reference Homepage : https://gun0912.tistory.com/23 (fragment)
// Reference Homepage : http://zeany.net/5 (screen rolation) - http://zeany.net/4 , 5, 9, 10
public class TabFragment2 extends Fragment {
    private static final String TAG = "TabFragment2";
    private WebView webView;
    private String url = "https://google.com";
    //private String url = "https://www.naver.com";
    private static final String ENTRY_URL = "file:///android_asset/www/index.html";

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

        webView.loadUrl(url);
        //webView.loadUrl(ENTRY_URL); //http://zeany.net/9
        return view;
    }

    private class Tab2WebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        //Reference Homepage : http://zeany.net/10?category=666373
        @Override
        public void onPageFinished(WebView view, String url) {

            // Manage something with data that webView provides.

            if (url.equals(ENTRY_URL)) { // please use this code " webView.loadUrl(ENTRY_URL); " in onCreateView for testing.
                String keyword = "tistory";

                String script = "javascript:function afterLoad() {"
                        + "document.getElementById('keyword').value = '" + keyword + "';"
                        + "};"
                        + "afterLoad();";

                view.loadUrl(script);
            }
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

    /*
        // Reference Homepage : http://zeany.net/5

        webView.setWebChromeClient(new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            new AlertDialog.Builder(view.getContext())
                    .setTitle("Alert")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new AlertDialog.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which) {
                                    result.confirm();
                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
        final JsResult result) {
            new AlertDialog.Builder(view.getContext())
                    .setTitle("Confirm")
                    .setMessage(message)
                    .setPositiveButton("Yes",
                            new AlertDialog.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which) {
                                    result.confirm();
                                }
                            })
                    .setNegativeButton("No",
                            new AlertDialog.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which) {
                                    result.cancel();
                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();
            return true;
        }
    });
    */

}

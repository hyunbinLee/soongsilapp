package com.example.hblee.map2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.CallbackManager;

public class web extends Activity {
    WebView w;
    private CallbackManager callbackManager;
    String id="";
    String name="";
    String email="";
    Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        w= (WebView)findViewById(R.id.webView);
        WebSettings setting= w.getSettings();//web 설정할 수 있다.
        setting.setJavaScriptEnabled(true);
        w.loadUrl("http:m.naver.com"); //인터넷을 띄우는 메소드
        w.setWebViewClient(new WebViewClient());
    }
}


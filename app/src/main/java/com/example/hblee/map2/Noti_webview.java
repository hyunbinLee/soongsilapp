package com.example.hblee.map2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;


public class Noti_webview extends Activity {
    String data;
    private static Thread thread = null;
    String parsing_url;
    static String previous_url;
    ArrayList<String> array=new ArrayList<String>();
    WebView parser;
    String contents;
    Noti_webview_parsing a;
    NotiParse parse;
    final Handler ahandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            listUpdate();
        }
    };
    private void listUpdate()
    {
        if(parsing_url!=previous_url) {
            a= new Noti_webview_parsing();
            String contents = parse.get_data;
            parser.loadDataWithBaseURL(null, a.creHtml(contents), "text/html", "utf-8", null);
            previous_url=parsing_url;
        }
    }
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        previous_url=getIntent().getStringExtra("previous_url");
        parsing_url=getIntent().getStringExtra("url");
        parse=new NotiParse(parsing_url,ahandler,this);
        String contents=parse.get_data;
        parser = (WebView) findViewById(R.id.webView);
        WebSettings setting= parser.getSettings();
        parser.setWebViewClient(new WebViewClient());
    }

}

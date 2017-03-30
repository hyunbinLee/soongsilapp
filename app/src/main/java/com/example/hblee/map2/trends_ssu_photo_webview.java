package com.example.hblee.map2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class trends_ssu_photo_webview extends Activity {
    WebView w;
    String getdata=null;
    String getlink=null;
    String curdata=null;
    trends_ssu_photo_parse parse;
    final Handler handlser = new Handler() {
        public void handleMessage(Message msg) {
            update();
        }
    };
    public void update()
    {
        if(curdata==null) {
            w.loadDataWithBaseURL(null, creHtml(), "text/html", "utf-8", null);
            curdata=getdata;
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trends_webview);
        w= (WebView)findViewById(R.id.contents);
        getIntent();
        getlink=getIntent().getStringExtra("photo_link");
        trends_ssu_photo_parse parse=new trends_ssu_photo_parse(getlink,handlser,this);
        getdata=parse.get_data;
        WebSettings setting= w.getSettings();//web 설정할 수 있다.
        setting.setJavaScriptEnabled(true);///중용함!!!!!
        w.setWebViewClient(new WebViewClient());
        w.setHorizontalScrollBarEnabled(false);
        w.setVerticalScrollBarEnabled(false);
        w.loadDataWithBaseURL(null,creHtml(),"text/html","utf-8",null);
    }
    public String creHtml()
    {
        StringBuffer sb= new StringBuffer("<HTML>");
        sb.append("<HEAD>");
        //sb.append("<meta name=viewport"+"content=initial-scale=1.0; minimum-scale=1.0; user-scalable=yes;/>");
        sb.append("</HEAD>");
        sb.append("<BODY style='margin:0;'>");
        //  sb.append("<img src=\""+imageuri+"\">");
        //sb.append("<img width='100%' height='70%' src=\""+imageuri+"\">");
        sb.append(getdata);
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}


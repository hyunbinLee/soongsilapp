
package com.example.hblee.map2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class trends_ssu_news_parse {
    URL nURL;
    static String link=new String();
    private ProgressDialog progressDialog;
    private static Thread thread = null;
    static String parsing_url=new String();
    static String imgsrc=new String();
    static String title=new String();
    static String get_data=new String();
    Handler thandler;
    ArrayList<String> array;
    Handler mHandler=new Handler();
    public Context context;
    public trends_ssu_news_parse(String str, Handler handler, Context context1) {
        parsing_url=str;
        this.context=context1;
        this.thandler=handler;
        Runnable task =
                new Runnable(){
                    public void run(){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog = ProgressDialog.show(context, "", "잠시만 기다려주세요");
                            }
                        });
                        getData(parsing_url);
                        mHandler.post(new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                                thandler.sendEmptyMessage(1);
                            }
                        });
                    }
                };
        thread = new Thread(task);
        thread.start();
    }
    public String getData(String strURL){
        final Handler handler;
        Source source;
        array = new ArrayList();
        try{
            nURL= new URL(strURL);
            InputStream html = nURL.openStream();
            source = new Source(new InputStreamReader(html, "UTF-8"));
            Element table = (Element)source.getAllElementsByClass("bbs-body").get(0);
            get_data=table.toString();
            get_data=get_data.replace("src=\"","src=\"http://www.ssu.ac.kr");
            get_data=get_data.replace("width=\"700\"","width=\"400\"");

        }catch(Exception e){
        }
        return get_data;
    }
}

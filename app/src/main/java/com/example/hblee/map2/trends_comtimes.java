
package com.example.hblee.map2;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class trends_comtimes {
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
    public trends_comtimes(String str, Handler handler, Context context1) {
        parsing_url=str;
        this.context=context1;
        this.thandler=handler;
        Runnable task =
                new Runnable(){
                    public void run(){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        getData(parsing_url);
                        mHandler.post(new Runnable() {
                            public void run() {

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
            source = new Source(new InputStreamReader(html, "EUC-KR"));
            Element table = (Element)source.getAllElementsByClass("BLOCK_HTML_DIV").get(1);
            Element TITLE=table.getFirstElementByClass("TITLE");
            Element element = (Element) table.getAllElements(HTMLElementName.A).get(0);
            title=TITLE.getTextExtractor().toString();
            link=element.getAttributeValue("href");
            Element element2 = (Element) table.getAllElements(HTMLElementName.IMG).get(0);
            imgsrc=element2.getAttributeValue("src");
            get_data=table.toString();
        }catch(Exception e){
        }
        return get_data;
    }
}

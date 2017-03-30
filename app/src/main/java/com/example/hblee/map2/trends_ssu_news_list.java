package com.example.hblee.map2;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class trends_ssu_news_list {
    private static Thread thread = null;
    String get_data;
    private String url="http://www.ssu.ac.kr/web/kor/intro_h_01";
    private Context context;
    private Handler handler;
    private ProgressDialog progressDialog;
    private Source source;
    String Linkhref;
    ArrayList<String> Link=new ArrayList<String>();
    ArrayList<String> array=new ArrayList<String>();
    public trends_ssu_news_list(Context context, Handler handler)
    {
        this.context = context;
        this.handler = handler;
    }
    public void open()
    {
        try
        {
            process();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getarray()
    {
        return array;
    }
    public ArrayList<String> getLink() {return Link;}
    private void process() throws IOException
    {
        final Handler aHandler = new Handler();
        new Thread()
        {
            @Override
            public void run()
            {
                URL nURL;
                try
                {
                    nURL = new URL(url);
                    aHandler.post(new Runnable(){
                        @Override
                        public void run()
                        {
                        }
                    });
                    InputStream html = nURL.openStream();
                    source = new Source(new InputStreamReader(html, "UTF-8"));
                    Element table = (Element)source.getAllElementsByClass("bbs-list").get(0);
                    int size=table.getAllElements(HTMLElementName.A).size();
                    for(int i=0;i<5;i++) {
                        Element element1 = (Element) table.getAllElements(HTMLElementName.A).get(i);
                        Link.add(element1.getAttributeValue("href"));
                        array.add(element1.getTextExtractor().toString());
                    }
                    get_data=table.toString();
                    aHandler.post(new Runnable()
                    {
                        public void run()
                        {
                            handler.sendEmptyMessage(0);

                        }
                    });

                }catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}


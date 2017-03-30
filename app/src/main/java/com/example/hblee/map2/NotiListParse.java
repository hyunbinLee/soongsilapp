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
import java.util.HashMap;

public class NotiListParse {
    private static Thread thread = null;
    String get_data;
    private String url;
    private Context context;
    private Handler handler;
    private ProgressDialog progressDialog;
    private Source source;

    String Linkhref;
    ArrayList<String> Link=new ArrayList<String>();
    ArrayList<String> array=new ArrayList<String>();
    public NotiListParse(Context context, Handler handler, ArrayList<HashMap<String, String>> data, String strurl)
    {
        this.context = context;
        this.handler = handler;
        this.url=strurl;
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
                    Element table = (Element) source.getAllElements(HTMLElementName.OL).get(0);
                    int tr_count = table.getAllElements(HTMLElementName.A).size();
                    Element element = null;

                    for(int i=0; i<tr_count; i++)
                    {
                        element = (Element) table.getAllElements(HTMLElementName.A).get(i);
                        array.add(element.getTextExtractor().toString());
                        Linkhref=element.getAttributeValue("href").toString();
                        Link.add(Linkhref);

                    }
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


package com.example.hblee.map2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;


public class Frgtrends extends Fragment { //최신 뉴스 탭 프래그먼트 액티비티
    View layout;
    String data;
    private ListView listview;
    private ArrayList<String> list=new ArrayList<>();
    private ArrayList<String> Link= new ArrayList<>();
    private ArrayAdapter adpt;
    private String tabTitle;
    private int position;
    String previous_link=null;
    WebView trend_thmbnail;
    WebView ssu_photo_webView;
    String trend_data=null;
    String ssu_thumbnail=null;
    trends_comtimes trendsparse;
    trends_ssu_news_list news;
    trends_ssu_photo_thumbnail ssu;
    final Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            listUpdate();
        }
    };
    final Handler handler4 = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            listUpdate3();
        }
    };
    public void listUpdate()
    {
        if(trend_data==null)
        {
            trend_thmbnail.loadDataWithBaseURL(null, creHtml(trendsparse.imgsrc, "[컴타임즈]"+trendsparse.title), "text/html", "EUC-KR", null);
            trend_data=trendsparse.get_data;
        }
        if(ssu_thumbnail==null)
        {

            if(ssu.imgsrc.hashCode()!=0) {
                ssu_thumbnail = ssu.get_data;
                ssu_photo_webView.loadDataWithBaseURL(null, creHtml(ssu.imgsrc, "[숭실포토뉴스]" + ssu.title), "text/html", "utf-8", null);
            }
        }
    }

    public void listUpdate3()
    {
        adpt.notifyDataSetChanged();
    }
    public static Frgtrends create(String tabTitle, int position) {
        Frgtrends frg = new Frgtrends();
        Bundle b = new Bundle();
        b.putString("tabTitle", tabTitle);
        b.putInt("position", position);
        frg.setArguments(b);
        return frg;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.tabTitle = this.getArguments().getString("tabTitle");
        this.position = this.getArguments().getInt("position");
    }
    public int CheckPage() {
        return 0;
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (ScrollView) inflater.inflate(R.layout.trends_grid, null);
        listview=(ListView)layout.findViewById(R.id.ssu_news);
        news=new trends_ssu_news_list(getActivity(),handler4);
        trendsparse = new trends_comtimes("http://www.comtimes.kr/paper/main.php", handler, getActivity());
        ssu=new trends_ssu_photo_thumbnail("http://www.ssu.ac.kr/web/kor/intro_h_13",handler,getActivity());
        news.open();
        list=news.getarray();
        Link=news.getLink();
        adpt=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adpt);
        adpt.notifyDataSetChanged();
        trend_thmbnail= (WebView)layout.findViewById(R.id.first);
        ssu_photo_webView=(WebView)layout.findViewById(R.id.ssu_photo);
        WebSettings setting= trend_thmbnail.getSettings();//web 설정할 수 있다.
        setting.setJavaScriptEnabled(true);///중용함!!!!
        trend_thmbnail.setWebViewClient(new WebViewClient());
        trend_thmbnail.setHorizontalScrollBarEnabled(false);
        trend_thmbnail.setVerticalScrollBarEnabled(true);
        ssu_photo_webView.setWebViewClient(new WebViewClient());
        ssu_photo_webView.setHorizontalScrollBarEnabled(false);
        ssu_photo_webView.setVerticalScrollBarEnabled(false);
        trend_thmbnail.loadDataWithBaseURL(null, creHtml(trendsparse.imgsrc, "[컴타임즈]"+trendsparse.title), "text/html", "EUC-KR", null);
        ssu_photo_webView.loadDataWithBaseURL(null, creHtml(ssu.imgsrc,"[숭실포토뉴스]"+ssu.title), "text/html", "utf-8", null);
        ssu_photo_webView.setOnTouchListener(new WebViewOnTouchListener());
        listview.setOnItemClickListener(new ssu_news_click());
        return layout;
    }
    class WebViewOnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            //    trends_ssu_photo_parse trends = new trends_ssu_photo_parse(trendsParse_ssu_photo_thumbnail.link, handler4, getActivity());
            Intent intent = new Intent(getActivity(), trends_ssu_photo_webview.class);
            intent.putExtra("photo_link", trends_ssu_photo_thumbnail.link);
            startActivity(intent);
            return true;
        }
    }
    class ssu_news_click implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //Toast.makeText(getActivity(),Link.get(i),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), trends_ssu_news_webview.class);
            if(previous_link==null)
                previous_link=Link.get(i);
            intent.putExtra("news_link", Link.get(i));
            intent.putExtra("previous_link",previous_link);
            startActivity(intent);
        }
    }
    public String creHtml(String imageuri, String data)
    {
        StringBuffer sb= new StringBuffer("<HTML>");
        sb.append("<HEAD>");
        //sb.append("<meta name=viewport"+"content=initial-scale=1.0; minimum-scale=1.0; user-scalable=yes;/>");
        sb.append("</HEAD>");
        sb.append("<BODY style='margin:0;'>");
        //  sb.append("<img src=\""+imageuri+"\">");
        sb.append("<img width='100%' height='70%' src=\""+imageuri+"\">");
        sb.append("<p padding-top='0' padding-bottom='0' margin='0' align='center'>"+data+"<p>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

}

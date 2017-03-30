package com.example.hblee.map2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class FrgNoti extends Fragment { //NOTI 탭 프래그먼트 액티비티
    NotiParse cur;
    AlertDialog.Builder alertdig;
    int position;
    String array;
    int pageindex=1;
    TextView prepage;
    TextView curpage;
    TextView nextpage;
    NotiParse notiparse[];
    ArrayList<String> list=new ArrayList<String>();
    ArrayAdapter sa;
    NotiListParse parse;
    ListView listview;
    ArrayList<String> Link=new ArrayList<>();
    ArrayList<HashMap<String, String>> data;
    ArrayAdapter<String> adapter;
    String tabTitle;
    String previous_url=null;
    final Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            listUpdate();
        }
    };
    private void listUpdate()
    {
        sa.notifyDataSetChanged();
    }
    public static FrgNoti create(String tabTitle, int position) {
        FrgNoti frg = new FrgNoti();
        Bundle b = new Bundle();
        b.putString("tabTitle", tabTitle);
        b.putInt("position", position);
        frg.setArguments(b);
        return frg;
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = null;
        layout = (RelativeLayout) inflater.inflate(R.layout.noti_lay, null);
        data = new ArrayList<HashMap<String, String>>();
        listview = (ListView) layout.findViewById(R.id.listView);
        curpage=(TextView)layout.findViewById(R.id.cur);
        prepage=(TextView)layout.findViewById(R.id.pre);
        nextpage=(TextView)layout.findViewById(R.id.next);
        curpage.setText("("+pageindex+")");
        if(pageindex==1)
            prepage.setVisibility(View.INVISIBLE);
        prepage.setOnClickListener(new prepageChange());
        nextpage.setOnClickListener(new nextpageChange());
        parse = new NotiListParse(getActivity(),handler, data,"http://m.ssu.ac.kr/html/themes/m/html/notice_univ_list.jsp?curPage="+pageindex+"&sCategory=&sKeyType=&sKeyword=");
        list=parse.getarray();
        Link=new ArrayList<String>();
        Link=parse.getLink();
        notiparse=new NotiParse[Link.size()];
        sa= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        parse.open();
        listview.setAdapter(sa);
        listview.setOnItemClickListener(new ListViewOnClickListener());
        sa.notifyDataSetChanged();

        return layout;
    }
    class prepageChange implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            pageindex--;
            curpage.setText("("+pageindex+")");
            parse = new NotiListParse(getActivity(),handler, data,"http://m.ssu.ac.kr/html/themes/m/html/notice_univ_list.jsp?curPage="+pageindex+"&sCategory=&sKeyType=&sKeyword=");
            list=parse.getarray();
            Link=parse.getLink();
            sa= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
            parse.open();
            listview.setAdapter(sa);
            if(pageindex==1)
            prepage.setVisibility(View.INVISIBLE);
            sa.notifyDataSetChanged();
        }
    }

    class nextpageChange implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            pageindex++;
            curpage.setText("("+pageindex+")");
            parse = new NotiListParse(getActivity(),handler, data,"http://m.ssu.ac.kr/html/themes/m/html/notice_univ_list.jsp?curPage="+pageindex+"&sCategory=&sKeyType=&sKeyword=");
            list=parse.getarray();
            Link=parse.getLink();
            sa= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
            parse.open();
            listview.setAdapter(sa);
            prepage.setVisibility(View.VISIBLE);
            sa.notifyDataSetChanged();
        }
    }
    class ListViewOnClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
            //    cur =new NotiParse("http://m.ssu.ac.kr" +Link.get(position),handler,getActivity());
            if(previous_url==null)
                previous_url="http://m.ssu.ac.kr"+Link.get(position);
            Intent intent = new Intent(getActivity(),Noti_webview.class);
            intent.putExtra("url","http://m.ssu.ac.kr"+Link.get(position));
            intent.putExtra("previous_url",previous_url);
            startActivityForResult(intent, 1001);
        }
    }
}

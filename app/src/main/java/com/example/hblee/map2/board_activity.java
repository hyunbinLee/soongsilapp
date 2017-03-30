package com.example.hblee.map2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class board_activity extends ActionBarActivity {
    String title;
    String content;
    ListView listview;
    replyAdapter adpt;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_lay);
        ActionBar actionBar= getActionBar();
        Intent intent= getIntent();
        title=intent.getStringExtra("title");
        content=intent.getStringExtra("content");
        setTitle(title);
        final EditText editbox = (EditText) findViewById(R.id.BoardEditText);

        listview=(ListView)findViewById(R.id.reply_list);
        list=new ArrayList<>();
        adpt=new replyAdapter();
        adpt.addItem(content,0);
        listview.setAdapter(adpt);
        adpt.notifyDataSetChanged();
        //  l1.setGravity(Gravity.CENTER|Gravity.TOP);
        Button button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputtext=editbox.getText().toString();
                editbox.setText("");
                adpt.setflag(1);
                adpt.addItem(inputtext,1);
                adpt.notifyDataSetChanged();
            }
        });
    }
    class replyAdapter extends BaseAdapter
    {
        int rep=0;
        ArrayList<Integer> flags=new ArrayList<>();
        ArrayList<String> reply =new ArrayList<String>();
       public void setflag(int i){rep=i;}
        @Override
        public int getCount() {;
            return reply.size();
        }
        @Override
        public Object getItem(int i) {
            return reply.get(i);
        }
        public Object addItem(String re, int i) {flags.add(i);
            return reply.add(re);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(flags.get(i)==1) {
                board_sending re = new board_sending(getApplicationContext());
                re.setReply(reply.get(i));
                return re;
            }
            else {
                board_receiving re1 = new board_receiving(getApplicationContext());
                re1.setReply(reply.get(i));
                return re1;

            }
        }

    }
}
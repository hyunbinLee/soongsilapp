package com.example.hblee.map2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static android.widget.AdapterView.OnItemClickListener;

public class Frgboard1 extends Fragment { //board탭 프래그먼트 액티비티
    String new_title2;
    String new_contents;
    View layout;
    private ListView listview;
    ArrayList<String> list=new ArrayList<>();
    ArrayList<String> content=new ArrayList<>();
    private ArrayAdapter adpt;
    private String tabTitle;
    private int position;
    String new_title;
    board_new b=new board_new();
    public static Frgboard1 create(String tabTitle, int position) {
        Frgboard1 frg = new Frgboard1();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==1) {
            super.onActivityResult(requestCode, resultCode, data);
            new_title = data.getStringExtra("new_title");
            new_contents = data.getStringExtra("new_contents");
            list.add(new_title);
            content.add(new_contents);
            adpt.notifyDataSetChanged();
        }
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        layout = (RelativeLayout) inflater.inflate(R.layout.board_start, null);
        ListView listview= (ListView) layout.findViewById(R.id.boardlist1);
        Button button = (Button) layout.findViewById(R.id.new_board);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(),board_new.class);

                startActivityForResult(intent, 1001);
            }
        });
        adpt=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adpt);
        listview.setOnItemClickListener(new ListViewOnClickListener());
        adpt.notifyDataSetChanged();
        return layout;
    }
    class ListViewOnClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String p= Integer.toString(i);
            Intent intent=new Intent(getActivity(), board_activity.class);
            intent.putExtra("title",list.get(i));
            intent.putExtra("content",content.get(i));
            startActivityForResult(intent, 1);

        }
    }
}

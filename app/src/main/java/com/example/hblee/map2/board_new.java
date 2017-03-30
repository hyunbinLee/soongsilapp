package com.example.hblee.map2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class board_new extends ActionBarActivity {
    String result;
    String puttitle;
    String putcontent;
    Intent intent= getIntent();
    public String get_title()
    {
        return puttitle;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_new_lay);
        ActionBar actionBar= getActionBar();
        setTitle("새 글");
        final EditText title = (EditText) findViewById(R.id.board_new_text1);
        final EditText content= (EditText) findViewById(R.id.board_new_text2);
        Button button = (Button)findViewById(R.id.board_new_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puttitle=title.getText().toString();
                putcontent=content.getText().toString();
                Intent intent=new Intent(getApplicationContext(),Frgboard1.class);
                intent.putExtra("new_title",puttitle);
                intent.putExtra("new_contents",putcontent);
                setResult(1,intent);//메인 액티비티로 전달
                finish();
            }
        });
    }
}
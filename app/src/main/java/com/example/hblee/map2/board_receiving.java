package com.example.hblee.map2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class board_receiving extends LinearLayout {
    int FLAG=0;
    TextView reply;
    public board_receiving(Context context) {
        super(context);
        init(context);
    }

    public board_receiving(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public board_receiving(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public board_receiving(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    public void setReply(String re)
    {
        reply.setText(re);
    }
    private void init(Context context)
    {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.board_receiving,this,true);
        reply= (TextView) findViewById(R.id.receiviing);
    }
}

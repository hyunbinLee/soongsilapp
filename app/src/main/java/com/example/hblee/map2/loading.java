package com.example.hblee.map2;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class loading extends Activity { //앱을 처음 작동시켰을때 실행되는 스플래쉬 액티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_first);
        Handler hd= new Handler();
        hd.postDelayed(new splashhandler(),3000);
    }
    public class splashhandler implements Runnable //스플래쉬 액티비티가 끝나면 menupage클래스를 호출
    {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(),Menupage.class));
            loading.this.finish();
        }
    }
}

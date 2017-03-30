package com.example.hblee.map2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.astuetz.PagerSlidingTabStrip;
import com.facebook.appevents.AppEventsLogger;
import com.navdrawer.SimpleSideDrawer;



public class Menupage extends FragmentActivity { //스플래쉬 액티비티가 끝난후 출력되는 첫 화면
    //String new_title;
    private PagerSlidingTabStrip tab;
    private ViewPager pager;
    private String[] tabTitle={"Noti","NEW SSU","Board"};
    private int position;
    private String title;
    private static Thread thread = null;
    Button side;
    SimpleSideDrawer sidemenu;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_lay);
        side=(Button) findViewById(R.id.side);

        tab= (PagerSlidingTabStrip)this.findViewById(R.id.tabs);
        pager= (ViewPager) this.findViewById(R.id.container);
        MyPagerAdapter adpt= new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adpt);
        tab.setViewPager(pager);

    }
    public void scheduler(View v) // 스케쥴러 액티비티 호출
    {
        startActivity(new Intent(this, Schedulemain.class));
    }

    public void onPopupButtonClick(View button) { //버튼 클릭시 popupmenu 생성
        //PopupMenu 객체 생성.
        PopupMenu popup = new PopupMenu(this, button);

        //설정한 popup XML을 inflate.
        popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
        //팝업메뉴 클릭 시 이벤트
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {


                    case R.id.usaint:

                        String url ="http://saint.ssu.ac.kr/irj/portal";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        break;

                    case R.id.homepage:
                        String url2 ="http://m.ssu.ac.kr/html/themes/m/html/index.jsp";
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                        startActivity(intent2);
                        break;

                    case R.id.homepage2:
                        String url3="http://cse.ssu.ac.kr/";
                        Intent intent3= new Intent(Intent.ACTION_VIEW, Uri.parse(url3));
                        startActivity(intent3);
                        break;
                    case R.id.library:
                        String url4="http://203.253.28.47/seat/domian5.asp";
                        Intent intent4=new Intent(Intent.ACTION_VIEW, Uri.parse(url4));
                        startActivity(intent4);
                        break;
                    case R.id.map1234:
                        startActivity(new Intent(Menupage.this,MapsActivity.class));
                }
                return true;
            }
        });
        popup.show();
    }

    public class MyPagerAdapter extends FragmentPagerAdapter // 프래그먼트 페이저 생성 (화면을 프래그먼트로 생성해서 탭을 나눔)
    {
        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int arg0)
        {
            position=arg0;
            switch(position)
            {
                case 0: {
                    return FrgNoti.create(tabTitle[arg0], arg0); //NOTI 프래그먼트
                }
                case 1:
                    return Frgtrends.create(tabTitle[arg0], arg0); //최신 뉴스 프래그먼트
                case 2:
                    return Frgboard1.create(tabTitle[arg0], arg0); //board 프래그먼트
               // case 3:
                 //   return Frgcommunity.create(tabTitle[arg0], arg0); //commutiny 프래그먼트
                default:
                    return FrgNoti.create(tabTitle[arg0], arg0);
            }
        }
        @Override
        public int getCount() { //탭의 개수를 샌다
            return tabTitle.length;
        }
        @Override
        public CharSequence getPageTitle(int position)        //페이지 위치별 타이틀을 얻어옴
        {
            return tabTitle[position];
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }
}

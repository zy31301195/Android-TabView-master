package com.ycl.tabview.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.alamkanak.weekview.WeekView;
import com.ycl.tabview.Fragement.ClassifyFragment;
import com.ycl.tabview.Fragement.HomeFragment;
import com.ycl.tabview.Fragement.MyFragment;
import com.ycl.tabview.Fragement.OrderFragment;
import com.ycl.tabview.R;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabView tabView;
    Menu mMenu;
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private WeekView mWeekView;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private Myapplication mMyapplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tabView= (TabView) findViewById(R.id.tabView);
        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.drawable.tab01_sel,R.drawable.tab01_unsel,"首页",  HomeFragment.newInstance());
        TabViewChild tabViewChild02=new TabViewChild(R.drawable.tab02_sel,R.drawable.tab02_unsel,"分类",  ClassifyFragment.newInstance());
        TabViewChild tabViewChild03=new TabViewChild(R.drawable.tab03_sel,R.drawable.tab03_unsel,"订单",  OrderFragment.newInstance());
        TabViewChild tabViewChild04=new TabViewChild(R.drawable.tab05_sel,R.drawable.tab05_unsel,"我的",  MyFragment.newInstance());

        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);



        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
               if(position==2){
                   showEditMenu();
               }else{
                   hiddenEditMenu();
               }
            }
        });
        Date dt=new Date();
        SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
        mMyapplication= (Myapplication) getApplication();
        if(!(mMyapplication.users.getUser_date().toString()).equals(matter1.format(dt))){
            AlertDialog.Builder version = new AlertDialog.Builder(MainActivity.this);
            version.setMessage("新增"+mMyapplication.my+"个监考竞拍成功；"+mMyapplication.take+"个监考被调配出去；"+mMyapplication.lose+"个监考流拍");
            version.setPositiveButton("OK",null);
            version.create().show();
        }
    }

    private void hiddenEditMenu(){
        if(null != mMenu){
            for (int i = 0; i < mMenu.size(); i++){
                mMenu.getItem(i).setVisible(false);
                mMenu.getItem(i).setEnabled(false);
            }
        }
    }

    private void showEditMenu(){
        if(null != mMenu){
            for (int i = 0; i < mMenu.size(); i++){
                mMenu.getItem(i).setVisible(true);
                mMenu.getItem(i).setEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        mMenu = menu;
        hiddenEditMenu();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        mWeekView = (WeekView) findViewById(R.id.weekView);
        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);

    }
}

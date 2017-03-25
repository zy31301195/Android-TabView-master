package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ycl.tabview.Adapter.MenuListAdapter;
import com.ycl.tabview.R;
import com.ycl.tabview.View.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseActivity extends Activity {
    private TextView secondTxt;

    //菜单标题
    private String headers[] = {"综合", "时间", "价格"};
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
    private MenuListAdapter mMenuAdapter1;
    private MenuListAdapter mMenuAdapter2;
    private MenuListAdapter mMenuAdapter3;

    private DropDownMenu mDropDownMenu;


    private String times[] = {"三天内", "一周内", "两周内", "全部"};

    private String price[] = {"从高到低", "从低到高"};

    private List<View> popupViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        Intent intent = getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        String str=bundle.getString("title");//getString()返回指定key的值
        secondTxt=(TextView)findViewById(R.id.tv_title);//用TextView显示值
        secondTxt.setText(str);
        initView();

    }

    private void initView() {

        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);


        //init menu listview

        //这里是每个下拉菜单之后的布局,目前只是简单的listview作为展示
        listView1 = new ListView(ChooseActivity.this);
        listView2 = new ListView(ChooseActivity.this);
        listView3 = new ListView(ChooseActivity.this);

        listView1.setDividerHeight(0);
        listView2.setDividerHeight(0);
        listView3.setDividerHeight(0);

        //mMenuAdapter1 = new MenuListAdapter(ChooseActivity.this, Arrays.asList(citys));
        mMenuAdapter2 = new MenuListAdapter(ChooseActivity.this, Arrays.asList(times));
        mMenuAdapter3 = new MenuListAdapter(ChooseActivity.this, Arrays.asList(price));

        listView1.setAdapter(mMenuAdapter1);
        listView2.setAdapter(mMenuAdapter2);
        listView3.setAdapter(mMenuAdapter3);

        popupViews.add(listView1);
        popupViews.add(listView2);
        popupViews.add(listView3);

//        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//                mDropDownMenu.setTabText(citys[position]);
//                mDropDownMenu.closeMenu();
//            }
//        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mDropDownMenu.setTabText(times[position]);
                mDropDownMenu.closeMenu();
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mDropDownMenu.setTabText(price[position]);
                mDropDownMenu.closeMenu();
            }
        });


        //这里添加 内容显示区域,可以是任何布局
        TextView contentView = new TextView(this);
        contentView.setText("这里是内容区域");
        contentView.setTextSize(20);
        contentView.setGravity(Gravity.CENTER);


        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,contentView );

    }
}

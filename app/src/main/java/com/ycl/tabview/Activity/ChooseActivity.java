package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Adapter.ListViewAdapter;
import com.ycl.tabview.Adapter.MenuListAdapter;
import com.ycl.tabview.Adapter.MyAdapter;
import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.R;
import com.ycl.tabview.View.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseActivity extends Activity implements MyAdapter.OnRecycleItemClick{
    private TextView secondTxt;
    private List<Exam> mData = new ArrayList<>();
    private ListViewAdapter mAdapter;
    //菜单标题
    private String headers[] = {"时间", "价格"};
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
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
        listView2 = new ListView(ChooseActivity.this);
        listView3 = new ListView(ChooseActivity.this);

        listView2.setDividerHeight(0);
        listView3.setDividerHeight(0);

        mMenuAdapter2 = new MenuListAdapter(ChooseActivity.this, Arrays.asList(times));
        mMenuAdapter3 = new MenuListAdapter(ChooseActivity.this, Arrays.asList(price));

        listView2.setAdapter(mMenuAdapter2);
        listView3.setAdapter(mMenuAdapter3);

        popupViews.add(listView2);
        popupViews.add(listView3);


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
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for(int i=0;i<2;i++){
            Exam bean = new Exam();
            bean.setExam_name("数据库"+i);
            bean.setExam_date("2017-3-21");
            bean.setExam_prices("100");
            bean.setExam_school("计算");
            mData.add(bean);
        }
        this.mAdapter = new ListViewAdapter(this,mData);
        ListView listView = new ListView(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ChooseActivity.this,"click",Toast.LENGTH_LONG).show();
            }
        });
        listView.setAdapter(mAdapter);
        linearLayout.addView(listView);

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,linearLayout );

    }

    public void back(View view) {
        this.finish();
    }

    @Override
    public void onItemClick(View view, Object object) {
        Intent intent = new Intent(ChooseActivity.this, GoodsActivity.class);
        startActivity(intent);
        // Toast.makeText(view.getContext(),((MyItemBean)object).exam_name,Toast.LENGTH_LONG).show();
    }
}

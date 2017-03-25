package com.ycl.tabview.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

import com.ycl.tabview.R;

/**
 * Created by Administrator on 2017/3/24.
 */

public class GoodsActivity extends Activity {

    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_activity);
        mTabHost = (TabHost) findViewById(R.id.tabHost);
        mTabHost.setup();// init
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("考试详情").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("发布者信息").setContent(R.id.tab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("留言").setContent(R.id.tab3));

    }
}

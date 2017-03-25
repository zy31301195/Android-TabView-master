package com.ycl.tabview.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.ycl.tabview.R;
import com.ycl.tabview.View.SelectLogoutWindow;

/**
 * Created by Administrator on 2017/3/23.
 */

public class SettingActivity extends Activity{
    private RelativeLayout logot;
    private RelativeLayout version;
    private  RelativeLayout update_pwd;
    private SelectLogoutWindow menuWindow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        logot = (RelativeLayout) findViewById(R.id.logout);
        version = (RelativeLayout) findViewById(R.id.versions);
        update_pwd = (RelativeLayout) findViewById(R.id.update_pwd);

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder version = new AlertDialog.Builder(SettingActivity.this);
                version.setTitle("版本信息");
                version.setMessage("版本号:"+getVersion());
                version.setPositiveButton("OK",null);
                version.create().show();
            }
        });
        logot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化SelectPicPopupWindow
                menuWindow = new SelectLogoutWindow(SettingActivity.this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(SettingActivity.this.findViewById(R.id.logout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                //设置layout在PopupWindow中显示的位置
            }
        });

        update_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,UpdateUserActivity.class);
                startActivity(intent);
            }
        });

    }


    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_exit:
                    Intent intent=new Intent(SettingActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.cancel:
                    break;

            }

        }

    };


    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return  version;
        } catch (Exception e) {
            e.printStackTrace();
            return "没有发现版本信息";
        }
    }

    public void back(View view) {
        this.finish();
    }
}

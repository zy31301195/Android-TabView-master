package com.ycl.tabview.Activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ycl.tabview.R;

import static com.ycl.tabview.Activity.RegisterActivity.KEY_SEARCH_HISTORY_KEYWORD;


/**
 * Created by Administrator on 2017/3/18.
 */

public class LoginActivity extends Activity {
    private Button btn_login;
    private TextView btn_register1;
    private EditText tel;
    private EditText pwd;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mPref =  getSharedPreferences("register", Activity.MODE_PRIVATE);
        mEditor = mPref.edit();
        pwd = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register1 = (TextView) findViewById(R.id.register);
        tel = (EditText) findViewById(R.id.et_usertel);
        pwd = (EditText) findViewById(R.id.et_password);
        String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD,"");
        if (!TextUtils.isEmpty(history)){
            tel.setText(history);
        }

        btn_login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, tel.getText().toString());
                mEditor.commit();
                startActivity(intent);
            }
        });

        btn_register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

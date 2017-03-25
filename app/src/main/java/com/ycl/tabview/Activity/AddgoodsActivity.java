package com.ycl.tabview.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.ycl.tabview.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/25.
 */

public class AddgoodsActivity extends Activity {
    private RelativeLayout dates;
    private RelativeLayout times;
    private EditText goods_date;
    private EditText goods_time;
    private Button button;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    StringBuilder stringBuilder = new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgoods);
        dates = (RelativeLayout) findViewById(R.id.dates);
        times = (RelativeLayout) findViewById(R.id.times);
        goods_date = (EditText) findViewById(R.id.goods_date);
        goods_time = (EditText) findViewById(R.id.goods_time);
        button = (Button) findViewById(R.id.btn_add);
        Calendar calendar =Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddgoodsActivity.this,DateListener,year,month,day);
                datePickerDialog.show();

            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddgoodsActivity.this,TimeListener,hour,minute,true);
                timePickerDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddgoodsActivity.this, MyGoodsActivity.class);
                startActivity(intent);
            }
        });

    }

    private DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int myear, int mmonth, int dayOfMonth) {
            year = myear;
            month = mmonth;
            day = dayOfMonth;
            goods_date.setText(year+"年"+(month+1)+"月"+day+"日");
        }
    };

    private TimePickerDialog.OnTimeSetListener TimeListener = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int mminute) {
            hour = hourOfDay;
            minute = mminute;
            goods_time.setText(hour+":"+minute);

        }

    };
}

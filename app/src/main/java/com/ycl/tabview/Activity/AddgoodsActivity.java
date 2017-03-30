package com.ycl.tabview.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ycl.tabview.R;

import java.util.Calendar;
import java.util.Date;

import static com.ycl.tabview.Activity.RegisterActivity.school_name;

/**
 * Created by Administrator on 2017/3/25.
 */

public class AddgoodsActivity extends Activity {
    private TextView goods_date;
    private TextView goods_time;
    private TextView goods_school;
    private Button button;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgoods);
        Calendar calendar =Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        initView();

    }

    private void initView(){
        goods_date = (TextView) findViewById(R.id.goods_date);
        goods_time = (TextView) findViewById(R.id.goods_time);
        goods_school = (TextView) findViewById(R.id.goods_school);
        button = (Button) findViewById(R.id.btn_add);
        goods_date.setOnClickListener(new DateButtonClickListener());
        goods_time.setOnClickListener(new TimeButtonClickListener());
        button.setOnClickListener(new ButtonClickListener());
        goods_school.setOnClickListener(new SchoolButtonClickListener());
    }

    private final class DateButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddgoodsActivity.this,DateListener,year,month,day);
            datePickerDialog.show();

        }
    }

    private final class TimeButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddgoodsActivity.this,TimeListener,hour,minute,true);
            timePickerDialog.show();
        }
    }

    private final class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AddgoodsActivity.this, MyGoodsActivity.class);
            startActivity(intent);
        }
    }

    private final class SchoolButtonClickListener implements View.OnClickListener {
        int index = 0 ;
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(AddgoodsActivity.this);
            builder.setTitle("请选择所属分院");
            builder.setSingleChoiceItems(school_name,0,new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which)
                {
                    index = which;
                }
            });

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    goods_school.setText(school_name[index]);

                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                }
            });
            builder.show();

        }
    }

    private DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int myear, int mmonth, int dayOfMonth) {
            year = myear;
            month = mmonth;
            day = dayOfMonth;
            goods_date.setText(year+"-"+(month+1)+"-"+day);
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



    public void back(View view) {
        this.finish();
    }
}

package com.ycl.tabview.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.R;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.LoginBeanTest;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

import static com.ycl.tabview.Activity.RegisterActivity.school_name;

/**
 * Created by Administrator on 2017/3/25.
 */

public class AddgoodsActivity extends Activity {
    private TextView goods_date;
    private TextView goods_time;
    private TextView goods_endtime;
    private TextView goods_school;
    private EditText goods_name;
    private EditText goods_place;
    private EditText goods_price;
    private Button button;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private Myapplication mMyapplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgoods);
        Calendar calendar =Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        mMyapplication= (Myapplication) getApplication();
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
        goods_endtime = (TextView) findViewById(R.id.goods_endtime);
        goods_school = (TextView) findViewById(R.id.goods_school);
        goods_name = (EditText) findViewById(R.id.goods_name);
        goods_place = (EditText) findViewById(R.id.goods_place);
        goods_price = (EditText) findViewById(R.id.goods_price);
        button = (Button) findViewById(R.id.btn_add);
        goods_date.setOnClickListener(new DateButtonClickListener());
        goods_time.setOnClickListener(new TimeButtonClickListener());
        goods_endtime.setOnClickListener(new TimeButtonClickListener2());
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

    private final class TimeButtonClickListener2 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddgoodsActivity.this,TimeListener2,hour,minute,true);
            timePickerDialog.show();
        }
    }

    private final class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String datetime = simpleDateFormat.format(new Date());
            Exam exam = new Exam();
            exam.setExam_name(goods_name.getText().toString());
            exam.setExam_place(goods_place.getText().toString());
            exam.setExam_date(goods_date.getText().toString());
            exam.setExam_time(goods_time.getText().toString());
            exam.setExam_endtime(goods_endtime.getText().toString());
            exam.setExam_school(goods_school.getText().toString());
            exam.setExam_prices(goods_price.getText().toString());
            exam.setExam_user_id(mMyapplication.users.getUser_id());
            exam.setExam_createtime(datetime);
            Map<String, Object> map = exam.createCommitParams();


            Retrofitutil.getmRetrofit().create(LoginHttps.class).addExamsJson(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subject<LoginBeanTest>() {
                        @Override
                        public boolean hasObservers() {
                            return false;
                        }

                        @Override
                        public boolean hasThrowable() {
                            return false;
                        }

                        @Override
                        public boolean hasComplete() {
                            return false;
                        }

                        @Override
                        public Throwable getThrowable() {
                            return null;
                        }

                        @Override
                        protected void subscribeActual(Observer<? super LoginBeanTest> observer) {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBeanTest s) {
                            if(s.getUser().equals("addExamOk")){
                                Toast.makeText(AddgoodsActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                                AddgoodsActivity.this.finish();
                            }
                            else{
                                Toast.makeText(AddgoodsActivity.this,"考试将在三天内进行，无法进行调配",Toast.LENGTH_LONG).show();
                                AddgoodsActivity.this.finish();
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(AddgoodsActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                            // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                        }
                    });
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
            String line1 ="-";
            String line2 = "-";
            if(month<9 ) {
                line1="-0";
            }
            if (day<10){
                line2 = "-0";
            }
            goods_date.setText(year+line1+(month+1)+line2+day);

        }
    };

    private TimePickerDialog.OnTimeSetListener TimeListener = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int mminute) {
            hour = hourOfDay;
            minute = mminute;
            String line1 ="";
            String line2 =":";
            if(hour<10)
                line1 = "0";
            if(minute<10)
                line2 = ":0";
            goods_time.setText(line1+hour+line2+minute);

        }

    };

    private TimePickerDialog.OnTimeSetListener TimeListener2 = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int mminute) {
            hour = hourOfDay;
            minute = mminute;
            String line1 ="";
            String line2 =":";
            if(hour<10)
                line1 = "0";
            if(minute<10)
                line2 = ":0";
            goods_endtime.setText(line1+hour+line2+minute);

        }

    };



    public void back(View view) {
        this.finish();
    }
}

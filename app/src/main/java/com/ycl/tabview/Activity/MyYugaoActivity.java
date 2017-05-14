package com.ycl.tabview.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Adapter.ListViewAdapter;
import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.R;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.ExamBean;
import com.ycl.tabview.httpBean.LoginBeanTest;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2017/5/7.
 */

public class MyYugaoActivity extends Activity {
    private ListView mListView;
    private List<Exam> mData = new ArrayList<>();
    private ListViewAdapter mAdapter;
    private TextView add;
    private TextView title;
    private int item_position=0;
    private Myapplication mMyapplication;
    private static final int ITEM1 = Menu.FIRST;//菜单选项id
    private static final int ITEM2 = Menu.FIRST+1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygoods_activivty);
        mMyapplication = (Myapplication) getApplication();
        initView();
        initData();
        mAdapter = new ListViewAdapter(MyYugaoActivity.this,mData);
        mListView.setAdapter(mAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MyYugaoActivity.this,TodayActivity.class);
                intent.putExtra("examId",mData.get(position).getExam_id());
                intent.putExtra("userId",mData.get(position).getExam_user_id());
                intent.putExtra("state",-1);
                startActivity(intent);
            }
        });
        this.mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                mListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        //添加菜单项
                        menu.add(0,ITEM1,0,"修改");
                        menu.add(0,ITEM2,0,"删除");
                        item_position = position;
                    }
                });
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.mygoods_list);
        add = (TextView) findViewById(R.id.add);
        add.setOnClickListener(new MyYugaoActivity.AddButtonClickListener());
        title = (TextView) findViewById(R.id.tv_title);
        title.setText("发布中的考试");

    }

    private void initData(){

        Retrofitutil.getmRetrofit().create(LoginHttps.class).MyYugaoJson(mMyapplication.users.getUser_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<ExamBean>() {
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
                    protected void subscribeActual(Observer<? super ExamBean> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExamBean s) {
                        mData.clear();
                        mData.addAll(s.getList());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MyYugaoActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void updateData(Map map){

        Retrofitutil.getmRetrofit().create(LoginHttps.class).updateExamJson(map)
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
                       if (s.getUser().equals("delete")){
                           Toast.makeText(MyYugaoActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                       }
//                        else if (s.getUser().equals("update")){
//
//                       }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MyYugaoActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                    }
                });
    }

    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case ITEM1:
                Intent intent=new Intent(MyYugaoActivity.this,UpdateExamActivity.class);
                intent.putExtra("examId",mData.get(item_position).getExam_id());
                intent.putExtra("examName",mData.get(item_position).getExam_name());
                intent.putExtra("examDate",mData.get(item_position).getExam_date());
                intent.putExtra("examTime",mData.get(item_position).getExam_time());
                intent.putExtra("examEndtime",mData.get(item_position).getExam_endtime());
                intent.putExtra("examPlace",mData.get(item_position).getExam_place());
                intent.putExtra("examUserId",mData.get(item_position).getExam_user_id());
                intent.putExtra("examPrices",mData.get(item_position).getExam_prices());
                intent.putExtra("examSchool",mData.get(item_position).getExam_school());
                intent.putExtra("examCreatetime",mData.get(item_position).getExam_createtime());
                startActivity(intent);
                break;
            case ITEM2:
                AlertDialog.Builder normalDia = new AlertDialog.Builder(MyYugaoActivity.this);
                normalDia.setTitle("提示");
                normalDia.setMessage("是否确定删除?");
                normalDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除的操作
                        Exam exam = new Exam();
                        exam.setExam_id(mData.get(item_position).getExam_id());
                        exam.setExam_name(mData.get(item_position).getExam_name().toString());
                        exam.setExam_place(mData.get(item_position).getExam_place().toString());
                        exam.setExam_date(mData.get(item_position).getExam_date());
                        exam.setExam_time(mData.get(item_position).getExam_time());
                        exam.setExam_endtime(mData.get(item_position).getExam_endtime());
                        exam.setExam_school(mData.get(item_position).getExam_school().toString());
                        exam.setExam_prices(mData.get(item_position).getExam_prices());
                        exam.setExam_user_id(mData.get(item_position).getExam_user_id());
                        exam.setExam_createtime(mData.get(item_position).getExam_createtime());
                        exam.setExam_state(1);
                        Map<String, Object> map = exam.createCommitParam();
                        mData.remove(item_position);
                        mAdapter.notifyDataSetChanged();
                        updateData(map);
                    }
                });
                normalDia.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                normalDia.create().show();
                //Toast.makeText(MyYugaoActivity.this,"已删除",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }


    private final class AddButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MyYugaoActivity.this, AddgoodsActivity.class);
            startActivity(intent);
        }
    }

    public void back(View view) {
        this.finish();
    }
}

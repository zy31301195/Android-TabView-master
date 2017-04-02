package com.ycl.tabview.Activity;

import android.app.Activity;
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
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.ExamBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ycl.tabview.Activity.LoginActivity.users;
import static com.ycl.tabview.http.LoginHttps.API_BASE_URL;

/**
 * Created by Administrator on 2017/3/25.
 */

public class MyGoodsActivity extends Activity{
    private ListView mListView;
    private TextView add;
    private List<Exam> mData = new ArrayList<>();
    private ListViewAdapter mAdapter;
    private static final int ITEM1 = Menu.FIRST;//菜单选项id
    private static final int ITEM2 = Menu.FIRST+1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygoods_activivty);
        initView();
        initData();
        mAdapter = new ListViewAdapter(MyGoodsActivity.this,mData);
        mListView.setAdapter(mAdapter);



        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyGoodsActivity.this,mData.get(position).getExam_name(),Toast.LENGTH_LONG).show();
            }
        });
        this.mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        //添加菜单项
                        menu.add(0,ITEM1,0,"修改");
                        menu.add(0,ITEM2,0,"删除");
                    }
                });
                return false;
            }
        });
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.mygoods_list);
        add = (TextView) findViewById(R.id.add);
        add.setOnClickListener(new AddButtonClickListener());

    }

    private void initData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(LoginHttps.class).getJson(users.getUser_id())
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
                        mData = s.getList();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MyGoodsActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
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
                Intent intent=new Intent(MyGoodsActivity.this,UpdateUserActivity.class);
                startActivity(intent);
                break;
            case ITEM2:
                Toast.makeText(MyGoodsActivity.this,"已删除",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    private final class AddButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MyGoodsActivity.this, AddgoodsActivity.class);
            startActivity(intent);
        }
    }

    public void back(View view) {
        this.finish();
    }

}

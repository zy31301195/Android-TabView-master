package com.ycl.tabview.Fragement;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.ycl.tabview.Activity.GoodsActivity;
import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.R;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.ExamBean;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/18.
 */

public class OrderFragment extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    private static OrderFragment orderFragment = new OrderFragment();
    private WeekView mWeekView;
    private int ranColor;
    private Myapplication mMyapplication;
    private List<Exam> mData = new ArrayList<>();
    private List<WeekViewEvent> mEvents = new ArrayList<>();

    public static OrderFragment newInstance() {
        return orderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) view.findViewById(R.id.weekView);
        mMyapplication = (Myapplication) getActivity().getApplication();

        mWeekView.setOnEventClickListener(this);

        mWeekView.setMonthChangeListener(this);

        Retrofitutil.getmRetrofit().create(LoginHttps.class).completeJson(mMyapplication.users.getUser_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ExamBean>() {
                    @Override
                    public void accept(@NonNull ExamBean s) throws Exception {
                        mData.clear();
                        mData.addAll(s.getList());
                        mEvents.clear();
                        for (int i = 0; i < mData.size(); i++) {
                            // 随机颜色
                            Random random = new Random();
                            ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                            Calendar startTime = Calendar.getInstance();
                            startTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(mData.get(i).getExam_time().split(":")[0]));
                            startTime.set(Calendar.MINUTE, Integer.valueOf(mData.get(i).getExam_time().split(":")[1]));
                            startTime.set(Calendar.DATE, Integer.valueOf(mData.get(i).getExam_date().split("-")[2]));
                            startTime.set(Calendar.MONTH, Integer.valueOf(mData.get(i).getExam_date().split("-")[1])-1);
                            startTime.set(Calendar.YEAR, Integer.valueOf(mData.get(i).getExam_date().split("-")[0]));
                            Calendar endTime = (Calendar) startTime.clone();
                            endTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(mData.get(i).getExam_endtime().split(":")[0]));
                            endTime.set(Calendar.MINUTE, Integer.valueOf(mData.get(i).getExam_endtime().split(":")[1]));
                            WeekViewEvent event = new WeekViewEvent(i, getEventTitle(startTime), startTime, endTime);
                            event.setColor(ranColor);
                            mEvents.add(event);
                        }
                        mWeekView.notifyDatasetChanged();
                    }
                });
        return view;
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(final int newYear, final int newMonth) {
        final List<WeekViewEvent> events = new ArrayList<>();
        for (WeekViewEvent event : mEvents) {
            if (event.getStartTime().get(Calendar.MONTH) +1 == newMonth
                    && event.getStartTime().get(Calendar.YEAR) == newYear){
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {

    }


    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Intent intent = new Intent(getContext(), GoodsActivity.class);
        startActivity(intent);

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }
}

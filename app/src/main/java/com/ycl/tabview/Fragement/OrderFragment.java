package com.ycl.tabview.Fragement;

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
import com.ycl.tabview.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */

public class OrderFragment extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener{

    private static OrderFragment orderFragment =new OrderFragment();
    private WeekView mWeekView;
    public static OrderFragment newInstance(){
        return orderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.order_fragment,container,false);
        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) view.findViewById(R.id.weekView);

// Set an action when any event is clicked.
//        mWeekView.setOnEventClickListener(mEventClickListener);

// The week view has infinite scrolling horizontally. We have to provide the events of a
// month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

// Set long press listener for events.
//        mWeekView.setEventLongPressListener(mEventLongPressListener);
        return view;
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        WeekViewEvent event;

        Calendar startTime = Calendar.getInstance();
        Calendar endTime = (Calendar) startTime.clone();
        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.colorPrimary));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 4);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.colorAccent));
        events.add(event);
        return events;
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {

    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }
}

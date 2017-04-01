package com.ycl.tabview.httpBean;

import com.google.gson.annotations.SerializedName;
import com.ycl.tabview.Bean.Exam;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ExamBean {
    @SerializedName("exams")
    private List<Exam> list;

    public List<Exam> getList() {
        return list;
    }

    public void setList(List<Exam> list) {
        this.list = list;
    }
}

package com.ycl.tabview.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 */

public class Exam {
    private int exam_id;//考试id
    private String exam_name;//考试课程
    private String exam_date;//考试日期
    private String exam_time;//考试时间
    private String exam_endtime;//开始结束时间
    private String exam_place;//考试地点
    private int exam_user_id;//发布者id
    private String exam_prices;//初始价
    private String exam_newprice;//现价
    private String exam_school;//所属分院
    private int exam_state;//状态
    private String exam_createtime;//创建日期


    public String getExam_newprice() {
        return exam_newprice;
    }

    public void setExam_newprice(String exam_newprice) {
        this.exam_newprice = exam_newprice;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getExam_place() {
        return exam_place;
    }

    public void setExam_place(String exam_place) {
        this.exam_place = exam_place;
    }

    public int getExam_user_id() {
        return exam_user_id;
    }

    public void setExam_user_id(int exam_user_id) {
        this.exam_user_id = exam_user_id;
    }

    public String getExam_prices() {
        return exam_prices;
    }

    public void setExam_prices(String exam_prices) {
        this.exam_prices = exam_prices;
    }

    public String getExam_school() {
        return exam_school;
    }

    public void setExam_school(String exam_school) {
        this.exam_school = exam_school;
    }

    public int getExam_state() {
        return exam_state;
    }

    public void setExam_state(int exam_state) {
        this.exam_state = exam_state;
    }

    public String getExam_createtime() {
        return exam_createtime;
    }

    public void setExam_createtime(String exam_createtime) {
        this.exam_createtime = exam_createtime;
    }

    public String getExam_endtime() {
        return exam_endtime;
    }

    public void setExam_endtime(String exam_endtime) {
        this.exam_endtime = exam_endtime;
    }

    public Map<String, Object> createCommitParams(){
        Map<String, Object> params = new HashMap<>();
        params.put("exam_name", this.getExam_name());
        params.put("exam_date", this.getExam_date());
        params.put("exam_endtime", this.getExam_endtime());
        params.put("exam_time", this.getExam_time());
        params.put("exam_place", this.getExam_place());
        params.put("exam_user_id", this.getExam_user_id());
        params.put("exam_prices", this.getExam_prices());
        params.put("exam_school", this.getExam_school());
        params.put("exam_createtime", this.getExam_createtime());
        return params;
    }

    public Map<String, Object> createCommitParam(){
        Map<String, Object> params = new HashMap<>();
        params.put("exam_id",this.getExam_id());
        params.put("exam_name", this.getExam_name());
        params.put("exam_date", this.getExam_date());
        params.put("exam_endtime", this.getExam_endtime());
        params.put("exam_time", this.getExam_time());
        params.put("exam_place", this.getExam_place());
        params.put("exam_user_id", this.getExam_user_id());
        params.put("exam_prices", this.getExam_prices());
        params.put("exam_school", this.getExam_school());
        params.put("exam_createtime", this.getExam_createtime());
        params.put("exam_state",this.getExam_state());
        return params;
    }
}

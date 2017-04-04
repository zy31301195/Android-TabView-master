package com.ycl.tabview.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 */

public class Record {
    private int record_id;//记录id
    private String record_price;//出价价格
    private String reocrd_time;//起拍时间
    private int exam_id;//考试id
    private int buyer_id;//买家id

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getRecord_price() {
        return record_price;
    }

    public void setRecord_price(String record_price) {
        this.record_price = record_price;
    }

    public String getReocrd_time() {
        return reocrd_time;
    }

    public void setReocrd_time(String reocrd_time) {
        this.reocrd_time = reocrd_time;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public Map<String, Object> createCommitParams(){
        Map<String, Object> params = new HashMap<>();
        params.put("record_price", this.getRecord_price());
        params.put("record_time", this.getReocrd_time());
        params.put("exam_id", this.getExam_id());
        params.put("buyer_id", this.getBuyer_id());

        return params;
    }
}

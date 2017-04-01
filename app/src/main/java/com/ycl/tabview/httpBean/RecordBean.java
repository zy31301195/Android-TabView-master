package com.ycl.tabview.httpBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class RecordBean {

    /**
     * exam : {"exam_id":1,"exam_name":"安卓","exam_date":"2017-2-28","exam_time":"8:30","exam_place":"lisi-324","exam_user_id":9,"exam_prices":"100","exam_school":"计算","exam_state":0}
     * allMessage : [{"message_id":3,"message_content":"好好","message_time":"2017-2-22","user_id":9,"exam_id":1}]
     * allRecord : [{"record_id":1,"record_price":"50","reocrd_time":"2017-3-21","exam_id":1,"buyer_id":1}]
     * user : {"user_id":9,"user_name":"张瑜","user_pwd":"111","user_tel":"13773781146"}
     */

    private ExamBean exam;
    private UserBean user;
    private List<AllMessageBean> allMessage;
    private List<AllRecordBean> allRecord;

    public ExamBean getExam() {
        return exam;
    }

    public void setExam(ExamBean exam) {
        this.exam = exam;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<AllMessageBean> getAllMessage() {
        return allMessage;
    }

    public void setAllMessage(List<AllMessageBean> allMessage) {
        this.allMessage = allMessage;
    }

    public List<AllRecordBean> getAllRecord() {
        return allRecord;
    }

    public void setAllRecord(List<AllRecordBean> allRecord) {
        this.allRecord = allRecord;
    }

    public static class ExamBean {
        /**
         * exam_id : 1
         * exam_name : 安卓
         * exam_date : 2017-2-28
         * exam_time : 8:30
         * exam_place : lisi-324
         * exam_user_id : 9
         * exam_prices : 100
         * exam_school : 计算
         * exam_state : 0
         */

        private int exam_id;
        private String exam_name;
        private String exam_date;
        private String exam_time;
        private String exam_place;
        private int exam_user_id;
        private String exam_prices;
        private String exam_school;
        private int exam_state;

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
    }

    public static class UserBean {
        /**
         * user_id : 9
         * user_name : 张瑜
         * user_pwd : 111
         * user_tel : 13773781146
         */

        private int user_id;
        private String user_name;
        private String user_pwd;
        private String user_tel;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_pwd() {
            return user_pwd;
        }

        public void setUser_pwd(String user_pwd) {
            this.user_pwd = user_pwd;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }
    }

    public static class AllMessageBean {
        /**
         * message_id : 3
         * message_content : 好好
         * message_time : 2017-2-22
         * user_id : 9
         * exam_id : 1
         */

        private int message_id;
        private String message_content;
        private String message_time;
        private int user_id;
        private int exam_id;

        public int getMessage_id() {
            return message_id;
        }

        public void setMessage_id(int message_id) {
            this.message_id = message_id;
        }

        public String getMessage_content() {
            return message_content;
        }

        public void setMessage_content(String message_content) {
            this.message_content = message_content;
        }

        public String getMessage_time() {
            return message_time;
        }

        public void setMessage_time(String message_time) {
            this.message_time = message_time;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getExam_id() {
            return exam_id;
        }

        public void setExam_id(int exam_id) {
            this.exam_id = exam_id;
        }
    }

    public static class AllRecordBean {
        /**
         * record_id : 1
         * record_price : 50
         * reocrd_time : 2017-3-21
         * exam_id : 1
         * buyer_id : 1
         */

        private int record_id;
        private String record_price;
        private String reocrd_time;
        private int exam_id;
        private int buyer_id;

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
    }
}

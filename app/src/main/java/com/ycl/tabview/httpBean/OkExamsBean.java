package com.ycl.tabview.httpBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class OkExamsBean {

    /**
     * exam : {"exam_id":4,"exam_name":"微积分","exam_date":"2017-04-21","exam_time":"8:30","exam_endtime":"10:30","exam_place":"理四-108","exam_user_id":1,"exam_prices":"100","exam_newprice":"98","exam_school":"计算"}
     * allRecord : [{"record_id":3,"record_price":"98","record_time":"2017-04-04 15:44:11","exam_id":4,"buyer_id":1,"user_name":"admin"},{"record_id":2,"record_price":"99","record_time":"2017-04-04 14:40:26","exam_id":4,"buyer_id":9,"user_name":"张瑜"}]
     * user : {"user_id":1,"user_name":"admin","user_pwd":"888","user_tel":"15168282630","user_sex":"女","user_zgid":"31301190","user_school":"计算机","user_sign":"双色球"}
     */

    private ExamBean exam;
    private UserBean user;
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

    public List<AllRecordBean> getAllRecord() {
        return allRecord;
    }

    public void setAllRecord(List<AllRecordBean> allRecord) {
        this.allRecord = allRecord;
    }

    public static class ExamBean {
        /**
         * exam_id : 4
         * exam_name : 微积分
         * exam_date : 2017-04-21
         * exam_time : 8:30
         * exam_endtime : 10:30
         * exam_place : 理四-108
         * exam_user_id : 1
         * exam_prices : 100
         * exam_newprice : 98
         * exam_school : 计算
         */

        private int exam_id;
        private String exam_name;
        private String exam_date;
        private String exam_time;
        private String exam_endtime;
        private String exam_place;
        private int exam_user_id;
        private String exam_prices;
        private String exam_newprice;
        private String exam_school;

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

        public String getExam_endtime() {
            return exam_endtime;
        }

        public void setExam_endtime(String exam_endtime) {
            this.exam_endtime = exam_endtime;
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

        public String getExam_newprice() {
            return exam_newprice;
        }

        public void setExam_newprice(String exam_newprice) {
            this.exam_newprice = exam_newprice;
        }

        public String getExam_school() {
            return exam_school;
        }

        public void setExam_school(String exam_school) {
            this.exam_school = exam_school;
        }
    }

    public static class UserBean {
        /**
         * user_id : 1
         * user_name : admin
         * user_pwd : 888
         * user_tel : 15168282630
         * user_sex : 女
         * user_zgid : 31301190
         * user_school : 计算机
         * user_sign : 双色球
         */

        private int user_id;
        private String user_name;
        private String user_pwd;
        private String user_tel;
        private String user_sex;
        private String user_zgid;
        private String user_school;
        private String user_sign;

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

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_zgid() {
            return user_zgid;
        }

        public void setUser_zgid(String user_zgid) {
            this.user_zgid = user_zgid;
        }

        public String getUser_school() {
            return user_school;
        }

        public void setUser_school(String user_school) {
            this.user_school = user_school;
        }

        public String getUser_sign() {
            return user_sign;
        }

        public void setUser_sign(String user_sign) {
            this.user_sign = user_sign;
        }
    }

    public static class AllRecordBean {
        /**
         * record_id : 3
         * record_price : 98
         * record_time : 2017-04-04 15:44:11
         * exam_id : 4
         * buyer_id : 1
         * user_name : admin
         */

        private int record_id;
        private String record_price;
        private String record_time;
        private int exam_id;
        private int buyer_id;
        private String user_name;

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

        public String getRecord_time() {
            return record_time;
        }

        public void setRecord_time(String record_time) {
            this.record_time = record_time;
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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}

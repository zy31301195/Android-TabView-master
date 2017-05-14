package com.ycl.tabview.httpBean;

/**
 * Created by Administrator on 2017/5/7.
 */

public class YugaoBean {

    /**
     * exam : {"exam_id":10,"exam_name":"急急急","exam_date":"2017-04-06","exam_time":"15:29","exam_endtime":"17:29","exam_place":"理四424","exam_user_id":9,"exam_prices":"77","exam_newprice":"77","exam_school":"商学院","exam_state":0}
     * user : {"user_id":9,"user_name":"张瑜","user_pwd":"111","user_tel":"13773781146","user_sex":"女","user_zgid":"31301195","user_school":"计算","user_sign":"hhh"}
     */

    private ExamBean exam;
    private UserBean user;

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

    public static class ExamBean {
        /**
         * exam_id : 10
         * exam_name : 急急急
         * exam_date : 2017-04-06
         * exam_time : 15:29
         * exam_endtime : 17:29
         * exam_place : 理四424
         * exam_user_id : 9
         * exam_prices : 77
         * exam_newprice : 77
         * exam_school : 商学院
         * exam_state : 0
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
        private int exam_state;
        private String exam_createtime;//创建日期

        public String getExam_createtime() {
            return exam_createtime;
        }

        public void setExam_createtime(String exam_createtime) {
            this.exam_createtime = exam_createtime;
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
         * user_sex : 女
         * user_zgid : 31301195
         * user_school : 计算
         * user_sign : hhh
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
}

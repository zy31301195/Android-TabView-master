package com.ycl.tabview.httpBean;

/**
 * Created by Administrator on 2017/4/2.
 */

public class LoginBean {

    /**
     * take : 1
     * lose : 0
     * my : 1
     * user : 3
     * users : {"user_id":9,"user_name":"张瑜","user_pwd":"111","user_tel":"13773781146","user_sex":"女","user_zgid":"31301195","user_school":"计算","user_sign":"hhh","user_date":"2017-05-10"}
     */

    private int take;
    private int lose;
    private int my;
    private String user;
    private UsersBean users;

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getMy() {
        return my;
    }

    public void setMy(int my) {
        this.my = my;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UsersBean getUsers() {
        return users;
    }

    public void setUsers(UsersBean users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * user_id : 9
         * user_name : 张瑜
         * user_pwd : 111
         * user_tel : 13773781146
         * user_sex : 女
         * user_zgid : 31301195
         * user_school : 计算
         * user_sign : hhh
         * user_date : 2017-05-10
         */

        private int user_id;
        private String user_name;
        private String user_pwd;
        private String user_tel;
        private String user_sex;
        private String user_zgid;
        private String user_school;
        private String user_sign;
        private String user_date;

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

        public String getUser_date() {
            return user_date;
        }

        public void setUser_date(String user_date) {
            this.user_date = user_date;
        }
    }
}

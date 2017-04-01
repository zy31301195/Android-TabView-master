package com.ycl.tabview.httpBean;

/**
 * Created by Administrator on 2017/4/1.
 */

public class UserBean {

    /**
     * users : {"user_id":1,"user_name":"admin","user_pwd":"123","user_tel":"15168282630","user_sex":"女","user_zgid":"31301195","user_school":"计算机","user_sign":"你好"}
     */

    private UsersBean users;

    public UsersBean getUsers() {
        return users;
    }

    public void setUsers(UsersBean users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * user_id : 1
         * user_name : admin
         * user_pwd : 123
         * user_tel : 15168282630
         * user_sex : 女
         * user_zgid : 31301195
         * user_school : 计算机
         * user_sign : 你好
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

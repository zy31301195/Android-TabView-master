package com.ycl.tabview.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27.
 */

public class Users {
    private int user_id;//编号
    private String user_name;//用户名
    private String user_pwd;//密码
    private String user_tel;//电话号码
    private String user_sex;//用户性别
    private String user_zgid;//职工号
    private String user_school;//所在分院
    private String user_sign;//签名

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

    public Map<String, String> createCommitParams(){
        Map<String, String> params = new HashMap<>();
        params.put("tel", this.getUser_tel());
        params.put("pwd", this.getUser_pwd());
        params.put("name", this.getUser_name());
        params.put("sex", this.getUser_sex());
        params.put("zgid", this.getUser_zgid());
        params.put("school", this.getUser_school());
        return params;
    }
}

package com.model;

/**
 * @Description TODO
 * @Classname User
 * @Author Kehao Liu
 * @Date 2020/7/23 10:01 下午
 * @Version V1.0
 */
public class User {
    private String username; //用户名
    private String pwd; //密码

    public User(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}

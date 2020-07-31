package com.model;

/**
 * @Description TODO
 * @Classname Card
 * @Author Kehao Liu
 * @Date 2020/7/23 10:01 下午
 * @Version V1.0
 */
public class Card {
    private String name;
    private String job;
    private String mobile;
    private String company;
    private String address;

    public Card(String name, String job, String mobile, String company, String address) {
        this.name = name;
        this.job = job;
        this.mobile = mobile;
        this.company = company;
        this.address = address;
    }

    public Card() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

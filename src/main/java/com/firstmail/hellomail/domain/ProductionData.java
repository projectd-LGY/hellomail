package com.firstmail.hellomail.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

//同于存储生产数据
@Entity//注解表示这是一个实体类
@Table(name="production_data")//表示这个类对应的表名
@Proxy(lazy = false)
public class ProductionData {

    @Id//id表明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表明自增长方式
    @Column(name="id")//表明对应数据库字段名
    int id;

    private int userid;

    private String data1;

    private String data2;

    private String data3;

    private String clientmail;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getClientmail() {
        return clientmail;
    }

    public void setClientmail(String clientmail) {
        this.clientmail = clientmail;
    }

    @Override
    public String toString() {
        return "ProductionData{" +
                "id=" + id +
                ", userid=" + userid +
                ", data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", data3='" + data3 + '\'' +
                ", clientmail='" + clientmail + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userId) {
        this.userid = userId;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }
}

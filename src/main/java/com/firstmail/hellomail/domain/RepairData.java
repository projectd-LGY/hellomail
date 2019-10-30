package com.firstmail.hellomail.domain;


import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity//注解表示这是一个实体类
@Table(name="repair_data")//表示这个类对应的表名
@Proxy(lazy = false)
public class RepairData {

    @Id//id表明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表明自增长方式
    @Column(name="id")//表明对应数据库字段名
    int id;

    private int userid;//对应公司名称

    private Date createdate;//创建日期

    private String clientmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getClientmail() {
        return clientmail;
    }

    public void setClientmail(String clientmail) {
        this.clientmail = clientmail;
    }

    @Override
    public String toString() {
        return "RepairData{" +
                "id=" + id +
                ", userid=" + userid +
                ", createdate=" + createdate +
                ", clientmail='" + clientmail + '\'' +
                '}';
    }
}

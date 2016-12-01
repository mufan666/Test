package com.mufan.bus.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by aswe on 2016/7/23.
 */
@Entity
public class SubGroup implements Serializable {
    private Integer id;
    //车队名称
    private String name;
    //简介
    private String descInfo;
    //大巴
    private List<Bus> buses = new ArrayList<Bus>();

    private Timestamp createtime;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 50,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    @OneToMany(mappedBy = "subGroup")
    @OrderBy("id")
    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}

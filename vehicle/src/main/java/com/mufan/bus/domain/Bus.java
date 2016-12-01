package com.mufan.bus.domain;

import com.mufan.ap.domain.Hosts;

import javax.persistence.*;

/**
 * Created by aswe on 2016/7/23.
 */
@Entity
public class Bus {
    private Long id;
    //车牌
    private String licensePlate;
    //起点站
    private String startStation;
    //终站
    private String endStation;
    //车队
    private SubGroup subGroup;
    //ap
    private Hosts hosts;
    //司机
    private String driver;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length = 20,nullable = false)
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }


    @ManyToOne()
    @JoinColumn(name="subgroup_id",referencedColumnName = "id")
    public SubGroup getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(SubGroup subGroup) {
        this.subGroup = subGroup;
    }

    @OneToOne()
    @JoinColumn(name = "hostsId")
    public Hosts getHosts() {
        return hosts;
    }

    public void setHosts(Hosts hosts) {
        this.hosts = hosts;
    }
    @Column(length = 30)
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}

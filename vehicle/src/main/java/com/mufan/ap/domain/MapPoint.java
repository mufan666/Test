package com.mufan.ap.domain;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by aswe on 2016/7/31.
 */
@Entity
public class MapPoint implements Serializable {
    private Long id;

    private String apDeviceType;
    private String apSoftwareVersion;
    private String apHardwareVersion;
    private String apMAC;
    private String reserveDate;
    private Date reporttime;


    private String apTimeData;
    private Date apTime;
    private String fixedSystem;
    private String utcTime;
    //n 北纬 s 南纬
    private String n;
    //e 东经 w 西经
    private String e;

    //定位状态标识
    private String fixedStatus;
    //参与定位的卫星数量
    private String nosv;
    //水平精度因子
    private String hdop;
    //椭球高
    private String msl;
    //椭球高单位
    private String mslUnit;
    //海平面分离度
    private String altref;
    //海平面分离度单位
    private String altrefUnit;
    //差分校正时延
    private String diffAge;
    //参考站ID
    private String diffStation;
    //校验和
    private String cs;
//    private Integer hostsId;
    private Hosts hosts;
    //经度
    private String longitude;
    //纬度
    private String latitude;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "hostsId")
    public Hosts getHosts() {
        return hosts;
    }

    public void setHosts(Hosts hosts) {
        this.hosts = hosts;
    }

    /*public Integer getHostsId() {
        return hostsId;
    }

    public void setHostsId(Integer hostsId) {
        this.hostsId = hostsId;
    }*/

    @Column(length = 20, nullable = false)
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(length = 20, nullable = false)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @JSON(serialize = false)
    @Column(length = 20)
    public String getUtcTime() {
        return utcTime;
    }

    public void setUtcTime(String utcTime) {
        this.utcTime = utcTime;
    }

    @JSON(serialize = false)
    @Column(length = 2)
    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    @JSON(serialize = false)
    @Column(length = 2)
    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
    @JSON(serialize = false)
    @Column(length = 2)
    public String getFixedStatus() {
        return fixedStatus;
    }

    public void setFixedStatus(String fixedStatus) {
        this.fixedStatus = fixedStatus;
    }
    @JSON(serialize = false)
    @Column(length = 5)
    public String getNosv() {
        return nosv;
    }

    public void setNosv(String nosv) {
        this.nosv = nosv;
    }
    @JSON(serialize = false)
    @Column(length = 20)
    public String getHdop() {
        return hdop;
    }

    public void setHdop(String hdop) {
        this.hdop = hdop;
    }
    @JSON(serialize = false)
    @Column(length = 20)
    public String getMsl() {
        return msl;
    }

    public void setMsl(String msl) {
        this.msl = msl;
    }
    @JSON(serialize = false)
    @Column(length = 5)
    public String getMslUnit() {
        return mslUnit;
    }

    public void setMslUnit(String mslUnit) {
        this.mslUnit = mslUnit;
    }

    @JSON(serialize = false)
    @Column(length = 20)
    public String getAltref() {
        return altref;
    }

    public void setAltref(String altref) {
        this.altref = altref;
    }
    @JSON(serialize = false)
    @Column(length = 5)
    public String getAltrefUnit() {
        return altrefUnit;
    }

    public void setAltrefUnit(String altrefUnit) {
        this.altrefUnit = altrefUnit;
    }
    @JSON(serialize = false)
    @Column(length = 20)
    public String getDiffAge() {
        return diffAge;
    }

    public void setDiffAge(String diffAge) {
        this.diffAge = diffAge;
    }
    @JSON(serialize = false)
    @Column(length = 20)
    public String getDiffStation() {
        return diffStation;
    }

    public void setDiffStation(String diffStation) {
        this.diffStation = diffStation;
    }
    @JSON(serialize = false)
    @Column(length = 5)
    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }
    @JSON(serialize = false)
    @Column(length = 10)
    public String getFixedSystem() {
        return fixedSystem;
    }

    public void setFixedSystem(String fixedSystem) {
        this.fixedSystem = fixedSystem;
    }
    @JSON(serialize = false)
    @Column(length = 2)
    public String getApDeviceType() {
        return apDeviceType;
    }

    public void setApDeviceType(String apDeviceType) {
        this.apDeviceType = apDeviceType;
    }
    @JSON(serialize = false)
    @Column(length = 3)
    public String getApSoftwareVersion() {
        return apSoftwareVersion;
    }

    public void setApSoftwareVersion(String apSoftwareVersion) {
        this.apSoftwareVersion = apSoftwareVersion;
    }
    @Column(length = 2)
    public String getApHardwareVersion() {
        return apHardwareVersion;
    }

    public void setApHardwareVersion(String apHardwareVersion) {
        this.apHardwareVersion = apHardwareVersion;
    }
    @Column(length = 12)
    public String getApMAC() {
        return apMAC;
    }

    public void setApMAC(String apMAC) {
        this.apMAC = apMAC;
    }
    @Column(length = 4)
    public String getReserveDate() {
        return reserveDate;
    }
    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    @Column(length = 20)
    public String getApTimeData() {
        return apTimeData;
    }

    public void setApTimeData(String apTimeData) {
        this.apTimeData = apTimeData;
    }

    public Date getApTime() {
        return apTime;
    }

    public void setApTime(Date apTime) {
        this.apTime = apTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapPoint mapPoint = (MapPoint) o;

        return id != null ? id.equals(mapPoint.id) : mapPoint.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}

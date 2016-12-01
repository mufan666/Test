package com.mufan.ap.domain;

import com.mufan.bus.domain.Bus;
import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aswe on 2016/7/20.
 */
@Entity
@Table(name = "hostsbean")
public class Hosts implements Serializable {
    private Integer id;
    //mac
    private String serialno;
    //当前版本
    private String currentSoftware;
    //硬件版本
    private String hardware;
    private boolean reboot = false;
    private boolean factoryReset = false;
    private String profileName;
    private Timestamp lastContact = new Timestamp(24 * 3600 * 1000);
    private String lastCrres = "never";
    private Timestamp cfgUpdTime = new Timestamp(24 * 3600 * 1000);
    private Timestamp sfwUpdTime = new Timestamp(24 * 3600 * 1000);

    private String ssid;
    private Integer channel;
    private String bandWidth;
    private String basicEncryptionmodes;
    //密码
    private String wpapass;
    //wpa加密方法
    private String WPAEncryptionmodes;
    private String wepKey;

    private Bus bus;
    private boolean starting;
    //设备状态
    private boolean devicestatus;
    private String remark;
    //批量上传行号
    private Integer lineNum;
    //上传检查信息
    private String checkMsg;

    private Integer hwid;

    private String configname = "Default";

    private boolean forcePasswords=false;

    private List<MapPoint> mapPoints = new ArrayList<MapPoint>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getCurrentSoftware() {
        return currentSoftware;
    }

    public void setCurrentSoftware(String currentSoftware) {
        this.currentSoftware = currentSoftware;
    }

    @JSON(serialize = false)
    public boolean isReboot() {
        return reboot;
    }

    public void setReboot(boolean reboot) {
        this.reboot = reboot;
    }
    @JSON(serialize = false)
    public boolean isFactoryReset() {
        return factoryReset;
    }

    public void setFactoryReset(boolean factoryReset) {
        this.factoryReset = factoryReset;
    }
    @JSON(serialize = false)
    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    /*@Temporal(TemporalType.TIMESTAMP)*/
    public Timestamp getLastContact() {
        return lastContact;
    }

    public void setLastContact(Timestamp lastContact) {
        this.lastContact = lastContact;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
    @JSON(serialize = false)
    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }
    @JSON(serialize = false)
    public String getBandWidth() {
        return bandWidth;
    }

    public void setBandWidth(String bandWidth) {
        this.bandWidth = bandWidth;
    }
    @JSON(serialize = false)
    public String getBasicEncryptionmodes() {
        return basicEncryptionmodes;
    }

    public void setBasicEncryptionmodes(String basicEncryptionmodes) {
        this.basicEncryptionmodes = basicEncryptionmodes;
    }

    public String getWpapass() {
        return wpapass;
    }

    public void setWpapass(String wpapass) {
        this.wpapass = wpapass;
    }

    public String getWPAEncryptionmodes() {
        return WPAEncryptionmodes;
    }

    public void setWPAEncryptionmodes(String WPAEncryptionmodes) {
        this.WPAEncryptionmodes = WPAEncryptionmodes;
    }
    @JSON(serialize = false)
    public String getWepKey() {
        return wepKey;
    }

    public void setWepKey(String wepKey) {
        this.wepKey = wepKey;
    }
    @JSON(serialize = false)
    public String getLastCrres() {
        return lastCrres;
    }

    public void setLastCrres(String lastCrres) {
        this.lastCrres = lastCrres;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getCfgUpdTime() {
        return cfgUpdTime;
    }

    public void setCfgUpdTime(Timestamp cfgUpdTime) {
        this.cfgUpdTime = cfgUpdTime;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getSfwUpdTime() {
        return sfwUpdTime;
    }

    public void setSfwUpdTime(Timestamp sfwUpdTime) {
        this.sfwUpdTime = sfwUpdTime;
    }

    @OneToOne()
    @JoinColumn(name = "busId")
    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @Transient
    public boolean isStarting() {
        return starting;
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
    }
    @Transient
    public boolean isDevicestatus() {
        return devicestatus;
    }

    public void setDevicestatus(boolean devicestatus) {
        this.devicestatus = devicestatus;
    }

    public void initDevicestatus(){
        if(System.currentTimeMillis()-this.lastContact.getTime()>60*1000){
            this.devicestatus = false;
        }else{
            this.devicestatus = true;
        }
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    @Transient
    public String getCheckMsg() {
        return checkMsg;
    }

    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }

    public Integer getHwid() {
        return hwid;
    }

    public void setHwid(Integer hwid) {
        this.hwid = hwid;
    }

    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public boolean isForcePasswords() {
        return forcePasswords;
    }

    public void setForcePasswords(boolean forcePasswords) {
        this.forcePasswords = forcePasswords;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "hosts")
//    @JoinColumn(name = "hostsId")
    public List<MapPoint> getMapPoints() {
        return mapPoints;
    }

    public void setMapPoints(List<MapPoint> mapPoints) {
        this.mapPoints = mapPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hosts hosts = (Hosts) o;

        if (reboot != hosts.reboot) return false;
        if (factoryReset != hosts.factoryReset) return false;
        if (starting != hosts.starting) return false;
        if (devicestatus != hosts.devicestatus) return false;
        if (id != null ? !id.equals(hosts.id) : hosts.id != null) return false;
        if (serialno != null ? !serialno.equals(hosts.serialno) : hosts.serialno != null) return false;
        if (currentSoftware != null ? !currentSoftware.equals(hosts.currentSoftware) : hosts.currentSoftware != null)
            return false;
        if (hardware != null ? !hardware.equals(hosts.hardware) : hosts.hardware != null) return false;
        if (profileName != null ? !profileName.equals(hosts.profileName) : hosts.profileName != null) return false;
        if (lastContact != null ? !lastContact.equals(hosts.lastContact) : hosts.lastContact != null) return false;
        if (lastCrres != null ? !lastCrres.equals(hosts.lastCrres) : hosts.lastCrres != null) return false;
        if (cfgUpdTime != null ? !cfgUpdTime.equals(hosts.cfgUpdTime) : hosts.cfgUpdTime != null) return false;
        if (sfwUpdTime != null ? !sfwUpdTime.equals(hosts.sfwUpdTime) : hosts.sfwUpdTime != null) return false;
        if (ssid != null ? !ssid.equals(hosts.ssid) : hosts.ssid != null) return false;
        if (channel != null ? !channel.equals(hosts.channel) : hosts.channel != null) return false;
        if (bandWidth != null ? !bandWidth.equals(hosts.bandWidth) : hosts.bandWidth != null) return false;
        if (basicEncryptionmodes != null ? !basicEncryptionmodes.equals(hosts.basicEncryptionmodes) : hosts.basicEncryptionmodes != null)
            return false;
        if (wpapass != null ? !wpapass.equals(hosts.wpapass) : hosts.wpapass != null) return false;
        if (WPAEncryptionmodes != null ? !WPAEncryptionmodes.equals(hosts.WPAEncryptionmodes) : hosts.WPAEncryptionmodes != null)
            return false;
        if (wepKey != null ? !wepKey.equals(hosts.wepKey) : hosts.wepKey != null) return false;
        if (bus != null ? !bus.equals(hosts.bus) : hosts.bus != null) return false;
        if (remark != null ? !remark.equals(hosts.remark) : hosts.remark != null) return false;
        return lineNum != null ? lineNum.equals(hosts.lineNum) : hosts.lineNum == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (serialno != null ? serialno.hashCode() : 0);
        result = 31 * result + (currentSoftware != null ? currentSoftware.hashCode() : 0);
        result = 31 * result + (hardware != null ? hardware.hashCode() : 0);
        result = 31 * result + (reboot ? 1 : 0);
        result = 31 * result + (factoryReset ? 1 : 0);
        result = 31 * result + (profileName != null ? profileName.hashCode() : 0);
        result = 31 * result + (lastContact != null ? lastContact.hashCode() : 0);
        result = 31 * result + (lastCrres != null ? lastCrres.hashCode() : 0);
        result = 31 * result + (cfgUpdTime != null ? cfgUpdTime.hashCode() : 0);
        result = 31 * result + (sfwUpdTime != null ? sfwUpdTime.hashCode() : 0);
        result = 31 * result + (ssid != null ? ssid.hashCode() : 0);
        result = 31 * result + (channel != null ? channel.hashCode() : 0);
        result = 31 * result + (bandWidth != null ? bandWidth.hashCode() : 0);
        result = 31 * result + (basicEncryptionmodes != null ? basicEncryptionmodes.hashCode() : 0);
        result = 31 * result + (wpapass != null ? wpapass.hashCode() : 0);
        result = 31 * result + (WPAEncryptionmodes != null ? WPAEncryptionmodes.hashCode() : 0);
        result = 31 * result + (wepKey != null ? wepKey.hashCode() : 0);
        result = 31 * result + (bus != null ? bus.hashCode() : 0);
        result = 31 * result + (starting ? 1 : 0);
        result = 31 * result + (devicestatus ? 1 : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (lineNum != null ? lineNum.hashCode() : 0);
        return result;
    }
}

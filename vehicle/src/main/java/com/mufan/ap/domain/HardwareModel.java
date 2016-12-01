package com.mufan.ap.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by aswe on 2016/8/4.
 */
@Entity
@Table(name = "hardwareModelBean")
public class HardwareModel implements Serializable {
    private Integer id;
    private String oui = "FFFFFF";
    private String manufacturer="easycwmp";
    private String productClass="easycwmp";
    private String version;
    private String displayName="easycwmp";
    private String profileToAssign = "Default";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 30, nullable = false)
    public String getOui() {
        return oui;
    }

    public void setOui(String oui) {
        this.oui = oui;
    }

    @Column(length = 30)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Column(length = 30, nullable = false,name = "hclass")
    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    @Column(length = 30)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(length = 30)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileToAssign() {
        return profileToAssign;
    }

    public void setProfileToAssign(String profileToAssign) {
        this.profileToAssign = profileToAssign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HardwareModel that = (HardwareModel) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

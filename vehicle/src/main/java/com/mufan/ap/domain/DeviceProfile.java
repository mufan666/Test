package com.mufan.ap.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by aswe on 2016/7/20.
 */
@Entity
@Table(name = "deviceprofilebean")
public class DeviceProfile {
    private String name;
    private Integer informinterval;
    private String scriptName;

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInforminterval() {
        return informinterval;
    }

    public void setInforminterval(Integer informinterval) {
        this.informinterval = informinterval;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceProfile that = (DeviceProfile) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

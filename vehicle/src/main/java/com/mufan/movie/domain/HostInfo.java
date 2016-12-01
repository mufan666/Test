package com.mufan.movie.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aswe on 2016/7/31.
 */
@XmlRootElement(name="host")
public class HostInfo {
    private String ip;
    private String port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}

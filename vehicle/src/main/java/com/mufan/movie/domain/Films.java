package com.mufan.movie.domain;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/31.
 */
@XmlType(name = "films",propOrder = {"dateversion","hostInfo","filmInfos"})
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Films {

    private Long dateversion;
    private HostInfo hostInfo;
    private List<FilmInfo> filmInfos = new ArrayList<FilmInfo>();

    public Long getDateversion() {
        return dateversion;
    }

    public void setDateversion(Long dateversion) {
        this.dateversion = dateversion;
    }

    public HostInfo getHostInfo() {
        return hostInfo;
    }

    public void setHostInfo(HostInfo hostInfo) {
        this.hostInfo = hostInfo;
    }

    @XmlElement(name = "film")
    public List<FilmInfo> getFilmInfos() {
        return filmInfos;
    }

    public void setFilmInfos(List<FilmInfo> filmInfos) {
        this.filmInfos = filmInfos;
    }
}

package com.mufan.movie.domain;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/31.
 */
//Jaxb编组出来的xml中的字段顺序是随机的，你可以使用@XmlType的propOrder属性  来指定序列化的顺序
@XmlType(name="film",propOrder={"cname","ename","descInfo","country","type","date","score","click","imagepath","imagesize","imagecode","filmpath","filmsize","filmcode"})
//只有 属性 才能被转换成 xml 中的标签
@XmlAccessorType(XmlAccessType.PROPERTY)
//类文件注解：@XmlRootElement
@XmlRootElement
public class FilmInfo {
    private Long id;
    private String cname = "";
    private String ename;
    private String descInfo;
    private String country;
    private List<String> type = new ArrayList<String>();
    private String date;
    private Double score=0.0;
    private Integer click;
    private String imagepath;
    private Long imagesize;
    private String imagecode;
    private String filmpath;
    private Long filmsize;
    private String filmcode;

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
    @XmlElement(name = "desc")
    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElementWrapper(name = "types")
    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(required = true,nillable = true,namespace = "",defaultValue = " ")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Long getImagesize() {
        return imagesize;
    }

    public void setImagesize(Long imagesize) {
        this.imagesize = imagesize;
    }

    public String getImagecode() {
        return imagecode;
    }

    public void setImagecode(String imagecode) {
        this.imagecode = imagecode;
    }

    public String getFilmpath() {
        return filmpath;
    }

    public void setFilmpath(String filmpath) {
        this.filmpath = filmpath;
    }

    public Long getFilmsize() {
        return filmsize;
    }

    public void setFilmsize(Long filmsize) {
        this.filmsize = filmsize;
    }

    public String getFilmcode() {
        return filmcode;
    }

    public void setFilmcode(String filmcode) {
        this.filmcode = filmcode;
    }

    public void genInfo(Film film){
        if(null != film.getId()) this.setId(film.getId());
        if(null != film.getEname()) this.setEname(film.getEname());
        if(null != film.getCname()) this.setCname(film.getCname());
        if(null != film.getDescInfo()) this.setDescInfo(film.getDescInfo());
        if(null != film.getDate()) this.setDate(film.getDate());
        if(null != film.getScore()) this.setScore(film.getScore());
        if(null != film.getClick()) this.setClick(film.getClick());
        if(null != film.getImagepath()) this.setImagepath(film.getImagepath());
        if(null != film.getImagesize()) this.setImagesize(film.getImagesize());
        if(null != film.getImagecode()) this.setImagecode(film.getImagecode());
        if(null != film.getFilmpath()) this.setImagepath(film.getFilmpath());
        if(null != film.getFilmsize()) this.setFilmsize(film.getFilmsize());
        if(null != film.getFilmcode()) this.setFilmcode(film.getFilmcode());
        if(null != film.getFilmTypes() && 0!=film.getFilmTypes().size() && !film.getFilmTypes().isEmpty()){
            for(FilmType type:film.getFilmTypes()){
                this.getType().add(type.getTypeName());
            }
        }
    }

    public FilmInfo genFilmInfo(Film film){
        FilmInfo info = new FilmInfo();
        if(null != film.getId()) info.setId(film.getId());
        if(null != film.getEname()) info.setEname(film.getEname());
        if(null != film.getCname()) info.setCname(film.getCname());
        if(null != film.getDescInfo()) info.setDescInfo(film.getDescInfo());
        if(null != film.getDate()) info.setDate(film.getDate());
        if(null != film.getScore()) info.setScore(film.getScore());
        if(null != film.getClick()) info.setClick(film.getClick());
        if(null != film.getImagepath()) info.setImagepath(film.getImagepath());
        if(null != film.getImagesize()) info.setImagesize(film.getImagesize());
        if(null != film.getImagecode()) info.setImagecode(film.getImagecode());
        if(null != film.getFilmpath()) info.setImagepath(film.getFilmpath());
        if(null != film.getFilmsize()) info.setFilmsize(film.getFilmsize());
        if(null != film.getFilmcode()) info.setFilmcode(film.getFilmcode());
        if(null != film.getFilmTypes() && 0!=film.getFilmTypes().size() && !film.getFilmTypes().isEmpty()){
            for(FilmType type:film.getFilmTypes()){
                info.getType().add(type.getTypeName());
            }
        }
        return info;
    }
}

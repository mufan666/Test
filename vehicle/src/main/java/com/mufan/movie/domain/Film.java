package com.mufan.movie.domain;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/29.
 */
@Entity
public class Film implements Serializable {
    private Long id;
    //中文名称，暂不用
    private String cname;
    //英文名称
    private String ename;
    //电影简介
    private String descInfo;
    //制片地区
    private String country;
    private List<String> type;
    private List<FilmType> filmTypes = new ArrayList<FilmType>();
    //上映时间
    private String date;
    //评分
    private Double score;
    //点击率
    private Integer click;
    //图片路径
    private String imagepath;
    //图片大小
    private Long imagesize;
    //图片MD5
    private String imagecode;
    private String filmpath;
    private Long filmsize;
    private String filmcode;

    private String filmFileName;
    private String filmSuffix;

    //图片名称
    private String imageFileName;
    //图片文件后缀名
    private String imageSuffix;
    //是否发布
    private boolean publish = false;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JSON(serialize = false,deserialize = false)
    @Transient
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

    public String getFilmFileName() {
        return filmFileName;
    }

    public void setFilmFileName(String filmFileName) {
        this.filmFileName = filmFileName;
    }

    public String getFilmSuffix() {
        return filmSuffix;
    }

    public void setFilmSuffix(String filmSuffix) {
        this.filmSuffix = filmSuffix;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageSuffix() {
        return imageSuffix;
    }

    public void setImageSuffix(String imageSuffix) {
        this.imageSuffix = imageSuffix;
    }


    @JoinTable(name = "film_type",inverseJoinColumns = @JoinColumn(name = "typeId"),joinColumns = @JoinColumn(name = "filmId"))
    @ManyToMany
    /*@OrderColumn(name = "typeId")*/
    @OrderBy("id")
    public List<FilmType> getFilmTypes() {
        return filmTypes;
    }

    public void setFilmTypes(List<FilmType> filmTypes) {
        this.filmTypes = filmTypes;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        return id != null ? id.equals(film.id) : film.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

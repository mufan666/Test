package com.mufan.movie.domain;

import org.hibernate.hql.internal.classic.ClauseParser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/29.
 */
@Entity
public class FilmType implements Serializable {
    private Integer id;
    private String typeName;
    private List<Film> films=new ArrayList<Film>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    @ManyToMany(mappedBy = "filmTypes")
    /*@OrderColumn(name = "filmId")*/
    @OrderBy("id")
    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmType filmType = (FilmType) o;

        return id != null ? id.equals(filmType.id) : filmType.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

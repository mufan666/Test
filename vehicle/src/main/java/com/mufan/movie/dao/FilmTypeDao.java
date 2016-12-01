package com.mufan.movie.dao;

import com.mufan.movie.domain.FilmType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by aswe on 2016/7/30.
 */
public interface FilmTypeDao extends JpaRepository<FilmType,Integer> {
}

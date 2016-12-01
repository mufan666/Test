package com.mufan.movie.service;

import com.mufan.movie.dao.FilmTypeDao;
import com.mufan.movie.domain.FilmType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by aswe on 2016/8/4.
 */
@Service("filmTypeService")
public class FilmTypeServiceImpl implements FilmTypeService {
    @Resource
    private FilmTypeDao filmTypeDao;
    public List<FilmType> findAll() {
        return filmTypeDao.findAll();
    }
}

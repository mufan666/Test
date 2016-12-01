package com.mufan.movie.service;

import com.mufan.movie.domain.Film;
import com.mufan.movie.domain.Films;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by aswe on 2016/7/27.
 */
public interface FilmService {
//    public String calculateMD5(String path) throws IOException;
    public String calculateMd5(File file) throws IOException, NoSuchAlgorithmException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    public void writeFile(File outPath, File tempFile) throws IOException;

    public boolean validateMD5(File file,Film film,String type) throws InvocationTargetException, NoSuchMethodException, NoSuchAlgorithmException, IllegalAccessException, IOException;

    public Film saveFilm(Film film);
    public Film getFilmById(Long id);

    public int publishFilm(Long id);
    public int unPublishFilm(Long id);

    public Films getPublish();

    public List<Film> findAllFilms();

    public String getPropertiesValue(String value);
}

package com.mufan.movie;

import com.mufan.movie.dao.FilmDao;
import com.mufan.movie.dao.FilmTypeDao;
import com.mufan.movie.domain.Film;
import com.mufan.movie.domain.FilmType;
import com.mufan.movie.service.FilmService;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Created by aswe on 2016/7/30.
 */
public class FilmServiceTest {
    private static ApplicationContext context;
    private static FilmDao filmDao;
    private static FilmTypeDao filmTypeDao;
    private static FilmService filmService;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        filmDao = (FilmDao) context.getBean("filmDao");
        filmTypeDao = (FilmTypeDao) context.getBean("filmTypeDao");
        filmService = (FilmService) context.getBean("filmService");
    }

    @Test
    public void testInsertFilm(){
        Film film = new Film();
        film.setCname("死神");
        FilmType filmType1 = new FilmType();
        filmType1.setId(1);
        FilmType filmType2 = new FilmType();
        filmType2.setId(2);
        film.getFilmTypes().add(filmType1);
        film.getFilmTypes().add(filmType2);

        filmService.saveFilm(film);
    }

    @Test
    public void testSave(){
        Film film = new Film();
        film.setId(5L);
        film.setCname("死神1");
        film.setEname("abc");
        FilmType filmType1 = new FilmType();
        filmType1.setId(4);
        FilmType filmType2 = new FilmType();
        filmType2.setId(2);
        FilmType filmType3 = new FilmType();
        filmType3.setId(3);
//        film.getFilmTypes().add(filmType1);
        film.getFilmTypes().add(filmType2);
        film.getFilmTypes().add(filmType3);

        filmService.saveFilm(film);
    }

    @Test
    public void testProperties(){
        System.out.println(filmService.getPropertiesValue("jdbc.driverClassName"));
    }
}

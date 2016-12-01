package com.mufan.movie;

import com.mufan.movie.dao.FilmDao;
import com.mufan.movie.dao.FilmTypeDao;
import com.mufan.movie.domain.Film;
import com.mufan.movie.domain.FilmType;
import com.mufan.movie.service.FilmService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by aswe on 2016/7/30.
 */
public class FilmDaoTest {
    private static  ApplicationContext context;
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
    public void testProperties01(){
        MessageSource resou = (MessageSource) context.getBean("messageSource");
        String msg = resou.getMessage("jdbc.driverClassName",null,null);
        System.out.println(msg);
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

        filmDao.save(film);
    }

    @Test
    public void testDelete(){
        Film film = new Film();
        film.setId(1L);

        filmDao.delete(film);
    }

    @Test
    public void testPublish()  {
        try {
            filmService.publishFilm(1L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFindByPublishIsTrue(){
        List<Film> filmList = filmDao.findByPublishIsTrue();
        for(Film f:filmList){
            System.out.println(f.getId());
        }
    }

}

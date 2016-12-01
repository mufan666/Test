package com.mufan.movie;

import com.mufan.movie.dao.FilmTypeDao;
import com.mufan.movie.domain.FilmType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by aswe on 2016/7/30.
 */
public class FilmTypeDaoTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
    }
    @Test
    public void testFindAll(){
        FilmTypeDao filmTypeDao = (FilmTypeDao) context.getBean("filmTypeDao");
        List<FilmType> filmTypes = filmTypeDao.findAll();
        for(FilmType f:filmTypes){
            System.out.println(f.getId()+"\t"+f.getTypeName());
        }
    }
}

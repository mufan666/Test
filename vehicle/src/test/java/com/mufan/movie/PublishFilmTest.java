package com.mufan.movie;

import com.mufan.movie.domain.FilmInfo;
import com.mufan.movie.domain.Films;
import com.mufan.movie.domain.HostInfo;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aswe on 2016/7/31.
 */
public class PublishFilmTest {
    @Test
    public void test01() throws JAXBException {
        List<FilmInfo> filmInfos = new ArrayList<FilmInfo>();
        for(int i=1;i<10;i++){
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setId((long)i);
//            filmInfo.setCname("cname"+i);
            filmInfo.setEname("ename"+i);
            filmInfo.setDescInfo("descinfo"+i);
            filmInfo.setCountry("cn"+i);
            List<String> list = new ArrayList<String>();
            list.add("horror"+i);
            list.add("action"+i);
            filmInfo.setType(list);
            filmInfo.setDate("201"+i);
//            filmInfo.setScore(8.0);
            filmInfo.setClick(i);
            filmInfo.setImagepath("/"+i+"/image"+i+"/.jpg");
            filmInfo.setImagesize((long) 1111);
            filmInfo.setImagecode("imagemd5"+i);
            filmInfo.setFilmpath("/"+i+"/film"+i+"/.avi");
            filmInfo.setFilmcode("filmMd5"+i);
            filmInfo.setFilmsize(1231L);
            filmInfos.add(filmInfo);
        }
        HostInfo hostInfo = new HostInfo();
        hostInfo.setIp("111.111.111.111");
        hostInfo.setPort("21");
        Films films = new Films();
        films.setDateversion(new Date().getTime());
        films.setFilmInfos(filmInfos);
        films.setHostInfo(hostInfo);

        JAXBContext context = JAXBContext.newInstance(Films.class);
        Marshaller us = context.createMarshaller();
        us.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        us.marshal(films, System.out);
    }
}

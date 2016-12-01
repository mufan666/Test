package com.mufan.utils;

import com.mufan.ap.domain.Hosts;
import com.mufan.movie.domain.Film;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * Created by aswe on 2016/7/31.
 */
public class AnyTest {
    @Test
    public void test() {
        String s = "121.9999999";
//        String s = "99.9999999";
        /*int length = s.length();
        String last = s.substring(length-1,length);

        StringBuilder sb = new StringBuilder(s);
        sb.replace(length-1,length,"8");
        System.out.println(sb.toString());

        System.out.println(last);*/
        String ss = addOne(s, s.length());
        System.out.println(ss);
    }

    public String addOne(String ss, int length) {
        String temp = ss;
        if(length==0){
            temp = "1"+ss;
        }else {
            String last = ss.substring(length - 1, length);
            if (".".equals(last)) {
                temp = addOne(ss, length - 1);
            } else {
                int num = Integer.parseInt(last) + 1;
                StringBuilder sb = new StringBuilder(ss);
                if (num > 9) {
                    sb.replace(length - 1, length, "0");
                    temp = addOne(sb.toString(), length - 1);
                } else {
                    temp = sb.replace(length - 1, length, String.valueOf(num)).toString();
                }
            }
        }
        return temp;
    }

    @Test
    public void testListRepeat(){
        Hosts hosts1 = new Hosts();
        Hosts hosts2 = new Hosts();
        /*hosts1.setId(1);
        hosts2.setId(1);*/
        hosts1.setSerialno("1234");
        hosts2.setSerialno("1235");
        hosts1.setFactoryReset(false);
        hosts2.setFactoryReset(false);
        hosts1.setCheckMsg("exit");
        hosts2.setCheckMsg("exit1");

        List<Hosts> list = new ArrayList<Hosts>();
        list.add(hosts1);
        if(list.contains(hosts2)) {
            System.out.println("exsit");
            /*list.remove(hosts2);
            list.add(hosts2);*/
        }
        for(Hosts h:list){
            System.out.println(h.getSerialno());
        }
    }

    @Test
    public void testMap(){
    Map<String,String> map = new TreeMap<String, String>();
    map.put("123","123");
    map.put("123","abc");
    for(Map.Entry<String,String> entry:map.entrySet()){
        System.out.println(entry.getKey()+"\t"+entry.getValue());
    }
}

    @Test
    public void testRuntimeException(){
        System.out.println(4/0);
    }

    @Test
    public void testFilm(){
        Film film = new Film();
        film.setImagepath("5"+File.separator+"aaa.jpg");
        film.setFilmpath("5"+ File.separator+"aaa.mv");
        System.out.println(film.getImagepath()+"\t"+film.getFilmpath());
    }

    @Test
    public void testHostsObject(){
        Hosts hosts = new Hosts();
        hosts.setSerialno("abc");
        if(null == hosts.getBus() || null == hosts.getBus().getLicensePlate()){
            System.out.println("null");
        }
    }

    @Test
    public void test01(){
        FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        FastDateFormat fdf1 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        FastDateFormat fdf2 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        System.out.println(fdf==fdf1);
        System.out.println(fdf);
        System.out.println(fdf1);
        System.out.println(fdf.format(new Date(System.currentTimeMillis()-1000*60*10)));
    }
}

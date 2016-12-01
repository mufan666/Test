package com.mufan.ap.service;

import com.mufan.ap.domain.MapPoint;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/31.
 */
public class MapPointServiceTest {
    private static ApplicationContext context;
    private static MapPointService mapPointService;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        mapPointService = (MapPointService) context.getBean("mapPointService");
    }

    @Test
    public void testInsertTestData(){
        List<MapPoint> points = new ArrayList<MapPoint>();

        DecimalFormat df = new DecimalFormat("#.000000");

        BigDecimal s = new BigDecimal(121.233537);
        BigDecimal ss = new BigDecimal(31.102290);
        BigDecimal va = new BigDecimal(0.00005);
//        System.out.println(df.format(s)+"\t"+df.format(ss));
        for(int i=1;i<101;i++){
            /*s = s.add(va.multiply(new BigDecimal(i)));
            ss = ss.add(va.multiply(new BigDecimal(i)));*/
            s = s.add(va);
            ss = ss.add(va);
//            System.out.println(i+"\t"+df.format(s)+"\t"+df.format(ss));

            MapPoint point = new MapPoint();
//            point.setHostsId(1);
            point.setLatitude(df.format(ss));
            point.setLongitude(df.format(s));
            points.add(point);
        }
        mapPointService.batchInsertPoint(points);
    }
    /*public String addOne(String ss, int length) {
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
    }*/
    @Test
    public void test01(){
        double s = 121.233537;
        System.out.println(s+0.00005*3);
    }
}

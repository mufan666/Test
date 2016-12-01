package com.mufan.ap.dao;

import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.MapPoint;
import com.mufan.ap.service.MapPointService;
import com.mufan.bus.dao.BusDao;
import com.mufan.bus.domain.Bus;
import com.mufan.message.service.MessageResolveService;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aswe on 2016/9/22.
 */
public class MapPointDaoTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
    }

    @Test
    public void test01(){
        MapPointDao mapPointDao = (MapPointDao) context.getBean("mapPointDao");
        String message = "B110211122334455660000$GNGGA,075538.920,3110.061707,S,12123.904650,W,0,00,127.000,50.061,M,0,M,,*55,1474530938";

        Pattern p = Pattern.compile("\\$\\S+?\\*");
        Matcher m = p.matcher(message);
        String gpsText="";
        int i=0;
        while (m.find()){
            gpsText=m.group();
            i++;
        }
        System.out.println(i);
        if(i!=1){
            return;
        }

        MapPoint mapPoint = new MapPoint();
        mapPoint.setApDeviceType(message.substring(0, 2));
        mapPoint.setApSoftwareVersion(message.substring(2, 5));
        mapPoint.setApHardwareVersion(message.substring(5, 6));
        mapPoint.setApMAC(message.substring(6, 18));
        mapPoint.setReserveDate(message.substring(18,22));
        mapPoint.setReporttime(new Date(System.currentTimeMillis()));

        String gpsStr = message.substring(22);
        System.out.println(gpsStr);
        String[] fields = gpsStr.split(",");
        String time = fields[fields.length-1];
        System.out.println(time);
        mapPoint.setApTimeData(time);
        mapPoint.setApTime(new Date(Long.parseLong(time)*1000));
        mapPoint.setFixedSystem(fields[0].substring(1));
        System.out.println(mapPoint.getFixedSystem()+"\t"+mapPoint.getApTime());

        mapPoint.setUtcTime(fields[1]);
        String n = fields[3];
        String e = fields[5];

        String latstr = fields[2];
        double latOfDegree = Double.parseDouble(latstr.substring(0,2));
        double lat = Double.parseDouble(latstr.substring(2));
        double lattemp =latOfDegree+ lat/0.6/100;
        String lonstr = fields[4];
        double lonOfDegree = Double.parseDouble(lonstr.substring(0,3));
        double lon = Double.parseDouble(lonstr.substring(3));
        double longtemp =lonOfDegree+ lon/0.6/100;
        mapPoint.setN(n);
        if(n.equals("N"))  mapPoint.setLatitude(lattemp+"");
        if(n.equals("S"))  mapPoint.setLatitude("-"+lattemp);
        mapPoint.setE(e);
        if(e.equals("E")) mapPoint.setLongitude(longtemp+"");
        if(e.equals("W")) mapPoint.setLongitude("-"+longtemp);
        System.out.println(mapPoint.getLongitude()+"\t"+mapPoint.getLatitude());
        mapPoint.setFixedStatus(fields[6]);
        mapPoint.setNosv(fields[7]);
        mapPoint.setHdop(fields[8]);
        mapPoint.setMsl(fields[9]);
        mapPoint.setMslUnit(fields[10]);
        mapPoint.setAltref(fields[11]);
        mapPoint.setAltrefUnit(fields[12]);
        mapPoint.setCs(fields[fields.length-2].substring(1));
        System.out.println(mapPoint.getCs());
        System.out.println("g"+gpsText);
        System.out.println("p"+gpsStr);
        mapPointDao.save(mapPoint);
    }
    @Test
    public void test02() {
        MessageResolveService messageResolveService = (MessageResolveService) context.getBean("messageResolveService");
        String message = "B110211122334455660000$GNGGA,075538.920,3110.061707,S,12123.904650,W,0,00,127.000,50.061,M,0,M,,*55,1474530938";
        messageResolveService.parserMessage(message);
    }
    @Test
    public void test03() {
        MapPointService mapPointService = (MapPointService) context.getBean("mapPointService");
        MapPoint mapPoint = mapPointService.getLastPoint(1);
        System.out.println(mapPoint.getId());
    }

    @Test
    public void test04() {
        MapPointService mapPointService = (MapPointService) context.getBean("mapPointService");
        MapPoint mapPoint = mapPointService.getLastPoint();
        System.out.println(mapPoint.getId());
    }

    @Test
    public void test05() {
        MapPointDao mapPointDao = (MapPointDao) context.getBean("mapPointDao");
        FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        List<MapPoint> mapPoints = mapPointDao.findlsatPoint(new Date(System.currentTimeMillis()-1000*60*10));
        mapPoints.forEach(new Consumer<MapPoint>() {
            public void accept(MapPoint mapPoint) {
                System.out.println(mapPoint.getId());
                System.out.println(mapPoint.getHosts().getSerialno());
            }
        });
//        mapPoints.forEach((final MapPoint mapPoint)-> System.out.println(mapPoint.getId()));
    }

    @Test
    public void testfindTrack() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MapPointDao mapPointDao = (MapPointDao) context.getBean("mapPointDao");
        Hosts hosts = new Hosts();
        hosts.setId(15);
        List<MapPoint> mapPoints = mapPointDao.findByHostsIsAndReporttimeBetween(hosts,sdf.parse("2016-10-19 08:48:00"),sdf.parse("2016-10-19 09:35:00"));
        mapPoints.forEach(mapPoint -> System.out.println(mapPoint.getId()+"\t"+mapPoint.getFixedStatus()));
//        mapPoints.forEach(System.out::println);
    }

    @Test
    public void testfindTrackExcouldeFixed() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MapPointDao mapPointDao = (MapPointDao) context.getBean("mapPointDao");
        Hosts hosts = new Hosts();
        hosts.setId(15);
        List<MapPoint> mapPoints = mapPointDao.findByHostsIsAndFixedStatusNotAndReporttimeBetween(hosts,"0",sdf.parse("2016-10-19 08:48:00"),sdf.parse("2016-10-19 09:35:00"));
        mapPoints.forEach(mapPoint -> System.out.println(mapPoint.getId()+"\t"+mapPoint.getFixedStatus()));
//        mapPoints.forEach(System.out::println);
    }

    @Test
    public void testfindTrackGroupBus() throws ParseException {
        MapPointDao mapPointDao = (MapPointDao) context.getBean("mapPointDao");
        List<MapPoint> mapPoints = mapPointDao.getLastPointsGroupByBus();
        System.out.println(mapPoints.size());
        mapPoints.forEach(mapPoint -> System.out.println(mapPoint.getId()+"\t"+mapPoint.getFixedStatus()+"\t"+mapPoint.getHosts().getId()+"\t"+mapPoint.getHosts().getBus().getLicensePlate()));
    }
}

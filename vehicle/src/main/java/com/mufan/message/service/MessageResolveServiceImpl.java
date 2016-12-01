package com.mufan.message.service;

import com.mufan.ap.dao.MapPointSweeperImpl;
import com.mufan.ap.dao.MapPointSweeper;
import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.MapPoint;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aswe on 2016/9/22.
 */
public class MessageResolveServiceImpl implements MessageResolveService {
    private MapPointSweeper mapPointDao = new MapPointSweeperImpl();

    public void parserMessage(String message) {
        Pattern p = Pattern.compile("\\$\\S+?\\*");
        Matcher m = p.matcher(message);
        String gpsText="";
        int i=0;
        while (m.find()){
            gpsText=m.group();
            i++;
        }
//        System.out.println(i);

        if(i!=1) return;
        String apMac = message.substring(6, 18);
        Hosts h = mapPointDao.isExist(apMac);
        if(null == h ) {
            return;
        }
        MapPoint mapPoint = new MapPoint();
        mapPoint.setHosts(h);
        mapPoint.setApDeviceType(message.substring(0, 2));
        mapPoint.setApSoftwareVersion(message.substring(2, 5));
        mapPoint.setApHardwareVersion(message.substring(5, 6));
        mapPoint.setApMAC(message.substring(6, 18));
        mapPoint.setReserveDate(message.substring(18,22));
        mapPoint.setReporttime(new Date(System.currentTimeMillis()));

        String gpsStr = message.substring(22);
//        System.out.println(gpsStr);
        String[] fields = gpsStr.split(",");
        String time = fields[fields.length-1];
//        System.out.println(time);
        mapPoint.setApTimeData(time);
        mapPoint.setApTime(new Date(Long.parseLong(time)*1000));
        mapPoint.setFixedSystem(fields[0].substring(1));

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
//        System.out.println(mapPoint.getLongitude()+"\t"+mapPoint.getLatitude());
        mapPoint.setFixedStatus(fields[6]);
        mapPoint.setNosv(fields[7]);
        mapPoint.setHdop(fields[8]);
        mapPoint.setMsl(fields[9]);
        mapPoint.setMslUnit(fields[10]);
        mapPoint.setAltref(fields[11]);
        mapPoint.setAltrefUnit(fields[12]);
        mapPoint.setCs(fields[fields.length-2].substring(1));
        mapPointDao.insertSweep(mapPoint);
    }
}

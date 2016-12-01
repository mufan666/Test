package com.mufan.utils;

import com.mufan.ap.domain.MapPoint;
import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aswe on 2016/9/14.
 */
public class SomeTest {

    @Test
    public void test(){
        String string = "$GNGGA,000000.000,3110.061707,N,12123.904650,E,0,00,127.000,50.061,M,0,M,,*52,1473750722";
        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + String.format("%4s", Integer.toHexString(c)).replace(" ","0"));
        }
        System.out.println(unicode.toString());
    }

    @Test
    public void testUnicode2String(){
//        String unicode="\\u0024\\u0047\\u004e\\u0047\\u0047\\u0041\\u002c\\u0030\\u0030\\u0030\\u0030\\u0030\\u0030\\u002e\\u0030\\u0030\\u0030\\u002c\\u0033\\u0031\\u0031\\u0030\\u002e\\u0030\\u0036\\u0031\\u0037\\u0030\\u0037\\u002c\\u004e\\u002c\\u0031\\u0032\\u0031\\u0032\\u0033\\u002e\\u0039\\u0030\\u0034\\u0036\\u0035\\u0030\\u002c\\u0045\\u002c\\u0030\\u002c\\u0030\\u0030\\u002c\\u0031\\u0032\\u0037\\u002e\\u0030\\u0030\\u0030\\u002c\\u0035\\u0030\\u002e\\u0030\\u0036\\u0031\\u002c\\u004d\\u002c\\u0030\\u002c\\u004d\\u002c\\u002c\\u002a\\u0035\\u0032\\u002c\\u0031\\u0034\\u0037\\u0033\\u0037\\u0035\\u0030\\u0037\\u0032\\u0032";
        String unicode="\u0024\u0047\u004e\u0047\u0047\u0041\u002c\u0030\u0030\u0030\u0030\u0030\u0030\u002e\u0030\u0030\u0030\u002c\u0033\u0031\u0031\u0030\u002e\u0030\u0036\u0031\u0037\u0030\u0037\u002c\u004e\u002c\u0031\u0032\u0031\u0032\u0033\u002e\u0039\u0030\u0034\u0036\u0035\u0030\u002c\u0045\u002c\u0030\u002c\u0030\u0030\u002c\u0031\u0032\u0037\u002e\u0030\u0030\u0030\u002c\u0035\u0030\u002e\u0030\u0036\u0031\u002c\u004d\u002c\u0030\u002c\u004d\u002c\u002c\u002a\u0035\u0032\u002c\u0031\u0034\u0037\u0033\u0037\u0035\u0030\u0037\u0032\u0032";
        System.out.println("\u0024\u0047\u004e\u0047\u0047\u0041\u002c\u0030\u0030\u0030\u0030\u0030\u0030\u002e\u0030\u0030\u0030\u002c\u0033\u0031\u0031\u0030\u002e\u0030\u0036\u0031\u0037\u0030\u0037\u002c\u004e\u002c\u0031\u0032\u0031\u0032\u0033\u002e\u0039\u0030\u0034\u0036\u0035\u0030\u002c\u0045\u002c\u0030\u002c\u0030\u0030\u002c\u0031\u0032\u0037\u002e\u0030\u0030\u0030\u002c\u0035\u0030\u002e\u0030\u0036\u0031\u002c\u004d\u002c\u0030\u002c\u004d\u002c\u002c\u002a\u0035\u0032\u002c\u0031\u0034\u0037\u0033\u0037\u0035\u0030\u0037\u0032\u0032");
        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        System.out.println(string);
    }

    @Test
    public void test01(){
        System.out.println(Integer.toHexString('你').toString());
        System.out.println(String.format("%4s", Integer.toHexString('a').toString()));
        System.out.println(String.format("%04x", (int)'a'));
       /* System.out.println((int) '你');
        System.out.println(Integer.toHexString(20320));*/
    }

    @Test
    public void test02(){
        String ss = "$GNGGA,000000.000,3110.061707,N,12123.904650,E,0,00,127.000,50.061,M,0,M,,*52,1473750722";
        String[] str = ss.split(",");
//        System.out.println(str[2]+"\t"+str[4]);
        String longitude = str[4];
        String latitude = str[2];

        double lonOfDegree = Double.parseDouble(longitude.substring(0,3));
        double lon = Double.parseDouble(longitude.substring(3));
//        System.out.println(lonOfDegree+"\t"+lon);

        double temp =lonOfDegree+ lon/0.6/100;
        System.out.println(temp);

        double latOfDegree = Double.parseDouble(latitude.substring(0,2));
        double lat = Double.parseDouble(latitude.substring(2));
//        System.out.println(lonOfDegree+"\t"+lon);

        double lattemp =latOfDegree+ lat/0.6/100;
        System.out.println(lattemp);
    }

    @Test
    public void test03(){
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

    }
}

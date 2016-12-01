package com.mufan.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mufan.ap.domain.Hosts;
import com.mufan.bus.domain.Bus;
import com.mufan.bus.domain.SubGroup;
import com.mufan.utils.jsonStrategy.gson.BusRelationStrategy;
import com.mufan.utils.jsonStrategy.gson.SubGroupRelationStrategy;
import com.mufan.utils.jsonStrategy.json.JsonConfigStrategy;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aswe on 2016/7/25.
 */
public class JsonTest {
    @Test
    public void test01(){
        SubGroup subGroup = new SubGroup();
        subGroup.setId(1);
        subGroup.setName("第一");
        subGroup.setDescInfo("123avcc");

        Bus bus = new Bus();
        bus.setId(1L);
        bus.setSubGroup(subGroup);
        bus.setLicensePlate("abc_12345");

        subGroup.getBuses().add(bus);
        ExclusionStrategy myExclusionStrategy = new ExclusionStrategy() {
            public boolean shouldSkipField(FieldAttributes fa) {
                List<String> list = new ArrayList<String>();
                list.add("subGroup");
                return list.contains(fa.getName());
            }

            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };
        Gson gson = new GsonBuilder().setExclusionStrategies(myExclusionStrategy).create();
        System.out.println(gson.toJson(subGroup));
    }

    @Test
    public void test02(){
        SubGroup subGroup = new SubGroup();
        subGroup.setId(1);
        subGroup.setName("第一");
        subGroup.setDescInfo("123avcc");
        subGroup.setCreatetime(new Timestamp(System.currentTimeMillis()));

        Bus bus = new Bus();
        bus.setId(1L);
        bus.setSubGroup(subGroup);

        bus.setLicensePlate("abc_12345");

        subGroup.getBuses().add(bus);

        Hosts hosts = new Hosts();
        hosts.setId(1);
        hosts.setSerialno("adfasdf");
        hosts.setSsid("123");
        hosts.setBus(bus);
        bus.setHosts(hosts);

        /*Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setExclusionStrategies(new SubGroupRelationStrategy()).create();
        System.out.println(gson.toJson(subGroup));*/

        Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setExclusionStrategies(new BusRelationStrategy()).create();
        System.out.println(gson1.toJson(bus));
    }

    @Test
    public void test03(){
        SubGroup subGroup = new SubGroup();
        subGroup.setId(1);
        subGroup.setName("第一");
        subGroup.setDescInfo("123avcc");

        Bus bus = new Bus();
        bus.setId(1L);
        bus.setSubGroup(subGroup);
        bus.setLicensePlate("abc_12345");

        subGroup.getBuses().add(bus);

        JSONObject jsonObject = JSONObject.fromObject(subGroup, JsonConfigStrategy.getSubGroupStrategy());
        System.out.println(jsonObject.toString());
    }

    @Test
    public void test04(){
        String ss = "get(\"abc\")get(\"abc1\")get(\"abc2\")get(\"abc3\")";
        Pattern p = Pattern.compile("(get\\()(\"[1-9a-zA-Z]+\")(\\))");
        Matcher m = p.matcher(ss);
        while (m.find()) {
            System.out.println(m.group());
            System.out.println(m.group(2));
        }
        String sstr = ss.replaceAll("([g][e][t][\\(])([\"][1-9a-zA-Z]+[\"])([\\)])","$2");
        System.out.println(sstr);
    }
    @Test
    public void test004(){
        Pattern p = Pattern.compile("\\.(wmv|asf|asx|rm|rmvb|mpg|mpeg|mpe|3gp|mp4|m4v|avi|dat|mkv|flv|vob)$");
        String ss = "m.rmvb";
        Matcher m = p.matcher(ss);
        while (m.find()){
            System.out.println(m.group());
        }
    }

    @Test
    public void test05(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getTime());
    }

    @Test
    public void test06(){
        String s = "abc.vfr.nv";
        System.out.println(s.substring(s.lastIndexOf(".")+1));
    }

    @Test
    public void jsonToXML(){
        /* String jsonstr = "{\"name\":\"Michael\",\"address\":{\"id\":1,\"city\":\"Suzou\",\"street\":\" Changjiang Road \",\"postcode\":100025},\"blog\":\"http://www.ij2ee.com\"}";
        JSONObject jobj = JSONObject.fromObject(jsonstr);
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setRootName("person");
        String xml =  xmlSerializer.write(jobj);
        System.out.println(xml);*/
        boolean f = 1==1&&false;
        System.out.println(f);

    }
}

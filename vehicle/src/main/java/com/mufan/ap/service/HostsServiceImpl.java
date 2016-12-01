package com.mufan.ap.service;

import com.mufan.ap.dao.DeviceProfileDao;
import com.mufan.ap.dao.HardwareModelDao;
import com.mufan.ap.dao.HostsDao;
import com.mufan.ap.dao.ScriptDao;
import com.mufan.ap.domain.DeviceProfile;
import com.mufan.ap.domain.HardwareModel;
import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.Script;
import com.mufan.bus.dao.BusDao;
import com.mufan.bus.dao.SubGroupDao;
import com.mufan.bus.domain.Bus;
import com.mufan.bus.domain.SubGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by aswe on 2016/7/21.
 */
@Service("hostsService")
public class HostsServiceImpl implements HostsService {

    @Resource(name = "hostsDao", type = com.mufan.ap.dao.HostsDao.class)
    private HostsDao hostsDao;
    @Resource(name = "scriptDao", type = ScriptDao.class)
    private ScriptDao scriptDao;
    @Resource(name = "deviceProfileDao", type = DeviceProfileDao.class)
    private DeviceProfileDao deviceProfileDao;
    @Resource
    private BusDao busDao;
    @Resource
    private SubGroupDao subGroupDao;
    @Resource
    private HardwareModelDao hardwareModelDao;

    public List<Hosts> batchInsert(List<Hosts> hostses) {
        /*Map<String,SubGroup> subGroupMap = new TreeMap<String, SubGroup>();
        List<SubGroup> subGroupList = subGroupDao.findAll();
        for(SubGroup sg:subGroupList){
            subGroupMap.put(sg.getName(),sg);
        }*/
        List<Hosts> error = new ArrayList<Hosts>();
        Map<String, Hosts> errorMap = new TreeMap<String, Hosts>();
        /*List<HardwareModel> hardwareModelList = hardwareModelDao.findAll();
        Map<String, HardwareModel> hardwareModelMap = new TreeMap<String, HardwareModel>();
        for (HardwareModel h : hardwareModelList) {
            hardwareModelMap.put(h.getVersion(), h);
        }*/
        for (Hosts h : hostses) {
            h.setCheckMsg("");
            System.out.println(h.getCfgUpdTime());
            if (null == h.getHardware() || "".equals(h.getHardware())) {
                h.setCheckMsg(new StringBuffer(h.getCheckMsg()).append("hardware version is null!").toString());
                errorMap.put(h.getSerialno(), h);
                continue;
            } else {
                HardwareModel hardwareModel = hardwareModelDao.findByVersionIs(h.getHardware());
                if (null == hardwareModel) {
                    HardwareModel hm = new HardwareModel();
                    hm.setVersion(h.getHardware());
                    HardwareModel hmNew = hardwareModelDao.save(hm);
                    h.setHwid(hmNew.getId());
                } else {
                    h.setHwid(hardwareModel.getId());
                }
            }

            if (null == h.getSerialno() || "".equals(h.getSerialno())) {
                h.setCheckMsg(new StringBuffer(h.getCheckMsg()).append("mac is null!").toString());
                errorMap.put(h.getSerialno(), h);
                continue;
            } else {
                Hosts hosts = hostsDao.findBySerialnoIs(h.getSerialno());
                if (null != hosts) {
                    h.setCheckMsg(new StringBuffer(h.getCheckMsg()).append("ap is exsit!").toString());
                    errorMap.put(h.getSerialno(), h);
                    continue;
                }else{
                    h.setReboot(false);
                    h.setFactoryReset(false);
                }
            }

            if (null == h.getBus() || null == h.getBus().getLicensePlate() || "".equals(h.getBus().getLicensePlate())) {
                h.setCheckMsg(new StringBuffer(h.getCheckMsg()).append("licensePlate is null!").toString());
                errorMap.put(h.getSerialno(), h);
                continue;
            } else {
                String licensePlate = h.getBus().getLicensePlate();
                Bus bus = busDao.findByLicensePlateIs(licensePlate);
                if (null != bus) {
                    h.setCheckMsg(new StringBuffer(h.getCheckMsg()).append("bus is exsit!").toString());
                    errorMap.put(h.getSerialno(), h);
                    continue;
                }
            }
            if (null == h.getBus() || null == h.getBus().getSubGroup() || null == h.getBus().getSubGroup().getName() || "".equals(h.getBus().getSubGroup().getName())) {
                h.setCheckMsg(new StringBuffer(h.getCheckMsg()).append("group is null!").toString());
                errorMap.put(h.getSerialno(), h);
                continue;
            } else {
                String groupName = h.getBus().getSubGroup().getName();
                SubGroup subGroup = subGroupDao.findByNameIs(groupName);
                if (null == subGroup) {
                    SubGroup sgNew = subGroupDao.save(h.getBus().getSubGroup());
                    h.getBus().setSubGroup(sgNew);
                } else {
                    h.getBus().setSubGroup(subGroup);
                }
            }
        }

        if (errorMap.isEmpty()) {
            for (Hosts h : hostses) {

                Bus bus = busDao.save(h.getBus());
                h.setBus(bus);
                Hosts hosts = hostsDao.save(h);
                busDao.setHostsById(hosts.getId(),bus.getId());
                String name = "conreq"+hosts.getId();
                hosts.setProfileName(name);
                DeviceProfile deviceProfile = new DeviceProfile();
                deviceProfile.setName(name);
                deviceProfile.setScriptName(name);
                deviceProfile.setInforminterval(30);
                Script script = new Script();
                script.setName(name);
                scriptDao.save(script);
                deviceProfileDao.save(deviceProfile);
                hostsDao.setProfileName(name,hosts.getId());
            }
            /*List<Hosts> hostsList = hostsDao.save(hostses);
            List<DeviceProfile> deviceProfileList = new ArrayList<DeviceProfile>();
            List<Script> scriptList = new ArrayList<Script>();

            for(Hosts hosts:hostsList){
                String name = "conreq"+hosts.getId();
                hosts.setProfileName(name);
                DeviceProfile deviceProfile = new DeviceProfile();
                deviceProfile.setName(name);
                deviceProfile.setScriptName(name);
                deviceProfile.setInforminterval(30);
                deviceProfileList.add(deviceProfile);
                Script script = new Script();
                script.setName(name);
                scriptList.add(script);
            }
            hostsDao.save(hostsList);
            deviceProfileDao.save(deviceProfileList);
            scriptDao.save(scriptList);*/
        } else {
            for (Map.Entry<String, Hosts> entry : errorMap.entrySet()) {
                error.add(entry.getValue());
            }
        }

        return error;
    }

    public List<Hosts> findAllBinding() {
        List<Hosts> hostses = hostsDao.findByBusIsNotNull();
        for (Hosts h : hostses) {
            h.initDevicestatus();
        }
        return hostses;
    }

    public Hosts findApByID(Integer id) {
        Hosts h = hostsDao.findOne(id);
        return h;
    }

    public List<Hosts> findByOffline(Long time) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - time);
//        List<Hosts> hostses = hostsDao.findOffline(timestamp);
        List<Hosts> hostses = hostsDao.findByLastContactLessThanAndBusIsNotNull(timestamp);
        for (Hosts h : hostses) {
            System.out.println(h.getId() + "\t");
            if (null != h.getBus()) {
                System.out.println(h.getBus().getId());
            }
        }
        return hostses;
    }

    public void setReboot() {

    }

    public void genConifgScript(Hosts hosts) {
        genSsidScript(hosts);

    }


    public void genConifgScriptAndRequest(Hosts hosts) {
        genConfigScript(hosts);

    }

    private void genSsidScript(Hosts hosts) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("var parameters = new Array ();").append("\n");
        sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.Enable', value: '1'};").append("\n");
        i++;
        sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.SSID', value: '").append(hosts.getSsid())
                .append("'};").append("\n");
        i++;

        sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WPAAuthenticationMode', value: '")
                .append(hosts.getWPAEncryptionmodes()).append("'};").append("\n");
        i++;
        sb.append("parameters[").append(i)
                .append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1.PreSharedKey', value: '")
                .append(hosts.getWpapass()).append("'};").append("\n");

        sb.append("cpe.SetParameterValues (parameters, \"commandKey\");");

        System.out.println(sb.toString());
//        Hosts h = null;
        String id = "conreq" + hosts.getId();
        Script sl = scriptDao.findOne(id);
        DeviceProfile dp = deviceProfileDao.findOne(id);

        if (null == dp) {
            dp = new DeviceProfile();
            dp.setName(id);
            dp.setScriptName(id);
            dp.setInforminterval(30);
            deviceProfileDao.save(dp);
        }
        if (null == sl) {
            sl = new Script();
            sl.setName(id);
        }
        sl.setScript(sb.toString());
        scriptDao.save(sl);
        hostsDao.setProfileName(id, hosts.getId());
    }

    private void genConfigScript(Hosts hosts) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("var parameters = new Array ();").append("\n");
        sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.Enable', value: '1'};").append("\n");
        i++;
        sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.Channel', value: '")
                .append(hosts.getChannel()).append("'};").append("\n");
        i++;
        sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.SSID', value: '").append(hosts.getSsid())
                .append("'};").append("\n");
        i++;
        sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.ASWE_wlancfg.1.BandWidth', value: '")
                .append(hosts.getBandWidth()).append("'};").append("\n");
        i++;

        String basicEncryptionmodes = hosts.getBasicEncryptionmodes();
        if (basicEncryptionmodes.equals("wep")) {
            sb.append("parameters[").append(i)
                    .append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.BasicEncryptionModes', value: 'WEPEncryption'};").append("\n");
            i++;
            int p = 0;
            String wepkey = hosts.getWepKey();
            if (null != wepkey && wepkey.equals("key1")) {
                p = 1;
            } else if (null != wepkey && wepkey.equals("key2")) {
                p = 2;
            } else if (null != wepkey && wepkey.equals("key3")) {
                p = 3;
            } else if (null != wepkey && wepkey.equals("key4")) {
                p = 4;
            }
            sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WEPKeyIndex', value: '").append(p)
                    .append("'};").append("\n");
            i++;
            sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WEPKey.").append(p)
                    .append(".WEPKey', value: '").append(hosts.getWpapass()).append("'};").append("\n");

        } else if (basicEncryptionmodes.equals("wpapsk")) {
            sb.append("parameters[").append(i)
                    .append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.BasicEncryptionModes', value: 'None'};").append("\n");
            i++;
            // 开启wpa安全模式
            sb.append("parameters[").append(i)
                    .append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WPAAuthenticationMode', value: 'PSKAuthentication'};")
                    .append("\n");
            i++;
            // 设置加密算法
            sb.append("parameters[").append(i).append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.WPAEncryptionModes', value: '")
                    .append(hosts.getWPAEncryptionmodes()).append("'};").append("\n");
            i++;
            sb.append("parameters[").append(i)
                    .append("] = {name: 'InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1.PreSharedKey', value: '")
                    .append(hosts.getWpapass()).append("'};").append("\n");
        }

        sb.append("cpe.SetParameterValues (parameters, \"commandKey\");");

        System.out.println(hosts.getSsid() + hosts.getChannel());
        System.out.println(sb.toString());
//        Hosts h = null;
        String id = "conreq" + hosts.getId();
        Script sl = scriptDao.findOne(id);
        DeviceProfile dp = deviceProfileDao.findOne(id);

        if (null == dp) {
            dp = new DeviceProfile();
            dp.setName(id);
            dp.setScriptName(id);
            dp.setInforminterval(30);
            deviceProfileDao.save(dp);
        }
        if (null == sl) {
            sl = new Script();
            sl.setName(id);
        }
        sl.setScript(sb.toString());
        scriptDao.save(sl);
        hostsDao.setProfileName(id, hosts.getId());
    }
}

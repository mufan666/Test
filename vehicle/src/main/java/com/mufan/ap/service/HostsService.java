package com.mufan.ap.service;

import com.mufan.ap.domain.Hosts;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by aswe on 2016/7/21.
 */
public interface HostsService {
    public void genConifgScript(Hosts hosts);

    List<Hosts> findByOffline(Long time);

    List<Hosts> findAllBinding();

    Hosts findApByID(Integer id);

    List<Hosts> batchInsert(List<Hosts> hostses);
    /*void setReboot(Integer id);

    void setFactoryReset(Integer id);*/
}

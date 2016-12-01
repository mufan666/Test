package com.mufan.bus.service;

import com.mufan.bus.dao.BusDao;
import com.mufan.bus.domain.Bus;
import com.mufan.bus.domain.SubGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by aswe on 2016/8/7.
 */
@Service("busService")
public class BusServiceImpl implements BusService {
    @Resource
    private BusDao busDao;
    public void changeSubgroup(Bus bus) {
        busDao.setSubGroup(bus.getSubGroup().getId(),bus.getId());
    }

    public Bus findById(Long id) {
        return busDao.findOne(id);
    }

    public void updateBus(Bus bus) {
        busDao.save(bus);
    }
}

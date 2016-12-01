package com.mufan.bus.service;

import com.mufan.bus.dao.SubGroupDao;
import com.mufan.bus.domain.Bus;
import com.mufan.bus.domain.SubGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by aswe on 2016/7/25.
 */
@Service("subGroupService")
public class SubGroupServiceImpl implements SubGroupService {

    @Resource
    private SubGroupDao subGroupDao;

    public SubGroup findSubGroup(SubGroup sg) {

        SubGroup subGroup = subGroupDao.findOne(sg.getId());
        List<Bus> buses = subGroup.getBuses();
        for(Bus bus:buses){
            if(null!=bus.getHosts())
                bus.getHosts().initDevicestatus();
        }
        return subGroup;
    }

    public List<SubGroup> findAllGroup() {
        return subGroupDao.findAll();
    }
}

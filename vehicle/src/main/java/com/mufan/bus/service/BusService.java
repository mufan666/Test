package com.mufan.bus.service;

import com.mufan.bus.domain.Bus;
import com.mufan.bus.domain.SubGroup;

import java.util.List;

/**
 * Created by aswe on 2016/7/25.
 */
public interface BusService {
    void changeSubgroup(Bus bus);

    Bus findById(Long id);

    void updateBus(Bus bus);
}

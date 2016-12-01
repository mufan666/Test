package com.mufan.bus.service;

import com.mufan.bus.domain.SubGroup;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aswe on 2016/7/25.
 */

public interface SubGroupService {

    public SubGroup  findSubGroup(SubGroup sg);

    List<SubGroup> findAllGroup();


}

package com.mufan.bus.dao;

import com.mufan.bus.domain.SubGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by aswe on 2016/7/23.
 */
@Transactional
public interface SubGroupDao extends JpaRepository<SubGroup,Integer> {

    SubGroup findByNameIs(String name);
}

package com.mufan.ap.dao;

import com.mufan.ap.domain.HardwareModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by aswe on 2016/8/5.
 */
public interface HardwareModelDao extends JpaRepository<HardwareModel,Integer> {

    HardwareModel findByVersionIs(String version);
}

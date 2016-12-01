package com.mufan.bus.dao;

import com.mufan.bus.domain.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by aswe on 2016/7/23.
 */
@Transactional
public interface BusDao extends JpaRepository<Bus,Long>,JpaSpecificationExecutor{

    @Modifying
    @Query("select count(b) from Bus b where b.subGroup = :subGroup")
    public int countByGroupId(@Param("subGroup") Integer subGroup);

    Bus findByLicensePlateIs(String licensePlate);

    //设置关联bus
    @Modifying(clearAutomatically = true)
    @Query(value = "update Bus h set h.hostsId=:hosts where h.id=:id",nativeQuery = true)
    int setHostsById(@Param("hosts") Integer hosts,@Param("id") Long id);

    //设置关联bus
    @Modifying(clearAutomatically = true)
    @Query("update Bus h set h.hosts=null where h.id=?1")
    int cancelHostsById(Long id);

    //设置关联subgroup
    @Modifying(clearAutomatically = true)
    @Query(value = "update Bus h set h.subgroup_id=:subGroupId where h.id=:id",nativeQuery = true)
    int setSubGroup(@Param("subGroupId") Integer subGroupId,@Param("id") Long id);
}

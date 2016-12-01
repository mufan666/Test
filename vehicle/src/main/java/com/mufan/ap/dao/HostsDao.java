package com.mufan.ap.dao;

import com.mufan.ap.domain.Hosts;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by aswe on 2016/7/20.
 */
@Transactional
public interface HostsDao extends JpaRepository<Hosts,Integer> {

    @Modifying(clearAutomatically = true)
    @Query("update Hosts h set h.reboot=?1 where h.id=?2")
    int setReboot(boolean reboot,Integer id);

    @Modifying(clearAutomatically = true)
    @Query("update Hosts h set h.factoryReset=?1 where h.id=?2")
    int setFactoryReset(boolean factoryReset,Integer id);

    @Modifying(clearAutomatically = true)
    @Query("update Hosts h set h.profileName=?1 where h.id=?2")
    int setProfileName(String profileName,Integer id);

    @Modifying(clearAutomatically = true)
    @Query("update Hosts h set h.lastContact=?1 where h.id=?2")
    int setLastContact(Timestamp timestamp, Integer id);

    @Modifying(clearAutomatically = true)
    @Query("update Hosts h set h.lastContact=?1 where h.id=?2")
    int setLastContactAsDate(Date timestamp, Integer id);

    @Query("select h from Hosts h where h.lastContact < :timestamp and h.bus is not null")
    List<Hosts> findOffline(@Param("timestamp") Date timestamp);

    //查看所有关联的ap，且lastContact小于...
    List<Hosts> findByLastContactLessThanAndBusIsNotNull(Timestamp timestamp);
    //查找 所有已经关联bus的ap
    List<Hosts> findByBusIsNotNull();

    //根据mac查找ap
    Hosts findBySerialnoIs(String serialno);

    //设置关联bus
    @Modifying(clearAutomatically = true)
    @Query(value = "update HostsBean h set h.busid=?1 where h.id=?2",nativeQuery = true)
    int setBusById(long busId,Integer id);

    //设置关联bus
    @Modifying(clearAutomatically = true)
    @Query("update Hosts h set h.bus=null where h.id=?1")
    int cancelBusById(Integer id);
}

package com.mufan.movie.dao;

import com.mufan.movie.domain.PublishFilmVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by aswe on 2016/8/5.
 */
@Transactional
public interface PublishFilmVersionDao extends JpaRepository<PublishFilmVersion,Long> {
    @Modifying(clearAutomatically = true)
    @Query("update PublishFilmVersion p set p.version=:version where p.id=1")
    int updateVersion(@Param("version")long version);

    @Modifying(clearAutomatically = true)
    @Query(value = "insert into PublishFilmVersion(id,version) values(1,:version)",nativeQuery = true)
    int insertVersion(@Param("version")long version);
}

package com.mufan.movie.dao;

import com.mufan.movie.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by aswe on 2016/7/30.
 */

@Transactional
public interface FilmDao extends JpaRepository<Film, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Film f set f.filmcode=:filmcode,f.filmFileName=:filmFileName,f.filmSuffix=:filmSuffix,f.filmpath=:filmpath,f.filmsize=:filmsize where f.id = :id")
    int updateFilmInfo(@Param("filmcode") String filmcode, @Param("filmFileName") String filmFileName,
                       @Param("filmSuffix") String filmSuffix, @Param("filmpath") String filmpath,@Param("filmsize") long filmsize, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Film f set f.imagecode=:imagecode,f.imageFileName=:imageFileName,f.imageSuffix=:imageSuffix,f.imagepath=:imagepath,f.imagesize=:imagesize where f.id = :id")
    int updateImageInfo(@Param("imagecode") String imagecode, @Param("imageFileName") String imageFileName,
                       @Param("imageSuffix") String imageSuffix, @Param("imagepath") String imagepath, @Param("imagesize")long imagesize, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Film f set f.publish = true where id=:id")
    int updateFilmPublish(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Film f set f.publish = false where id=:id")
    int updateFilmUnPublish(@Param("id") Long id);

    List<Film> findByPublishIsTrue();
}

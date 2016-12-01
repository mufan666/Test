package com.mufan.ap.dao;

import com.mufan.ap.domain.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by aswe on 2016/7/21.
 */
@Transactional
public interface ScriptDao extends JpaRepository<Script,String> {

}

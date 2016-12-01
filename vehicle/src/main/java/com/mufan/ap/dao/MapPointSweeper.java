package com.mufan.ap.dao;

import com.mufan.ap.domain.Hosts;
import com.mufan.ap.domain.MapPoint;

/**
 * Created by aswe on 2016/9/23.
 */
public interface MapPointSweeper {
    public void insertSweep(MapPoint mapPoint);
    public Hosts isExist(String apMac);
}

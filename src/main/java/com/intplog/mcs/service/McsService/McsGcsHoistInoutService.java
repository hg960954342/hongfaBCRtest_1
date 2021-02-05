package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsGcsHoistInout;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/7
 */
public interface McsGcsHoistInoutService {
    /**
     *rgv到位提升机
     * @param
     * @return
     */
    McsGcsHoistInout getHoistName(String type);
    //rgv是否到位
    McsGcsHoistInout getInPlaces(String inPlace);
    //插入数据
    int insertData(McsGcsHoistInout mcsGcsHoistInout);
    //更新数据
    int updateDataIs(McsGcsHoistInout mcsGcsHoistInout);
    //删除数据
    int deleteData(String id);

    List<McsGcsHoistInout> getId(String id);
}

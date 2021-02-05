package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsBcrLog;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

/**
 * @program: mcs
 * @description
 * @author tianlei
 * @create: 2020-02-22 16:16
 **/
public interface McsBcrLogService {
    PageData getAll(String createTime,String boxNum,String bcrNum,int pageNum, int pageSize);

    McsBcrLog getMcsLogById(String id);

    int deleteMcsLog(int day);

    int insertMcsLog(McsBcrLog mcsLog);

    void insertMcsLog(String id, String code);

    int updateMcsLog(McsBcrLog mcsLog);

    List<McsBcrLog> getListExcel(String createTime,String boxNum,String bcrNum);
}

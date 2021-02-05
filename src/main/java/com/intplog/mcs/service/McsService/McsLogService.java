package com.intplog.mcs.service.McsService;


import com.intplog.mcs.bean.model.McsModel.McsLog;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;


/**
 * @author quqingmao
 * @date 2019-10-11
 */
public interface McsLogService {

        PageData getAll(String createTime, int pageNum, int pageSize);

        McsLog getMcsLogById(String id);

        int deleteMcsLog(int day);

        int insertMcsLog(McsLog mcsLog);

        int updateMcsLog(McsLog mcsLog);

        List<McsLog> getListExcel(String createTime);
}

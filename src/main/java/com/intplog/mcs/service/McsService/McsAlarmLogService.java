package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsAlarmLog;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

/**
 * @program: wcs2
 * @description
 * @author: Tianlei
 * @create: 2019-10-12 10:01
 **/
public interface McsAlarmLogService {
        PageData getAll(String createTime, String id, String type, int pageNum, int pageSize);

        int deleteLog(int day);

        int insertMcsAlarmLog(McsAlarmLog mcsAlarmLog);

        int updateMcsAlarmLog(McsAlarmLog mcsAlarmLog);

        McsAlarmLog getMcsAlarmLogById(String id);

        List<McsAlarmLog> getList(String createTime, String id, String type);
}

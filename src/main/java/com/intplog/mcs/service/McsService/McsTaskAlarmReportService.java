package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsTaskAlarmReport;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;

import java.util.List;

/**
 * @program: mcs
 * @description
 * @author: tianlei
 * @create: 2019-11-06 11:57
 **/
public interface McsTaskAlarmReportService {

        List<McsTaskAlarmReport> getAll();

        int insertMcsAlarmReport(McsTaskAlarmReport mcsTaskAlarmReport);
        int insertByMcsTaskInfo(McsTaskInfo mcsTaskInfo,int status,int type,String msg);

        int batchInsert(List<McsTaskAlarmReport> mcsTaskAlarmReports);

        List<McsTaskAlarmReport> getListByReportStatus(int reportStatus);

        int update(McsTaskAlarmReport mcsTaskAlarmReport);

}

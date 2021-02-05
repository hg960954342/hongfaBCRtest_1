package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsCrossLayerAlarm;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerReport;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

public interface McsCrossLayerReportService {
    //查询所有数据
    PageData getAll(String taskId, String hoistId, int pageNum, int pageSize);
    //根据状态和提升机编号查询
    List<McsCrossLayerReport> getByStatusAndHoist(int status, String hoistId);
    //根据状态和提升机编号查询
    List<McsCrossLayerReport> getByStatus(int status);
    McsCrossLayerReport getByTaskId(String taskId);
    McsCrossLayerReport getByTaskIdAndStatus(String taskId,int status);
    //更新数据
    int update(McsCrossLayerReport mcsCrossLayerReport);
    //插入数据
    int insert(McsCrossLayerReport mcsCrossLayerReport);
    int insertCrossLayerReport(McsCrossLayerTask mcsCrossLayerTask,int status);

    //更新数据
    int delete(String  id);
}

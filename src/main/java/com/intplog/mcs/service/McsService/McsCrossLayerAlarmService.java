package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsCrossLayerAlarm;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

public interface McsCrossLayerAlarmService {
    //查询所有数据
    PageData getAll(String taskId, String hoistId, int pageNum, int pageSize);
    //根据状态和提升机编号查询
    List<McsCrossLayerAlarm> getByStatusAndHoist(int status, String hoistId);
    //根据状态和提升机编号查询
    List<McsCrossLayerAlarm> getByStatus(int status);
    McsCrossLayerAlarm getByTaskId(String taskId);
    //更新数据
    int update(McsCrossLayerAlarm mcsCrossLayerAlarm);
    //插入数据
    int insert(McsCrossLayerAlarm mcsCrossLayerAlarm);

    //更新数据
    int delete(String  id);
}

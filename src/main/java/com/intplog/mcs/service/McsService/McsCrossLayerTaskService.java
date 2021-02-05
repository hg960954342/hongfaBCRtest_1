package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.viewmodel.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McsCrossLayerTaskService {
    //查询所有数据
    PageData getAll(String taskId, String floor, int pageNum, int pageSize);
    //根据状态和提升机编号查询
    List<McsCrossLayerTask> getByStatusAndHoist(int status, String hoistId);
    McsCrossLayerTask getByTaskId(String taskId);
    McsCrossLayerTask getByTaskIdAndStatus(String taskId,int status);
    //更新数据
    int update(McsCrossLayerTask mcsCrossLayerTask);
    //插入数据
    int insert(McsCrossLayerTask mcsCrossLayerTask);
    List<McsCrossLayerTask> getLayerTask();
    //更新数据
    int delete(String  id);
}

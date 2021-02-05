package com.intplog.mcs.service.McsService;


import com.intplog.mcs.bean.model.McsModel.McsClearTask;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

public interface McsClearTaskService {
    //查询所有数据
    PageData getAll(String taskId, String clearCrood, int pageNum, int pageSize);
    //根据状态和提升机编号查询
    List<McsClearTask> getByStatusAndHoist(int status,String hoistId);
    //根据状态和任务类型号查询
    List<McsClearTask> getByStatusAndType(int status,int type);
    //根据状态和提升机编号及PLC编号
    List<McsClearTask> getByStatusAndPlcAndAddress(int status,int addressType,String plcId);
    McsClearTask getByTaskId(String taskId);
    //更新数据
    int update(McsClearTask mcsClearTask);
    int updateByTaskId(String taskId,int status);
    //插入数据
    int insert(McsClearTask mcsClearTask);

    int insertGcsClearTask(McsTaskInfo mcsTaskInfo);

    //更新数据
    int delete(String  id);

    int delete(int data);
}

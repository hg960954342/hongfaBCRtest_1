package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsPlcVariable1;
import com.intplog.mcs.bean.model.McsModel.McsTriggerTask;
import com.intplog.mcs.bean.viewmodel.PageData;

/**
 * @author quqingmao
 * @date 2020/9/21
 */
public interface McsTriggerTaskService {

    PageData getAll(String mcsId, String taskId, int pageNum, int pageSize);
    //查询可用数据
    McsTriggerTask getMcsTriggerTaskData(String bcrId , int status);
    //查询最新注册条码
    McsTriggerTask getMcsTriggerTaskDataTheLatest();

    McsPlcVariable1 getMcsPlcVariable1(String bcrId);

    //插入触发数据
    int insertMcsTriggerTaskData(McsTriggerTask mcsTriggerTask);
    //更新数据
    int updateMcsTriggerTaskData(McsTriggerTask mcsTriggerTask);
    //删除数据
    int deleteMcsTriggerTaskData(int day);
    //根据状态删除数据
    int deleteStatusData(int status);
    McsTriggerTask getMcsTriggerTaskId(String bcrId);
}

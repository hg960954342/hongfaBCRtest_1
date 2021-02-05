package com.intplog.mcs.service.EisService;

import com.intplog.mcs.bean.model.EisModel.EisContainerBind;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

/**
 * @author tianlei
 * @Date 2019-10-31 11:50
 * 子母托盘绑定 MCS-EIS
 */
public interface EisContainerBindService {

    PageData getList(int status, String taskId, int pageNum, int pageSize);

    List<EisContainerBind> getAll();
    //根据状态查询
    List<EisContainerBind> getMcsContainerBindByStatus(int status);

    //根据状态查询和任务编号查询
    EisContainerBind getByStatusAndCode(int status, String taskId);

    int update(EisContainerBind eisContainerBind);
    int updateByTaskId(int status,String taskId);

    int updateByReport(int status,String taskId);

    //单个插入
    int insertMcsContainerBind(EisContainerBind mcsContainerBind);
    ///读码器上报插入托盘绑定回告表
    void insertMcsContainer(String id, String code);

    //称数据上报更新到对应字母托绑定表
    void updateByWeight(String weight, String address);

    //批量插入
    int batchInsert(List<EisContainerBind> mcsContainerBindList);

    //根据原位置和状态查询任务
    List<EisContainerBind>getListBySource(String source, int status);

    //根据原位置和状态查询任务
    EisContainerBind getEisBind(String source, int status);

    //根据任务号查询
    EisContainerBind getMcsContainerBindByTaskCode(String taskId);

    PageData deleteByTaskId(String taskId);
}


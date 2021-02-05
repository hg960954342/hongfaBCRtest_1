package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.model.McsModel.McsTaskOrderReport;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.Date;
import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-06 16:46
 **/
public interface McsTaskOrderReportService {

    PageData getAll(String taskId,String reportStatus,String createTime,int pageNum, int pageSize);

    /**
     * 查询所有数据
     * @return
     */
    List<McsTaskOrderReport>getAllList();

    /**
     * 根据Eis流水号查询
     * @param taskId
     * @return
     */
    List<McsTaskOrderReport>getListByTaskId(String taskId);
    /**
     * 根据Eis流水号查询
     * @param taskId
     * @return
     */
    McsTaskOrderReport getListByTaskIdAndStatus(String taskId ,int status);

    /**
     * 根据状态查询
     * @param reportStatus
     * @return
     */
    List<McsTaskOrderReport>getListByReportStatus(int reportStatus);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    McsTaskOrderReport getListById(String id);

    /**
     * 添加数据
     * @param mcsTaskOrderReport
     * @return
     */
    int insert(McsTaskOrderReport mcsTaskOrderReport);

    /**
     * 更新数据
     * @param mcsTaskOrderReport
     * @return
     */
    int update(McsTaskOrderReport mcsTaskOrderReport);

    int updateById(int status, String id, Date date);

    /**
     * 删除数据
     * @param day
     * @return
     */
    int deleteByCreateTime(int day);

    int  insertReport(McsTaskInfo mcsTaskInfo,int status,int reportStatus);
}

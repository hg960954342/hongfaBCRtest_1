package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsTaskReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/15
 */

public interface McsTaskReturnService {

    //查询所有数据
    List<McsTaskReturn> getAll();
    //根据上报状态查询数据
    public List<McsTaskReturn> getListByReportStatus(int status);
    //任务状态查询
    public List<McsTaskReturn> getListByReportTaskStatus(int status , String taskId);
    //任务号查询
    public List<McsTaskReturn> getListByReportTask(String taskId);
    //任务类型查询
    public List<McsTaskReturn> getListByReportType(@Param("type") int type);

    //回告eis查询
    public List<McsTaskReturn>  eisTaskReturn( int status,int reportStatus);

    //插入数据
    int insert(McsTaskReturn mcsTaskReturn);
    //更新数据
    int update(McsTaskReturn mcsTaskReturn);
    //删除数据
    int delete(String taskId);

    int deleteDate(int data);
}

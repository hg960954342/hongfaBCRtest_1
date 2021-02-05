package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.viewmodel.PageData;
import org.apache.ibatis.annotations.Param;
import org.w3c.dom.ls.LSInput;

import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-06 10:25
 **/
public interface McsTaskInfoService {

    //查询所有数据
    PageData getAll( String mcsId, String taskId, int pageNum, int pageSize);

    List<McsTaskInfo> getRefuseList(int layer);

    //根据状态查询数据
    List<McsTaskInfo> getByStatus(int status);

    List<McsTaskInfo> getByStatusAndHoist(String hoistId,int status ,int type);


    List<McsTaskInfo>  getListMove(int status ,int type);
    McsTaskInfo getListByTaskStatus(String taskId , int status);
    McsTaskInfo getBySourceAndStatus(String crood,int status);
    McsTaskInfo getByStatusAndCroodAndHoist(String hoistId,String crood,int status);

    //根据任务号查询
    McsTaskInfo getByTaskId(String taskId);

    //根据mcsId查询
    McsTaskInfo getByMcsId(String mcsId);

    //根据状态和任务号查询
    McsTaskInfo getByStatusAndMcsId(String mcsId, int status);
    int getCountByStatus(String hoistId,int status);
    //插入数据
    int insert(McsTaskInfo mcsTaskInfo);

    //更新数据
    int update(McsTaskInfo mcsTaskInfo);

    //删除几天前数据
    int deleteByDay(int day);

    int deleteId(String id);

    int deleteStatus(int status);

}

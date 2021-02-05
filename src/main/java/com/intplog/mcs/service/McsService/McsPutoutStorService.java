package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsPutoutStor;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/13
 */
public interface McsPutoutStorService {


    //查询所有数据
    PageData getAll(String mcsId, String taskId, int pageNum, int pageSize);

    List<McsPutoutStor> getRefuseList(int layer);

    //根据状态查询数据
    List<McsPutoutStor> getByStatus(int status);

    List<McsPutoutStor> getByStatusAndHoist(String hoistId,int status ,int type );


    List<McsPutoutStor>  getListMove(int status ,int type);

    McsPutoutStor getListByTaskStatus(String taskId , int status);
    McsPutoutStor getBySourceAndStatus(String crood,int status);
    McsPutoutStor getByStatusAndCroodAndHoist(String hoistId,String crood,int status);

    //根据任务号查询
    McsPutoutStor getByTaskId(String taskId);

    //根据mcsId查询
    McsPutoutStor getByMcsId(String mcsId);

    //根据状态和任务号查询
    McsPutoutStor getByStatusAndMcsId(String mcsId, int status);
    int getCountByStatus(String hoistId,int status);
    //插入数据
    int insert(McsPutoutStor mcsTaskInfo);

    //更新数据
    int update(McsPutoutStor mcsTaskInfo);

    //删除几天前数据
    int deleteByDay(int day);

    int deleteId(String id);

    int deleteStatus(int status);
}

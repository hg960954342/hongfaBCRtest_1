package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.model.McsModel.McsRgvChangeStoryTask;
import com.intplog.mcs.bean.viewmodel.PageData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/14
 */
public interface McsRgvChangeStoryTaskService {

    PageData getAllPageData(String taskId, String hoistId, int pageNum, int pageSize);

    //换层任务id查询
    List<McsRgvChangeStoryTask> getList(String taskId);
    //查询所有
    List<McsRgvChangeStoryTask> getAll();

    McsRgvChangeStoryTask getId(String id);

    //状态查询
    List<McsRgvChangeStoryTask> getByStatus(int status);


    List<McsRgvChangeStoryTask> getGcsStatus();

    List<McsRgvChangeStoryTask> getByStatusAndHoist(int status, String hoistId);

    //任务状态查询
   List<McsRgvChangeStoryTask>  getByTaskId(String taskId);

    McsRgvChangeStoryTask getIdStatus(String id , int status);
    //插入数据
    int insert(McsRgvChangeStoryTask mcsRgvChangeStoryTask);

    List<McsCrossLayerTask> getLayerTask();

    //更新数据
    int update(McsRgvChangeStoryTask McsRgvChangeStoryTask);

    //删除数据
    int deleteById(String id);

    int deleteDate(int a);

    McsRgvChangeStoryTask getGcsId(int status, String gcsId);

    int deleteStatus(int status);

}

package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface McsCrossLayerTaskMapper {

    @Select({"<script>",
            "select id ,task_id as taskId,floor,task_type as taskType,",
            "create_time as createTime,update_time as updateTime,status," ,
            "hoist_id as hoistId from mcs_cross_layer_task where 1=1",
            "<if test='taskId!=null'>",
            "and task_id = #{taskId}",
            "</if>",
            "<if test='floor!=null'>",
            "and floor = #{floor}",
            "</if>",
            "order by create_time desc",
            "</script>"

    })
    List<McsCrossLayerTask> getList(@Param("taskId") String taskId, @Param("floor") String floor);

    @Select("select id ,task_id as taskId,floor,task_type as taskType," +
            "create_time as createTime,update_time as updateTime,status," +
            "hoist_id as hoistId from mcs_cross_layer_task")
    public List<McsCrossLayerTask> getAll();

    @Select(" select * from  mcs_cross_layer_task where status=#{status}  and hoist_id=#{hoistId}")
    public List<McsCrossLayerTask> getByStatusAndHoist(@Param("status") int status, @Param("hoistId") String hoistId);
    @Select("select id ,task_id as taskId,floor,task_type as taskType," +
            "create_time as createTime,update_time as updateTime,status," +
            "hoist_id as hoistId from mcs_cross_layer_task where task_id=#{taskId}")
    public McsCrossLayerTask getByTaskId(@Param("taskId")String taskId);
    @Select("select * from mcs_cross_layer_task where task_id=#{taskId}" +
            "and status=#{status}")
    public McsCrossLayerTask getByTaskIdAndStatus(@Param("taskId")String taskId,@Param("status")int status);

    @Select("select * from mcs_cross_layer_task where status<10 limit 0,1")
    List<McsCrossLayerTask> getLayerTask();

    @Insert("insert into mcs_cross_layer_task(id,task_id,floor,task_type,create_time,update_time," +
            "status,hoist_id)values(#{id},#{taskId},#{floor},#{taskType},#{createTime}," +
            "#{updateTime},#{status},#{hoistId})")
    public int insert(McsCrossLayerTask mcsCrossLayerTask);

    @Update("update mcs_cross_layer_task set task_id=#{taskId},floor=#{floor},task_type=#{taskType},create_time=#{createTime}," +
            "update_time=#{updateTime},status=#{status},hoist_id=#{hoistId} where id=#{id}")
    public int update(McsCrossLayerTask mcsCrossLayerTask);

    @Delete("delete from mcs_cross_layer_task where id={#id}")
    public int deleteById(@Param("id") String id);
}

package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsCrossLayerReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface McsCrossLayerReportMapper {
    @Select({"<script>",
            "select id ,task_id as taskId,floor,task_type as taskType,create_time as createTime,",
            "update_time as updateTime,work_status as workStatus,report_status as" ,
            " reportStatus,hoist_id as hoistId from mcs_cross_layer_report where 1=1",
            "<if test='taskId!=null'>",
            "and task_id = #{taskId}",
            "</if>",
            "<if test='hoistId!=null'>",
            "and hoist_id = #{hoistId}",
            "</if>",
            "order by create_time desc",
            "</script>"

    })
    List<McsCrossLayerReport> getList(@Param("taskId") String taskId, @Param("hoistId") String hoistId);

    @Select("select id ,task_id as taskId,floor,task_type as taskType  ,work_status as workStatus,report_status as" +
            "reportStatus,create_time as createTime,update_time as updateTime," +
            "hoist_id as hoistId from mcs_cross_layer_report")
    public List<McsCrossLayerReport> getAll();

    @Select("select id ,task_id as taskId,floor,task_type as taskType ,work_status as workStatus,report_status as" +
            "reportStatus,create_time as createTime,update_time as updateTime," +
            "hoist_id as hoistId from mcs_cross_layer_report where report_status=#{reportStatus} and hoist_id" +
            " =#{hoistId}")
    public List<McsCrossLayerReport> getByStatusAndHoist(@Param("reportStatus") int reportStatus, @Param("hoistId") String hoistId);
    @Select("select id ,task_id as taskId,floor,task_type as taskType ,work_status as workStatus,report_status as" +
            "reportStatus,create_time as createTime,update_time as updateTime," +
            "hoist_id as hoistId from mcs_cross_layer_report where task_id=#{taskId}")
    public McsCrossLayerReport getByTaskId(@Param("taskId")String taskId);
    @Select("select id ,task_id as taskId,floor,task_type as taskType ,work_status as workStatus,report_status as" +
            "reportStatus,create_time as createTime,update_time as updateTime," +
            "hoist_id as hoistId from mcs_cross_layer_report where report_status=#{reportStatus}")
    public List<McsCrossLayerReport> getByStatus(@Param("reportStatus")int  reportStatus);

    @Select("select * from mcs_cross_layer_report where work_status=#{workStatus} and task_id=#{taskId}")
    public McsCrossLayerReport getByTaskIdAndStatus(@Param("taskId")String taskId,@Param("workStatus")int  workStatus);


    @Insert("insert into mcs_cross_layer_report(id,task_id,floor,task_type,create_time,update_time," +
            "work_status,report_status,hoist_id)values(#{id},#{taskId},#{floor},#{taskType},#{createTime}," +
            "#{updateTime},#{workStatus},#{reportStatus},#{hoistId})")
    public int insert(McsCrossLayerReport mcsCrossLayerReport);

    @Update("update mcs_cross_layer_report set task_id=#{taskId},floor=#{floor},task_type=#{taskType},create_time=#{createTime}," +
            "update_time=#{updateTime},work_status=#{workStatus},report_status=#{reportStatus},hoist_id=#{hoistId} where id=#{id}")
    public int update(McsCrossLayerReport mcsCrossLayerReport);

    @Delete("delete from mcs_cross_layer_report where id={#id}")
    public int deleteById(@Param("id") String id);
}

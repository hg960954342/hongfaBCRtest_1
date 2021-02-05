package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsCrossLayerAlarm;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import lombok.Getter;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface McsCrossLayerAlarmMapper {
    @Select({"<script>",
            "select id ,task_id as taskId,msg,error_type as errorType,",
            "create_time as createTime,update_time as updateTime,report_status as reportStatus," ,
            "hoist_id as hoistId from mcs_cross_layer_alarm where 1=1",
            "<if test='taskId!=null'>",
            "and task_id = #{taskId}",
            "</if>",
            "<if test='hoistId!=null'>",
            "and floor = #{hoistId}",
            "</if>",
            "order by create_time desc",
            "</script>"

    })
    List<McsCrossLayerAlarm> getList(@Param("taskId") String taskId, @Param("hoistId") String hoistId);

    @Select("select id ,task_id as taskId,msg,error_type as errorType," +
            "create_time as createTime,update_time as updateTime,report_status as reportStatus," +
            "hoist_id as hoistId from mcs_cross_layer_alarm")
    public List<McsCrossLayerAlarm> getAll();

    @Select("select id ,task_id as taskId,msg,error_type as errorType," +
            "create_time as createTime,update_time as updateTime,report_status as reportStatus," +
            "hoist_id as hoistId from mcs_cross_layer_alarm  where report_status=#{reportStatus}")
    public List<McsCrossLayerAlarm> getByStatus(@Param("reportStatus") int reportStatus);


    @Select("select id ,task_id as taskId,msg,error_type as errorType," +
            "create_time as createTime,update_time as updateTime,report_status as reportStatus," +
            "hoist_id as hoistId from mcs_cross_layer_alarm where report_status=#{reportStatus} and hoist_id" +
            " =#{hoistId}")
    public List<McsCrossLayerAlarm> getByStatusAndHoist(@Param("reportStatus") int reportStatus, @Param("hoistId") String hoistId);
    @Select("select id ,task_id as taskId,msg,error_type as errorType," +
            "create_time as createTime,update_time as updateTime,report_status as reportStatus," +
            "hoist_id as hoistId from mcs_cross_layer_alarm where task_id=#{taskId}")
    public McsCrossLayerAlarm getByTaskId(@Param("taskId")String taskId);

    @Insert("insert into mcs_cross_layer_alarm(id,task_id,msg,error_type,create_time,update_time," +
            "report_status,hoist_id)values(#{id},#{taskId},#{msg},#{errorType},#{createTime}," +
            "#{updateTime},#{reportStatus},#{hoistId})")
    public int insert(McsCrossLayerAlarm mcsCrossLayerAlarm);

    @Update("update mcs_cross_layer_alarm set task_id=#{taskId},msg=#{msg},error_type=#{errorType},create_time=#{createTime}," +
            "update_time=#{updateTime},report_status=#{reportStatus},hoist_id=#{hoistId} where id=#{id}")
    public int update(McsCrossLayerAlarm mcsCrossLayerAlarm);

    @Delete("delete from mcs_cross_layer_alarm where id={#id}")
    public int deleteById(@Param("id") String id);
}

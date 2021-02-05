package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.GcsModel.GcsAlarm;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/1
 */
public interface McsGcsAlarmMapper {
    @Select("select * from bis_alarm_report where report_status = #{reportStatus}")
    List<GcsAlarm> getStatusList(@Param("reportStatus") int reportStatus);

    @Select("select * from bis_alarm_report where id=#{id}")
    GcsAlarm getId(@Param("id") String id);

    @Insert("insert bis_alarm_report(id,task_id,container_id,error_type,msg," +
            "report_status,create_time) inserts(#{id},#{taskId},#{containerId}," +
            "#{errorType},#{msg},#{reportStatus},#{createTime})")
    int insert(GcsAlarm gcsAlarm);

    @Update("update set bis_alarm_report id=#{id} , task_id=#{taskId}," +
            "container_id=#{containerId},error_type=#{errorType} ," +
            "msg =#{msg} ,report_status=#{reportStatus},create_time=#{createTime}")
    int update(GcsAlarm gcsAlarm);

    @Delete("delete from bis_alarm_report where report_status = #{reportStatus}")
    int delete(@Param("reportStatus") int reportStatus);
}

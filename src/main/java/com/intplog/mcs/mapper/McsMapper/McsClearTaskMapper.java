package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsClearTask;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface McsClearTaskMapper {


    @Select({"<script>",
            "select id ,task_id as taskId,stock_id as stockId,clear_crood as clearCrood,",
            "create_time as createTime,update_time as updateTime,status,plc_name as plcName,",
            "hoist_id as hoistId,address_type as addressType,type from mcs_clear_task where 1=1",
            "<if test='taskId!=null'>",
            "and task_id = #{taskId}",
            "</if>",
            "<if test='clearCrood!=null'>",
            "and clear_crood = #{clearCrood}",
            "</if>",
            "order by create_time desc",
            "</script>"
    })
    List<McsClearTask> getList(@Param("taskId") String taskId, @Param("clearCrood") String clearCrood);

    @Select("select id ,task_id as taskId,stock_id as stockId,clear_crood as clearCrood," +
            "create_time as createTime,update_time as updateTime,status,plc_name as plcName," +
            "hoist_id as hoistId,address_type as addressType,type from mcs_clear_task")
     List<McsClearTask> getAll();

    @Select("select id ,task_id as taskId,stock_id as stockId,clear_crood as clearCrood," +
            "create_time as createTime,update_time as updateTime,status,plc_name as plcName," +
            "hoist_id as hoistId,address_type as addressType,type from mcs_clear_task where status=#{status} and plc_name" +
            "=#{plcId} and address_type =#{addressType} and type=1")
     List<McsClearTask> getByStatusAndPlcIdAndHoist(@Param("status") int status, @Param("plcId") String plcId, @Param("addressType") int addressType);

    @Select("select id ,task_id as taskId,stock_id as stockId,clear_crood as clearCrood," +
            "create_time as createTime,update_time as updateTime,status,plc_name as plcName," +
            "hoist_id as hoistId,address_type as addressType,type from mcs_clear_task where status=#{status} and hoist_id" +
            " =#{hoistId} and type=1")
     List<McsClearTask> getByStatusAndHoist(@Param("status") int status, @Param("hoistId") String hoistId);

    @Select("select * from mcs_clear_task where status=#{status} and type" +
            " =#{type}")
     List<McsClearTask> getByStatusAndType(@Param("status") int status, @Param("type") int type);

    @Select("select id ,task_id as taskId,stock_id as stockId,clear_crood as clearCrood," +
            "create_time as createTime,update_time as updateTime,status,plc_name as plcName," +
            "hoist_id as hoistId,address_type as addressType,type from mcs_clear_task where task_id=#{taskId}")
     McsClearTask getByTaskId(@Param("taskId") String taskId);

    @Insert("insert into mcs_clear_task(id,task_id,stock_id,clear_crood,create_time,update_time," +
            "status,plc_name,hoist_id,address_type,type)values(#{id},#{taskId},#{stockId},#{clearCrood},#{createTime}," +
            "#{updateTime},#{status},#{plcName},#{hoistId},#{addressType},#{type})")
     int insert(McsClearTask mcsClearTask);

    @Update("update mcs_clear_task set task_id=#{taskId},type=#{type},stock_id=#{stockId},clear_crood=#{clearCrood},create_time=#{createTime}," +
            "update_time=#{updateTime},plc_name=#{plcName},hoist_id=#{hoistId},address_type=#{addressType},status=#{status},type=#{type}" +
            " where id=#{id}")
     int update(McsClearTask mcsClearTask);
    @Update("update mcs_clear_task set update_time=#{updateTime},status=#{status} where task_id=#{taskId}")
    int updateByTaskId(@Param("taskId") String taskId, @Param("status")int status, @Param("updateTime")Date updateTime);

    @Delete("delete from mcs_clear_task where id={#id}")
     int deleteById(@Param("id") String id);

    @Delete("delete from mcs_clear_task where rq_time < #{date}")
    public int deleteLog(@Param("date") Date date);
}

package com.intplog.mcs.mapper.McsMapper;


import com.intplog.mcs.bean.model.McsModel.McsTaskOrderReport;
import com.intplog.mcs.bean.model.McsModel.McsTaskReturn;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/15
 */
public interface McsTaskReturnMapper {

    /**
     * 查询所有数据
     *
     * @return
     */
    @Select(" select * from mcs_task_return order by create_time")
    List<McsTaskReturn> getAll();

    /**
     * 根据上报状态查询数据
     *
     * @param status
     * @return
     */
    @Select(" select * from mcs_task_return where " +
            "status=#{status} order by create_time ")
    public List<McsTaskReturn> getListByReportStatus(@Param("status") int status);

    /**
     * 任务状态查询
     * @param status
     * @param taskId
     * @return
     */
    @Select(" select * from mcs_task_return where " +
            "status=#{status} and task_id = #{taskId} order by create_time ")
    public List<McsTaskReturn> getListByReportTaskStatus(@Param("status") int status ,@Param("taskId") String taskId);

    @Select("select * from mcs_task_return where status=#{status} and report_status=#{reportStatus}")
    public List<McsTaskReturn>  eisTaskReturn(@Param("status") int status,@Param("reportStatus") int reportStatus);
    /**
     * 任务号查询
     * @param taskId
     * @return
     */
    @Select(" select * from mcs_task_return where " +
            "task_id = #{taskId} order by create_time ")
    public List<McsTaskReturn> getListByReportTask(@Param("taskId") String taskId);

    /**
     * 任务类型查询
     * @param type
     * @return
     */
    @Select(" select * from mcs_task_return where " +
            "type=#{type} order by create_time ")
    public List<McsTaskReturn> getListByReportType(@Param("type") int type);


    @Insert("insert into mcs_task_return (id,task_id,mcs_id,detection,type ," +
            "container_no ,rgv_id,address,status,create_time,report_status," +
            "update_time) values(#{id},#{taskId},#{mcsId},#{detection},#{type},#{containerNo}," +
            "#{rgvId} ,#{address} ,#{status},#{createTime},#{reportStatus},#{updateTime})")
    int insert(McsTaskReturn mcsTaskReturn);

    @Update("update mcs_task_return set task_id=#{taskId},mcs_id=#{mcsId},type=#{type},detection=#{detection},container_no=#{containerNo}," +
            "rgv_id=#{rgvId},address=#{address},status=#{status},report_status=#{reportStatus},create_time=#{createTime}," +
            "update_time=#{updateTime}  where id=#{id}" )
    int update(McsTaskReturn mcsTaskReturn);


    @Delete("delete from mcs_task_return where task_id = #{taskId}")
    int delete(@Param("taskId") String taskId);

    @Delete("delete from mcs_task_return where create_time < #{date} ")
    int deletes(@Param("date")Date date);
}

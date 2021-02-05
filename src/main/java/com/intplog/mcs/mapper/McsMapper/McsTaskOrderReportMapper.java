package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.model.McsModel.McsTaskOrderReport;
import org.apache.ibatis.annotations.*;

import javax.annotation.PostConstruct;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-06 15:32
 **/
public interface McsTaskOrderReportMapper {
    /**
     * 查询所有数据
     *
     * @return
     */
    @Select(" select id,task_id as taskId,type,stock_id as stockId,source,target," +
            "weight,priority,status,report_status as reportStatus,create_time as" +
            "createTime,update_time as updateTime from mcs_task_order_report order by create_time")
    public List<McsTaskOrderReport> getAll();

    /**
     * 根据上报状态查询数据
     *
     * @param reportStatus
     * @return
     */
    @Select(" select id,task_id as taskId,type,stock_id as stockId,source,target," +
            "weight,priority,status,report_status as reportStatus,create_time as" +
            "createTime,update_time as updateTime from mcs_task_order_report where " +
            "report_status=#{reportStatus} order by create_time ")
    public List<McsTaskOrderReport> getListByReportStatus(@Param("reportStatus") int reportStatus);

    /**
     * 根据EIS任务号查询
     *
     * @param taskId
     * @return
     */
    @Select(" select id,task_id as taskId,type,stock_id as stockId,source,target," +
            "weight,priority,status,report_status as reportStatus,create_time as" +
            "createTime,update_time as updateTime from mcs_task_order_report where " +
            "report_status=#{reportStatus} order by create_time")
    public List<McsTaskOrderReport> getListByTaskId(@Param("taskId") String taskId);

    /**
     * 根据EIS任务号查询
     *
     * @param taskId
     * @return
     */
    @Select(" select id,task_id as taskId,type,stock_id as stockId,source,target," +
            "weight,priority,status,report_status as reportStatus,create_time as" +
            "createTime,update_time as updateTime from mcs_task_order_report where " +
            "status=#{status} and task_id=#{taskId} order by create_time")
    public McsTaskOrderReport getListByTaskIdAndStatus(@Param("taskId") String taskId, @Param("status") int status);

    /**
     * 模糊查询
     *
     * @param taskId
     * @return
     */
    @Select({"<script> ",
            "select * from mcs_task_order_report where 1=1",
            "<if test='taskId!=null'>",
            "and task_id=#{taskId}",
            "</if>",
            "<if test='reportStatus!=null'>",
            "and report_status=#{reportStatus}",
            "</if>",
            "<if test='createTime!=null'>",
            "and create_time=#{createTime}",
            "</if>",
            "</script>"
    })
    public List<McsTaskOrderReport> getList(@Param("taskId") String taskId, @Param("reportStatus") int reportStatus,
                                            @Param("createTime") String createTime);

    /**
     * 跟据主键查询
     * @param id
     * @return
     */
    @Select("select * from mcs_task_order_report where id=#{id}")
    public McsTaskOrderReport getById(@Param("id") String id);

    /**
     * 插入数据
     * @param mcsTaskOrderReport
     * @return
     */
    @Insert(" insert into mcs_task_order_report (id,task_id,type,stock_id,source,target," +
            "weight,priority,report_status,status,create_time,update_time)values" +
            "(#{id},#{taskId},#{type},#{stockId},#{source},#{target},#{weight}," +
            "#{priority},#{reportStatus},#{status},#{createTime},#{updateTime})")
    public int insert(McsTaskOrderReport mcsTaskOrderReport);

    /**
     * 更新数据
     * @param mcsTaskOrderReport
     * @return
     */
    @Update("update mcs_task_order_report set task_id=#{taskId},type=#{type}," +
            "stock_id=#{stockId},source=#{source},target=#{target},weight=" +
            "#{weight},priority=#{priority},report_status=#{reportStatus}," +
            "status=#{status},create_time=#{createTime},update_time=#{updateTime}" +
            " where id=#{id}")
    public int update(McsTaskOrderReport mcsTaskOrderReport);
    @Update("update mcs_task_order_report set report_status=#{reportStatus},update_time=#{updateTime}" +
            " where id=#{id}")
    int updateById(@Param("reportStatus")int reportStatus,@Param("id")String id,@Param("updateTime")Date updateTime);

    /**
     * 删除数据
     * @param createTime
     * @return
     */
    @Delete("delete from mcs_task_order_report where create_time<#{createTime}")
    public int delete(@Param("createTime")Date createTime);
}
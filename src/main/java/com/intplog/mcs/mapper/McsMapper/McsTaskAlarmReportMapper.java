package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsTaskAlarmReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: wcs
 * @description
 * @author: tianlei
 * @create: 2019-11-05 12:28
 **/
public interface McsTaskAlarmReportMapper {

    /**
     * 查询所有数据
     */
    @Select({"<script>" +
            "select id,task_id as taskId,stock_id as stockId,type,source,target,weight,priority," +
            "error_type as errorType,msg,status,report_status as reportStatus,create_date as createDate " +
            "from mcs_task_alarm_report" +
            "</script>"})
    public List<McsTaskAlarmReport> getAll();

    /**
     * 根据taskStatus查询
     * @param reportStatus
     * @return
     */
    @Select({"<script>" +
            "select * from mcs_task_alarm_report where 1=1" +
            "<if test='reportStatus!=null'>",
            "and report_status=#{reportStatus}",
            "</if>",
            "</script>"
    })
    public List<McsTaskAlarmReport> getOrderReportByStatus(@Param("reportStatus") int reportStatus);

    /**
     * 根据billCode查询
     * @param taskId
     * @return
     */
    @Select({"<script>" +
            "select id,task_id as taskId,stock_id as stockId,type,source,target,weight,priority," +
            "error_type as errorType,msg,status,report_status as reportStatus,create_date as createDate" +
            "from mcs_task_alarm_report where 1=1" +
            "<if test='taskId!=null'>",
            "and task_id=#{taskId}",
            "</if>",
            "</script>"
    })
    public List<McsTaskAlarmReport> getOrderReportByCode(@Param("taskId") String taskId);

    /**
     * 添加
     *
     * @param mcsTaskAlarmReport
     * @return
     */
    @Insert({"insert into mcs_task_alarm_report (id,task_id,type,stock_id,source,target,weight,priority," +
            "status,report_status,msg,error_type,create_time)values(#{id},#{taskId},#{type},#{stockId}," +
            "#{source},#{target},#{weight},#{priority},#{status},#{reportStatus},#{msg},#{errorType}," +
            "#{createTime})"})
    public int insertIntoMcsAlarmReport(McsTaskAlarmReport mcsTaskAlarmReport);

    /**
     * 批量添加
     *
     * @param mcsTaskAlarmReports
     * @return
     */
    @Insert({"<script>" +
            "insert into mcs_task_alarm_report (id,task_id,type,stock_id,source,target,weight,priority," +
            "status,report_status,msg,error_type,create_time)values" +
            "<foreach collection='mcsTaskAlarmReports' item='mcsAlarmReport' separator=','>" +
            "(#{mcsAlarmReport.id},#{mcsAlarmReport.taskId},#{mcsAlarmReport.type},#{mcsAlarmReport.stockId}," +
            "#{mcsAlarmReport.source},#{mcsAlarmReport.target},#{mcsAlarmReport.weight},#{mcsAlarmReport.priority}," +
            "#{mcsAlarmReport.status},#{mcsAlarmReport.reportStatus},#{mcsAlarmReport.msg},#{mcsAlarmReport.errorType}," +
            "#{mcsAlarmReport.createTime})" +
            "</foreach>" +
            "</script>"})
    public int butchInsert(@Param("mcsTaskAlarmReports") List<McsTaskAlarmReport> mcsTaskAlarmReports);

    /**
     * 更新数据
     * @param mcsTaskAlarmReport
     * @return
     */
    @Update("update mcs_task_alarm_report set task_id=#{taskId},type=#{type},stock_id=#{stockId},source=#{source}," +
            "target=#{target},weight=#{weight},priority=#{priority},status=#{status},report_status=#{reportStatus}," +
            "msg=#{msg},error_type=#{errorType},create_time=#{createTime} where id=#{id}")
    public int update(McsTaskAlarmReport mcsTaskAlarmReport);

}


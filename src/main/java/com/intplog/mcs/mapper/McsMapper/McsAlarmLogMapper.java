package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsAlarmLog;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-03 21:03
 **/
public interface McsAlarmLogMapper {

    /**
     * 查询全部数据
     *
     * @return
     */
    @Select("SELECT id,type,create_time as createTime,content FROM mcs_alarm_log order by create_time desc")
    public List<McsAlarmLog> getAll();

    /**
     * 根据日志id查询
     *
     * @param id
     * @return
     */
    @Select("SELECT id,type,create_time as createTime,content FROM mcs_alarm_log where id=#{id} order by create_time desc")
    McsAlarmLog getMcsAlarmLog(String id);

    /**
     * 模糊查询
     *
     * @param createTime 创建时间
     * @param id         编号
     * @param type       类型
     * @return
     */
    @Select({"<script>",
            "SELECT id,type,create_time as createTime,content FROM mcs_alarm_log where 1=1",
            "<if test ='createTime!=null'>",
            "and create_time like CONCAT(CONCAT('%',#{createTime}),'%')",
            "</if>",
            "<if test ='id!=null'>",
            "and id like CONCAT(CONCAT('%',#{id}),'%')",
            "</if>",
            "<if test='type!=null'>",
            "and type like CONCAT(CONCAT('%',#{type}),'%')",
            "</if>",
            "order by create_time desc",
            "</script>"})
    public List<McsAlarmLog> getList(@Param("createTime") String createTime, @Param("id") String id, @Param("type") String type);

    /**
     * 插入错误日志
     *
     * @param mcsAlarmLog
     * @return
     */
    @Insert("insert into  mcs_alarm_log(id,type,create_time,content)values(#{id},#{type},#{createTime},#{content})")
    public int insert(McsAlarmLog mcsAlarmLog);

    /**
     * 更新数据
     *
     * @param mcsAlarmLog
     * @return
     */
    @Update("update mcs_alarm_log set type= #{type},create_time=#{createTime},content=#{content} where id=#{id} ")
    public int updateByPrimaryKey(McsAlarmLog mcsAlarmLog);

    /**
     * 删除指定日期之前的日志记录
     *
     * @param date
     * @return
     */
    @Delete("delete from mcs_alarm_log where create_time < #{date}")
    public int deleteLog(@Param("date") Date date);


}

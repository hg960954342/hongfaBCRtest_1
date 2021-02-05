package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsTaskLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/16 10:08
 */
public interface McsTaskLogMapper {

    /**
     * 根据id、boxNum查询信息
     *
     * @param
     * @param boxNum
     * @return
     */
    @Select({"<script>",
            "select * from mcs_task_log where 1=1",
            "<if test='mcsTaskLogType!=null'>",
            "and type=#{mcsTaskLogType}",
            "</if>",
            "<if test='mcsTaskLogBoxNum!=null'>",
            "and box_num=#{mcsTaskLogBoxNum}",
            "</if>",
            "order by create_time desc",
            "</script>"
    })
    public List<McsTaskLog> getList(@Param("mcsTaskLogType") String type, @Param("mcsTaskLogBoxNum") String boxNum);

    /**
     * 根据日期查询信息
     *
     * @param createTime
     * @return
     */
    @Select({"<script>",
            "select * from mcs_task_log where 1=1",
            "<if test='mcsTaskLogCreateTime!=null'>",
            "and create_time=#{mcsTaskLogCreateTime}",
            "</if>",
            "order by create_time desc",
            "</script>"
    })
    public List<McsTaskLog> getListExcel(@Param("mcsTaskLogCreateTime") String createTime);

    /**
     * 查询所有信息
     *
     * @return
     */
    @Select("select id,box_num,content,type,create_time,update_time from mcs_task_log order by create_time desc")
    public List<McsTaskLog> getAll();

    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    @Select("select id,boxNum,content,type,create_time,update_time from mcs_task_log where id=#{mcsTaskLogId} order by create_time desc")
    public List<McsTaskLog> getMcsTaskLogById(@Param("mcsTaskLogId") String id);

    /**
     * 新增McsTaskLog信息
     *
     * @param mcsTaskLog
     * @return
     */
    @Update("insert into mcs_task_log(id,box_num,content,type,create_time,update_time) values (#{id},#{boxNum},#{content},#{type},#{createTime},#{updateTime})")
    public int insertMcsTaskLog(McsTaskLog mcsTaskLog);

    /**
     * 删除McsTaskLog信息
     *
     * @param id
     * @return
     */
    @Delete("delete from mcs_task_log where id =#{mcsTaskLogId}")
    public int deleteMcsTaskLog(@Param("mcsTaskLogId") String id);
}

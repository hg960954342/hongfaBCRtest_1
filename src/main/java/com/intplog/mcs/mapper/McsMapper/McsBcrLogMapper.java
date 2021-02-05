package com.intplog.mcs.mapper.McsMapper;


import com.intplog.mcs.bean.model.McsModel.McsBcrLog;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author tianlei
 * @date 2020-10-11
 */
public interface McsBcrLogMapper {

    /**
     * @return
     */
    @Select({"<script>",
            "select id,create_time as createTime,name ,box_num as boxNum ,bcr_num as bcrNum,remark from mcs_bcr_log where 1=1",
            "<if test='createTime!=null'>",
            "and create_time like CONCAT(CONCAT('%',#{createTime}),'%')",
            "</if>",
            "<if test='boxNum!=null'>",
            "and box_num =#{boxNum}",
            "</if>",
            "<if test='bcrNum!=null'>",
            "and bcr_num =#{bcrNum}",
            "</if>",
            "order by create_time desc",
            "</script>"
    })
    List<McsBcrLog> getLists(@Param("createTime") String createTime,@Param("boxNum")String boxNum,@Param("bcrNum")String bcrNum);

    /**
     *
     * @param createTime
     * @return
     */
    @Select({"<script>",
            "select id,create_time as createTime,name ,box_num as boxNum ,bcr_num as bcrNum,remark from mcs_bcr_log where 1=1",
            "<if test='createTime!=null'>",
            "and create_time like CONCAT(CONCAT('%',#{createTime}),'%')",
            "</if>",
            "<if test='boxNum!=null'>",
            "and box_num =#{boxNum}",
            "</if>",
            "<if test='bcrNum!=null'>",
            "and bcr_num =#{bcrNum}",
            "</if>",
            "order by create_time desc",
            "</script>"
    })
    List<McsBcrLog> getListExcel(@Param("createTime") String createTime,@Param("boxNum")String boxNum,@Param("bcrNum")String bcrNum);

    @Select("SELECT id,create_time as createTime,name ,box_num as boxNum ,bcr_num as bcrNum,remark FROM mcs_bcr_log WHERE id=#{id} " +
            "order by create_time desc")
    McsBcrLog getMcsLogById(@Param("id") String id);

    /**
     * @param mcsLog
     * @return
     */
    @Insert("insert into mcs_bcr_log (id,create_time ,name,box_num ,bcr_num , remark) values " +
            "(#{id}, #{createTime}, #{name},#{boxNum},#{bcrNum},#{remark})")
    int inserts(McsBcrLog mcsLog);

    /**
     * 更新数据
     *
     * @param mcsLog
     * @return
     */
    @Update("update mcs_bcr_log set create_time=#{createTime},name=#{name},box_num=#{boxNum},bcr_num=#{bcrNum},remark= #{remark} where id=#{id}")
    int updateByPrimaryKeys(McsBcrLog mcsLog);

    /**
     * 查询全部数据
     *
     * @return
     */
    @Select("SELECT id,create_time as createTime,name,box_num as boxNum ,bcr_num as bcrNum, remark FROM mcs_bcr_log order by create_time desc")
    List<McsBcrLog> getAll();


    /**
     * 删除指定日期之前的日志记录
     *
     * @param date
     * @return
     */
    @Delete("delete from mcs_bcr_log where create_time < #{date}")
    int deleteLogs(@Param("date") Date date);
}

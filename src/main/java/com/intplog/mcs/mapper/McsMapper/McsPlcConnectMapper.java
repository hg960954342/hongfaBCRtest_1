package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsPlcConnect;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/12 10:56
 */

public interface McsPlcConnectMapper {


    /**
     * 根据id,name查询
     *
     * @return
     */
    @Select({"<script>",
            "select * from mcs_plc_connect where 1=1",
            "<if test='plcConIp!=null'>",
            "and ip=#{plcConIp}",
            "</if>",
            "<if test='plcConName!=null'>",
            "and plc_name=#{plcConName}",
            "</if>",
            "</script>"
    })
    public List<McsPlcConnect> getList(@Param("plcConIp") String ip, @Param("plcConName") String name);



    /**
     * 查询全部信息
     *
     * @return
     */
    @Select("select id,plc_name as plcName,ip,type from mcs_plc_connect order by plc_name")
    public List<McsPlcConnect> getAll();

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Select({"<script>",
            "select id,plc_name as plcName,ip,type from mcs_plc_connect where 1=1 ",
            "<if test='plcConId!=null'>",
            "and id=#{plcConId}",
            "</if>",
            "</script>"
    })
    public McsPlcConnect getMcsPlcConnectById(@Param("plcConId") String id);

    /**
     * 添加PLC连接信息
     *
     * @param mcsPlcConnect
     * @return
     */
    @Insert("insert into mcs_plc_connect(id,plc_name,ip,type)values(#{id},#{plcName},#{ip},#{type})")
    public int insertMcsPlcConnect(McsPlcConnect mcsPlcConnect);

    /**
     * 更新PLC连接信息
     *
     * @param mcsPlcConnect
     * @return
     */
    @Update("update mcs_plc_connect set ip=#{ip},plc_name=#{plcName},type=#{type} where id=#{id}")
    public int updateMcsPlcConnect(McsPlcConnect mcsPlcConnect);

    /**
     * 删除PLC连接信息
     *
     * @param id
     * @return
     */
    @Delete("delete from mcs_plc_connect where id=#{plcConId}")
    public int deleteMcsPlcConnect(@Param("plcConId") String id);

    /**
     * 根据队列插入
     *
     * @param mcsPlcConnectList
     * @return
     */
    @Insert({"<script>" +
            "insert into mcs_plc_connect(id,plc_name,ip,type)" +
            "values" +
            "<foreach collection='mcsPlcConnectList' item='mcsPlcConnect' separator=','>" +
            "(#{mcsPlcConnect.id},#{mcsPlcConnect.plcName},#{mcsPlcConnect.ip},#{mcsPlcConnect.type})" +
            "</foreach>" +
            "</script>"})
    public int importList(@Param("mcsPlcConnectList") List<McsPlcConnect> mcsPlcConnectList);

}

package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsBcrConnect;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: wcs
 * @description
 * @author: taineli
 * @create: 2019-10-12 14:35
 **/
public interface McsBcrConnectMapper {

        /**
         * 查询所有连接Bcr
         *
         * @return
         */
        @Select("SELECT id,name,ip,port,remark FROM mcs_bcr_connect order by name")
        public List<McsBcrConnect> getAll();

        /**
         * 查询对应编号或名称数据
         *
         * @param bcrConIp   读码器连接编号
         * @param bcrConName 读码器连接名称
         * @return
         */
        @Select({"<script>",
                "select id,ip,port,name,remark from mcs_bcr_connect where 1=1",
                "<if test='bcrConIp!=null'>",
                "and ip like CONCAT(CONCAT('%',#{bcrConIp}),'%')",
                "</if>",
                "<if test='bcrConName!=null'>",
                "and name like CONCAT(CONCAT('%',#{bcrConName}),'%')",
                "</if>",
                "</script>"
        })
        public List<McsBcrConnect> getList(@Param("bcrConIp") String bcrConIp, @Param("bcrConName") String bcrConName);

        /**
         * 根据id编号查询
         *
         * @param bcrConId
         * @return
         */
        @Select({"<script>",
                "select id,ip,port,name,remark  from mcs_bcr_connect where 1=1",
                "<if test='bcrConId!=null'>",
                "and id like CONCAT(CONCAT('%',#{bcrConId}),'%')",
                "</if>",
                "</script>"
        })
        public McsBcrConnect getBcrConnectById(@Param("bcrConId") String bcrConId);

        /**
         * 添加读码器连接信息
         *
         * @param mcsBcrConnect
         * @return
         */
        @Insert("insert into mcs_bcr_connect(id,ip,port,name ,remark)values(#{id},#{ip},#{port},#{name},#{remark}) ")
        public int insertMcsBcrConnect(McsBcrConnect mcsBcrConnect);

        @Insert("<script>" +
                "insert into mcs_bcr_connect(id,ip,port,name ,remark)" +
                "values" +
                "<foreach collection='mcsBcrConnectList' item='mcsBcrConnect'  separator=','>" +
                "(#{mcsBcrConnect.id},#{mcsBcrConnect.ip},#{mcsBcrConnect.port},#{mcsBcrConnect.name},#{mcsBcrConnect.remark})" +
                "</foreach>" +
                "</script>")
        public int importExcel(@Param("mcsBcrConnectList") List<McsBcrConnect> mcsBcrConnectList);

        /**
         * 更新读码器连接信息
         *
         * @param mcsBcrConnect
         * @return
         */
        @Update("update mcs_bcr_connect  set ip=#{ip},port=#{port},name=#{name},remark=#{remark}where id=#{id}")
        public int updateMcsBcrConnect(McsBcrConnect mcsBcrConnect);

        /**
         * 删除读码器连接信息
         *
         * @param id
         * @return
         */
        @Delete("delete from mcs_bcr_connect where id=#{bcrConnectId}")
        public int deleteMcsBcrConnect(@Param("bcrConnectId") String id);
}

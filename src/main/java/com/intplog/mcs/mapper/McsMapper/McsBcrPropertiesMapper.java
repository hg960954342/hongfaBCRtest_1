package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsBcrProperties;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author quqingmao
 * @date 2019-10-09
 */
public interface McsBcrPropertiesMapper {

    /**
     * @param name
     * @param connectId
     * @return
     */
    @Select({
            "<script>",
            "select * from mcs_bcr_properties where 1=1",
            "<if test='name!=null'>",
            "and name like CONCAT(CONCAT('%',#{name}),'%')",
            "</if>",
            "<if test='connectId!=null'>",
            "and connect_id like CONCAT(CONCAT('%',#{connectId}),'%')",
            "</if>",
            "</script>"
    })
    List<McsBcrProperties> getBcrList(@Param("name") String name, @Param("connectId") String connectId);

    /**
     * 添加BCR编号
     *
     * @param record
     * @return
     */
    @Insert("insert into mcs_bcr_properties(id,name,connect_id,remark,type,is_online) " +
            "values (#{id},#{name},#{connectId},#{remark},#{type},#{isOnline})")
    int insert(McsBcrProperties record);

    @Select("select * from  mcs_bcr_properties order by name")
    List<McsBcrProperties> getAll();

    /**
     * 查询BCR编号
     *
     * @param name
     * @return
     */
    @Select({
            "<script>",
            "select * from mcs_bcr_properties where 1=1",
            "<if test='name!=null'>",
            "and name = #{name})",
            "</if>",
            "<if test='connectId!=null'>",
            "and connect_id =#{connectId})",
            "</if>",
            "</script>"
    })
    List<McsBcrProperties> getBcrName(@Param("name") String name, @Param("connectId") String connectId);

    /**
     * 删除bcr连接编号
     *
     * @param id
     * @return
     */
    @Delete("delete from mcs_bcr_properties where id = #{id}")
    int deleteBcrConnectId(@Param("id") String id);

    /**
     * @param mcsBcrProperties
     * @return
     */
    @Update("update mcs_bcr_properties set name = #{name}, connect_id=#{connectId},remark=#{remark},type=#{type}," +
            "is_online=#{isOnline} where id=#{id}")
    int updateMcsBcrProperties(McsBcrProperties mcsBcrProperties);

    /**
     * @param id
     * @return
     */
    @Select("select * from mcs_bcr_properties where id = #{id}")
    McsBcrProperties getMcsBcrId(@Param("id") String id);

    /**
     * @param connectId
     * @return
     */
    @Select("select * from mcs_bcr_properties where connect_id = #{connectId}")
    McsBcrProperties getByConnectId(@Param("connectId") String connectId);

    @Insert("<script>" +
            "insert into mcs_bcr_properties(id,name,connect_id,remark,type,is_online)" +
            "values" +
            "<foreach collection='roleUserList' item='roleAcl' separator=',' >" +
            "(#{roleAcl.id}, #{roleAcl.name}, #{roleAcl.connectId}, #{roleAcl.remark},#{roleAcl.type},#{roleAcl.isOnline})" +
            "</foreach>" +
            "</script>")
    int excelInsert(@Param("roleUserList") List<McsBcrProperties> roleUserList);
}
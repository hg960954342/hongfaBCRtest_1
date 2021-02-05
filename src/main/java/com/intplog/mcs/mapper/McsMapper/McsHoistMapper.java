package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsHoist;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author tainlei
 * @date 2020-4-11
 */
public interface McsHoistMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select *  from mcs_hoist order by id")
    List<McsHoist> getAll();

    @Select({"<script>",
            "select * from mcs_hoist where 1=1",
            "<if test='id!=null'>",
            "and id = #{id}",
            "</if>",
            "order by id",
            "</script>"

    })
    List<McsHoist> getList(@Param("id") String id);

    /**
     * 根据提升机编号查询
     *
     * @param id
     * @return
     */
    @Select("select * from mcs_hoist " +
            "where id =#{id} order by id")
    public McsHoist getMcsHoistById(@Param("id") String id);


    @Select("select * from mcs_hoist where plc_name =#{plcName} " +
            "order by id")
    McsHoist getPlcName(@Param("plcName") String plcName);

    @Select("select * from mcs_hoist where id =#{id} ")
    McsHoist getHoist(@Param("id") String id);

    /**
     * 根据Plc查询对应提升机
     *
     * @param plcName
     * @return
     */
    @Select("select * from mcs_hoist where plc_name =#{plcName} " +
            "order by id")
    List<McsHoist> getListByPLC(@Param("plcName") String plcName);

    @Insert("insert into mcs_hoist(id,status,content,task_status,type,plc_name,`lock` ,is_permit ，current_floor)" +
            "values(#{id},#{status},#{content},#{taskStatus},#{type},#{plcName},#{lock},#{isPermit},#{currentFloor})")
    public int insert(McsHoist mcsHoist);

    @Update("update mcs_hoist set status=#{status},content=#{content},task_status=#{taskStatus},type=#{type}," +
            "plc_name=#{plcName},`lock`=#{lock} , is_permit=#{isPermit} ,current_floor=#{currentFloor} where id=#{id}")
    public int update(McsHoist mcsHoist);

    @Delete("delete from mcs_hoist where id=#{id}")
    public int delete(@Param("id") String id);
}

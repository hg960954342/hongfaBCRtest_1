package com.intplog.mcs.mapper.McsMapper;


import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.model.McsModel.McsGcsHoistInout;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/7
 */
public interface McsGcsHoistInOutMapper {
    @Select("select * from mcs_gcs_hoist_inout WHERE  type = #{type} order by create_time desc Limit 0,1")
    McsGcsHoistInout getHoistName(@Param("type")String type );

    @Select("select * from mcs_gcs_hoist_inout where id = #{id} ")
    List<McsGcsHoistInout> getId(@Param("id") String id);
    @Select("select id,hoist_name as hoistName,in_place as inPlace," +
            "type ,create_time as createTime" +
            "from mcs_gcs_hoist_inout where in_place=#{inPlace}")
    public McsGcsHoistInout getInPlace(@Param("inPlace")String inPlace);

    @Insert("insert into mcs_gcs_hoist_inout(id,hoist_name,in_place,type,create_time , coord) values(#{id},#{hoistName},#{inPlace},#{type},#{createTime},#{coord})")
    public int insertData(McsGcsHoistInout mcsGcsHoistInout);

    @Update("update mcs_gcs_hoist_inout set id=#{id},hoist_name=#{hoistName},in_place=#{inPlace},type=#{type}," +
            "create_time=#{createTime} ,coord=#{coord} where type=#{type}")
    public int updateType(McsGcsHoistInout mcsGcsHoistInout);

    @Update("update mcs_gcs_hoist_inout set id=#{id},hoist_name=#{hoistName},in_place=#{inPlace},type=#{type}," +
            "create_time=#{createTime} ,coord=#{coord} where id = #{id}")
    public int updateInPlace(McsGcsHoistInout mcsGcsHoistInout);

    @Delete("delete from mcs_gcs_hoist_inout where type=#{type}")
    public int deleteType(@Param("type") String id);
}

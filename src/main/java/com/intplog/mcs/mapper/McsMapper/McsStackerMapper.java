package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsStacker;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author tianlei
 */
public interface McsStackerMapper {

    @Select("select * from mcs_stacker")
    List<McsStacker> getAll();

    @Select("select * from mcs_stacker where status=#{status} ")
    List<McsStacker> getByStatus(@Param("status") int status);

    @Select("select * from mcs_stacker where type=#{type}")
    List<McsStacker> getByTypeAndPlcName(@Param("type") int type, @Param("plcName") String plcName);

    @Select("select * from mcs_stacker where coord=#{coord}")
    McsStacker getByCoord(@Param("coord") String coord);

    @Insert("insert into mcs_stacker(id,number,status,coord,type,plc_name,container_code)values" +
            "(#{id},#{number},#{status},#{coord},#{type},#{plcName},#{containerCode}) ")
    int insert(McsStacker mcsStacker);

    @Update("update mcs_stacker set number=#{number},status=#{status},coord=#{coord}," +
            "type=#{type},plc_name=#{plcName},container_code=#{containerCode} where id=#{id}")
    int update(McsStacker mcsStacker);

    @Delete("delete from mcs_stacker where id=#{id}")
    int deleteById(@Param("id") String id);

    @Update("update mcs_stacker set type=#{type} where number=#{number}")
    int updateByNumber(McsStacker mcsStacker);
}

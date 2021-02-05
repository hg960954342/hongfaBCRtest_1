package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsPlcVariable;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/15 11:54
 */
public interface McsPlcVariableMapper {

    /**
     * 根据id、name查询信息
     *
     * @param groupNumber
     * @param plcName
     * @return
     */
    @Select({"<script>",
            "select * from mcs_plc_variable where 1=1",
            "<if test='groupNumber!=null'>",
            "and group_number=#{groupNumber}",
            "</if>",
            "<if test='plcName!=null'>",
            "and plc_name=#{plcName}",
            "</if>",
            "</script>"
    })
    public List<McsPlcVariable> getList(@Param("plcName") String plcName, @Param("groupNumber") String groupNumber);
    @Select("select *  from mcs_plc_variable where group_number=#{groupNumber} and coord=#{coord} and type=#{type}")
    McsPlcVariable getPLcAddress(@Param("groupNumber") String groupNumber, @Param("coord") String coord,@Param("type") int type);

    /**
     * 查询所有信息
     *
     * @return
     */
    @Select("select id,type,plc_name as plcName,address,refresh_time,group_number,coord,direction  from mcs_plc_variable")
    public List<McsPlcVariable> getAll();

    /**
     * 查询所有信息
     *
     * @return
     */
    @Select("select id,type,plc_name as plcName,address,refresh_time,group_number,coord,direction  from mcs_plc_variable" +
            " where plc_name=#{plcName}")
    public List<McsPlcVariable> getByPlcName(@Param("plcName")String plcName);
    /**
     * 根据id查询信息
     *
     * @param Id
     * @return
     */
    @Select("select id,type,plc_name as plcName,address,refresh_time,group_number,coord,direction  from mcs_plc_variable" +
            " where id=#{plcVarId}")
    public McsPlcVariable getMcsPlcVariableById(@Param("plcVarId") String Id);

    /**
     * 根据坐标和类型查询信息
     * @param coord
     * @return
     */
    @Select("select *from mcs_plc_variable" +
            " where coord=#{coord}  LIMIT 0,1")
    public McsPlcVariable getByTypeAndCoord(@Param("coord")String coord);

    /**
     * 根据名称和类型查询信息
     * @param type
     * @param name
     * @return
     */
    @Select("select id,type,plc_name as plcName,address,refresh_time,group_number,coord,direction  from mcs_plc_variable" +
            " where plc_name=#{name} and type=#{type} LIMIT 0, 1")
    public McsPlcVariable getByTypeAndName(@Param("type") int type,@Param("name")String name);

    /**
     * 根据名称和类型查询信息
     * @param coord
     * @return
     */
    @Select("select *  from mcs_plc_variable where coord=#{coord} LIMIT 0,1 ")
    public McsPlcVariable getByCoord(@Param("coord")String coord);

    /**
     *
     * @param coord
     * @param type
     * @return
     */
    @Select("select *  from mcs_plc_variable where coord=#{coord} and type=#{type} LIMIT 0,1 ")
    McsPlcVariable getByCoordAndType(@Param("coord")String coord,@Param("type") int type);
    /**
     * 添加PLC路径信息
     *
     * @param mcsPlcVariable
     * @return
     */
    @Insert("insert into mcs_plc_variable (id,type,plc_name,address,refresh_time,group_number,coord,direction )" +
            " values(#{id},#{type},#{plcName},#{address},#{refreshTime},#{groupNumber},#{coord},#{direction})")
    public int insertMcsPlcVariable(McsPlcVariable mcsPlcVariable);
    /**
     * 跟新PLC路径信息
     *
     * @param mcsPlcVariable
     * @return
     */
    @Update("update mcs_plc_variable set direction = #{direction} where group_number=#{groupNumber}")
    public int updateMcsPlcVariableByGroupNumber(McsPlcVariable mcsPlcVariable);

    /**
     * 更新PLC路径信息
     *
     * @param mcsPlcVariable
     * @return
     */
    @Update("update mcs_plc_variable set type=#{type},plc_name=#{plcName},address=#{address},refresh_time=" +
            "#{refreshTime},group_number=#{groupNumber},coord=#{coord},direction=#{direction} where id=#{id}")
    public int updateMcsPlcVariable(McsPlcVariable mcsPlcVariable);

    /**
     * 删除PLC路径信息
     *
     * @param id
     * @return
     */
    @Delete("delete from mcs_plc_variable where id=#{plcVarId}")
    public int deleteMcsPlcVariableById(@Param("plcVarId") String id);

    @Insert({"<script>",
            "insert into mcs_plc_variable (id,type,plc_name,address,refresh_time,group_number,coord,direction )",
            "values",
            "<foreach collection='mcsPlcVariableList' item='mcsPlcVariable' separator=','>",
            "(#{mcsPlcVariable.id},#{mcsPlcVariable.type},#{mcsPlcVariable.plcName}," +
                    "#{mcsPlcVariable.address},#{mcsPlcVariable.refreshTime},#{mcsPlcVariable.groupNumber}" +
                    "#{mcsPlcVariable.coord},#{mcsPlcVariable.direction}",
            "</foreach>",
            "</script>"})
    public int importList(@Param("mcsPlcVariableList") List<McsPlcVariable> mcsPlcVariableList);
}

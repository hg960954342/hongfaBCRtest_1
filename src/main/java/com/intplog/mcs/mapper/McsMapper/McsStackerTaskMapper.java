package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsStacker;
import com.intplog.mcs.bean.model.McsModel.McsStackerTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author tianlei
 */
public interface McsStackerTaskMapper {
    @Select("select * from mcs_stacker_task")
    List<McsStackerTask> getAll();

    @Select("select * from mcs_stacker_task where report_status=#{reportStatus} ")
    List<McsStackerTask> getByStatus(@Param("reportStatus") int reportStatus);

    /**
     * 更具状态和类型查询数据
     * @param reportStatus
     * @param type
     * @return
     */
    @Select("select * from mcs_stacker_task where report_status=#{status} and type=#{type} ")
    List<McsStackerTask> getByStatusAndType(@Param("reportStatus") int reportStatus,@Param("type")int type);

    @Select("select * from mcs_stacker_task where device_no=#{deviceNo}")
    McsStackerTask getByCoord(@Param("deviceNo") String deviceNo);

    @Insert("insert into mcs_stacker_task(id,number,report_status,device_no,type,create_time)values" +
            "(#{id},#{number},#{reportStatus},#{deviceNo},#{type},#{createTime}) ")
    int insert(McsStackerTask mcsStackerTask);

    @Update("update mcs_stacker_task set number=#{number},report_status=#{reportStatus},device_no=#{deviceNo}," +
            "type=#{type} where id=#{id}")
    int update(McsStackerTask mcsStackerTask);

    @Delete("delete from mcs_stacker_task where id=#{id}")
    int deleteById(@Param("id") String id);
}

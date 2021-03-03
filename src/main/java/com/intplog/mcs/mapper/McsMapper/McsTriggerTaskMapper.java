package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsPlcVariable1;
import com.intplog.mcs.bean.model.McsModel.McsSlidCount;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.model.McsModel.McsTriggerTask;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/9/21
 */
public interface McsTriggerTaskMapper {

    @Select({"<script>",
            "select  *" ,
            "from mcs_trigger_task where 1=1",
            "<if test='bcrId!=null'>",
            "and bcr_id = #{bcrId}",
            "</if>","<if test='bcrCode!=null'>",
            "and bcr_code = #{bcrCode}",
            "</if>",
            "order by create_time desc",
            "</script>"

    })
    List<McsTriggerTask> getList(@Param("bcrId") String bcrId, @Param("bcrCode") String bcrCode);

    @Select("select * from mcs_trigger_task where bcr_id=#{bcrId}")
    McsTriggerTask getMcsTriggerTaskId(@Param("bcrId") String bcrId);

    @Select("Select * from mcs_trigger_task where bcr_id=#{bcrId} and status=#{status} order by create_time desc limit 0,1")
    McsTriggerTask getMcsTriggerTaskData(@Param("bcrId") String bcrId,@Param("status") int status);

    @Select("Select * from mcs_plc_variable_1 where bcr_id=#{bcrId} ")
    McsPlcVariable1 getMcsplcvariable1(@Param("bcrId") String bcrId);

    @Select("Select * from mcs_trigger_task  order by create_time desc limit 0,1")
    McsTriggerTask getMcsTriggerTaskDataTheLatest();

    @Insert("insert into mcs_trigger_task(id,bcr_id,bcr_code,length,width,height,weight,shape,status,create_time) " +
            "values(#{id},#{bcrId},#{bcrCode},#{length},#{width},#{height},#{weight},#{shape},#{status},#{createTime}) ")
    int insertMcsTriggerTaskData(McsTriggerTask mcsTriggerTask);

    @Update("update mcs_trigger_task set bcr_id=#{bcrId},bcr_code=#{bcrCode}," +
            "length=#{length},width=#{width},height=#{height},status=#{status}," +
            "create_time=#{createTime} where id=#{id}")
    int updateMcsTriggerTaskData(McsTriggerTask mcsTriggerTask);

    @Delete("delete from mcs_trigger_task where create_time < #{date}")
    int deleteMcsTriggerTaskData(@Param("date") Date date );

    @Delete("delete from mcs_trigger_task where status=#{status}")
    int deleteStatus(@Param("status") int status);

}



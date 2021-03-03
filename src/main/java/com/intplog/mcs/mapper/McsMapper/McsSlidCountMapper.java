package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsPlcVariable1;
import com.intplog.mcs.bean.model.McsModel.McsSlidCount;
import com.intplog.mcs.bean.model.McsModel.McsTriggerTask;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/9/21
 */
public interface McsSlidCountMapper {

    @Select("Select * from mcs_slid_count where slid_id=#{slidId} ")
    McsSlidCount getMcsSlidCount(@Param("slidId") String slidId);

    @Update("update mcs_slid_count set box_count=#{boxCount},action_count=#{actionCount}," +
            "create_time=#{createTime} where slid_id=#{slidId}")
    int updateMcsSlidCountData(@Param("boxCount") String boxCount,@Param("actionCount") String actionCount,@Param("createTime") Date createTime);


}



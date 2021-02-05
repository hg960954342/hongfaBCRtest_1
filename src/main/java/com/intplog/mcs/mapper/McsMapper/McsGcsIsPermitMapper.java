package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsGcsIsPermit;
import com.intplog.mcs.bean.model.McsModel.McsHoist;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/9
 */
public interface McsGcsIsPermitMapper {
    @Select("select * from mcs_gcs_is_permit where plc_name =#{plcName} and  is_permit =#{isPermit}  Limit 0,1")
    McsGcsIsPermit getListByPLC(@Param("plcName") String plcName ,@Param("isPermit") String isPermit);

    @Select("select * from mcs_gcs_is_permit where hoist_name = #{hoistName} and layer =#{layer} ")
    McsGcsIsPermit getInOut(@Param("hoistName") String hoistName , @Param("layer") String layer);



    @Update("update mcs_gcs_is_permit set is_permit=#{isPermit} where plc_name =#{plcName} and  layer =#{layer}")
    public int update(@Param("plcName") String plcName ,@Param("layer") String layer,@Param("isPermit") int isPermit);
}

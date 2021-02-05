package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.dto.EisDto.EisRgvData;
import com.intplog.mcs.bean.model.EisModel.RgvData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/20
 */
public interface RgvDataMapper {
    @Select("select id as rgvId , layer , status from mcs_rgv_data")
    List<EisRgvData>  getAll();



    @Update("update mcs_rgv_data set layer=#{layer},status=#{status},alarm=#{alarm} ,curr_coord=#{currCoord},is_up=#{isUp} where id=#{rgvId}")
    int update(RgvData rgvData);

    @Insert("insert into mcs_rgv_data(id ,layer ,status, alarm,curr_coord,is_up) values(#{rgvId}, #{layer}, #{status}, #{alarm},#{currCoord},#{isUp})")
    int inset(RgvData rgvData);
}


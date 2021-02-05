package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsBoxMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author quqingmao
 * @date 2020/9/24
 */
public interface McsBoxMessageMapper {

    @Select("select * from mcs_box_message where slide_id=#{slideId}")
    McsBoxMessage getMcsBoxMessage(@Param("slideId") int slideId);

    @Update("update mcs_box_message set slide_id=#{slideId} ,is_box=#{isBox},create_time=#{createTime} where id=#{id}")
    int updateMcsBoxMessage(McsBoxMessage mcsBoxMessage);
}

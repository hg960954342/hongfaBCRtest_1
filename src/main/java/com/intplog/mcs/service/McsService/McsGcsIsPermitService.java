package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsGcsIsPermit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/9
 */
public interface McsGcsIsPermitService {
    McsGcsIsPermit getMcsGcsIsPermitList(String plcName , String isPermit);

    int updateMcsGcsIsPermit(String plcName , String layer ,int isPermit);

    McsGcsIsPermit getInOut( String hoistName ,String layer);
}

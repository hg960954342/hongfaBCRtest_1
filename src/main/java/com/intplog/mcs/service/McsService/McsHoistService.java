package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsHoist;
import com.intplog.mcs.bean.model.McsModel.McsPlcConnect;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.common.JsonData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tianlei
 * @date 2020-4-11
 */
public interface McsHoistService {

    PageData getAllList(String ip, int pageNum, int pageSize);

    List<McsHoist> getAll();
    List<McsHoist> getListByPlc(String plcName);

    McsHoist getMcsHoistById(String id);

    int insertMcsHoist(McsHoist mcsHoist);

    int updateMcsHoist(McsHoist mcsHoist);

    PageData deleteMcsHoistById(String id);

    McsHoist getPLcName(String name);

    McsHoist getHoist(String id);

}

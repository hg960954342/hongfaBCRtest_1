package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsPlcVariable1;
import com.intplog.mcs.bean.model.McsModel.McsSlidCount;
import com.intplog.mcs.bean.model.McsModel.McsTriggerTask;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/9/21
 */
public interface McsSlidCountService {


    McsSlidCount getMcsSlidCount(String slidId);

    int updateMcsSlidCountData(String boxCount, String actionCount, Date creatTime);


}

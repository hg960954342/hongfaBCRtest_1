package com.intplog.mcs.service.McsService;


import com.intplog.mcs.bean.model.McsModel.McsInterfaceLog;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

/**
 * @author quqingmao
 * @date 2019-10-11
 */
public interface McsBcrInterfaceLogService {

    PageData getAll(String method, int pageNum, int pageSize);

    McsInterfaceLog getInterfaceLogById(String Id);

    List<McsInterfaceLog>getList(String method);



    int deleteLog(int date);

    int save(McsInterfaceLog mcsInterfaceLog);

    int updateId(McsInterfaceLog mcsInterfaceLog);
}

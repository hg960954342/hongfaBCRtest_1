package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsTaskLog;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/16 11:34
 */
public interface McsTaskLogService {

    List<McsTaskLog> getMcsTaskLogById(String id);

    PageData getAll(String type, String boxNum, int pageNum, int pageSize);

    int insertMcsTaskLog(McsTaskLog mcsTaskLog);

    int deleteMcsTaskLog(String id);

    List<McsTaskLog> getListExcel(String createTime);
}

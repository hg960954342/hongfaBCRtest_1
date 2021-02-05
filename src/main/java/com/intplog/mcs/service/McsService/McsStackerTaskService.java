package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsStacker;
import com.intplog.mcs.bean.model.McsModel.McsStackerTask;

import java.util.List;

/**
 * @author tianlei
 */
public interface McsStackerTaskService {
    List<McsStackerTask> getALL();

    List<McsStackerTask> getByStatus(int status);
    List<McsStackerTask> getByStatusAndType(int status,int type);

    McsStackerTask getByCoord(String coord);

    int insert(McsStackerTask mcsStackerTask);

    int update(McsStackerTask mcsStackerTask);

    int delete(String id);
}

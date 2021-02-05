package com.intplog.mcs.service.McsService;


import com.intplog.mcs.bean.model.McsModel.McsStacker;
import org.w3c.dom.ls.LSInput;

import java.util.List;

/**
 * @author tianlei
 */
public interface McsStackerService {

    List<McsStacker> getALL();

    List<McsStacker> getByStatus(int status);

    List<McsStacker> getByTypeAndPLcName(int type, String plcName);

    McsStacker getByCoord(String coord);

    int insert(McsStacker mcsStacker);

    int update(McsStacker mcsStacker);

    int updateByNumber(McsStacker mcsStacker);

    int delete(String id);

}

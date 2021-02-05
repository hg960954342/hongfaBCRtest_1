package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsBoxMessage;

/**
 * @author quqingmao
 * @date 2020/9/24
 */
public interface McsBoxMessageService {
    /**
     * 更新
     * @param mcsBoxMessage
     * @return
     */
    int updateMcsBoxMessage(McsBoxMessage mcsBoxMessage);

    McsBoxMessage getMcsBoxMessage(int slideId);
}

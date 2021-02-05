package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.model.McsModel.McsBoxMessage;
import com.intplog.mcs.mapper.McsMapper.McsBoxMessageMapper;
import com.intplog.mcs.service.McsService.McsBoxMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author quqingmao
 * @date 2020/9/25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsBoxMessageImpl implements McsBoxMessageService {
    @Resource
    private McsBoxMessageMapper mcsBoxMessageMapper;
    /**
     * 更新
     *
     * @param mcsBoxMessage
     * @return
     */
    @Override
    public int updateMcsBoxMessage(McsBoxMessage mcsBoxMessage) {
        return mcsBoxMessageMapper.updateMcsBoxMessage(mcsBoxMessage);
    }

    @Override
    public McsBoxMessage getMcsBoxMessage(int slideId) {
        return mcsBoxMessageMapper.getMcsBoxMessage(slideId);
    }
}

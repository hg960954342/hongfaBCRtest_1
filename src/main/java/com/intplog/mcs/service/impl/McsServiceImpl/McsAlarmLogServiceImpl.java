package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsAlarmLog;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsAlarmLogMapper;
import com.intplog.mcs.service.McsService.McsAlarmLogService;
import com.intplog.mcs.utils.DateHelpUtil;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: wcs2
 * @description
 * @author: tianlei
 * @create: 2019-10-12 10:02
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class McsAlarmLogServiceImpl implements McsAlarmLogService {
        @Resource
        public McsAlarmLogMapper mcsAlarmLogMapper;

        @Override
        public PageData getAll(String createTime, String id, String type, int pageNum, int pageSize) {
                PageData pd = new PageData();
                Page<Object> page = PageHelper.startPage(pageNum, pageSize);
                List<McsAlarmLog> all = mcsAlarmLogMapper.getList(createTime, id, type);
                pd.setMsg("");
                pd.setCode(0);
                pd.setCount(page.getTotal());
                pd.setData(all);
                return pd;
        }

        @Override
        public int deleteLog(int day) {
                Date dt = DateHelpUtil.getDate(day);
                return mcsAlarmLogMapper.deleteLog(dt);
        }

        @Override
        public int insertMcsAlarmLog(McsAlarmLog mcsAlarmLog) {
                return mcsAlarmLogMapper.insert(mcsAlarmLog);
        }

        @Override
        public int updateMcsAlarmLog(McsAlarmLog mcsAlarmLog) {
                return mcsAlarmLogMapper.updateByPrimaryKey(mcsAlarmLog);
        }

        @Override
        public McsAlarmLog getMcsAlarmLogById(String id) {
                return mcsAlarmLogMapper.getMcsAlarmLog(id);
        }

        @Override
        public List<McsAlarmLog> getList(String createTime, String id, String type) {
                return mcsAlarmLogMapper.getList(createTime,id,type);
        }

}

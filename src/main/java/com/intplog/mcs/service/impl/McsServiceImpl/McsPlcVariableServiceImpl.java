package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsPlcVariable;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.mapper.McsMapper.McsPlcVariableMapper;
import com.intplog.mcs.service.McsService.McsPlcVariableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/15 13:50
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsPlcVariableServiceImpl implements McsPlcVariableService {

    @Resource
    private McsPlcVariableMapper mcsPlcVariableMapper;


    @Override
    public McsPlcVariable getPlc(String groupNumber, String cooder, int type) {
        return mcsPlcVariableMapper.getPLcAddress(groupNumber,cooder,type);
    }

    @Override
    public List<McsPlcVariable> getExcelExport(String ip, String name) {
        return mcsPlcVariableMapper.getList(ip, name);
    }

    @Override
    public JsonData importExcel(List<McsPlcVariable> mcsPlcVariableList) {
        List<McsPlcVariable> plcVariableList = mcsPlcVariableMapper.getAll();
        List<String> idList = plcVariableList.stream().map(a -> a.getId()).collect(Collectors.toList());
        List<String> ipList = plcVariableList.stream().map(a -> a.getAddress()).collect(Collectors.toList());
        if (mcsPlcVariableList.size() > 0) {
            for (int i = 0; i < mcsPlcVariableList.size(); i++) {
                McsPlcVariable mcsPlcVariable=mcsPlcVariableList.get(i);
                if (idList.contains(mcsPlcVariable.getId())||ipList.contains(mcsPlcVariable.getAddress())){
                    return JsonData.fail(MessageFormat.format("导入数据第{0}行编号和IP地址已存在",i+1),mcsPlcVariable.id);
                }
            }
            int i=mcsPlcVariableMapper.importList(mcsPlcVariableList);
            if (i>0){
                return JsonData.success(MessageFormat.format("导入数据成功，受影响行数{0}",mcsPlcVariableList.size()));
            }
            else {
                return JsonData.fail("2");
            }
        } else {
            return JsonData.fail("导入数据为空", "3");
        }
    }

    @Override
    public PageData getAll(String name, String groupNumber, int pageNum, int pageSize) {
        PageData pageData = new PageData();
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<McsPlcVariable> all = mcsPlcVariableMapper.getList(name, groupNumber);
        pageData.setMsg("");
        pageData.setCount(page.getTotal());
        pageData.setCode(0);
        pageData.setData(all);
        return pageData;
    }

    @Override
    public List<McsPlcVariable> getAll() {
        return mcsPlcVariableMapper.getAll();
    }

    @Override
    public List<McsPlcVariable> getByPlcName(String plcName) {
        return mcsPlcVariableMapper.getByPlcName(plcName) ;
    }

    @Override
    public McsPlcVariable getMcsPlcVariableById(String id) {
        return mcsPlcVariableMapper.getMcsPlcVariableById(id);
    }

    @Override
    public McsPlcVariable getByTypeAndCoord(String coord) {
        return mcsPlcVariableMapper.getByTypeAndCoord(coord);
    }

    @Override
    public McsPlcVariable getByTypeAndName(int type, String name) {
        return mcsPlcVariableMapper.getByTypeAndName(type,name);
    }

    @Override
    public McsPlcVariable getByCoord(String coord) {
        return mcsPlcVariableMapper.getByCoord(coord);
    }


    @Override
    public int insertMcsPlcVariable(McsPlcVariable mcsPlcVariable) {
        return mcsPlcVariableMapper.insertMcsPlcVariable(mcsPlcVariable);
    }

    @Override
    public int updateMcsPlcVariable(McsPlcVariable mcsPlcVariable) {
        return mcsPlcVariableMapper.updateMcsPlcVariable(mcsPlcVariable);
    }

    @Override
    public int updateMcsPlcVariableByGroupNumber(McsPlcVariable mcsPlcVariable) {
        return mcsPlcVariableMapper.updateMcsPlcVariableByGroupNumber(mcsPlcVariable);
    }

    @Override
    public McsPlcVariable getCoordAndType(String coord, int type) {
        return mcsPlcVariableMapper.getByCoordAndType(coord,type);
    }

    @Override
    public PageData deleteMcsPlcVariableById(String id) {
        PageData pageData = new PageData();
        int i = mcsPlcVariableMapper.deleteMcsPlcVariableById(id);
        pageData.setMsg("");
        pageData.setCode(0);
        if (i < 1) {
            pageData.setMsg("更新失败");
        }
        return pageData;
    }

}

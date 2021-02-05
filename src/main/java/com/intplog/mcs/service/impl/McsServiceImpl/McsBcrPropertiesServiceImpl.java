package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsBcrProperties;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsBcrPropertiesMapper;
import com.intplog.mcs.service.McsService.McsBcrPropertiesService;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author quqingmao
 * @date 2019-10-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsBcrPropertiesServiceImpl implements McsBcrPropertiesService {

    @Resource
    private McsBcrPropertiesMapper mcsBcrPropertiesMapper;

    @Override
    public int save(McsBcrProperties param) {
        return mcsBcrPropertiesMapper.insert(param);
    }

    @Override
    public PageData getAll(String name, String connectId, int pageNum, int pageSize) {
        PageData pageData = new PageData();

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<McsBcrProperties> mcsBcrProperties = mcsBcrPropertiesMapper.getBcrList(name, connectId);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(page.getTotal());
        pageData.setData(mcsBcrProperties);
        return pageData;
    }

    @Override
    public List<McsBcrProperties> getBcrName(String id, String name) {
        return mcsBcrPropertiesMapper.getBcrName(id, name);
    }

    @Override
    public PageData deleteBcrConnectId(String connectId) {
        PageData pd = new PageData();
        int i = mcsBcrPropertiesMapper.deleteBcrConnectId(connectId);

        pd.setCode(0);
        pd.setMsg("");

        if (i < 1) {
            pd.setMsg("删除数据失败！");
        } else {
            pd.setMsg("删除数据成功");

        }
        return pd;
    }

    @Override
    public int updateMcsBcr(McsBcrProperties mcsBcrProperties) {
        return mcsBcrPropertiesMapper.updateMcsBcrProperties(mcsBcrProperties);
    }


    @Override
    public McsBcrProperties getBcrId(String id) {
        return mcsBcrPropertiesMapper.getMcsBcrId(id);
    }

    @Override
    public McsBcrProperties getByConnectId(String connectId) {
        return mcsBcrPropertiesMapper.getByConnectId(connectId);
    }

    @Override
    public int batchInsert(List<McsBcrProperties> mcsBcrProperties) {

        List<McsBcrProperties> mcsBcrPropertiesList = new ArrayList<McsBcrProperties>();
        mcsBcrPropertiesList = mcsBcrPropertiesMapper.getAll();
        //stream这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
        List<Object> idList = mcsBcrPropertiesList.stream().map(x -> x.getId()).collect(Collectors.toList());

        return mcsBcrPropertiesMapper.excelInsert(mcsBcrProperties);
    }
    @Override
        public List<McsBcrProperties>getAllList(){
            return  mcsBcrPropertiesMapper.getAll();
    }


}

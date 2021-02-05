package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsBcrConnect;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.mapper.McsMapper.McsBcrConnectMapper;
import com.intplog.mcs.service.McsService.McsBcrConnectService;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: wcs
 * @description
 * @author: tainelei
 * @create: 2019-10-12 15:32
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class McsBcrConnectServiceImpl implements McsBcrConnectService {
        @Resource
        private McsBcrConnectMapper mcsBcrConnectMapper;

        @Override
        public List<McsBcrConnect> getExcelExport(String ip, String name) {
                return mcsBcrConnectMapper.getList(ip, name);
        }

        @Override
        public PageData getList(String ip, String name, int pageNum, int pageSize) {
                PageData pageData = new PageData();
                Page<Object> page = PageHelper.startPage(pageNum, pageSize);
                List<McsBcrConnect> getall = mcsBcrConnectMapper.getList(ip, name);
                pageData.setMsg("");
                pageData.setCode(0);
                pageData.setCount(page.getTotal());
                pageData.setData(getall);
                return pageData;
        }

        @Override
        public List<McsBcrConnect> getAll() {
                return mcsBcrConnectMapper.getAll();
        }

        @Override
        public McsBcrConnect getMcsBcrConnectById(String id) {
                return mcsBcrConnectMapper.getBcrConnectById(id);
        }

        @Override
        public int insertMcsBcrConnect(McsBcrConnect mcsBcrConnect) {
                return mcsBcrConnectMapper.insertMcsBcrConnect(mcsBcrConnect);
        }

        @Override
        public int updateMcsBcrConnect(McsBcrConnect mcsBcrConnect) {
                return mcsBcrConnectMapper.updateMcsBcrConnect(mcsBcrConnect);
        }

        @Override
        public JsonData importExcel(List<McsBcrConnect> mcsBcrConnectList) {

                List<McsBcrConnect> bcrConnectList = new ArrayList<McsBcrConnect>();
                bcrConnectList = mcsBcrConnectMapper.getAll();
                List<Object> idList = bcrConnectList.stream().map(x -> x.getId()).collect(Collectors.toList());//数据库已存在编号
                List<String> ipAndPortList = new ArrayList<String>();//数据库已存在ip和端口号
                List<String> importId = new ArrayList<String>();//导入数据编号
                List<String> importIpAndPort = new ArrayList<String>();//导入数据ip和端口
                if (mcsBcrConnectList.size() > 0) {
                        //判断导入数据主键，IP和端口号是否存在重复
                        for (int i = 0; i < mcsBcrConnectList.size(); i++) {
                                McsBcrConnect mcsBcrConnect = mcsBcrConnectList.get(i);
                                if (importId.contains(mcsBcrConnect.getId())) {//导入数据第{}行编号在导入数据中已存在
                                      return JsonData.fail(MessageFormat.format("导入数据第{0}行编号在导入数据中已存在",i+1),"3") ;
                                } else {
                                        importId.add(mcsBcrConnect.getId());
                                        if (importIpAndPort.contains(mcsBcrConnect.getIp() + mcsBcrConnect.getPort())) {
                                                return JsonData.fail(MessageFormat.format("导入数据第{0}行ip和端口在导入数据中已存在", i+1), "3");
                                        } else {
                                                importIpAndPort.add(mcsBcrConnect.getIp() + mcsBcrConnect.getPort());
                                        }
                                }
                        }
                        for (int n = 0; n < bcrConnectList.size(); n++) {
                                McsBcrConnect mcsBcrConnect = bcrConnectList.get(n);
                                ipAndPortList.add(mcsBcrConnect.getIp() + mcsBcrConnect.getPort());
                        }

                        for (int a = 0; a < mcsBcrConnectList.size(); a++) {
                                McsBcrConnect mcsBcrConnect = mcsBcrConnectList.get(a);
                                //判断导入主键是否在数据库存在
                                if (idList.contains(mcsBcrConnect.getId())) {
                                        return JsonData.fail(MessageFormat.format("导入失败!,第{0}行数据编号已存在", a+1), "3");
                                }
                                //判断是否已存在导入连接的IP与PORT
                                else {
                                        if (ipAndPortList.contains((mcsBcrConnect.getIp() + mcsBcrConnect.getPort()))) {
                                                return JsonData.fail(MessageFormat.format("导入失败!,第{0}行数据IP与端口号已存在", a+1), "3");
                                        }

                                }
                                // mcsBcrConnect.setId(StringUtil.getUUID32());
                        }
                        int i = mcsBcrConnectMapper.importExcel(mcsBcrConnectList);
                        if (i > 0) {
                                //1表示导入成功，2表示导入失败
                                return JsonData.success(MessageFormat.format("导入成功！从Excel导入数据一共 {0} 行", mcsBcrConnectList.size()));
                        } else {
                                return JsonData.fail("2");
                        }
                } else {
                        return JsonData.fail("导入数据为空", "3");
                }
        }

        @Override
        public PageData deleteMcsBcrConnect(String id) {
                PageData pageData = new PageData();
                int i = mcsBcrConnectMapper.deleteMcsBcrConnect(id);
                pageData.setCode(0);
                pageData.setMsg("");
                if (i < 1) {
                        pageData.setMsg("删除数据失败！");
                } else {
                        pageData.setMsg("删除数据成功！");
                }
                return pageData;
        }
}

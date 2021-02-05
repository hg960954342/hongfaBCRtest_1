package com.intplog.mcs.controller.McsController;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.intplog.mcs.bean.model.McsModel.McsBcrConnect;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.service.McsService.McsBcrConnectService;
import com.intplog.mcs.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: wcs
 * @description
 * @author: tianlei
 * @create: 2019-10-12 15:45
 **/
@Controller
@RequestMapping("/mcsBcrConnect")
@Slf4j
public class McsBcrConnectController {
        @Autowired
        private McsBcrConnectService mcsBcrConnectService;

        @GetMapping("/")
        public String toData() {
                return "bcrconnect/list";
        }

        /**
         * json请求数据
         *
         * @param ip
         * @param name
         * @param page
         * @param limit
         * @return
         */
        @GetMapping("/data")
        @ResponseBody
        public Object toData(String ip, String name, int page, int limit) {
                String i = ip.equals("") ? null : ip;
                String na = name.equals("") ? null : name;
                return mcsBcrConnectService.getList(i, na, page, limit);

        }

        @GetMapping("/add")
        public String toAddMcsBcrConnectPage() {
                return "bcrconnect/add";
        }

        @GetMapping("export")
        public void exportExcel(HttpServletResponse httpServletResponse, String ip, String name) {
                String bcrConnectIp = ip.equals("") ? null : ip;
                String bcrConnectName = name.equals("") ? null : name;
                List<McsBcrConnect> mcsBcrConnectList = mcsBcrConnectService.getExcelExport(bcrConnectIp, bcrConnectName);
                ExcelUtils.exportExcel(mcsBcrConnectList, "BCR连接信息", "BCR连接信息", McsBcrConnect.class, "BCR连接信息.xls", httpServletResponse);
        }

        @PostMapping("/import")
        @ResponseBody
        public JsonData importExcel(@RequestParam("file") MultipartFile file) throws Exception {
                ImportParams importParams = new ImportParams();
                //数据处理
                importParams.setHeadRows(1);
                importParams.setTitleRows(1);
                importParams.setNeedVerfiy(true);
                ExcelImportResult<McsBcrConnect> result = ExcelImportUtil.importExcelMore(file.getInputStream(), McsBcrConnect.class,
                        importParams);
                List<McsBcrConnect> mcsBcrConnectList = result.getList();
                return mcsBcrConnectService.importExcel(mcsBcrConnectList);
        }

        @GetMapping("/edit/{id}")
        public String toEditMcsBcrConnectPage(@PathVariable("id") String id, Model model) {
                McsBcrConnect mcsBcrConnect = mcsBcrConnectService.getMcsBcrConnectById(id);
                model.addAttribute("mcsBcrConnectInfo", mcsBcrConnect);
                return "bcrconnect/add";
        }

        /**
         * 添加读码器连接信息
         *
         * @param mcsBcrConnect
         * @return
         */
        @PostMapping("/")
        @ResponseBody
        public String toAddMcsBcrConnect(McsBcrConnect mcsBcrConnect) {
                int i = mcsBcrConnectService.insertMcsBcrConnect(mcsBcrConnect);
                if (i > 0) {
                        return "添加读码器连接信息成功";
                } else {
                        return "添加读码器连接信息失败";
                }
        }

        /**
         * 修改读码器连接信息
         *
         * @param mcsBcrConnect
         * @return
         */
        @PutMapping("/")
        @ResponseBody
        public String updateMcsBcrConnect(McsBcrConnect mcsBcrConnect) {
                McsBcrConnect mcs = mcsBcrConnectService.getMcsBcrConnectById(mcsBcrConnect.getId());
                if (mcs != null) {
                        mcs.setIp(mcsBcrConnect.getIp());
                        mcs.setName(mcsBcrConnect.getName());
                        mcs.setPort(mcsBcrConnect.getPort());
                        mcs.setRemark(mcsBcrConnect.getRemark());
                        int i = mcsBcrConnectService.updateMcsBcrConnect(mcs);
                        if (i > 0) {
                                return "修改读码器连接信息成功";
                        }

                }
                return "修改读码器连接信息失败";
        }

        /**
         * 删除读码器连接信息
         *
         * @param id
         * @return
         */
        @DeleteMapping("/{id}")
        @ResponseBody
        public Object toDeleteMcsBcrConnect(@PathVariable("id") String id) {
//                PageData pageData= mcsBcrConnectService.deleteMcsBcrConnect(id);
//                return pageData.getMsg();
                return mcsBcrConnectService.deleteMcsBcrConnect(id);
        }
}

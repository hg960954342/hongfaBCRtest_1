package com.intplog.mcs.controller.McsController;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.intplog.mcs.bean.model.McsModel.McsBcrProperties;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.service.McsService.McsBcrPropertiesService;
import com.intplog.mcs.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


/**
 * @author quqingmao
 * @date 2019-10-11
 */
@Controller
@RequestMapping("/mcsBcrProperTies")
public class McsBcrProperTiesController {
    @Autowired
    private McsBcrPropertiesService mcsBcrPropertiesService;

    /**
     * 连接管理界面
     *
     * @return
     */
    @GetMapping("/")
    public String list() {
        return "bcrproperties/list";
    }

    /**
     * 添加界面
     *
     * @return
     */
    @GetMapping("/adds")
    public String toAddLedConnectPage() {

        return "bcrproperties/add";
    }

    /**
     * 编辑界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String toEditMcsBcrPropertiesPage(@PathVariable("id") String id, Model model) {

        McsBcrProperties mcsBcrProperties = mcsBcrPropertiesService.getBcrId(id);
        model.addAttribute("mcsBcrProperties", mcsBcrProperties);
        return "bcrproperties/add";
    }

    /**
     * 保存功能
     *
     * @param mcsBcrProperties
     * @return
     */
    @PostMapping("/")
    @ResponseBody
    public String toAdd(McsBcrProperties mcsBcrProperties) {

        int i = mcsBcrPropertiesService.save(mcsBcrProperties);
        if (i > 0) {
            return "保存成功";
        } else {
            return "保存失败";
        }
    }

    /**
     * name or  connectId查询
     *
     * @param name
     * @param connectId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/data")
    @ResponseBody
    public Object selectBcr(String name, String connectId, int page, int limit) {
        String names = name.equals("") ? null : name;
        String connectIds = connectId.equals("") ? null : connectId;
        return mcsBcrPropertiesService.getAll(names, connectIds, page, limit);
    }

    /**
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/selectId")
    public String selectId(String id, String name) {
        mcsBcrPropertiesService.getBcrName(id, name);
        return "bcr";
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Object deleteBcr(@PathVariable("id") String id) {

        return mcsBcrPropertiesService.deleteBcrConnectId(id);
    }


    /**
     * 更新
     *
     * @param info
     * @return
     */
    @PutMapping("/")
    @ResponseBody
    public String updateConnectId(McsBcrProperties info) {

        McsBcrProperties mcsBcrProperties = mcsBcrPropertiesService.getBcrId(info.getId());

        String a =mcsBcrProperties.getId();
        String b = info.getId() ;


        if (a.equals(b) && mcsBcrProperties.getConnectId().equals(info.getConnectId()) && mcsBcrProperties.getName().equals(info.getName()) && mcsBcrProperties.getRemark().equals(info.getRemark())) {
            return "修改数据不能与原始数据相同";
        } else if (mcsBcrProperties.getId().equals(info.getId())  ||
                mcsBcrProperties.getConnectId().equals(info.getConnectId())
                || mcsBcrProperties.getName().equals(info.getName())  ||
                mcsBcrProperties.getRemark().equals(info.getRemark())) {
            mcsBcrProperties.setConnectId(info.getConnectId());
            mcsBcrProperties.setName(info.getName());
            mcsBcrProperties.setRemark(info.getRemark());
            int i = mcsBcrPropertiesService.updateMcsBcr(mcsBcrProperties);
            if (i > 0) {
                return "更新成功";
            }

        }

        return "更新失败";
    }

    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response, String name, String connectId) {
        String names = name.equals("") ? null : name;
        String ids = connectId.equals("") ? null : connectId;

        List<McsBcrProperties> mcsBcrProperties = mcsBcrPropertiesService.getBcrName(names, ids);
        ExcelUtils.exportExcel(mcsBcrProperties, "BCR配置信息", "BCR配置信息", McsBcrProperties.class, "BCR配置信息.xls", response);

    }

    @PostMapping("/import")
    @ResponseBody
    public String importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();


        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
        //需要验证
        importParams.setNeedVerfiy(false);

        ExcelImportResult<McsBcrProperties> result = ExcelImportUtil.importExcelMore(file.getInputStream(), McsBcrProperties.class,
                importParams);
        List<McsBcrProperties> mcsBcrPropertiesList = result.getList();

        for (McsBcrProperties mcsBcrProperties:mcsBcrPropertiesList
             ) {
            //mcsBcrProperties.setId(StringUtil.getUUID32());
            McsBcrProperties id = mcsBcrPropertiesService.getBcrId(mcsBcrProperties.getId());
            if(id!=null){
                return "3";
            }
            else {
                return "4";
            }
        }
        int i = mcsBcrPropertiesService.batchInsert(mcsBcrPropertiesList);
        if(i>1){
            return "1";
        }
        return "2";
    }

        /**
         * 获取读码器配置信息
         * @return
         */
    @PostMapping("/getBcrProperties")
    @ResponseBody
    public JsonData getBcrProperties(){
            String message= "";
            boolean isOk;
            Date begin= new Date();
            JsonData jsonData= new JsonData();
            List<McsBcrProperties>mcsBcrPropertiesList= mcsBcrPropertiesService.getAllList();
            if(mcsBcrPropertiesList.size()>0){
                    isOk=true;
            }
            else {
                    message+="获取读码器配置信息失败";
            }
            isOk = message.equals("")?true:false;
            jsonData.setSuccess(isOk);
            jsonData.setMessage(message);
            jsonData.setData(mcsBcrPropertiesList);
            return  jsonData;
    }
}

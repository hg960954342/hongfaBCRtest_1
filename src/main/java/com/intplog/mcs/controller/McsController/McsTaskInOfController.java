package com.intplog.mcs.controller.McsController;

import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.model.McsModel.McsTriggerTask;
import com.intplog.mcs.bean.model.McsModel.McsWeightProperTies;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.service.McsService.McsTaskInfoService;
import com.intplog.mcs.service.McsService.McsTriggerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/7/29
 */
@Controller
@RequestMapping("/mcsTaskInOf")
public class McsTaskInOfController {
    @Autowired
    private McsTaskInfoService mcsTaskInfoService;
    @Autowired
    private McsTriggerTaskService mcsTriggerTaskService;
    @GetMapping("/")
    public String mcsTaskList () {return "McsList/list";}

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Object deleteBcr(@PathVariable("id") String id) {
        PageData pageData = new PageData();
        int i = mcsTaskInfoService.deleteId(id);
        pageData.setMsg("");
        pageData.setCode(0);
        if (i < 1) {
            pageData.setMsg("删除失败");
        } else {
            pageData.setMsg("删除成功");
        }
        return pageData;
    }

    /**
     * Json请求数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/Data")
    @ResponseBody
    public Object toData(String bcrId , String bcrCode, int page, int limit) {
        String _name = bcrId.equals("") ? null : bcrId;
        String _group = bcrCode.equals("") ? null : bcrCode;
        return mcsTriggerTaskService.getAll( _name, _group, page, limit);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Object again(@PathVariable("id") String id) {
        PageData pageData = new PageData();
        McsTriggerTask mcsTriggerTaskId = mcsTriggerTaskService.getMcsTriggerTaskId(id);
        if (!StringUtils.isEmpty(mcsTriggerTaskId)) {
            mcsTriggerTaskId.setStatus(1);
            int i = mcsTriggerTaskService.updateMcsTriggerTaskData(mcsTriggerTaskId);
            pageData.setMsg("");
            pageData.setCode(0);
            if (i < 1) {
                pageData.setMsg("任务重新下发失败");
            } else {
                pageData.setMsg("任务重新下发成功");
            }
        } else {
            pageData.setMsg("任务重新下发失败");
        }
        return pageData;
    }

    @PutMapping("/send/{id}")
    @ResponseBody
    public Object send(@PathVariable("id") String id) {
        PageData pageData = new PageData();
        McsTaskInfo mcsTaskInfo = mcsTaskInfoService.getByTaskId(id);
        if (!StringUtils.isEmpty(mcsTaskInfo)&&mcsTaskInfo.getStatus()==1) {
            mcsTaskInfo.setStatus(2);
            int i = mcsTaskInfoService.update(mcsTaskInfo);
            //int m=mcsTaskOrderReportService.insertReport(mcsTaskInfo, 1, 0);
            pageData.setMsg("");
            pageData.setCode(0);
            if (i < 1) {
                pageData.setMsg("任务回告开始失败");
            } else {
                pageData.setMsg("任务回告开始成功");
            }
        } else {
            pageData.setMsg("没有任务需要回告开始");
        }
        return pageData;
    }

    @PutMapping("/finish/{id}")
    @ResponseBody
    public Object finish(@PathVariable("id") String id) {
        PageData pageData = new PageData();
        McsTaskInfo mcsTaskInfo = mcsTaskInfoService.getByTaskId(id);
        if (!StringUtils.isEmpty(mcsTaskInfo)) {
            mcsTaskInfo.setStatus(3);
            int i = mcsTaskInfoService.update(mcsTaskInfo);
            //mcsTaskOrderReportService.insertReport(mcsTaskInfo,2,0);
            pageData.setMsg("");
            pageData.setCode(0);
            if (i < 1) {
                pageData.setMsg("任务回告完成失败");
            } else {
                pageData.setMsg("任务回告完成成功");
            }
        } else {
            pageData.setMsg("没有任务需要回告完成");
        }
        return pageData;
    }



    /**
     * 更新任务状态
     *
     * @param data
     * @return
     */
    @PutMapping("/")
    @ResponseBody
    public String updateConnectId(McsTriggerTask data) {
        data.setCreateTime(new Date());
        int i = mcsTriggerTaskService.updateMcsTriggerTaskData(data);

        if (i > 0) {
            return "更新成功";

        } else {
            return "更新失败";
        }
    }

    /**
     * 编辑界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String toEditMcsWeightPropertiesPage(@PathVariable("id") String id, Model model) {

        McsTriggerTask mcsTriggerTaskId = mcsTriggerTaskService.getMcsTriggerTaskId(id);
        model.addAttribute("McsList", mcsTriggerTaskId);
        return "McsList/add";
    }

}

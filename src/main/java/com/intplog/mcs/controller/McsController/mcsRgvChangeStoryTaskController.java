package com.intplog.mcs.controller.McsController;

import com.intplog.mcs.bean.model.McsModel.McsRgvChangeStoryTask;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.service.McsService.McsRgvChangeStoryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/7/28
 */
@Controller
@RequestMapping("/mcsRgvChangeStoryTask")
public class mcsRgvChangeStoryTaskController {
    @Autowired
    private McsRgvChangeStoryTaskService mcsRgvChangeStoryTaskService;

    @GetMapping("/")
    public String McsRgvChange(){return "McsRgvChange/list";}

    /**
     * Json请求数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/Data")
    @ResponseBody
    public Object toData(String hoistId, String taskId, int page, int limit) {
        String _hoistId = hoistId.equals("") ? null : hoistId;
        String _taskId = taskId.equals("") ? null : taskId;
        return mcsRgvChangeStoryTaskService.getAllPageData( _taskId,_hoistId, page, limit);
    }

    /**
     * 删除
     *
     * @param taskId
     */
    @DeleteMapping("/{taskId}")
    @ResponseBody
    public Object deleteBcr(@PathVariable("taskId") String taskId) {
        PageData pageData = new PageData();
        int i = mcsRgvChangeStoryTaskService.deleteById(taskId);
        pageData.setMsg("");
        pageData.setCode(0);
        if (i < 1) {
            pageData.setMsg("删除失败");
        } else {
            pageData.setMsg("删除成功");
        }
        return pageData;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Object again(@PathVariable("id") String id) {
        PageData pageData = new PageData();
        McsRgvChangeStoryTask mcsTaskInfo = mcsRgvChangeStoryTaskService.getId(id);
        if (!StringUtils.isEmpty(mcsTaskInfo)) {
            mcsTaskInfo.setStatus(0);
            int i = mcsRgvChangeStoryTaskService.update(mcsTaskInfo);
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

    /**
     * 更新
     *
     * @param info
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public String updateConnectId(McsRgvChangeStoryTask info) {
        int i = mcsRgvChangeStoryTaskService.update(info);
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
        List<McsRgvChangeStoryTask> byTaskId = mcsRgvChangeStoryTaskService.getByTaskId(id);
        if(byTaskId.size()>0){
            for (McsRgvChangeStoryTask mcsRgvChangeStoryTask : byTaskId){
                model.addAttribute("McsRgvChange", mcsRgvChangeStoryTask);
                return "McsRgvChange/add";
            }

        }
        model.addAttribute("McsRgvChange", null);
        return  "McsRgvChange/add";

    }
}

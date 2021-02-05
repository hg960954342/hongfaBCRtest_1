package com.intplog.mcs.bean.model.McsModel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/27
 */
@Data
public class McsTaskRequest {

    public String LoginAuthType;
    /// <summary>
    /// 容器编号
    /// </summary>
    public String UserName ;

    public String UserPass ;

    public String AccountCode ;

    public String PluginModuleName;

    public String ActionName;

    public List<String> ParamList = new ArrayList<>();
}

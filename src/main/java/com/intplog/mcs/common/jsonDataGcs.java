package com.intplog.mcs.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quqingmao
 * @date 2020/7/22
 */
@Data
public class jsonDataGcs {
    private boolean ret;

    private String msg;

    private Object data;

    public jsonDataGcs() {

    }

    public jsonDataGcs(boolean ret){
        this.ret = ret;
    }

    public static jsonDataGcs success (Object object, String msg){
        jsonDataGcs jsonData = new jsonDataGcs(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public static jsonDataGcs success (Object object){
        jsonDataGcs jsonData = new jsonDataGcs(true);
        jsonData.data = object;
        return jsonData;
    }

    public static jsonDataGcs success (){
        jsonDataGcs jsonData = new jsonDataGcs(true);
        return jsonData;
    }

    public static jsonDataGcs fail(String msg){
        jsonDataGcs jsonData = new jsonDataGcs(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public static jsonDataGcs fail(Object object,String msg){
        jsonDataGcs jsonData = new jsonDataGcs(false);
        jsonData.msg = msg;
        jsonData.data=object;
        return jsonData;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<String,Object>();
        result.put("ret",ret);
        result.put("msg",msg);
        result.put("data",data);
        return result;
    }
}

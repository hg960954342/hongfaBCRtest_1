package com.intplog.mcs.plc.bean;

import com.intplog.mcs.bean.model.McsModel.McsHoist;
import com.intplog.mcs.bean.model.McsModel.McsPlcVariable;
import com.intplog.mcs.bean.model.McsModel.McsStacker;
import com.intplog.mcs.bean.model.McsModel.McsWeightProperTies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: mcs
 * @description
 * @author: tianlei
 * @create: 2020-03-09 15:07
 **/
@Getter
@Setter
@ToString
public class HoistDataPacket {
    /**
     * 直入直出口状态字典
     */
    private   Map<String,HoistPlc> straightHoistPlcListMap = new ConcurrentHashMap<>();
    /**
     * 非直入直出状态字典
     */
    private   Map<String,Map<String,HoistPlc>> hoistPlcListMap=new ConcurrentHashMap<>() ;
    /**
     * 提升机对象字典
     */
    private Map<String,McsHoist>hoistMap= new ConcurrentHashMap<>();
    /**
     *提升机跨层呼叫提升机写值字典
     */
    private Map<String,McsPlcVariable> crossLayerPlcMap =new ConcurrentHashMap<>();
    /**
     * 非直出入口称重设备字典
     */
    private Map<String,McsWeightProperTies> weightProperListTiesMap= new ConcurrentHashMap<>() ;
    /**
     *  非直入直出尺寸检测地址集合
     */
    private  Map<String,List<McsPlcVariable>>detectionPlcVariableMap = new ConcurrentHashMap<>();

    /**
     *  提升机状态和当前楼层检测地址集合
     */
    private  Map<String,McsPlcVariable>hoistStatusPlcVariableMap= new ConcurrentHashMap<>();
    /**
     * 非直出入口出入库地址字典
     */
    private Map<String, McsPlcVariable> weightPlcVariableMap= new ConcurrentHashMap<>() ;

    /**
     *  直入直出尺寸检测地址集合
     */
    private  Map<String,McsPlcVariable>  straightSizePlcVariableMap = new ConcurrentHashMap<>() ;

    /**
     * 直入直出是否可以称重触发地址集合
     */
    private Map<String,McsPlcVariable> straightWeightPlcVariableMap = new ConcurrentHashMap<>() ;

    /**
     * 拆叠盘机状态地址字典
     */
    private Map<String, McsStacker>  stackerMap= new ConcurrentHashMap<>();
    /**
     * 拆叠盘机写值地址字典
     */
    private Map<String,McsStacker>  stackerTaskMap= new ConcurrentHashMap<>();
    /**
     * 故障查询地址
     */
    private Map<String,AlarmStatus> mcsAlarmStatusMap=new ConcurrentHashMap<>();
    /**
     * PLC系统方向查询地址
     */
    private Map<String,HoistPlc> mcsPlcDirection= new ConcurrentHashMap<>();

    private Map<String,McsPlcVariable> bcrAndWeightStatus= new ConcurrentHashMap<>();

    /**
     *  提升机状态改变集合
     */
    private  Map<String,McsHoist> hoistStatusChangeMap = new ConcurrentHashMap<>();
    /**
     * 小车到提升机校准地址
     */
    private Map<String ,HoistPlc > rgvInHoistMap = new ConcurrentHashMap<>();

    /**
     * 小车是否允许进入提升机地址
     */
    private Map<String , McsPlcVariable> isIsPermitMap = new ConcurrentHashMap<>();

    private Map<String , McsPlcVariable> outStatusMap = new ConcurrentHashMap<>();

    private Map<String , McsPlcVariable> outClearMap = new ConcurrentHashMap<>();

    private Map<String , McsPlcVariable> interceptMap = new ConcurrentHashMap<>();

    private Map<String , McsPlcVariable> inPlaceMap = new ConcurrentHashMap<>();

    /**
     * 箱式输送线plc地址集合
     */
    private Map<String, McsPlcVariable> plcAddress = new ConcurrentHashMap<>();

}

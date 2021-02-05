package com.intplog.mcs.bean.dto.GcsDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/7/17
 */
@Getter
@Setter
@ToString
public class GcsOrderReport {
    /**
     * id 		  	  String	【32位uuid】
     * billDate	  String	【到位回告时间】
     * billCode      String    【EIS生成的任务单号】
     * containerCode String    【执行母托盘编号】
     * sysType	  	  Int       【系统类型：1:GCS  2:MCS】
     * taskType	  Int       【任务类型：1:入库 2:出库 3:搬运 4:跨层】
     * locCodeTo     String    【目的位置】
     * position      String    【当前位置】
     * workStatus    Int       【作业状态:1:开始  2:结束】
     * rgvId	      String 	【rgv小车编号】
     */

    private String id;

    private String billDate;

    private String billCode;

    private String containerCode;

    private int  sysType;

    private int  taskType;

    private String locCodeTo;

    private String position;

    private int  workStatus;

    private String rgvId;

}

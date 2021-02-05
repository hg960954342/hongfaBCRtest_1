package com.intplog.mcs.enums;

public enum HoistStatus {

    Manual(1,"手动状态"),
    Auto(2,"自动"),
    Fault(3,"故障");

 private  final int value;
 private final String  desc;
 HoistStatus(int value,String desc){
     this.value=value;
     this.desc=desc;

 }

 public int getValue(){
     return value;
 }
 public  String getDesc(){
     return  desc;
 }
 public static HoistStatus ofValue(Integer target){
     for(HoistStatus status:values()){
         if(status.value == target){
             return status;
         }
     }
     return null;
 }

}

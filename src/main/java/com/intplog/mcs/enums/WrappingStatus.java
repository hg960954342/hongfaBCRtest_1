package com.intplog.mcs.enums;

/**
 * @program: wcs
 * @description
 * @author: tianlei
 * @create: 2019-11-29 15:30
 * 枚举类：缠绕机回告状态
 **/
public enum WrappingStatus {

        FINISHED(20, "正常（缠膜完成）"),
        ERROR(20, "异常");

        private final int value;
        private final String desc;

        WrappingStatus(int value, String desc) {
                this.value = value;
                this.desc = desc;
        }

        public int getValue() {
                return value;
        }

        public String getDesc() {
                return desc;
        }

        public static WrappingStatus ofValue(Integer target) {
                for (WrappingStatus status : values()) {
                        if (status.value == target) {
                                return status;
                        }
                }
                return null;
        }


}

package com.intplog.mcs.bean.model;

import lombok.Data;

/**
 * @author quqingmao
 * @date 2020/7/25
 */
@Data
public class CompareModel {
    private int count;
    private Object msg;


    public CompareModel(int count, Object msg) {
        this.count = count;
        this.msg = msg;
    }

    public CompareModel() {
    }
}

package com.bjike.goddess.financeinit.vo;

import com.bjike.goddess.financeinit.enums.UseComm;

/**
 * 常用摘要表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-13 12:56 ]
 * @Description: [ 常用摘要表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class UseCommonlyVO {

    /**
     * id
     */
    private String id;
    /**
     * 常用摘要
     */
    private UseComm useComm;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UseComm getUseComm() {
        return useComm;
    }

    public void setUseComm(UseComm useComm) {
        this.useComm = useComm;
    }
}
package com.bjike.goddess.financeinit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.financeinit.enums.UseComm;

/**
 * 常用摘要业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-13 12:56 ]
 * @Description: [ 常用摘要业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class UseCommonlyBO extends BaseBO {

    /**
     * 常用摘要
     */
    private UseComm useComm;


    public UseComm getUseComm() {
        return useComm;
    }

    public void setUseComm(UseComm useComm) {
        this.useComm = useComm;
    }
}
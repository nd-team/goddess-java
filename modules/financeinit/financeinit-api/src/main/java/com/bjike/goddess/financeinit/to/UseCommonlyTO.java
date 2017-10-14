package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.financeinit.enums.UseComm;

/**
 * 常用摘要
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-13 12:56 ]
 * @Description: [ 常用摘要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class UseCommonlyTO extends BaseTO {

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
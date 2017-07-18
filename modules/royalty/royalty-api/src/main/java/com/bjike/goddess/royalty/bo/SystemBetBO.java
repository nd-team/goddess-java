package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 体系间对赌表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-11 11:31 ]
 * @Description: [ 体系间对赌表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetBO extends BaseBO {

    /**
     * 体系间对赌表A
     */
    private SystemBetABO systemBetABO;


    public SystemBetABO getSystemBetABO() {
        return systemBetABO;
    }

    public void setSystemBetABO(SystemBetABO systemBetABO) {
        this.systemBetABO = systemBetABO;
    }

}
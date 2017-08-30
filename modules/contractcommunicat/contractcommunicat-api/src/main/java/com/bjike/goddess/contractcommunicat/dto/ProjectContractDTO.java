package com.bjike.goddess.contractcommunicat.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;

/**
 * 项目承包洽谈数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.912 ]
 * @Description: [ 项目承包洽谈数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectContractDTO extends BaseDTO {

    /**
     * 洽谈人
     */
    private String communicateUser;
    /**
     * 洽谈对象
     */
    private String communicateObj;
    /**
     * 项目结果
     */
    private CommunicateResult communicateResult;


    public String getCommunicateUser() {
        return communicateUser;
    }

    public void setCommunicateUser(String communicateUser) {
        this.communicateUser = communicateUser;
    }

    public String getCommunicateObj() {
        return communicateObj;
    }

    public void setCommunicateObj(String communicateObj) {
        this.communicateObj = communicateObj;
    }

    public CommunicateResult getCommunicateResult() {
        return communicateResult;
    }

    public void setCommunicateResult(CommunicateResult communicateResult) {
        this.communicateResult = communicateResult;
    }

}
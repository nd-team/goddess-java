package com.bjike.goddess.marketactivitymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 客户信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 07:03 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerInfoTO extends BaseTO {

    /**
     * 客户信息编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "客户信息编号不能为空")
    private String clientInfoNo;

    /**
     * 客户姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "客户姓名不能为空")
    private String clientName;

    /**
     * 重要性级别
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "重要性级别不能为空")
    private String importanceLevel;


    /**
     * 市场招待唯一标识
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "市场招待唯一标识不能为空")
    private String marketServeId;

    public String getClientInfoNo() {
        return clientInfoNo;
    }

    public void setClientInfoNo(String clientInfoNo) {
        this.clientInfoNo = clientInfoNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(String importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public String getMarketServeId() {
        return marketServeId;
    }

    public void setMarketServeId(String marketServeId) {
        this.marketServeId = marketServeId;
    }

}
package com.bjike.goddess.marketactivitymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    public interface EditCustomer{}

    /**
     * 客户信息编号
     */
    private String clientInfoNo;

    /**
     * 客户姓名
     */
    private String clientName;

    /**
     * 重要性级别
     */
    private String importanceLevel;

    /**
     * 市场招待唯一标识
     */
    @NotBlank(groups = {ADD.class, CustomerInfoTO.EditCustomer.class}, message = "市场招待唯一标识不能为空")
    private String marketServeId;

    /**
     * 客户信息编号
     */
    @NotNull(groups = {ADD.class, CustomerInfoTO.EditCustomer.class}, message = "客户信息编号不能为空")
    private List<String> clientInfoNos;

    /**
     * 客户姓名
     */
    @NotNull(groups = {ADD.class, CustomerInfoTO.EditCustomer.class}, message = "客户姓名不能为空")
    private List<String> clientNames;

    /**
     * 客户重要性级别
     */
    @NotNull(groups = {ADD.class, CustomerInfoTO.EditCustomer.class}, message = "客户重要性级别不能为空")
    private List<String> importanceLevels;

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

    public List<String> getClientInfoNos() {
        return clientInfoNos;
    }

    public void setClientInfoNos(List<String> clientInfoNos) {
        this.clientInfoNos = clientInfoNos;
    }

    public List<String> getClientNames() {
        return clientNames;
    }

    public void setClientNames(List<String> clientNames) {
        this.clientNames = clientNames;
    }

    public List<String> getImportanceLevels() {
        return importanceLevels;
    }

    public void setImportanceLevels(List<String> importanceLevels) {
        this.importanceLevels = importanceLevels;
    }


}
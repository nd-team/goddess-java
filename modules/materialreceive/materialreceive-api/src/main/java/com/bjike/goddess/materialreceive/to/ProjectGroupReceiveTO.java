package com.bjike.goddess.materialreceive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 项目组领用归还登记
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还登记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectGroupReceiveTO extends BaseTO {

    public interface RETURNMATERIAL{}//归还物资

    /**
     * 领用时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "领用时间不能为空")
    private String receiveTime;

    /**
     * 物品名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "物品名称不能为空")
    private String materialName;

    /**
     * 物资领用编号
     */
    private String materialNo;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 领用人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "领用人不能为空")
    private String recipient;

    /**
     * 发放人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "发放人不能为空")
    private String issuer;

    /**
     * 归还时间
     */
    @NotBlank(groups = {ProjectGroupReceiveTO.RETURNMATERIAL.class}, message = "归还时间不能为空")
    private String returnTime;

    /**
     * 归还人
     */
    @NotBlank(groups = {ProjectGroupReceiveTO.RETURNMATERIAL.class}, message = "归还人不能为空")
    private String returnPerson;

    /**
     * 领用编号
     */
    @Size(groups = {ADD.class, EDIT.class, ProjectGroupReceiveTO.RETURNMATERIAL.class}, message = "领用数量必修是大于0的整数")
    private String[] materialNum;


    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnPerson() {
        return returnPerson;
    }

    public void setReturnPerson(String returnPerson) {
        this.returnPerson = returnPerson;
    }

    public String[] getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(String[] materialNum) {
        this.materialNum = materialNum;
    }
}
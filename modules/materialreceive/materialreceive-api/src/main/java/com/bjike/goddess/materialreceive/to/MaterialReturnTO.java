package com.bjike.goddess.materialreceive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialreceive.type.AuditState;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 物资归还to
 *
 * @Author: [sunfengtao]
 * @Date: [2017-06-05 16:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MaterialReturnTO extends BaseTO {

    public interface MATERIALRETURN {
    }//物资归还

    /**
     * 是否归还
     */
    @NotNull(groups = {MaterialReturnTO.MATERIALRETURN.class}, message = "是否归还不能为空")
    private Boolean ifReturn;

    /**
     * 审核情况
     */
    @NotNull(groups = {MaterialReturnTO.MATERIALRETURN.class}, message = "审核情况不能为空")
    private AuditState auditCase;

    /**
     * 归还时间
     */
    @NotBlank(groups = {MaterialReturnTO.MATERIALRETURN.class}, message = "归还时间不能为空")
    private String returnTime;

    /**
     * 物资状态
     */
    @NotNull(groups = {MaterialReturnTO.MATERIALRETURN.class}, message = "物资状态不能为空")
    private MaterialState materialState;

    /**
     * 领用编号
     */
    @NotNull(groups = {ADD.class, EDIT.class, MaterialReturnTO.MATERIALRETURN.class}, message = "领用数量必修是大于0的整数")
    private String[] materialNum;

    public Boolean getIfReturn() {
        return ifReturn;
    }

    public void setIfReturn(Boolean ifReturn) {
        this.ifReturn = ifReturn;
    }

    public AuditState getAuditCase() {
        return auditCase;
    }

    public void setAuditCase(AuditState auditCase) {
        this.auditCase = auditCase;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public MaterialState getMaterialState() {
        return materialState;
    }

    public void setMaterialState(MaterialState materialState) {
        this.materialState = materialState;
    }

    public String[] getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(String[] materialNum) {
        this.materialNum = materialNum;
    }
}

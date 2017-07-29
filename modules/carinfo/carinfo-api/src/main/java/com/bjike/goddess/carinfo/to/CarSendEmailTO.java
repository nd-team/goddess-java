package com.bjike.goddess.carinfo.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 邮箱发送
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 邮箱发送 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarSendEmailTO extends BaseTO {


    /**
     * 岗位Id
     */
    @NotBlank(message = "岗位Id不能为空", groups = {ADD.class, EDIT.class})
    private String positionNameId;
    /**
     * 项目组id
     */
    @NotBlank(message = "岗位Id不能为空", groups = {ADD.class, EDIT.class})
    private String projetManagerId;

    public String getPositionNameId() {
        return positionNameId;
    }

    public void setPositionNameId(String positionNameId) {
        this.positionNameId = positionNameId;
    }

    public String getProjetManagerId() {
        return projetManagerId;
    }

    public void setProjetManagerId(String projetManagerId) {
        this.projetManagerId = projetManagerId;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
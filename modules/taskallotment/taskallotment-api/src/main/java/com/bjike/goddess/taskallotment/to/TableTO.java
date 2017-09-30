package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.taskallotment.enums.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableTO extends BaseTO {
    public interface EDITTABLE{}

    /**
     * 表名称
     */
    @NotBlank(groups = TableTO.EDITTABLE.class,message = "表名称不能为空")
    private String name;
    /**
     * 状态
     */
    @NotNull(groups = TableTO.EDITTABLE.class,message = "状态不能为空")
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
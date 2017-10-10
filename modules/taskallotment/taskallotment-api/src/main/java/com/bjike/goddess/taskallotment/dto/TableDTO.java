package com.bjike.goddess.taskallotment.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 项目表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableDTO extends BaseDTO {
    public interface TABLE{}
    /**
     * 项目id数组
     */
    @NotNull(groups = TableDTO.TABLE.class,message = "项目id数组不能为空")
    private String[] projectIds;

    public String[] getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String[] projectIds) {
        this.projectIds = projectIds;
    }
}
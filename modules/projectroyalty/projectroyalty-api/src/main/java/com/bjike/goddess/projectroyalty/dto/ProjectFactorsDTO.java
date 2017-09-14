package com.bjike.goddess.projectroyalty.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目提成分配因素数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 11:39 ]
 * @Description: [ 项目提成分配因素数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectFactorsDTO extends BaseDTO {
    /**
     * 更新时间
     */
    private String modifyTime;

    /**
     * 更新人
     */
    private String name;

    /**
     * 方案编号
     */
    private String code;

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
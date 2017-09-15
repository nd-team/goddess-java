package com.bjike.goddess.managepromotion.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 技能评定标准数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 12:00 ]
 * @Description: [ 技能评定标准数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillStandardDTO extends BaseDTO {
    /**
     * 技能定位-专业(业务范围包含的技能)
     */
    private String major;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
package com.bjike.goddess.otherexpenses.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 其他费用数据传输对象
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-03 11:49 ]
 * @Description: [ 其他费用数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OtherExpensesDTO extends BaseDTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String project;

    /**
     * 类别
     */
    private String type;

    /**
     * 项目名称
     */
    private String name;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
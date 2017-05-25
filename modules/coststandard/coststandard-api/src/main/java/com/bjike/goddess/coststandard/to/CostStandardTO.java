package com.bjike.goddess.coststandard.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 费用标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:22 ]
 * @Description: [ 费用标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostStandardTO extends BaseTO {

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 标准
     */
    @NotBlank(message = "标准不能为空", groups = {ADD.class, EDIT.class})
    private String standard;

    /**
     * 描述
     */
    private String description;

    /**
     * 适用部门或项目组
     */
    @NotBlank(message = "适用部门或项目组不能为空", groups = {ADD.class, EDIT.class})
    private String department;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
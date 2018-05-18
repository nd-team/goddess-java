package com.bjike.goddess.analysis.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ike on 17-7-6.
 */
public class CollectTO extends BaseTO{
    public interface TestDepartment{}
    public interface TestArea{}
    /**
     * 地区
     */
    @NotNull(message = "地区不能为空",groups = CollectTO.TestArea.class)
    private String[] area;
    /**
     * 项目组
     */
    @NotNull(message = "项目组不能为空",groups = CollectTO.TestDepartment.class)
    private String[] department;


    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public String[] getDepartment() {
        return department;
    }

    public void setDepartment(String[] department) {
        this.department = department;
    }
}

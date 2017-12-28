package com.bjike.goddess.socialinsurance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 社会保险汇总
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 17:46]
 * @Description: [　社会保险汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SocialInsuranceCollectDTO extends BaseDTO{

    public interface TestGet{}

    /**
     * 开始时间 例:2017-11
     */
    @NotBlank(groups = {SocialInsuranceCollectDTO.TestGet.class}, message = "开始日期不能为空")
    private String startDate;

    /**
     * 结束时间 例:2017-12
     */
    @NotBlank(groups = {SocialInsuranceCollectDTO.TestGet.class}, message = "结束日期不能为空")
    private String endDate;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门
     */
    private String department;

    /**
     * 地区
     */
    private String area;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

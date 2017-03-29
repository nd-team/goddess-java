package com.bjike.goddess.businessinteraction.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 互动平台需求描述数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:21 ]
 * @Description: [ 互动平台需求描述数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemandDTO extends BaseDTO {

    public interface TESTDemandDTO{}
    public interface TESTDemandDto{}

    /**
     * 需求者姓名
     */
    @NotBlank(groups = DemandDTO.TESTDemandDto.class,message = "需求者姓名不能为空")
    private String name;

    /**
     * 业务方向
     */
    @NotBlank(groups = DemandDTO.TESTDemandDTO.class,message = "业务方向不能为空")
    private String businessTarget;

    /**
     * 规模
     */
    @NotBlank(groups = DemandDTO.TESTDemandDTO.class,message = "规模不为空")
    private String size;

    /**
     * 专业
     */
    @NotBlank(groups = DemandDTO.TESTDemandDTO.class,message = "专业不为空")
    private String profession;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessTarget() {
        return businessTarget;
    }

    public void setBusinessTarget(String businessTarget) {
        this.businessTarget = businessTarget;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
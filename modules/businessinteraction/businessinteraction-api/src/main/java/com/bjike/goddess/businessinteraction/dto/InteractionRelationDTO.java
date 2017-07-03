package com.bjike.goddess.businessinteraction.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 商业能力互动联系数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:06 ]
 * @Description: [ 商业能力互动联系数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InteractionRelationDTO extends BaseDTO {


    public interface TESTInteractionRelationDto{}

    /**
     * 公司名称
     */
    @NotBlank(groups = InteractionRelationDTO.TESTInteractionRelationDto.class,message = "公司名称不能为空")
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
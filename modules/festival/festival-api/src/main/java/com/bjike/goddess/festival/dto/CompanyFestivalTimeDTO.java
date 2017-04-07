package com.bjike.goddess.festival.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.festival.to.CompanyFestivalTimeTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 公司放假时间安排数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyFestivalTimeDTO extends BaseDTO {

    public interface TESTGetOne {
    }

    /**
     * 节假日名称
     */
    @NotBlank(groups = CompanyFestivalTimeDTO.TESTGetOne.class, message = "节假日名称不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
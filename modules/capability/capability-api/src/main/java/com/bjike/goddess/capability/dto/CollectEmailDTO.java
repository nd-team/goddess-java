package com.bjike.goddess.capability.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 邮件发送定制数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.878 ]
 * @Description: [ 邮件发送定制数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectEmailDTO extends BaseDTO {

    public interface TestCompany {
    }

    public interface TestPerson {
    }

    /**
     * 公司集合
     */
    @NotNull(groups = {CollectEmailDTO.TestCompany.class}, message = "公司集合不能为空")
    private String[] companys;

    /**
     * 个人姓名集合
     */
    @NotNull(groups = {CollectEmailDTO.TestPerson.class}, message = "个人姓名集合不能为空")
    private String[] names;

    public String[] getCompanys() {
        return companys;
    }

    public void setCompanys(String[] companys) {
        this.companys = companys;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}
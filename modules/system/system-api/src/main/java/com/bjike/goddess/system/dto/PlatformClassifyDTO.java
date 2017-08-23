package com.bjike.goddess.system.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.system.entity.PlatformClassify;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 平台分类数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 01:44 ]
 * @Description: [ 平台分类数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlatformClassifyDTO extends BaseDTO {
    public interface TestExport{}
    /**
     * 平台名称
     */
    @NotNull(message = "平台名称不能为空",groups = {PlatformClassifyDTO.TestExport.class})
    private String[] platformName;

    public String[] getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String[] platformName) {
        this.platformName = platformName;
    }
}
package com.bjike.goddess.businessinteraction.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 洽谈详情数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:27 ]
 * @Description: [ 洽谈详情数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TalkDetailDTO extends BaseDTO {

    public interface TESTTalkDetailDTO{}

    /**
     * 合作对象公司名称
     */
    @NotBlank(groups = TalkDetailDTO.TESTTalkDetailDTO.class,message = "合作对象公司名称不能为空")
    private String cooperCompany;

    public String getCooperCompany() {
        return cooperCompany;
    }

    public void setCooperCompany(String cooperCompany) {
        this.cooperCompany = cooperCompany;
    }
}
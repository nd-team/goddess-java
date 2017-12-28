package com.bjike.goddess.recruit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitProDTO extends BaseDTO {
    /**
     * 招聘网站
     */
    private String recruitSite;
    /**
     * 是否签订合同
     */
    private Boolean haveContract;

    public String getRecruitSite() {
        return recruitSite;
    }

    public void setRecruitSite(String recruitSite) {
        this.recruitSite = recruitSite;
    }

    public Boolean getHaveContract() {
        return haveContract;
    }

    public void setHaveContract(Boolean haveContract) {
        this.haveContract = haveContract;
    }
}

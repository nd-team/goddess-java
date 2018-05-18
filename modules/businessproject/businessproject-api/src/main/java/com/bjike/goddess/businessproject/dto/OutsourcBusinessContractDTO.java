package com.bjike.goddess.businessproject.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import scala.util.parsing.combinator.testing.Str;

import javax.validation.constraints.NotNull;

/**
 * 外包半外包项目合同管理数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:55 ]
 * @Description: [ 外包半外包项目合同管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutsourcBusinessContractDTO extends BaseDTO {
    public interface export{}
    /**
     * 签订时间
     */
    private String signedTime;

    /**
     * 地区
     */
    private String area;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 总包单位名称
     */
    private String majorCompany;

    /**
     * 所属项目组
     */
    private String projectGroup;
    /**
     * 验收是否通过
     */
    private Boolean acceptorPass;
    /**
     * 结算流程进度
     */
    private String settlementProgress;

    /**
     * 地区
     */
    @NotNull(message = "地区不能为空",groups = {OutsourcBusinessContractDTO.export.class})
    private String[] areas;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Boolean getAcceptorPass() {
        return acceptorPass;
    }

    public void setAcceptorPass(Boolean acceptorPass) {
        this.acceptorPass = acceptorPass;
    }

    public String getSettlementProgress() {
        return settlementProgress;
    }

    public void setSettlementProgress(String settlementProgress) {
        this.settlementProgress = settlementProgress;
    }
}
package com.bjike.goddess.competitormanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.competitormanage.enums.BusinessType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 竞争对手信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompetitorOrganizaeTO extends BaseTO {

    public interface Organization {
    }

    /**
     * 主管部门
     */
    @NotBlank(message = "主管部门不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String directDepartment;

    /**
     * 主管名称
     */
    @NotBlank(message = "主管部门不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String director;

    /**
     * 主管职权
     */
    @NotBlank(message = "主管职权不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String directAuthority;

    /**
     * 负责事项
     */
    @NotBlank(message = "负责事项不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String chargeItems;

    /**
     * 客户信息表序号
     */
    @NotBlank(message = "客户信息表序号不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String customerInfoCode;

    /**
     * 分管部门
     */
    @NotBlank(message = "分管部门不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String branchedDepartment;

    /**
     * 负责人名称
     */
    @NotBlank(message = "负责人名称不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String chargeMan;

    /**
     * 负责人职权
     */
    @NotBlank(message = "负责人职权不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String chargeManAuthority;

    /**
     * 接口人
     */
    @NotBlank(message = "接口人不能为空", groups = {CompetitorOrganizaeTO.Organization.class})
    private String interfaceMan;

    public String getDirectDepartment() {
        return directDepartment;
    }

    public void setDirectDepartment(String directDepartment) {
        this.directDepartment = directDepartment;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirectAuthority() {
        return directAuthority;
    }

    public void setDirectAuthority(String directAuthority) {
        this.directAuthority = directAuthority;
    }

    public String getChargeItems() {
        return chargeItems;
    }

    public void setChargeItems(String chargeItems) {
        this.chargeItems = chargeItems;
    }

    public String getCustomerInfoCode() {
        return customerInfoCode;
    }

    public void setCustomerInfoCode(String customerInfoCode) {
        this.customerInfoCode = customerInfoCode;
    }

    public String getBranchedDepartment() {
        return branchedDepartment;
    }

    public void setBranchedDepartment(String branchedDepartment) {
        this.branchedDepartment = branchedDepartment;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getChargeManAuthority() {
        return chargeManAuthority;
    }

    public void setChargeManAuthority(String chargeManAuthority) {
        this.chargeManAuthority = chargeManAuthority;
    }

    public String getInterfaceMan() {
        return interfaceMan;
    }

    public void setInterfaceMan(String interfaceMan) {
        this.interfaceMan = interfaceMan;
    }
}
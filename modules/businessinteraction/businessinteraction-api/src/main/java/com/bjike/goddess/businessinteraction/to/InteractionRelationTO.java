package com.bjike.goddess.businessinteraction.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 公司信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 08:58 ]
 * @Description: [ 公司信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InteractionRelationTO extends BaseTO {


    /**
     * 地区
     */
    private String area;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空",groups = {ADD.class, EDIT.class})
    private String companyName;

    /**
     * 公司电话
     */
    @NotBlank(message = "公司电话不能为空",groups = {ADD.class, EDIT.class})
    private String companyTel;

    /**
     * 公司邮箱
     */
    private String companyEmail;

    /**
     * 公司主页
     */
    private String companyMajorPage;

    /**
     * 公司业务方向
     */
    private String companyBussWay;

    /**
     * 公司微信号
     */
    private String companyWebchat;

    /**
     * 公司公众号
     */
    private String companyPublic;

    /**
     * 公司QQ号
     */
    private String companyQQ;

    /**
     * 公司论坛
     */
    private String companyTalk;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyMajorPage() {
        return companyMajorPage;
    }

    public void setCompanyMajorPage(String companyMajorPage) {
        this.companyMajorPage = companyMajorPage;
    }

    public String getCompanyBussWay() {
        return companyBussWay;
    }

    public void setCompanyBussWay(String companyBussWay) {
        this.companyBussWay = companyBussWay;
    }

    public String getCompanyWebchat() {
        return companyWebchat;
    }

    public void setCompanyWebchat(String companyWebchat) {
        this.companyWebchat = companyWebchat;
    }

    public String getCompanyPublic() {
        return companyPublic;
    }

    public void setCompanyPublic(String companyPublic) {
        this.companyPublic = companyPublic;
    }

    public String getCompanyQQ() {
        return companyQQ;
    }

    public void setCompanyQQ(String companyQQ) {
        this.companyQQ = companyQQ;
    }

    public String getCompanyTalk() {
        return companyTalk;
    }

    public void setCompanyTalk(String companyTalk) {
        this.companyTalk = companyTalk;
    }
}
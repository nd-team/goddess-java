package com.bjike.goddess.businessevaluate.to.interiorevaluate;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 商务负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:34 ]
 * @Description: [ 商务负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessPrincipalEvaluateTO extends BaseTO {

    /**
     * 评价项目负责人工作情况
     */
    @NotBlank(message = "评价项目负责人工作情况不能为空", groups = {ADD.class, EDIT.class})
    private String projectprincipal;

    /**
     * 客户关系发展情况
     */
    @NotBlank(message = "客户关系发展情况不能为空", groups = {ADD.class, EDIT.class})
    private String cusDevSituation;

    /**
     * 其他
     */
    private String another;

    /**
     * 项目信息Id
     */
    @NotBlank(message = "项目不能为空", groups = {ADD.class, EDIT.class})
    private String projectInfoId;


    public String getProjectprincipal() {
        return projectprincipal;
    }

    public void setProjectprincipal(String projectprincipal) {
        this.projectprincipal = projectprincipal;
    }

    public String getCusDevSituation() {
        return cusDevSituation;
    }

    public void setCusDevSituation(String cusDevSituation) {
        this.cusDevSituation = cusDevSituation;
    }

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}
package com.bjike.goddess.businessevaluate.to.interiorevaluate;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 商务负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 01:55 ]
 * @Description: [ 商务负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectPrincipalEvaluateTO extends BaseTO {

    /**
     * 评价现场工作人员情况
     */
    @NotBlank(message = "评价现场工作人员情况不能为空",groups = {ADD.class, EDIT.class})
    private String evaluateFrontline;

    /**
     * 评价商务部工作情况
     */
    @NotBlank(message = "评价商务部工作情况不能为空",groups = {ADD.class, EDIT.class})
    private String evaluateBusiness;

    /**
     * 其他
     */
    private String another;

    /**
     * 项目信息Id
     */
    @NotBlank(message = "项目不能为空",groups = {ADD.class, EDIT.class})
    private String projectInfoId;


    public String getEvaluateFrontline() {
        return evaluateFrontline;
    }

    public void setEvaluateFrontline(String evaluateFrontline) {
        this.evaluateFrontline = evaluateFrontline;
    }

    public String getEvaluateBusiness() {
        return evaluateBusiness;
    }

    public void setEvaluateBusiness(String evaluateBusiness) {
        this.evaluateBusiness = evaluateBusiness;
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
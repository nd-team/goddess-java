package com.bjike.goddess.businessevaluate.to.interiorevaluate;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 一线体系评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:00 ]
 * @Description: [ 一线体系评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FrontlineEvaluateTO extends BaseTO {

    /**
     * 现场实施情况
     */
    private String spotSituation;

    /**
     * 其它
     */
    private String another;

    /**
     * 项目信息Id
     */
    private String projectInfoId;


    public String getSpotSituation() {
        return spotSituation;
    }

    public void setSpotSituation(String spotSituation) {
        this.spotSituation = spotSituation;
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
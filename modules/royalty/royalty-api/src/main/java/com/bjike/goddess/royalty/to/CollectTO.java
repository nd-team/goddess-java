package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:21 ]
 * @Description: [ 汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectTO extends BaseTO {

    /**
     * 项目名称
     */
    private String[] projectName;

    public String[] getProjectName() {
        return projectName;
    }

    public void setProjectName(String[] projectName) {
        this.projectName = projectName;
    }
}
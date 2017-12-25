package com.bjike.goddess.projectissuehandle.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectissuehandle.enums.ProblemTypes;


/**
 * 项目执行中的问题受理业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemAcceBO extends BaseBO {
    /**
     * 项目问题编号
     */
    private String projectNum;

    /**
     * 地区
     */
    private String area;


    /**
     * 问题类型
     */
    private ProblemTypes problemTypes;


    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ProblemTypes getProblemTypes() {
        return problemTypes;
    }

    public void setProblemTypes(ProblemTypes problemTypes) {
        this.problemTypes = problemTypes;
    }
}
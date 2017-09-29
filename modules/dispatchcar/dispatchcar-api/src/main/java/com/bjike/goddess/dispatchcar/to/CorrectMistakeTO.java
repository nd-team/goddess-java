package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 09:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CorrectMistakeTO extends BaseTO{
    /**
     * 是否解决
     */
    private Boolean ifSolve;

    /**
     * 解决方案
     */
    private String solution;

    /**
     * 解决时间
     */
    private String solutionTime;

    public Boolean getIfSolve() {
        return ifSolve;
    }

    public void setIfSolve(Boolean ifSolve) {
        this.ifSolve = ifSolve;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolutionTime() {
        return solutionTime;
    }

    public void setSolutionTime(String solutionTime) {
        this.solutionTime = solutionTime;
    }
}

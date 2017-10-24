package com.bjike.goddess.dispatchcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* 出车核对修改记录业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-25 11:24 ]
* @Description:	[ 出车核对修改记录业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CheckChangeCarBO extends BaseBO {
    /**
     * 用车人
     */
    private String  carUser;

    /**
     * 出车日期
     */
    private String dispatchDate;

    /**
     * 出车单号
     */
    private String  number;

    /**
     * 修改日期
     */
    private String  modifyDate;

    /**
     * 修改人
     */
    private String  modifier;

    /**
     * 内容
     */
    private String  content;

    /**
     * 问题类型
     */
    private String  problemType;

    /**
     * 问题描述
     */
    private String  problemDes;

    /**
     * 是否解决
     */
    private Boolean  ifSolve;

    /**
     * 解决方案
     */
    private String  solution;

    /**
     * 解决时间
     */
    private String solutionTime;


    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDes() {
        return problemDes;
    }

    public void setProblemDes(String problemDes) {
        this.problemDes = problemDes;
    }

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

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getSolutionTime() {
        return solutionTime;
    }

    public void setSolutionTime(String solutionTime) {
        this.solutionTime = solutionTime;
    }
}
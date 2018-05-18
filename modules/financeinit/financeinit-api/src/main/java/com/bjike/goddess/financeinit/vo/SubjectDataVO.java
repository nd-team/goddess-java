package com.bjike.goddess.financeinit.vo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-12-16 11:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SubjectDataVO implements Serializable {

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 一级科目代码
     */
    private String firstSubjectCode;

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * 二级科目代码
     */
    private String secondSubjectCode;

    /**
     * 三级科目
     */
    private String thirdSubject;

    /**
     * 三级科目代码
     */
    private String thirdSubjectCode;

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getFirstSubjectCode() {
        return firstSubjectCode;
    }

    public void setFirstSubjectCode(String firstSubjectCode) {
        this.firstSubjectCode = firstSubjectCode;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getSecondSubjectCode() {
        return secondSubjectCode;
    }

    public void setSecondSubjectCode(String secondSubjectCode) {
        this.secondSubjectCode = secondSubjectCode;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public String getThirdSubjectCode() {
        return thirdSubjectCode;
    }

    public void setThirdSubjectCode(String thirdSubjectCode) {
        this.thirdSubjectCode = thirdSubjectCode;
    }
}

package com.bjike.goddess.financeinit.vo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-12-16 11:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SecondSubjectDataVO implements Serializable {

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * 二级科目代码
     */
    private String secondSubjectCode;

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
}

package com.bjike.goddess.financeinit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-12-16 11:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FirstSubjectDataBO extends BaseBO {

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 一级科目代码
     */
    private String firstSubjectCode;

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
}

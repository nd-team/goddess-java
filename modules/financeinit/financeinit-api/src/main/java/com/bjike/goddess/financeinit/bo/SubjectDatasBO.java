package com.bjike.goddess.financeinit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-12-16 11:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SubjectDatasBO extends BaseBO {


    /**
     * 三级科目
     */
    private String thirdSubject;

    /**
     * 三级科目代码
     */
    private String thirdSubjectCode;

    /**
     * 一级科目
     */
    private List<FirstSubjectDataBO> firstSubjectDataBOs;

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

    public List<FirstSubjectDataBO> getFirstSubjectDataBOs() {
        return firstSubjectDataBOs;
    }

    public void setFirstSubjectDataBOs(List<FirstSubjectDataBO> firstSubjectDataBOs) {
        this.firstSubjectDataBOs = firstSubjectDataBOs;
    }
}

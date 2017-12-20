package com.bjike.goddess.reportmanagement.vo;

import com.bjike.goddess.reportmanagement.enums.AnalyseType;

import java.io.Serializable;

/**
 * 现金流量分析业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 20171124 10:20 ]
 * @Description: [ 现金流量分析业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashAnalyse1VO implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 分析类型
     */
    private AnalyseType analyseType;

    /**
     * 分析
     */
    private String analyse;

    /**
     * 分析1
     */
    private String analyse1;

    /**
     * 分析2
     */
    private String analyse2;

    /**
     * 分析3
     */
    private String analyse3;

    /**
     * 分析4
     */
    private String analyse4;

    /**
     * 分析5
     */
    private String analyse5;

    /**
     * 分析6
     */
    private String analyse6;

    /**
     * 分析7
     */
    private String analyse7;

    /**
     * 分析8
     */
    private String analyse8;

    /**
     * 管理建议
     */
    private String advice;

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    public String getAnalyse1() {
        return analyse1;
    }

    public void setAnalyse1(String analyse1) {
        this.analyse1 = analyse1;
    }

    public String getAnalyse2() {
        return analyse2;
    }

    public void setAnalyse2(String analyse2) {
        this.analyse2 = analyse2;
    }

    public String getAnalyse3() {
        return analyse3;
    }

    public void setAnalyse3(String analyse3) {
        this.analyse3 = analyse3;
    }

    public String getAnalyse4() {
        return analyse4;
    }

    public void setAnalyse4(String analyse4) {
        this.analyse4 = analyse4;
    }

    public String getAnalyse5() {
        return analyse5;
    }

    public void setAnalyse5(String analyse5) {
        this.analyse5 = analyse5;
    }

    public String getAnalyse6() {
        return analyse6;
    }

    public void setAnalyse6(String analyse6) {
        this.analyse6 = analyse6;
    }

    public String getAnalyse7() {
        return analyse7;
    }

    public void setAnalyse7(String analyse7) {
        this.analyse7 = analyse7;
    }

    public String getAnalyse8() {
        return analyse8;
    }

    public void setAnalyse8(String analyse8) {
        this.analyse8 = analyse8;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AnalyseType getAnalyseType() {
        return analyseType;
    }

    public void setAnalyseType(AnalyseType analyseType) {
        this.analyseType = analyseType;
    }
}
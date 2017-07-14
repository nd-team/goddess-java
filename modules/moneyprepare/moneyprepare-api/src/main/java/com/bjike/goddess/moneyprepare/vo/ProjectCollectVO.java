package com.bjike.goddess.moneyprepare.vo;

import com.bjike.goddess.moneyprepare.bo.ProBO;

import java.io.Serializable;
import java.util.List;

/**
 * 资金准备表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectCollectVO implements Serializable {

    /**
     * id
     */
    private String id;
    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 二级科目
     */
    private String secondSubject;
    /**
     * 时间
     */
    private String time;

    /**
     * 准备金
     */
    private Double fund;

    /**
     * 各项目组和准备金
     */
    private List<ProBO> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public List<ProBO> getList() {
        return list;
    }

    public void setList(List<ProBO> list) {
        this.list = list;
    }
}
package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.marketdevelopment.enums.Status;

/**
 * 客户接触阶段表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 09:37 ]
 * @Description: [ 客户接触阶段表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerVO {

    /**
     * id
     */
    private String id;
    /**
     * 阶段编码
     */
    private String code;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 定义
     */
    private String define;

    /**
     * 输出结果
     */
    private String results;

    /**
     * 阶段数量
     */
    private String stageNum;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getStageNum() {
        return stageNum;
    }

    public void setStageNum(String stageNum) {
        this.stageNum = stageNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
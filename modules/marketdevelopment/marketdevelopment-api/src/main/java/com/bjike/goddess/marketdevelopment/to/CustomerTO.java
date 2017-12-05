package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 客户接触阶段
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 09:37 ]
 * @Description: [ 客户接触阶段 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerTO extends BaseTO {

//    /**
//     * 阶段编码
//     */
//    private String code;

    /**
     * 阶段
     */
    @NotBlank(message = "阶段不能为空", groups = {ADD.class, EDIT.class})
    private String stage;

    /**
     * 定义
     */
    private String define;

    /**
     * 输出结果
     */
    private String results;

//    /**
//     * 阶段数量
//     */
//    private String stageNum;
//
//    /**
//     * 状态
//     */
//    private Status status;


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
}
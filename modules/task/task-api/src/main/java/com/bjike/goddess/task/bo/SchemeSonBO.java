package com.bjike.goddess.task.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 汇总方案子表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:56 ]
 * @Description: [ 汇总方案子表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SchemeSonBO extends BaseBO {

    /**
     * 汇总方案id
     */
    private String schemeId;

    /**
     * 字段
     */
    private String title;

    /**
     * 字段下标
     */
    private Integer titleIndex;


    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }
}
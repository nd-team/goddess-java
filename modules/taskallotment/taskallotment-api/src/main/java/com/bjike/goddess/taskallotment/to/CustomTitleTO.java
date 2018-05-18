package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.taskallotment.enums.TitleType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 自定义字段
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:35 ]
 * @Description: [ 自定义字段 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomTitleTO extends BaseTO {
    /**
     * 字段名
     */
    private String title;

    /**
     * 字段内容
     */
    private String content;

    /**
     * 是否必填选项
     */
    private Boolean mandatory;

    /**
     * 字段类型
     */
    private TitleType titleType;

    public TitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
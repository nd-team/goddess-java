package com.bjike.goddess.staffing.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 字段信息
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-31 17:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FieldVO {
    /**
     * 标题
     */
    private String title;
    /**
     * 编辑标题下标
     */
    private Integer titleIndex;

    /**
     * 列表标题下标
     */
    private Integer listTitleIndex;

    /**
     * 详细字段信息
     */
    private List<FieldVO> fieldVOs=new ArrayList<>();

    public Integer getListTitleIndex() {
        return listTitleIndex;
    }

    public void setListTitleIndex(Integer listTitleIndex) {
        this.listTitleIndex = listTitleIndex;
    }

    public List<FieldVO> getFieldVOs() {
        return fieldVOs;
    }

    public void setFieldVOs(List<FieldVO> fieldVOs) {
        this.fieldVOs = fieldVOs;
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

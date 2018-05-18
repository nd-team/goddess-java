package com.bjike.goddess.staffing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 人工成本计划详细业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 02:59 ]
 * @Description: [ 人工成本计划详细业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DetailBO extends BaseBO {

//    /**
//     * 标题
//     */
//    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 列表标题下标
     */
    private Integer listTitleIndex;


//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getListTitleIndex() {
        return listTitleIndex;
    }

    public void setListTitleIndex(Integer listTitleIndex) {
        this.listTitleIndex = listTitleIndex;
    }
}
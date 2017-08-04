package com.bjike.goddess.staffing.vo;

/**
 * 人工成本计划子表详细表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 02:24 ]
 * @Description: [ 人工成本计划子表详细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExpendPlanSonDetailVO {

    /**
     * id
     */
    private String id;
//    /**
//     * 标题
//     */
//    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标题下标
     */
    private Integer titleIndex;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }
}
package com.bjike.goddess.staffing.vo;

/**
 * 人工成本计划详细表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 02:59 ]
 * @Description: [ 人工成本计划详细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DetailVO {

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
     * 列表标题下标
     */
    private Integer listTitleIndex;


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

    public Integer getListTitleIndex() {
        return listTitleIndex;
    }

    public void setListTitleIndex(Integer listTitleIndex) {
        this.listTitleIndex = listTitleIndex;
    }
}
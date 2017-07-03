package com.bjike.goddess.democraticmeet.vo;

import java.util.List;

/**
 * 会议议题选项
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议议题选项 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetTitleOpinionVO {

    /**
     * id
     */
    private String id;

    /**
     * 会议议题
     */
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
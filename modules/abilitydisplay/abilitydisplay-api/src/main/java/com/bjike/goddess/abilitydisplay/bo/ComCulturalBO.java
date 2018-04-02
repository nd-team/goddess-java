package com.bjike.goddess.abilitydisplay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 公司文化活动业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:13 ]
 * @Description: [ 公司文化活动业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComCulturalBO extends BaseBO {

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动内容
     */
    private String content;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ComCulturalBO{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
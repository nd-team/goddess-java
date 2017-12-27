package com.bjike.goddess.socialinsurance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 转正图形展示传输对象
 * @Author: [lijuntao]
 * @Date: [2017-09-09 15:32]
 * @Description: [转正图形展示传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TitleBO extends BaseBO{
    /**
     * 名称
     */
    private String text;

    /**
     * title位置
     */
    private String x;

    public TitleBO() {

    }

    public TitleBO(String text) {
        this.text = text;
    }

    public TitleBO(String text, String x) {
        this.text = text;
        this.x = x;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}

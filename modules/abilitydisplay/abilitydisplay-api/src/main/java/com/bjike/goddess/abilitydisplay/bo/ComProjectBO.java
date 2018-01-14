package com.bjike.goddess.abilitydisplay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 公司项目业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:05 ]
 * @Description: [ 公司项目业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComProjectBO extends BaseBO {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目进度
     */
    private String progress;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
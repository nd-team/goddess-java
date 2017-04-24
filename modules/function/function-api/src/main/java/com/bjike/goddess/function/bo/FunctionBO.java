package com.bjike.goddess.function.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.function.enums.FunctionType;

/**
 * 模块功能业务传输对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 11:07 ]
 * @Description: [ 模块功能业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FunctionBO extends BaseBO {

    /**
     * 功能名
     */
    private String name;

    /**
     * 图标
     */
    private String icon;
    /**
     * 功能链接
     */
    private String url;
    /**
     * 排序序列
     */
    private Integer seq;

    /**
     * 功能类型
     */
    private FunctionType type;

    /**
     * 是否开启
     */
    private Boolean enable;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public FunctionType getType() {
        return type;
    }

    public void setType(FunctionType type) {
        this.type = type;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
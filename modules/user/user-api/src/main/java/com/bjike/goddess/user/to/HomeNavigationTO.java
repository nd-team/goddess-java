package com.bjike.goddess.user.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 首页导航
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HomeNavigationTO extends BaseTO {

    public interface testOrder{}
    /**
     * 导航名称
     */
    @NotBlank(message = "导航名称不能为空",groups = {ADD.class})
    private String navigationName;

    /**
     * 顺序序号
     */
    @NotNull(message = "顺序序号不能为空",groups = { HomeNavigationTO.testOrder.class})
    private Integer orderNum;

    /**
     * 对应的url
     */
    @NotBlank(message = "对应的url不能为空",groups = {ADD.class})
    private String url;


    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
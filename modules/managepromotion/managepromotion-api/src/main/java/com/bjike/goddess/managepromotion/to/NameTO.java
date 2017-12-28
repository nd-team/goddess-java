package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 员工已晋升情况
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NameTO extends BaseTO {
    public interface collect{}

    /**
     * 姓名
     */

    private String[] name;


    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

}
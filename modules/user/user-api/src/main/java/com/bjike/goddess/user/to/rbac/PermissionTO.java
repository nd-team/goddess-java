package com.bjike.goddess.user.to.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 权限传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 14:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PermissionTO extends BaseTO {

    @NotBlank(message = "资源名不能为空", groups = ADD.class)
    private String name;
    @NotBlank(message = "资源不能为空", groups = ADD.class)
    private String resource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }


}

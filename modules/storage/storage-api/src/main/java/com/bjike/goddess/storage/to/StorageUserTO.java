package com.bjike.goddess.storage.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 存储模块用户
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-30 09:39 ]
 * @Description: [ 存储模块用户 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StorageUserTO extends BaseTO {
    public interface LOGIN {
    }


    public interface REGISTER {
    }


    /**
     * 模块名
     */
    @NotBlank(message = "模块名不能为空", groups = StorageUserTO.REGISTER.class)
    private String moduleName;

    /**
     * 登录账号
     */
    @NotBlank(message = "账号不能为空", groups = {StorageUserTO.LOGIN.class, StorageUserTO.REGISTER.class})
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {StorageUserTO.LOGIN.class, StorageUserTO.REGISTER.class})
    private String password;

    /**
     * 状态
     */
    private Status status;


    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
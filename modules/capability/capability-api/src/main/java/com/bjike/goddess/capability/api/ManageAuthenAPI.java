package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.ManageAuthenBO;
import com.bjike.goddess.capability.to.ManageAuthenTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 管理资质认证业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:05 ]
 * @Description: [ 管理资质认证业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ManageAuthenAPI {

    /**
     * 添加
     *
     * @param managerAuths 管理资质认证信息
     * @param companyId    公司id
     * @return class ManageAuthenBO
     */
    default ManageAuthenBO addManageAuthen(String[] managerAuths, String companyId) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param managerAuths 管理资质认证信息
     * @param companyId    公司id
     * @return class ManageAuthenBO
     */
    default ManageAuthenBO editManageAuthen(String[] managerAuths, String companyId) throws SerException {
        return null;
    }

    /**
     * 删除管理资质认证
     *
     * @param id id
     */
    default void deleteManageAuthen(String id) throws SerException {
        return;
    }


}
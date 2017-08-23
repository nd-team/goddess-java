package com.bjike.goddess.staffpay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffpay.bo.AreaCollectBO;
import com.bjike.goddess.staffpay.bo.DepartmentCollectBO;
import com.bjike.goddess.staffpay.bo.NameCollectBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.PayRecordDTO;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;

import java.util.List;

/**
 * 已付款记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:00 ]
 * @Description: [ 已付款记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PayRecordAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @param areas areas
     * @return class AreaCollectBO
     * @throws SerException
     */
    default List<AreaCollectBO> collectArea(String[] areas) throws SerException {
        return null;
    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getAreas() throws SerException {
        return null;
    }
    /**
     * 汇总
     *
     * @param departments departments
     * @return class DepartmentCollectBO
     * @throws SerException
     */
    default List<DepartmentCollectBO> collectDepartment(String[] departments) throws SerException {
        return null;
    }

    /**
     * 获取部门
     *
     * @return class String
     */
    default List<String> getDepartments() throws SerException {
        return null;
    }
    /**
     * 汇总
     *
     * @param names names
     * @return class NameCollectBO
     * @throws SerException
     */
    default List<NameCollectBO> collectName(String[] names) throws SerException {
        return null;
    }

    /**
     * 获取个人
     *
     * @return class String
     */
    default List<String> getNames() throws SerException {
        return null;
    }
}
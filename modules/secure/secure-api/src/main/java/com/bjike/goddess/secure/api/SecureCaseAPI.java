package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.SecureCaseBO;
import com.bjike.goddess.secure.bo.SecureCaseCollectBO;
import com.bjike.goddess.secure.dto.SecureCaseDTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.SecureCaseCollectTO;

import java.util.List;
import java.util.Set;

/**
 * 社保购买情况（汇总明细表）业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-23 02:59 ]
 * @Description: [ 社保购买情况（汇总明细表）业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SecureCaseAPI {
    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 社保购买情况列表
     *
     * @param dto dto
     * @return SecureCaseBO
     * @throws SerException
     */
    List<SecureCaseBO> list(SecureCaseDTO dto) throws SerException;
    /**
     * 汇总
     *
     * @param to to
     * @return SecureCaseCollectBO
     * @throws SerException
     */
    List<SecureCaseCollectBO> collect(SecureCaseCollectTO to) throws SerException;
    /**
     * 获取所有地区
     *
     * @throws SerException
     */
    Set<String> allArea() throws SerException;
    /**
     * 获取所有项目组
     *
     * @throws SerException
     */
    Set<String> allProjectGroup() throws SerException;
    /**
     * 获取所有参保单位
     *
     * @throws SerException
     */
    Set<String> allUnit() throws SerException;
}
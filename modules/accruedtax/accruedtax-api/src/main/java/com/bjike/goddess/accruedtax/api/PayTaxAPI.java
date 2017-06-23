package com.bjike.goddess.accruedtax.api;

import com.bjike.goddess.accruedtax.bo.PayTaxBO;
import com.bjike.goddess.accruedtax.dto.PayTaxDTO;
import com.bjike.goddess.accruedtax.to.GuidePermissionTO;
import com.bjike.goddess.accruedtax.to.PayTaxTO;
import com.bjike.goddess.accruedtax.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 应交税金业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:17 ]
 * @Description: [ 应交税金业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PayTaxAPI {
    /**
     * 下拉导航权限
     */
    List<SonPermissionObject> sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 应交税金列表总条数
     */
    default Long countPayTax(PayTaxDTO payTaxDTO) throws SerException {
        return null;
    }

    /**
     * 应交税金列表id
     * @return class PayTaxBO
     */
    default PayTaxBO getOneById (String id) throws SerException {return null;}


    /**
     * 应交税金列表
     *
     * @return class PayTaxBO
     */
    default List<PayTaxBO> listPayTax(PayTaxDTO payTaxDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param payTaxTO 应交税金信息
     * @return class PayTaxBO
     */
    default PayTaxBO addPayTax(PayTaxTO payTaxTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param payTaxTO 应交税金信息
     * @return class PayTaxBO
     */
    default PayTaxBO editPayTax(PayTaxTO payTaxTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deletePayTax(String id) throws SerException {
        return;
    }

    ;


    /**
     * 分摊
     *
     * @param payTaxTO 应交税金信息
     * @return class PayTaxBO
     */
    default PayTaxBO splitTax(PayTaxTO payTaxTO) throws SerException {
        return null;
    }

    /**
     * 根据公司汇总
     *
     * @return class PayTaxBO
     */
    default List<PayTaxBO> collectCompany(PayTaxDTO payTaxDTO) throws SerException {
        return null;
    }

    /**
     * 根据税种汇总
     *
     * @return class PayTaxBO
     */
    default List<PayTaxBO> collectTaxType(PayTaxDTO payTaxDTO) throws SerException {
        return null;
    }
    /**
     * 获取所有公司
     *
     */
    default List<String> listCompany( ) throws SerException {
        return null;
    }
    /**
     * 获取所有税种
     */
    default List<String> listTaxType( ) throws SerException {
        return null;
    }



}
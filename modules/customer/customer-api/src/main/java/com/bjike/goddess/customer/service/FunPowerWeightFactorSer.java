package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.FunPowerWeightFactorBO;
import com.bjike.goddess.customer.dto.FunPowerWeightFactorDTO;
import com.bjike.goddess.customer.entity.FunPowerWeightFactor;
import com.bjike.goddess.customer.to.FunPowerWeightFactorTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;

import java.util.List;

/**
 * 职权因素层设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:37 ]
 * @Description: [ 职权因素层设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FunPowerWeightFactorSer extends Ser<FunPowerWeightFactor, FunPowerWeightFactorDTO> {
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
     * 职权因素层设置总条数
     */
    default Long countFunPower(FunPowerWeightFactorDTO funPowerWeightFactorDTO) throws SerException {
        return null;
    }

    /**
     * 一个职权因素层设置
     */
    default FunPowerWeightFactorBO getOneFunPower(String id) throws SerException {
        return null;
    }

    /**
     * 职权因素层设置列表
     *
     * @return class FunPowerWeightFactorBO
     */
    default List<FunPowerWeightFactorBO> listFunPower(FunPowerWeightFactorDTO funPowerWeightFactorDTO) throws SerException {

        return null;
    }

    /**
     * 添加
     *
     * @param funPowerWeightFactorTO 职权因素层设置
     * @return class FunPowerWeightFactorBO
     */
    default FunPowerWeightFactorBO addFunPower(FunPowerWeightFactorTO funPowerWeightFactorTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param funPowerWeightFactorTO 职权因素层设置
     * @return class FunPowerWeightFactorBO
     */
    default FunPowerWeightFactorBO editFunPower(FunPowerWeightFactorTO funPowerWeightFactorTO) throws SerException {
        return null;
    }

    /**
     * 删除职权因素层设置
     *
     * @param id id
     */
    default void deleteFunPower(String id) throws SerException {
        return;
    }
}
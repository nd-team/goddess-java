package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.CarRentalAgreementBO;
import com.bjike.goddess.contractware.dto.CarRentalAgreementDTO;
import com.bjike.goddess.contractware.to.CarRentalAgreementTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;

import java.util.List;

/**
 * 租车协议业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:43 ]
 * @Description: [ 租车协议业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CarRentalAgreementAPI {
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
     * 租车协议列表总条数
     */
    default Long countCarRentalAgreement(CarRentalAgreementDTO carRentalAgreementDTO) throws SerException {
        return null;
    }

    /**
     * 一个租车协议
     *
     * @return class CarRentalAgreementBO
     */
    default CarRentalAgreementBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 租车协议
     *
     * @param carRentalAgreementDTO 租车协议dto
     * @return class CarRentalAgreementBO
     * @throws SerException
     */
    default List<CarRentalAgreementBO> findListCarRentalAgreement(CarRentalAgreementDTO carRentalAgreementDTO) throws SerException {
        return null;
    }

    /**
     * 添加租车协议
     *
     * @param carRentalAgreementTO 租车协议数据to
     * @return class CarRentalAgreementBO
     * @throws SerException
     */
    default CarRentalAgreementBO insertCarRentalAgreement(CarRentalAgreementTO carRentalAgreementTO) throws SerException {
        return null;
    }

    /**
     * 编辑租车协议
     *
     * @param carRentalAgreementTO 租车协议数据to
     * @return class CarRentalAgreementBO
     * @throws SerException
     */
    default CarRentalAgreementBO editCarRentalAgreement(CarRentalAgreementTO carRentalAgreementTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除租车协议
     *
     * @param id
     * @throws SerException
     */
    default void removeCarRentalAgreement(String id) throws SerException {

    }


}
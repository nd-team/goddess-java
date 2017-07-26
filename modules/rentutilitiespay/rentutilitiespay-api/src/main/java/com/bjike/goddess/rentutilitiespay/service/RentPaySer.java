package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rentutilitiespay.bo.CollectAreaBO;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.entity.RentPay;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.excel.SonPermissionObject;
import com.bjike.goddess.rentutilitiespay.to.GuidePermissionTO;
import com.bjike.goddess.rentutilitiespay.to.RentPayTO;

import java.util.List;

/**
 * 房租缴费业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:10 ]
 * @Description: [ 房租缴费业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RentPaySer extends Ser<RentPay, RentPayDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 房租缴费列表总条数
     */
    default Long countRentPay(RentPayDTO rentPayDTO) throws SerException {
        return null;
    }

    /**
     * 一个房租缴费
     *
     * @return class RentPayBO
     */
    default RentPayBO getOne(String id) throws SerException {
        return null;
    }


    /**
     * 获取房租缴费
     *
     * @param rentPayDTO 房租缴费dto
     * @return class RentPayBO
     * @throws SerException
     */
    default List<RentPayBO> findListRentPay(RentPayDTO rentPayDTO) throws SerException {
        return null;
    }

    /**
     * 添加房租缴费
     *
     * @param rentPayTO 房租缴费数据to
     * @return class RentPayBO
     * @throws SerException
     */
    default RentPayBO insertRentPay(RentPayTO rentPayTO) throws SerException {
        return null;
    }

    /**
     * 编辑房租缴费
     *
     * @param rentPayTO 房租缴费数据to
     * @return class RentPayBO
     * @throws SerException
     */
    default RentPayBO editRentPay(RentPayTO rentPayTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除房租缴费
     *
     * @param id
     * @throws SerException
     */
    default void removeRentPay(String id) throws SerException {

    }
    /**
     * 汇总
     *
     * @param areas areas
     * @return class CollectAreaBO
     * @throws SerException
     */
    default List<CollectAreaBO> collectArea(String[] areas) throws SerException {
        return null;
    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getArea() throws SerException {
        return null;
    }
    /**
     * 运营财务部
     *
     * @param rentPayTO rentPayTO
     * @return class RentPayBO
     * @throws SerException
     */
    default RentPayBO financeAudit(RentPayTO rentPayTO) throws SerException {
        return null;
    }
}
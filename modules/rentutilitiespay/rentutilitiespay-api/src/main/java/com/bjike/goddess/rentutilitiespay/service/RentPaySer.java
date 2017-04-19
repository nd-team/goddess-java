package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.entity.RentPay;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
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
     * 上传附件
     */
    default void uploadAttachments() throws SerException {
        return ;
    }
    /**
     * 汇总
     *
     * @param area area
     * @return class RentPayBO
     * @throws SerException
     */
    default List<RentPayBO> collectArea(String[] area) throws SerException {
        return null;
    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getRentPayArea() throws SerException {
        return null;
    }
    /**
     * 获取项目组
     *
     * @return class String
     */
    default List<String> getRentPayProGroup() throws SerException {
        return null;
    }
    /**
     * 获取项目名称
     *
     * @return class String
     */
    default List<String> getRentPayProName() throws SerException {
        return null;
    }
    /**
     * 获取地址
     *
     * @return class String
     */
    default List<String> getRentPayAddress() throws SerException {
        return null;
    }
}
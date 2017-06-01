package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.BusInsuranceBO;
import com.bjike.goddess.businsurance.to.BusInsuranceTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.businsurance.entity.BusInsurance;
import com.bjike.goddess.businsurance.dto.BusInsuranceDTO;

import java.util.List;

/**
 * 商业保险方案业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-21 09:44 ]
 * @Description: [ 商业保险方案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusInsuranceSer extends Ser<BusInsurance, BusInsuranceDTO> {


    /**
     * 商业保险方案列表总条数
     *
     */
    default Long countBusInsurance(BusInsuranceDTO busInsuranceDTO) throws SerException {
        return null;
    }
    /**
     * 商业保险方案列表
     * @return class BusInsuranceBO
     */
    default List<BusInsuranceBO> listBusInsurance(BusInsuranceDTO busInsuranceDTO) throws SerException {return null;}
    /**
     *  添加
     * @param busInsuranceTO 商业保险方案信息
     * @return class BusInsuranceBO
     */
    default BusInsuranceBO addBusInsurance(BusInsuranceTO busInsuranceTO) throws SerException { return null;}

    /**
     *  编辑
     * @param busInsuranceTO 商业保险方案信息
     * @return class BusInsuranceBO
     */
    default BusInsuranceBO editBusInsurance(BusInsuranceTO busInsuranceTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteBusInsurance(String id ) throws SerException {return;};

    /**
     *  编辑购买条件
     * @param busInsuranceTO 商业保险方案信息
     * @return class BusInsuranceBO
     */
    default BusInsuranceBO editBuyCondition(BusInsuranceTO busInsuranceTO) throws SerException { return null;}

    /**
     *  编辑方案审核
     * @param busInsuranceTO 商业保险方案信息
     * @return class BusInsuranceBO
     */
    default BusInsuranceBO editAdvice(BusInsuranceTO busInsuranceTO) throws SerException { return null;}

    /**
     *  编辑方案运营审核
     * @param busInsuranceTO 商业保险方案信息
     * @return class BusInsuranceBO
     */
    default BusInsuranceBO editBusAdvice(BusInsuranceTO busInsuranceTO) throws SerException { return null;}
    /**
     *  总经办审核
     * @param busInsuranceTO 商业保险方案信息
     * @return class BusInsuranceBO
     */
    default BusInsuranceBO audit(BusInsuranceTO busInsuranceTO) throws SerException { return null;}
    /**
     * 查看明细
     * @return class BusInsuranceBO
     */
    default BusInsuranceBO getBusInsurance(String id ) throws SerException {return null;}



}
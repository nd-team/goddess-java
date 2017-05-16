package com.bjike.goddess.housepay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.housepay.bo.AreaCollectBO;
import com.bjike.goddess.housepay.bo.PayRecordBO;
import com.bjike.goddess.housepay.bo.ProjectCollectBO;
import com.bjike.goddess.housepay.dto.PayRecordDTO;

import java.util.List;

/**
 * 已付款记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:13 ]
 * @Description: [ 已付款记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PayRecordAPI {

    /**
     * 已付款记录列表总条数
     */
    default Long countPayRecord(PayRecordDTO payRecordDTO) throws SerException {
        return null;
    }

    /**
     * 一个已付款记录
     *
     * @return class PayRecordBO
     */
    default PayRecordBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 已付款记录
     *
     * @param payRecordDTO 已付款记录dto
     * @return class PayRecordBO
     * @throws SerException
     */
    default List<PayRecordBO> findListPayRecord(PayRecordDTO payRecordDTO) throws SerException {
        return null;
    }
    /**
     * 根据id删除已付款记录
     *
     * @param id
     * @throws SerException
     */
    default void removePayRecord(String id) throws SerException {

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
     * @param projects projects
     * @return class ProjectCollectBO
     * @throws SerException
     */
    default List<ProjectCollectBO> collectProject(String[] projects) throws SerException {
        return null;
    }

    /**
     * 获取项目
     *
     * @return class String
     */
    default List<String> getProject() throws SerException {
        return null;
    }
}
package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.InsureRecordBO;
import com.bjike.goddess.businsurance.dto.InsureRecordDTO;
import com.bjike.goddess.businsurance.to.InsureRecordTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 意外险记录业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 意外险记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InsureRecordAPI {

    /**
     * 意外险记录列表总条数
     *
     */
    default Long countInsureRecord(InsureRecordDTO insureRecordDTO) throws SerException {
        return null;
    }
    /**
     * 意外险记录列表
     * @return class InsureRecordBO
     */
    default List<InsureRecordBO> listInsureRecord(InsureRecordDTO insureRecordDTO) throws SerException {return null;}
    /**
     *  添加
     * @param insureRecordTO 意外险记录信息
     * @return class InsureRecordBO
     */
    default InsureRecordBO addInsureRecord(InsureRecordTO insureRecordTO) throws SerException { return null;}

    /**
     *  编辑
     * @param insureRecordTO 意外险记录信息
     * @return class InsureRecordBO
     */
    default InsureRecordBO editInsureRecord(InsureRecordTO insureRecordTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteInsureRecord(String id ) throws SerException {return;};

    /**
     * 查看明细
     * @return class InsureRecordBO
     */
    default InsureRecordBO getInsureRecord(String id ) throws SerException {return null;}




}
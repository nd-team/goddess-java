package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.InsureRecordBO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.to.InsureRecordNextTO;
import com.bjike.goddess.businsurance.to.InsureRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.businsurance.entity.InsureRecord;
import com.bjike.goddess.businsurance.dto.InsureRecordDTO;

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
public interface InsureRecordSer extends Ser<InsureRecord, InsureRecordDTO> {

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
     *  续保
     * @param insureRecordNextTO 意外险记录信息
     * @return class InsureRecordBO
     */
    default InsureRecordBO editNextInsureRecord(InsureRecordNextTO insureRecordNextTO) throws SerException { return null;}

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


    /**
     * 导出excel
     *
     * @return
     * @throws SerException
     */
    default byte[] exportExcel( ) throws SerException{return null;}



}
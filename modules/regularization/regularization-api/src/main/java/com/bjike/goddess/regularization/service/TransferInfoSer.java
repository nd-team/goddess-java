package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.regularization.bo.TransferInfoBO;
import com.bjike.goddess.regularization.dto.TransferInfoDTO;
import com.bjike.goddess.regularization.entity.TransferInfo;
import com.bjike.goddess.regularization.to.TransferInfoTO;

/**
 * 转正人员信息业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 转正人员信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferInfoSer extends Ser<TransferInfo, TransferInfoDTO> {
    /**
     * 转正人员信息总条数
     * @throws SerException
     */
    default Long countTrans(TransferInfoDTO transferInfoDTO) throws SerException {
        return null;
    }

    /**
     * 一个转正人员信息
     * @param id
     * @return TransferInfoBO
     * @throws SerException
     */
    default TransferInfoBO getOne(String id) throws SerException{
        return null;
    }

    /**
     * 跟进
     * @param transferInfoTO
     * @throws SerException
     */
    default void followUp(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
    /**
     * 福利模块考核
     * @param transferInfoTO
     * @throws SerException
     */
    default void welfareAssess(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
}
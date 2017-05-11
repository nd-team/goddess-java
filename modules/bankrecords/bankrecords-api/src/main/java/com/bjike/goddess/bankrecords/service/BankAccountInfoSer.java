package com.bjike.goddess.bankrecords.service;

import com.bjike.goddess.bankrecords.bo.BankAccountInfoBO;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.entity.BankAccountInfo;
import com.bjike.goddess.bankrecords.to.BankAccountInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 账号信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BankAccountInfoSer extends Ser<BankAccountInfo, BankAccountInfoDTO> {

    /**
     * 新增账户信息
     * @param to 账户信息
     * @return 账户信息
     */
    BankAccountInfoBO insertModel(BankAccountInfoTO to) throws SerException;
    /**
     * 编辑账户信息
     * @param to 账户信息
     * @return 账户信息
     */
    BankAccountInfoBO updateModel(BankAccountInfoTO to) throws SerException;
    /**
     * 分页查询账户信息
     * @param dto 分页条件
     * @return 账户信息结果集
     */
    List<BankAccountInfoBO> pageList(BankAccountInfoDTO dto) throws SerException;
}
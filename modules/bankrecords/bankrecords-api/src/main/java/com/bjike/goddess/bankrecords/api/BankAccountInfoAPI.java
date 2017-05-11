package com.bjike.goddess.bankrecords.api;

import com.bjike.goddess.bankrecords.bo.BankAccountInfoBO;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.to.BankAccountInfoTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface BankAccountInfoAPI {

    /**
     * 新增账户信息
     *
     * @param to 账户信息
     * @return 账户信息
     */
    BankAccountInfoBO add(BankAccountInfoTO to) throws SerException;

    /**
     * 编辑账户信息
     *
     * @param to 账户信息
     * @return 账户信息
     */
    BankAccountInfoBO edit(BankAccountInfoTO to) throws SerException;

    /**
     * 删除账户信息
     *
     * @param id 账户信息id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询账户信息
     *
     * @param dto 分页条件
     * @return 账户信息结果集
     */
    List<BankAccountInfoBO> pageList(BankAccountInfoDTO dto) throws SerException;
}
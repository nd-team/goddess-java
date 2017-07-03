package com.bjike.goddess.bankrecords.dao;

import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.entity.BankAccountInfo;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 账号信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BankAccountInfoRep extends JpaRep<BankAccountInfo, BankAccountInfoDTO> {

}
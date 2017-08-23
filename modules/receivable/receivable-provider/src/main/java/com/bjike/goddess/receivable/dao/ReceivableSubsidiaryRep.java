package com.bjike.goddess.receivable.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;

/**
 * 回款明细持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReceivableSubsidiaryRep extends JpaRep<ReceivableSubsidiary, ReceivableSubsidiaryDTO> {

}
package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.LendEditContentDTO;
import com.bjike.goddess.lendreimbursement.entity.LendEditContent;

/**
 * 借款审核直接修改持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:09 ]
 * @Description: [ 借款审核直接修改持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LendEditContentRep extends JpaRep<LendEditContent, LendEditContentDTO> {

}
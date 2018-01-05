package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendCopyDTO;
import com.bjike.goddess.lendreimbursement.entity.ApplyLendCopy;

/**
 * 申请借款副本持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:02 ]
 * @Description: [ 申请借款副本持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ApplyLendCopyRep extends JpaRep<ApplyLendCopy, ApplyLendCopyDTO> {

}
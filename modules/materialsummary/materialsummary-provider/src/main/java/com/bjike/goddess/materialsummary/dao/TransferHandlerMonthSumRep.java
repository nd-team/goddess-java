package com.bjike.goddess.materialsummary.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialsummary.dto.TransferHandlerMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerMonthSum;

/**
 * 调动经手人月汇总持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ 调动经手人月汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferHandlerMonthSumRep extends JpaRep<TransferHandlerMonthSum, TransferHandlerMonthSumDTO> {

}
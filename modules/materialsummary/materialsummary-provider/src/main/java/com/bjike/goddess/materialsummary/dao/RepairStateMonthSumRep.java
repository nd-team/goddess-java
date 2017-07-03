package com.bjike.goddess.materialsummary.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialsummary.dto.RepairStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateMonthSum;

/**
 * 维修状态月汇总持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ 维修状态月汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RepairStateMonthSumRep extends JpaRep<RepairStateMonthSum, RepairStateMonthSumDTO> {

}
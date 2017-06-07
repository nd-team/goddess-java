package com.bjike.goddess.materialsummary.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialsummary.dto.MaterialStatusDaySumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusDaySum;

/**
 * 物资状态日汇总持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:18 ]
 * @Description: [ 物资状态日汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialStatusDaySumRep extends JpaRep<MaterialStatusDaySum, MaterialStatusDaySumDTO> {

}
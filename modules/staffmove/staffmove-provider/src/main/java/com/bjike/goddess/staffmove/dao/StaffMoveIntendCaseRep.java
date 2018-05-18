package com.bjike.goddess.staffmove.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffmove.dto.StaffMoveIntendCaseDTO;
import com.bjike.goddess.staffmove.entity.StaffMoveIntendCase;

/**
 * 人员调动意愿情况持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffMoveIntendCaseRep extends JpaRep<StaffMoveIntendCase, StaffMoveIntendCaseDTO> {

}
package com.bjike.goddess.festival.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.festival.dto.HolidayWorkPlanDTO;
import com.bjike.goddess.festival.entity.HolidayWorkPlan;

/**
 * 节假日工作安排持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:08 ]
 * @Description: [ 节假日工作安排持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HolidayWorkPlanRep extends JpaRep<HolidayWorkPlan, HolidayWorkPlanDTO> {

}
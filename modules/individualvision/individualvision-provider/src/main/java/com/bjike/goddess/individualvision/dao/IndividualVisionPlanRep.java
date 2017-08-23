package com.bjike.goddess.individualvision.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.individualvision.dto.IndividualVisionPlanDTO;
import com.bjike.goddess.individualvision.entity.IndividualVisionPlan;

/**
 * 个人愿景计划持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndividualVisionPlanRep extends JpaRep<IndividualVisionPlan, IndividualVisionPlanDTO> {

}
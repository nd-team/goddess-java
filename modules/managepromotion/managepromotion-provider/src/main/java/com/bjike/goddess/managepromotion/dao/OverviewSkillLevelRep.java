package com.bjike.goddess.managepromotion.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managepromotion.dto.OverviewSkillLevelDTO;
import com.bjike.goddess.managepromotion.entity.OverviewSkillLevel;

/**
 * 技能等级情况概览持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OverviewSkillLevelRep extends JpaRep<OverviewSkillLevel, OverviewSkillLevelDTO> {

}
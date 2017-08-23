package com.bjike.goddess.managepromotion.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managepromotion.dto.SkillGradingADTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.entity.SkillGrading;
import com.bjike.goddess.managepromotion.entity.SkillGradingA;

/**
 * 技能定级A持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级A持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillGradingARep extends JpaRep<SkillGradingA, SkillGradingADTO> {

}
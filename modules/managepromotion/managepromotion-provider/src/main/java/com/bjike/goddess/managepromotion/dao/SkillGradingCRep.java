package com.bjike.goddess.managepromotion.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managepromotion.dto.SkillGradingADTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingCDTO;
import com.bjike.goddess.managepromotion.entity.SkillGradingA;
import com.bjike.goddess.managepromotion.entity.SkillGradingC;

/**
 * 技能定级C持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级C持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillGradingCRep extends JpaRep<SkillGradingC, SkillGradingCDTO> {

}
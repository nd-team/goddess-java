package com.bjike.goddess.managepromotion.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managepromotion.dto.SkillPromotionApplyDTO;
import com.bjike.goddess.managepromotion.entity.SkillPromotionApply;

/**
 * 技能晋升申请持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillPromotionApplyRep extends JpaRep<SkillPromotionApply, SkillPromotionApplyDTO> {

}
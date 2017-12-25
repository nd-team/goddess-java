package com.bjike.goddess.attainment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireUserDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireUser;

/**
* 问卷调查历史记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 11:31 ]
* @Description:	[ 问卷调查历史记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SurveyQuestionnaireUserRep extends JpaRep<SurveyQuestionnaireUser ,SurveyQuestionnaireUserDTO> { 

 }
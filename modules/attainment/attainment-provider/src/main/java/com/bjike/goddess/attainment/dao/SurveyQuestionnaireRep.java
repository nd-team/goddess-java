package com.bjike.goddess.attainment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaire;

/**
* 调研表问题持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 11:23 ]
* @Description:	[ 调研表问题持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SurveyQuestionnaireRep extends JpaRep<SurveyQuestionnaire ,SurveyQuestionnaireDTO> { 

 }
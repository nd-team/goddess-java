package com.bjike.goddess.system.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.system.dto.QuestionDTO;
import com.bjike.goddess.system.entity.Question;

/**
 * 问题持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QuestionRep extends JpaRep<Question, QuestionDTO> {

}
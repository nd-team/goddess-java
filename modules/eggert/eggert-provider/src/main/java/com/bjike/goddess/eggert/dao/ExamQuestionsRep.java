package com.bjike.goddess.eggert.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.eggert.dto.ExamQuestionsDTO;
import com.bjike.goddess.eggert.entity.ExamQuestions;

/**
 * 设置考题持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:09 ]
 * @Description: [ 设置考题持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ExamQuestionsRep extends JpaRep<ExamQuestions, ExamQuestionsDTO> {

}
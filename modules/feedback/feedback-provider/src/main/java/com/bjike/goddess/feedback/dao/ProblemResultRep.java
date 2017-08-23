package com.bjike.goddess.feedback.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.feedback.dto.ProblemResultDTO;
import com.bjike.goddess.feedback.entity.ProblemResult;

/**
 * 问题处理结果持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 07:02 ]
 * @Description: [ 问题处理结果持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemResultRep extends JpaRep<ProblemResult, ProblemResultDTO> {

}
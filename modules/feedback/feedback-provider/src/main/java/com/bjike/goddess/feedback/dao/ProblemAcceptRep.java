package com.bjike.goddess.feedback.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.feedback.dto.ProblemAcceptDTO;
import com.bjike.goddess.feedback.entity.ProblemAccept;

/**
 * 问题受理表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 06:12 ]
 * @Description: [ 问题受理表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemAcceptRep extends JpaRep<ProblemAccept, ProblemAcceptDTO> {

}
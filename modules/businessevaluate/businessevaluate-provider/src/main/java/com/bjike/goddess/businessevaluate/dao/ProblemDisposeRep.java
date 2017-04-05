package com.bjike.goddess.businessevaluate.dao;

import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.entity.ProblemDispose;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 项目问题受理和处理持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemDisposeRep extends JpaRep<ProblemDispose, ProblemDisposeDTO> {

}
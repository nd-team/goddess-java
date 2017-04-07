package com.bjike.goddess.financeinit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.entity.FirstSubject;

/**
 * 一级科目持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirstSubjectRep extends JpaRep<FirstSubject, FirstSubjectDTO> {

}
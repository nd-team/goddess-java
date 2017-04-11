package com.bjike.goddess.subjectcollect.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.subjectcollect.entity.SubjectCollect;

/**
 * 科目汇总表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SubjectCollectRep extends JpaRep<SubjectCollect, SubjectCollectDTO> {

}
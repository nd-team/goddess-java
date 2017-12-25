package com.bjike.goddess.moneyprepare.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyprepare.dto.ApplyAndExaminationDTO;
import com.bjike.goddess.moneyprepare.entity.ApplyAndExamination;

/**
 * 申请和审批持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 04:16 ]
 * @Description: [ 申请和审批持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ApplyAndExaminationRep extends JpaRep<ApplyAndExamination, ApplyAndExaminationDTO> {

}
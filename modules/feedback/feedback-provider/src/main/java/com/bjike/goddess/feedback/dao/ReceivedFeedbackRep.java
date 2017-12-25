package com.bjike.goddess.feedback.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.feedback.dto.ReceivedFeedbackDTO;
import com.bjike.goddess.feedback.entity.ReceivedFeedback;

/**
 * 已受理的反馈持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 09:24 ]
 * @Description: [ 已受理的反馈持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReceivedFeedbackRep extends JpaRep<ReceivedFeedback, ReceivedFeedbackDTO> {

}
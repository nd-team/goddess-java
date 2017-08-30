package com.bjike.goddess.receivable.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.receivable.dto.CollectEmailDTO;
import com.bjike.goddess.receivable.entity.CollectEmail;

/**
 * 回款管理邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.889 ]
 * @Description: [ 回款管理邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectEmailRep extends JpaRep<CollectEmail, CollectEmailDTO> {

}
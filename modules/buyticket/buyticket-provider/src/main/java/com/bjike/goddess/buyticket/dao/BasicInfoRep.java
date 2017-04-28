package com.bjike.goddess.buyticket.dao;

import com.bjike.goddess.buyticket.dto.BasicInfoDTO;
import com.bjike.goddess.buyticket.entity.BasicInfo;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 基本信息设置持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:14 ]
 * @Description: [ 基本信息设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BasicInfoRep extends JpaRep<BasicInfo, BasicInfoDTO> {

}
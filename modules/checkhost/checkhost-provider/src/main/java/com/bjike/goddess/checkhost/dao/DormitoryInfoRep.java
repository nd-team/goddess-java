package com.bjike.goddess.checkhost.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.checkhost.dto.DormitoryInfoDTO;
import com.bjike.goddess.checkhost.entity.DormitoryInfo;

/**
 * 宿舍信息管理持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:02 ]
 * @Description: [ 宿舍信息管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DormitoryInfoRep extends JpaRep<DormitoryInfo, DormitoryInfoDTO> {

}
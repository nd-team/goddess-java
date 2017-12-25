package com.bjike.goddess.fundcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.entity.Back;

/**
 * 回款持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:48 ]
 * @Description: [ 回款持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BackRep extends JpaRep<Back, BackDTO> {

}
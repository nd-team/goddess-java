package com.bjike.goddess.regularization.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.regularization.dto.ManagementScoreDTO;
import com.bjike.goddess.regularization.entity.ManagementScore;

/**
 * 管理层评分持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [ 管理层评分持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ManagementScoreRep extends JpaRep<ManagementScore, ManagementScoreDTO> {

}
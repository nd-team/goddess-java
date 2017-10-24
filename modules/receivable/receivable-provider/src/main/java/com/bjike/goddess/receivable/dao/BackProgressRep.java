package com.bjike.goddess.receivable.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.receivable.dto.BackProgressDTO;
import com.bjike.goddess.receivable.entity.BackProgress;

/**
 * 回款进度持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BackProgressRep extends JpaRep<BackProgress, BackProgressDTO> {

}
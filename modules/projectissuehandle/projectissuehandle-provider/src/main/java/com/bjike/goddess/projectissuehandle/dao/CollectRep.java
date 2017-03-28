package com.bjike.goddess.projectissuehandle.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectissuehandle.dto.CollectDTO;
import com.bjike.goddess.projectissuehandle.entity.Collect;

/**
 * 汇总项目执行中的问题受理及处理结果持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-27 10:49 ]
 * @Description: [ 汇总项目执行中的问题受理及处理结果持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectRep extends JpaRep<Collect, CollectDTO> {

}
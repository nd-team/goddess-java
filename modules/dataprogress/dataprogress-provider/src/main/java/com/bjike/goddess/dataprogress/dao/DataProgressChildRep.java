package com.bjike.goddess.dataprogress.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dataprogress.dto.DataProgressChildDTO;
import com.bjike.goddess.dataprogress.entity.DataProgressChild;

/**
 * 资料收集进度管理子表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:04 ]
 * @Description: [ 资料收集进度管理子表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DataProgressChildRep extends JpaRep<DataProgressChild, DataProgressChildDTO> {

}
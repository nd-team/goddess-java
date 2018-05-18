package com.bjike.goddess.dataprogress.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dataprogress.dto.DataProgressMainDTO;
import com.bjike.goddess.dataprogress.entity.DataProgressMain;

/**
 * 资料收集进度管理主表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:03 ]
 * @Description: [ 资料收集进度管理主表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DataProgressMainRep extends JpaRep<DataProgressMain, DataProgressMainDTO> {

}
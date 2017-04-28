package com.bjike.goddess.materialreceive.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialreceive.dto.ProjectGroupReceiveDTO;
import com.bjike.goddess.materialreceive.entity.ProjectGroupReceive;

/**
 * 项目组领用归还持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectGroupReceiveRep extends JpaRep<ProjectGroupReceive, ProjectGroupReceiveDTO> {

}
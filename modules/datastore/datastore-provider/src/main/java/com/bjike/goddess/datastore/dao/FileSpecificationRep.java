package com.bjike.goddess.datastore.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.datastore.dto.FileSpecificationDTO;
import com.bjike.goddess.datastore.entity.FileSpecification;

/**
 * 数据存储文件规范持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:12 ]
 * @Description: [ 数据存储文件规范持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FileSpecificationRep extends JpaRep<FileSpecification, FileSpecificationDTO> {

}
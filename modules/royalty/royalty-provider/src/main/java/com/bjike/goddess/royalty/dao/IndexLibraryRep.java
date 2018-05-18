package com.bjike.goddess.royalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.royalty.dto.IndexLibraryDTO;
import com.bjike.goddess.royalty.entity.IndexLibrary;

/**
 * 指标库持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 指标库持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndexLibraryRep extends JpaRep<IndexLibrary, IndexLibraryDTO> {

}
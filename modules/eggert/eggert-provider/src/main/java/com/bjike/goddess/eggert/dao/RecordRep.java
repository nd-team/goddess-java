package com.bjike.goddess.eggert.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.eggert.dto.RecordDTO;
import com.bjike.goddess.eggert.entity.Record;

/**
 * 信息记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:22 ]
 * @Description: [ 信息记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RecordRep extends JpaRep<Record, RecordDTO> {

}
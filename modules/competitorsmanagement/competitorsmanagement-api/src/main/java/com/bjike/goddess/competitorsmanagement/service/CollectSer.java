package com.bjike.goddess.competitorsmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.competitorsmanagement.bo.CollectBO;
import com.bjike.goddess.competitorsmanagement.dto.CollectDTO;
import com.bjike.goddess.competitorsmanagement.entity.Collect;

import java.util.List;

/**
 * 竞争对手管理汇总业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 04:55 ]
 * @Description: [ 竞争对手管理汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectSer extends Ser<Collect, CollectDTO> {

    List<CollectBO> getCollet() throws SerException;

}
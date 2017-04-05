package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.AbilityGrowUpBO;
import com.bjike.goddess.businessevaluate.dto.AbilityGrowUpDTO;
import com.bjike.goddess.businessevaluate.entity.AbilityGrowUp;
import com.bjike.goddess.businessevaluate.to.AbilityGrowUpTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 能力成长业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 04:13 ]
 * @Description: [ 能力成长业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AbilityGrowUpSer extends Ser<AbilityGrowUp, AbilityGrowUpDTO> {

    /**
     * 添加能力成长
     *
     * @param to 能力成长
     * @return 能力成长
     */
    AbilityGrowUpBO insertModel(AbilityGrowUpTO to) throws SerException;

    /**
     * 编辑能力成长
     *
     * @param to 能力成长
     * @return 能力成长
     */
    AbilityGrowUpBO updateModel(AbilityGrowUpTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 能力成长结果集
     */
    List<AbilityGrowUpBO> pageList(AbilityGrowUpDTO dto) throws SerException;
}
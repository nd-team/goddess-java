package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.AbilityGrowUpBO;
import com.bjike.goddess.businessevaluate.dto.AbilityGrowUpDTO;
import com.bjike.goddess.businessevaluate.to.AbilityGrowUpTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface AbilityGrowUpAPI {

    /**
     * 添加能力成长
     *
     * @param to 能力成长
     * @return 能力成长
     */
    AbilityGrowUpBO addModel(AbilityGrowUpTO to) throws SerException;

    /**
     * 编辑能力成长
     *
     * @param to 能力成长
     * @return 能力成长
     */
    AbilityGrowUpBO editModel(AbilityGrowUpTO to) throws SerException;

    /**
     * 删除能力成长
     *
     * @param id 能力成长id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 能力成长结果集
     */
    List<AbilityGrowUpBO> pageList(AbilityGrowUpDTO dto) throws SerException;
}
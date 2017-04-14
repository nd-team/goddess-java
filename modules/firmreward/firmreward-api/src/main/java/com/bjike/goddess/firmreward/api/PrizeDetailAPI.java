package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.PrizeDetailBO;
import com.bjike.goddess.firmreward.dto.PrizeDetailDTO;
import com.bjike.goddess.firmreward.to.PrizeDetailTO;

import java.util.List;

/**
 * 奖品明细业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:16 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PrizeDetailAPI {

    /**
     * 分页查询奖品明细
     *
     * @return class PrizeDetailBO
     * @throws SerException
     */
    List<PrizeDetailBO> list(PrizeDetailDTO dto) throws SerException;

    /**
     * 保存奖品明细
     *
     * @param to 奖品明细to
     * @return class PrizeDetailBO
     * @throws SerException
     */
    PrizeDetailBO save(PrizeDetailTO to) throws SerException;

    /**
     * 根据id删除奖品明细
     *
     * @param id 奖品明细唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新奖品明细
     *
     * @param to 奖品明细to
     * @throws SerException
     */
    void update(PrizeDetailTO to) throws SerException;

}
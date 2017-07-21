package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.RewardIndicatorBO;
import com.bjike.goddess.firmreward.dto.RewardIndicatorDTO;
import com.bjike.goddess.firmreward.to.RewardIndicatorTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;

import java.util.List;

/**
 * 奖励指标业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RewardIndicatorAPI {

    /**
     * 根据id查询奖励指标
     *
     * @param id 奖励指标唯一标识
     * @return class RewardIndicatorBO
     * @throws SerException
     */
    RewardIndicatorBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 奖励指标dto
     * @throws SerException
     */
    Long count(RewardIndicatorDTO dto) throws SerException;

    /**
     * 分页查询奖励指标
     *
     * @return class RewardIndicatorBO
     * @throws SerException
     */
    List<RewardIndicatorBO> list(RewardIndicatorDTO dto) throws SerException;

    /**
     * 保存奖励指标
     *
     * @param to 奖励指标to
     * @return class RewardIndicatorBO
     * @throws SerException
     */
    RewardIndicatorBO save(RewardIndicatorTO to) throws SerException;

    /**
     * 根据id删除奖励指标
     *
     * @param id 奖励指标唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新奖励指标
     *
     * @param to 奖励指标to
     * @throws SerException
     */
    void update(RewardIndicatorTO to) throws SerException;

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

}
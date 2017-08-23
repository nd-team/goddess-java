package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.RewardProgramRatioBO;
import com.bjike.goddess.firmreward.dto.RewardProgramRatioDTO;
import com.bjike.goddess.firmreward.to.RewardProgramRatioTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;

import java.util.List;

/**
 * 奖励项目比例业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:35 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RewardProgramRatioAPI {

    /**
     * 根据id查询奖励项目比例
     *
     * @param id 奖励项目比例唯一标识
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    RewardProgramRatioBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 奖励项目比例dto
     * @throws SerException
     */
    Long count(RewardProgramRatioDTO dto) throws SerException;

    /**
     * 分页查询奖励项目比例
     *
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    List<RewardProgramRatioBO> list(RewardProgramRatioDTO dto) throws SerException;

    /**
     * 保存奖励项目比例
     *
     * @param to 奖励项目比例to
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    RewardProgramRatioBO save(RewardProgramRatioTO to) throws SerException;

    /**
     * 根据id删除奖励项目比例
     *
     * @param id 奖励项目比例唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新奖励项目比例
     *
     * @param to 奖励项目比例to
     * @throws SerException
     */
    void update(RewardProgramRatioTO to) throws SerException;

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
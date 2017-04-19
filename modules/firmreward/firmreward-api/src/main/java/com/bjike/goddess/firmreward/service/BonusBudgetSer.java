package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.firmreward.bo.BonusBudgetBO;
import com.bjike.goddess.firmreward.bo.RewardProgramRatioBO;
import com.bjike.goddess.firmreward.entity.BonusBudget;
import com.bjike.goddess.firmreward.dto.BonusBudgetDTO;
import com.bjike.goddess.firmreward.to.BonusBudgetTO;

import java.util.List;

/**
 * 奖金预算业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BonusBudgetSer extends Ser<BonusBudget, BonusBudgetDTO> {

    /**
     * 分页查询奖金预算
     *
     * @return class BonusBudgetBO
     * @throws SerException
     */
    List<BonusBudgetBO> list(BonusBudgetDTO dto) throws SerException;

    /**
     * 保存奖金预算
     *
     * @param to 奖金预算to
     * @return class BonusBudgetBO
     * @throws SerException
     */
    BonusBudgetBO save(BonusBudgetTO to) throws SerException;

    /**
     * 根据id删除奖金预算
     *
     * @param id 奖金预算唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新奖金预算
     *
     * @param to 奖金预算to
     * @throws SerException
     */
    void update(BonusBudgetTO to) throws SerException;

    /**
     * 添加奖励项目比例
     *
     * @param to 奖金预算to
     * @throws SerException
     */
    void addRewardProgramRatios(BonusBudgetTO to) throws SerException;

    /**
     * 更新奖励项目比例
     *
     * @param to 奖金预算to
     * @throws SerException
     */
    void updateRewardProgramRatios(BonusBudgetTO to) throws SerException;

    /**
     * 查看奖励项目比例
     *
     * @param id 奖金预算id
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    List<RewardProgramRatioBO> checkRewardProgramRatios(String id) throws SerException;

}
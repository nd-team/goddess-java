package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.AwardDetailBO;
import com.bjike.goddess.firmreward.bo.RewardPeopleNoStatBO;
import com.bjike.goddess.firmreward.dto.RewardPeopleNoStatDTO;
import com.bjike.goddess.firmreward.entity.AwardDetail;
import com.bjike.goddess.firmreward.to.RewardPeopleNoStatTO;

import java.util.List;

/**
 * 奖励人数统计业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RewardPeopleNoStatAPI {

    /**
     * 分页查询奖励人数统计
     *
     * @return class RewardPeopleNoStatBO
     * @throws SerException
     */
    List<RewardPeopleNoStatBO> list(RewardPeopleNoStatDTO dto) throws SerException;

    /**
     * 保存奖励人数统计
     *
     * @param to 奖励人数统计to
     * @return class RewardPeopleNoStatBO
     * @throws SerException
     */
    RewardPeopleNoStatBO save(RewardPeopleNoStatTO to) throws SerException;

    /**
     * 根据id删除奖励人数统计
     *
     * @param id 奖励人数统计唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新奖励人数统计
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    void update(RewardPeopleNoStatTO to) throws SerException;

    /**
     * 添加获奖明细
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    void addAwardDetails(RewardPeopleNoStatTO to) throws SerException;

    /**
     * 更新获奖明细
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    void updateAwardDetails(RewardPeopleNoStatTO to) throws SerException;

    /**
     * 查看获奖明细
     *
     * @param statId 奖励人数统计id
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    List<AwardDetailBO> checkAwardDetails(String statId) throws SerException;

}
package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.intromanage.bo.StaffRewardBO;
import com.bjike.goddess.intromanage.dto.StaffRewardDTO;
import com.bjike.goddess.intromanage.entity.StaffReward;
import com.bjike.goddess.intromanage.to.StaffRewardTO;

import java.util.List;

/**
 * 员工奖励业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffRewardSer extends Ser<StaffReward, StaffRewardDTO> {

    /**
     * 分页查询员工奖励
     *
     * @return class StaffRewardBO
     * @throws SerException
     */
    List<StaffRewardBO> list(StaffRewardDTO dto) throws SerException;

    /**
     * 保存员工奖励
     *
     * @param to 员工奖励to
     * @return class StaffRewardBO
     * @throws SerException
     */
    StaffRewardBO save(StaffRewardTO to) throws SerException;

    /**
     * 根据id删除员工奖励
     *
     * @param id 员工奖励唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新员工奖励
     *
     * @param to 员工奖励to
     * @throws SerException
     */
    void update(StaffRewardTO to) throws SerException;

}
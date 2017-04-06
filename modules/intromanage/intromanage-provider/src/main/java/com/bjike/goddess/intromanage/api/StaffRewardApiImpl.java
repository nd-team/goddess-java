package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.StaffRewardBO;
import com.bjike.goddess.intromanage.dto.StaffRewardDTO;
import com.bjike.goddess.intromanage.service.StaffRewardSer;
import com.bjike.goddess.intromanage.to.StaffRewardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工奖励业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffRewardApiImpl")
public class StaffRewardApiImpl implements StaffRewardAPI {

    @Autowired
    private StaffRewardSer staffRewardSer;

    /**
     * 分页查询员工奖励
     *
     * @return class StaffRewardBO
     * @throws SerException
     */
    @Override
    public List<StaffRewardBO> list(StaffRewardDTO dto) throws SerException {
        return staffRewardSer.list(dto);
    }

    /**
     * 保存员工奖励
     *
     * @param to 员工奖励to
     * @return class StaffRewardBO
     * @throws SerException
     */
    @Override
    public StaffRewardBO save(StaffRewardTO to) throws SerException {
        return staffRewardSer.save(to);
    }

    /**
     * 根据id删除员工奖励
     *
     * @param id 员工奖励唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        staffRewardSer.remove(id);
    }

    /**
     * 更新员工奖励
     *
     * @param to 员工奖励to
     * @throws SerException
     */
    @Override
    public void update(StaffRewardTO to) throws SerException {
        staffRewardSer.update(to);
    }
}
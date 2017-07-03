package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.RewardSituationBO;
import com.bjike.goddess.supplier.service.RewardSituationSer;
import com.bjike.goddess.supplier.to.RewardSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获奖情况业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.060 ]
 * @Description: [ 获奖情况业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("rewardSituationApiImpl")
public class RewardSituationApiImpl implements RewardSituationAPI {

    @Autowired
    private RewardSituationSer rewardSituationSer;

    @Override
    public List<RewardSituationBO> findByInformation(String info_id) throws SerException {
        return rewardSituationSer.findByInformation(info_id);
    }

    @Override
    public RewardSituationBO save(RewardSituationTO to) throws SerException {
        return rewardSituationSer.save(to);
    }

    @Override
    public RewardSituationBO update(RewardSituationTO to) throws SerException {
        return rewardSituationSer.update(to);
    }

    @Override
    public RewardSituationBO delete(String id) throws SerException {
        return rewardSituationSer.delete(id);
    }

    @Override
    public RewardSituationBO getById(String id) throws SerException {
        return rewardSituationSer.getById(id);
    }
}
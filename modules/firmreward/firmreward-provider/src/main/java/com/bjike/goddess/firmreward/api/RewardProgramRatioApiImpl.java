package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.RewardProgramRatioBO;
import com.bjike.goddess.firmreward.dto.RewardProgramRatioDTO;
import com.bjike.goddess.firmreward.entity.RewardProgramRatio;
import com.bjike.goddess.firmreward.service.RewardProgramRatioSer;
import com.bjike.goddess.firmreward.to.RewardProgramRatioTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 奖励项目比例业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:35 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("rewardProgramRatioApiImpl")
public class RewardProgramRatioApiImpl implements RewardProgramRatioAPI {

    @Autowired
    private RewardProgramRatioSer rewardProgramRatioSer;

    /**
     * 根据id查询奖励项目比例
     *
     * @param id 奖励项目比例唯一标识
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public RewardProgramRatioBO findById(String id) throws SerException {
        RewardProgramRatio model = rewardProgramRatioSer.findById(id);
        return BeanTransform.copyProperties(model, RewardProgramRatioBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 奖励项目比例dto
     * @throws SerException
     */
    @Override
    public Long count(RewardProgramRatioDTO dto) throws SerException {
        return rewardProgramRatioSer.count(dto);
    }

    /**
     * 分页查询奖励项目比例
     *
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public List<RewardProgramRatioBO> list(RewardProgramRatioDTO dto) throws SerException {
        return rewardProgramRatioSer.list(dto);
    }

    /**
     * 保存奖励项目比例
     *
     * @param to 奖励项目比例to
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public RewardProgramRatioBO save(RewardProgramRatioTO to) throws SerException {
        return rewardProgramRatioSer.save(to);
    }

    /**
     * 根据id删除奖励项目比例
     *
     * @param id 奖励项目比例唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        rewardProgramRatioSer.remove(id);
    }

    /**
     * 更新奖励项目比例
     *
     * @param to 奖励项目比例to
     * @throws SerException
     */
    @Override
    public void update(RewardProgramRatioTO to) throws SerException {
        rewardProgramRatioSer.update(to);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return rewardProgramRatioSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return rewardProgramRatioSer.guidePermission(guidePermissionTO);
    }
}
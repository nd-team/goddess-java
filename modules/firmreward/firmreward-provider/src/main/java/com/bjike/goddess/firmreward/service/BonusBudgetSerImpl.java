package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.BonusBudgetBO;
import com.bjike.goddess.firmreward.bo.RewardProgramRatioBO;
import com.bjike.goddess.firmreward.dto.BonusBudgetDTO;
import com.bjike.goddess.firmreward.dto.RewardProgramRatioDTO;
import com.bjike.goddess.firmreward.entity.BonusBudget;
import com.bjike.goddess.firmreward.entity.RewardProgramRatio;
import com.bjike.goddess.firmreward.to.BonusBudgetTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 奖金预算业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class BonusBudgetSerImpl extends ServiceImpl<BonusBudget, BonusBudgetDTO> implements BonusBudgetSer {

    @Autowired
    private RewardProgramRatioSer rewardProgramRatioSer;

    /**
     * 分页查询奖金预算
     *
     * @return class BonusBudgetBO
     * @throws SerException
     */
    @Override
    public List<BonusBudgetBO> list(BonusBudgetDTO dto) throws SerException {
        List<BonusBudget> list = super.findByPage(dto);
        List<BonusBudgetBO> listBO = BeanTransform.copyProperties(list, BonusBudgetBO.class);
        return listBO;
    }

    /**
     * 保存奖金预算
     *
     * @param to 奖金预算to
     * @return class BonusBudgetBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public BonusBudgetBO save(BonusBudgetTO to) throws SerException {
        BonusBudget entity = BeanTransform.copyProperties(to, BonusBudget.class, true);
        entity = super.save(entity);
        BonusBudgetBO bo = BeanTransform.copyProperties(entity, BonusBudgetBO.class);
        return bo;
    }

    /**
     * 根据id删除奖金预算
     *
     * @param id 奖金预算唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        List<RewardProgramRatio> list = getRewardRatioById(id);
        rewardProgramRatioSer.remove(list);//删除子表中的数据
        super.remove(id);
    }

    /**
     * 更新奖金预算
     *
     * @param to 奖金预算to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(BonusBudgetTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            BonusBudget model = super.findById(to.getId());
            if (model != null) {
                updateBonusBudget(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新奖金预算
     *
     * @param to 奖金预算to
     * @param model 奖金预算
     */
    private void updateBonusBudget(BonusBudgetTO to, BonusBudget model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 添加奖励项目比例
     *
     * @param to 奖金预算to
     * @throws SerException
     */
    @Override
    public void addRewardProgramRatios(BonusBudgetTO to) throws SerException {
        String bonusBudgetId = to.getId();//奖金预算额度
        String[] rewardPrograms = to.getRewardPrograms();//奖励项目
        String[] focusingDegrees = to.getFocusingDegrees();//当月侧重程度
        Double[] budgetRanges = to.getBudgetRanges();//当月预算范围
        String[] bonusWeights = to.getBonusWeights();//奖金权重
        Double[] bonusLimits = to.getBonusLimits();//奖金额度
        String[] honorWeights = to.getHonorWeights();//荣誉衍生奖品权重
        Double[] honorLimits = to.getHonorLimits();//荣誉衍生奖品额度
        String[] empiricalValues = to.getEmpiricalValues();//经验值
        Double[] empiricalValueLimits = to.getEmpiricalValueLimits();//经验值额度

        boolean rewardProgramsNotEmpty = (rewardPrograms != null) && (rewardPrograms.length > 0);
        if (StringUtils.isNotEmpty(bonusBudgetId) && rewardProgramsNotEmpty) {
            List<RewardProgramRatio> list = new ArrayList<>(0);
            int len = rewardPrograms.length;
            for (int i = 0; i < len; i ++) {
                RewardProgramRatio model = new RewardProgramRatio();
                model.setRewardProgram(rewardPrograms[i]);
                model.setFocusingDegree(focusingDegrees[i]);
                model.setBudgetRange(budgetRanges[i]);
                model.setBonusWeight(bonusWeights[i]);
                model.setBonusLimit(bonusLimits[i]);
                model.setHonorWeight(honorWeights[i]);
                model.setHonorLimit(honorLimits[i]);
                model.setEmpiricalValue(empiricalValues[i]);
                model.setEmpiricalValueLimit(empiricalValueLimits[i]);
                model.setEmpiricalValueToMoney(empiricalValueLimits[i] * 10);
                model.setBonusBudgetId(bonusBudgetId);
                list.add(model);
            }
            rewardProgramRatioSer.save(list);//执行批量保存操作
        }
    }

    /**
     * 更新奖励项目比例
     *
     * @param to 奖金预算to
     * @throws SerException
     */
    @Override
    public void updateRewardProgramRatios(BonusBudgetTO to) throws SerException {
        List<RewardProgramRatio> list = getRatioByBudgetTo(to);
        rewardProgramRatioSer.remove(list);//删除奖励项目比例
        addRewardProgramRatios(to);//重新执行插入操作
    }

    /**
     * 根据奖金预算TO查询奖金项目比例
     *
     * @param to
     * @return
     * @throws SerException
     */
    private List<RewardProgramRatio> getRatioByBudgetTo(BonusBudgetTO to) throws SerException {
        String bonusBudgetId = to.getId();//奖金预算id
        return getRewardRatioById(bonusBudgetId);
    }

    /**
     * 根据奖金预算id查询奖金项目比例
     *
     * @param bonusBudgetId 奖金项目比例
     * @return class RewardProgramRatio
     * @throws SerException
     */
    private List<RewardProgramRatio> getRewardRatioById(String bonusBudgetId) throws SerException {
        RewardProgramRatioDTO dto = new RewardProgramRatioDTO();
        dto.getConditions().add(Restrict.eq("bonusBudgetId", bonusBudgetId));
        return rewardProgramRatioSer.findByCis(dto);
    }

    /**
     * 查看奖励项目比例
     *
     * @param id 奖金预算id
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public List<RewardProgramRatioBO> checkRewardProgramRatios(String id) throws SerException {
        List<RewardProgramRatio> list = getRewardRatioById(id);
        return BeanTransform.copyProperties(list, RewardProgramRatioBO.class);
    }
}
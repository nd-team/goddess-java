package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.AwardDetailBO;
import com.bjike.goddess.firmreward.bo.RewardPeopleNoStatBO;
import com.bjike.goddess.firmreward.dto.AwardDetailDTO;
import com.bjike.goddess.firmreward.dto.RewardPeopleNoStatDTO;
import com.bjike.goddess.firmreward.entity.AwardDetail;
import com.bjike.goddess.firmreward.entity.RewardPeopleNoStat;
import com.bjike.goddess.firmreward.to.RewardPeopleNoStatTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 奖励人数统计业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class RewardPeopleNoStatSerImpl extends ServiceImpl<RewardPeopleNoStat, RewardPeopleNoStatDTO> implements RewardPeopleNoStatSer {

    @Autowired
    private AwardDetailSer awardDetailSer;

    /**
     * 分页查询奖励人数统计
     *
     * @return class RewardPeopleNoStatBO
     * @throws SerException
     */
    @Override
    public List<RewardPeopleNoStatBO> list(RewardPeopleNoStatDTO dto) throws SerException {
        List<RewardPeopleNoStat> list = super.findByPage(dto);
        List<RewardPeopleNoStatBO> listBO = BeanTransform.copyProperties(list, RewardPeopleNoStatBO.class);
        return listBO;
    }

    /**
     * 保存奖励人数统计
     *
     * @param to 奖励人数统计to
     * @return class RewardPeopleNoStatBO
     * @throws SerException
     */
    @Override
    public RewardPeopleNoStatBO save(RewardPeopleNoStatTO to) throws SerException {
        RewardPeopleNoStat entity = BeanTransform.copyProperties(to, RewardPeopleNoStat.class, true);
        entity = super.save(entity);
        RewardPeopleNoStatBO bo = BeanTransform.copyProperties(entity, RewardPeopleNoStatBO.class);
        return bo;
    }

    /**
     * 根据id删除奖励人数统计
     *
     * @param id 奖励人数统计唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        List<AwardDetail> list = getAwardDetailsByStatId(id);
        awardDetailSer.remove(list);
        super.remove(id);
    }

    /**
     * 根据奖励人数统计id删除奖励明细
     *
     * @param id 奖励人数统计id
     * @return
     */
    private List<AwardDetail> getAwardDetailsByStatId(String id) throws SerException {
        AwardDetailDTO dto = new AwardDetailDTO();
        dto.getConditions().add(Restrict.eq("awardPersonNoStatId", id));
        return awardDetailSer.findByCis(dto);
    }

    /**
     * 更新奖励人数统计
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    public void update(RewardPeopleNoStatTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RewardPeopleNoStat model = super.findById(to.getId());
            if (model != null) {
                updateRewardPeopleNoStat(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新奖励人数统计
     *
     * @param to 奖励人数统计to
     * @param model 奖励人数统计
     */
    private void updateRewardPeopleNoStat(RewardPeopleNoStatTO to, RewardPeopleNoStat model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 添加获奖明细
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    public void addAwardDetails(RewardPeopleNoStatTO to) throws SerException {
        String rewardPeopleNoStatId = to.getId();//奖励人数统计id
        String[] awardRankings = to.getAwardRankings();//获奖名次
        String[] prizewinners = to.getPrizewinners();//获奖人姓名
        Double[] bonusLimits = to.getBonusLimits();  //奖金额度
        Double[] empiricalValueLimits = to.getEmpiricalValueLimits();//经验值额度
        Double[] honorAwardLimits = to.getHonorAwardLimits(); //荣誉衍生奖品额度

        boolean awardRankingNotEmpty = (awardRankings != null) && (awardRankings.length > 0);
        if (StringUtils.isNotBlank(rewardPeopleNoStatId) && awardRankingNotEmpty) {
            List<AwardDetail> list = new ArrayList<>(0);
            for (int i = 0; i < awardRankings.length; i ++) {
                AwardDetail model = new AwardDetail();
                model.setAwardRanking(awardRankings[i]);
                model.setPrizewinner(prizewinners[i]);
                model.setBonusLimit(bonusLimits[i]);
                model.setEmpiricalValueLimit(empiricalValueLimits[i]);
                model.setHonorAwardLimit(honorAwardLimits[i]);
                model.setAwardPersonNoStatId(rewardPeopleNoStatId);
                list.add(model);
            }
            awardDetailSer.save(list);
        }

    }

    /**
     * 更新获奖明细
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    public void updateAwardDetails(RewardPeopleNoStatTO to) throws SerException {
        String rewardPeopleNoStatId = to.getId();
        List<AwardDetail> list = getAwardDetailsByStatId(rewardPeopleNoStatId);
        awardDetailSer.remove(list);//执行删除操作
        addAwardDetails(to);//再执行插入操作
    }

    /**
     * 查看获奖明细
     *
     * @param statId 奖励人数统计id
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public List<AwardDetailBO> checkAwardDetails(String statId) throws SerException {
        List<AwardDetail> list = getAwardDetailsByStatId(statId);
        return BeanTransform.copyProperties(list, AwardDetailBO.class);
    }
}
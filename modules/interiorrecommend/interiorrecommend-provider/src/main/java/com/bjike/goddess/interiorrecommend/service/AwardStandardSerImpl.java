package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.AwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.AwardStandardDTO;
import com.bjike.goddess.interiorrecommend.entity.AwardStandard;
import com.bjike.goddess.interiorrecommend.entity.RecommendRequire;
import com.bjike.goddess.interiorrecommend.to.AwardStandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐奖励要求标准业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 11:39 ]
 * @Description: [ 推荐奖励要求标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class AwardStandardSerImpl extends ServiceImpl<AwardStandard, AwardStandardDTO> implements AwardStandardSer {

    @Autowired
    private RecommendRequireSer recommendRequireSer;

    @Override
    public AwardStandardBO insertModel(AwardStandardTO to) throws SerException {
        RecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
        if (recommendRequire != null) {
            AwardStandard model = BeanTransform.copyProperties(to, AwardStandard.class, true);
            model.setRecommendRequire(recommendRequire);
            super.save(model);
            to.setId(model.getId());
            return BeanTransform.copyProperties(to, AwardStandardBO.class);
        } else {
            throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
        }
    }

    @Override
    public AwardStandardBO updateModel(AwardStandardTO to) throws SerException {
        AwardStandard model = super.findById(to.getId());
        if (model != null) {
            RecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
            if (recommendRequire != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setRecommendRequire(recommendRequire);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, AwardStandardBO.class);
            } else {
                throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
            }
        } else {
            throw new SerException("非法Id,奖励标准设定对象不能为空!");
        }
    }

    @Override
    public List<AwardStandardBO> pageList(AwardStandardDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<AwardStandard> list = super.findByPage(dto);
        if (!CollectionUtils.isEmpty(list)) {
            List<AwardStandardBO> boList = new ArrayList<AwardStandardBO>();
            for (AwardStandard model : list) {
                AwardStandardBO bo = BeanTransform.copyProperties(model, AwardStandardBO.class);
                bo.setOpenTime(DateUtil.dateToString(model.getRecommendRequire().getRecommendScheme().getOpenTime()));
                bo.setCloseTime(DateUtil.dateToString(model.getRecommendRequire().getRecommendScheme().getCloseTime()));
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }
}
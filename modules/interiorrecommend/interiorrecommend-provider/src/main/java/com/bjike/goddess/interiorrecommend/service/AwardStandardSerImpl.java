package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.AwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.AwardStandardDTO;
import com.bjike.goddess.interiorrecommend.entity.AwardStandard;
import com.bjike.goddess.interiorrecommend.entity.RecommendRequire;
import com.bjike.goddess.interiorrecommend.entity.RecommendScheme;
import com.bjike.goddess.interiorrecommend.to.AwardStandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    private RecommendSchemeSer recommendSchemeSer;
    @Autowired
    private RecommendRequireSer recommendRequireSer;

    @Override
    public AwardStandardBO insertModel(AwardStandardTO to) throws SerException {
        AwardStandard model = BeanTransform.copyProperties(to, AwardStandard.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, AwardStandardBO.class);
    }

    @Override
    public AwardStandardBO updateModel(AwardStandardTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            AwardStandard model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, AwardStandardBO.class);
    }

    @Override
    public List<AwardStandardBO> pageList(AwardStandardDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<AwardStandard> list = super.findByPage(dto);
        List<AwardStandardBO> boList = new ArrayList<AwardStandardBO>();
        boList = BeanTransform.copyProperties(list, AwardStandardBO.class);
        DateUtil dateUtil = new DateUtil();
        if (boList != null && !boList.isEmpty()) {
            for (AwardStandardBO bo : boList) {
                RecommendRequire require = recommendRequireSer.findById(bo.getRequireId());
                if (require != null) {
                    RecommendScheme recommendScheme = recommendSchemeSer.findById(require.getRecommendSchemeId());
                    if (recommendScheme != null) {
                        bo.setOpenTime(dateUtil.dateToString(recommendScheme.getOpenTime()));
                        bo.setCloseTime(dateUtil.dateToString(recommendScheme.getCloseTime()));
                    }
                }
            }
        }
        return boList;
    }
}
package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.bo.AwardInfoBO;
import com.bjike.goddess.interiorrecommend.bo.AwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.AwardInfoDTO;
import com.bjike.goddess.interiorrecommend.entity.AwardInfo;
import com.bjike.goddess.interiorrecommend.entity.RecommendInfo;
import com.bjike.goddess.interiorrecommend.to.AwardInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 推荐奖励信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class AwardInfoSerImpl extends ServiceImpl<AwardInfo, AwardInfoDTO> implements AwardInfoSer {

    @Autowired
    private RecommendInfoSer recommendInfoSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    public AwardInfoBO updateModel(AwardInfoTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            AwardInfo model = super.findById(to.getId());
            if (model != null) {

                //判断领取奖励人是否为推荐人
                if (to.getGetAward()) {
                    RecommendInfo recommendInfo = model.getRecommendInfo();
                    if (recommendInfo != null) {
                        if (!userAPI.currentUser().getUsername().equals(recommendInfo.getRecommendUser())) {
                            throw new SerException("无法代领他人的推荐奖励!");
                        }
                    } else {
                        throw new SerException("推荐信息不存在!");
                    }
                }
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, AwardInfoBO.class);
    }

    @Override
    public List<AwardInfoBO> pageList(AwardInfoDTO dto) throws SerException {
        dto.getSorts().add("creatTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), AwardInfoBO.class);
    }

}
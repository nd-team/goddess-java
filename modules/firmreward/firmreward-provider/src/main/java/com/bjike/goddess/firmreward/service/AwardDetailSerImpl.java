package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.AwardDetailBO;
import com.bjike.goddess.firmreward.dto.AwardDetailDTO;
import com.bjike.goddess.firmreward.entity.AwardDetail;
import com.bjike.goddess.firmreward.to.AwardDetailTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 获奖明细业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 06:02 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class AwardDetailSerImpl extends ServiceImpl<AwardDetail, AwardDetailDTO> implements AwardDetailSer {

    /**
     * 分页查询获奖明细
     *
     * @return class AwardDetailBO
     * @throws SerException
     */
    @Override
    public List<AwardDetailBO> list(AwardDetailDTO dto) throws SerException {
        List<AwardDetail> list = super.findByPage(dto);
        List<AwardDetailBO> listBO = BeanTransform.copyProperties(list, AwardDetailBO.class);
        return listBO;
    }

    /**
     * 保存获奖明细
     *
     * @param to 获奖明细to
     * @return class AwardDetailBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AwardDetailBO save(AwardDetailTO to) throws SerException {
        AwardDetail entity = BeanTransform.copyProperties(to, AwardDetail.class, true);
        entity = super.save(entity);
        AwardDetailBO bo = BeanTransform.copyProperties(entity, AwardDetailBO.class);
        return bo;
    }

    /**
     * 更新获奖明细
     *
     * @param to 获奖明细to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AwardDetailTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            AwardDetail model = super.findById(to.getId());
            if (model != null) {
                updateAwardDetail(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新获奖明细
     *
     * @param to 获奖明细
     * @param model 获奖明细实体
     */
    private void updateAwardDetail(AwardDetailTO to, AwardDetail model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除获奖明细
     *
     * @param id 获奖明细唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
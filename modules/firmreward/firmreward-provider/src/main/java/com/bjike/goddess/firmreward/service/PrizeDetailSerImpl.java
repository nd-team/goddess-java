package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.PrizeDetailBO;
import com.bjike.goddess.firmreward.dto.PrizeDetailDTO;
import com.bjike.goddess.firmreward.entity.PrizeDetail;
import com.bjike.goddess.firmreward.to.PrizeDetailTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 奖品明细业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:16 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class PrizeDetailSerImpl extends ServiceImpl<PrizeDetail, PrizeDetailDTO> implements PrizeDetailSer {

    /**
     * 分页查询奖品明细
     *
     * @return class PrizeDetailBO
     * @throws SerException
     */
    @Override
    public List<PrizeDetailBO> list(PrizeDetailDTO dto) throws SerException {
        List<PrizeDetail> list = super.findByPage(dto);
        List<PrizeDetailBO> listBO = BeanTransform.copyProperties(list, PrizeDetailBO.class);
        return listBO;
    }

    /**
     * 保存奖品明细
     *
     * @param to 奖品明细to
     * @return class PrizeDetailBO
     * @throws SerException
     */
    @Override
    public PrizeDetailBO save(PrizeDetailTO to) throws SerException {
        PrizeDetail entity = BeanTransform.copyProperties(to, PrizeDetail.class, true);
        entity = super.save(entity);
        PrizeDetailBO bo = BeanTransform.copyProperties(entity, PrizeDetailBO.class);
        return bo;
    }

    /**
     * 根据id删除奖品明细
     *
     * @param id 奖品明细唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新奖品明细
     *
     * @param to 奖品明细to
     * @throws SerException
     */
    @Override
    public void update(PrizeDetailTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            PrizeDetail model = super.findById(to.getId());
            if (model != null) {
                updatePrizeDetail(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新奖品明细
     *
     * @param to 奖品明细to
     * @param model 奖品明细
     */
    private void updatePrizeDetail(PrizeDetailTO to, PrizeDetail model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}
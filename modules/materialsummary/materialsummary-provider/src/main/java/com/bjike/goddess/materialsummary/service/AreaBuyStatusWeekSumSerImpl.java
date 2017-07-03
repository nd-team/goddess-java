package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusWeekSum;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区购买情况周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:54 ]
 * @Description: [ 地区购买情况周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaBuyStatusWeekSumSerImpl extends ServiceImpl<AreaBuyStatusWeekSum, AreaBuyStatusWeekSumDTO> implements AreaBuyStatusWeekSumSer {

    /**
     * 分页查询地区购买情况周汇总记录
     *
     * @param dto 地区购买情况周汇总记录dto
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusWeekSumBO> list(AreaBuyStatusWeekSumDTO dto) throws SerException {
        List<AreaBuyStatusWeekSum> list = super.findByPage(dto);
        List<AreaBuyStatusWeekSumBO> listBO = BeanTransform.copyProperties(list, AreaBuyStatusWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存地区购买情况周汇总记录
     *
     * @param to 保存地区购买情况周汇总记录to
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaBuyStatusWeekSumBO save(AreaBuyStatusWeekSumTO to) throws SerException {
        AreaBuyStatusWeekSum marketServeRecord = BeanTransform.copyProperties(to, AreaBuyStatusWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaBuyStatusWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaBuyStatusWeekSumBO.class);
        return bo;
    }

    /**
     * 更新地区购买情况周汇总记录
     *
     * @param to 地区购买情况周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaBuyStatusWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaBuyStatusWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaBuyStatusWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区购买情况周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaBuyStatusWeekSum(AreaBuyStatusWeekSumTO to, AreaBuyStatusWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区购买情况周汇总记录
     *
     * @param id 地区购买情况周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区购买情况周汇总
     *
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaBuyStatusWeekSumBO> summary() throws SerException {
        return null;
    }

}
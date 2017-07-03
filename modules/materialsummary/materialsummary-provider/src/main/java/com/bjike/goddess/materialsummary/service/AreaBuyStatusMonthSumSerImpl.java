package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusMonthSum;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区购买情况月汇总业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:54 ]
 * @Description: [ 地区购买情况月汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaBuyStatusMonthSumSerImpl extends ServiceImpl<AreaBuyStatusMonthSum, AreaBuyStatusMonthSumDTO> implements AreaBuyStatusMonthSumSer {

    /**
     * 分页查询地区购买情况月汇总记录
     *
     * @param dto 地区购买情况月汇总记录dto
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusMonthSumBO> list(AreaBuyStatusMonthSumDTO dto) throws SerException {
        List<AreaBuyStatusMonthSum> list = super.findByPage(dto);
        List<AreaBuyStatusMonthSumBO> listBO = BeanTransform.copyProperties(list, AreaBuyStatusMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存地区购买情况月汇总记录
     *
     * @param to 保存地区购买情况月汇总记录to
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaBuyStatusMonthSumBO save(AreaBuyStatusMonthSumTO to) throws SerException {
        AreaBuyStatusMonthSum marketServeRecord = BeanTransform.copyProperties(to, AreaBuyStatusMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaBuyStatusMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaBuyStatusMonthSumBO.class);
        return bo;
    }

    /**
     * 更新地区购买情况月汇总记录
     *
     * @param to 地区购买情况月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaBuyStatusMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaBuyStatusMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaBuyStatusMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区购买情况月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaBuyStatusMonthSum(AreaBuyStatusMonthSumTO to, AreaBuyStatusMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区购买情况月汇总记录
     *
     * @param id 地区购买情况月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区购买情况月汇总
     *
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaBuyStatusMonthSumBO> summary() throws SerException {
        return null;
    }

}
package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaTransferWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferWeekSum;
import com.bjike.goddess.materialsummary.to.AreaTransferWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区调动周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:48 ]
 * @Description: [ 地区调动周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaTransferWeekSumSerImpl extends ServiceImpl<AreaTransferWeekSum, AreaTransferWeekSumDTO> implements AreaTransferWeekSumSer {

    /**
     * 分页查询地区调动周汇总记录
     *
     * @param dto 地区调动周汇总记录dto
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferWeekSumBO> list(AreaTransferWeekSumDTO dto) throws SerException {
        List<AreaTransferWeekSum> list = super.findByPage(dto);
        List<AreaTransferWeekSumBO> listBO = BeanTransform.copyProperties(list, AreaTransferWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存地区调动周汇总记录
     *
     * @param to 保存地区调动周汇总记录to
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaTransferWeekSumBO save(AreaTransferWeekSumTO to) throws SerException {
        AreaTransferWeekSum marketServeRecord = BeanTransform.copyProperties(to, AreaTransferWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaTransferWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaTransferWeekSumBO.class);
        return bo;
    }

    /**
     * 更新地区调动周汇总记录
     *
     * @param to 地区调动周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaTransferWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaTransferWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaTransferWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区调动周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaTransferWeekSum(AreaTransferWeekSumTO to, AreaTransferWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区调动周汇总记录
     *
     * @param id 地区调动周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区调动周汇总
     *
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaTransferWeekSumBO> summary() throws SerException {
        return null;
    }

}
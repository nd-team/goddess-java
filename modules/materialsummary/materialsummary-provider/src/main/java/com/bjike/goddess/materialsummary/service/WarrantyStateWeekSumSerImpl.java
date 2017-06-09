package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.WarrantyStateWeekSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateWeekSum;
import com.bjike.goddess.materialsummary.to.WarrantyStateWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 保修状态周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:50 ]
 * @Description: [ 保修状态周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class WarrantyStateWeekSumSerImpl extends ServiceImpl<WarrantyStateWeekSum, WarrantyStateWeekSumDTO> implements WarrantyStateWeekSumSer {

    /**
     * 分页查询保修状态周汇总记录
     *
     * @param dto 保修状态周汇总记录dto
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateWeekSumBO> list(WarrantyStateWeekSumDTO dto) throws SerException {
        List<WarrantyStateWeekSum> list = super.findByPage(dto);
        List<WarrantyStateWeekSumBO> listBO = BeanTransform.copyProperties(list, WarrantyStateWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存保修状态周汇总记录
     *
     * @param to 保存保修状态周汇总记录to
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public WarrantyStateWeekSumBO save(WarrantyStateWeekSumTO to) throws SerException {
        WarrantyStateWeekSum marketServeRecord = BeanTransform.copyProperties(to, WarrantyStateWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        WarrantyStateWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, WarrantyStateWeekSumBO.class);
        return bo;
    }

    /**
     * 更新保修状态周汇总记录
     *
     * @param to 保修状态周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(WarrantyStateWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            WarrantyStateWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateWarrantyStateWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新保修状态周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateWarrantyStateWeekSum(WarrantyStateWeekSumTO to, WarrantyStateWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除保修状态周汇总记录
     *
     * @param id 保修状态周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 保修状态周汇总
     *
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<WarrantyStateWeekSumBO> summary() throws SerException {
        return null;
    }

}
package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.WarrantyStateMonthSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateMonthSum;
import com.bjike.goddess.materialsummary.to.WarrantyStateMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 保修状态月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:15 ]
 * @Description: [ 保修状态月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class WarrantyStateMonthSumSerImpl extends ServiceImpl<WarrantyStateMonthSum, WarrantyStateMonthSumDTO> implements WarrantyStateMonthSumSer {

    /**
     * 分页查询保修状态月汇总记录
     *
     * @param dto 保修状态月汇总记录dto
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateMonthSumBO> list(WarrantyStateMonthSumDTO dto) throws SerException {
        List<WarrantyStateMonthSum> list = super.findByPage(dto);
        List<WarrantyStateMonthSumBO> listBO = BeanTransform.copyProperties(list, WarrantyStateMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存保修状态月汇总记录
     *
     * @param to 保存保修状态月汇总记录to
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public WarrantyStateMonthSumBO save(WarrantyStateMonthSumTO to) throws SerException {
        WarrantyStateMonthSum marketServeRecord = BeanTransform.copyProperties(to, WarrantyStateMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        WarrantyStateMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, WarrantyStateMonthSumBO.class);
        return bo;
    }

    /**
     * 更新保修状态月汇总记录
     *
     * @param to 保修状态月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(WarrantyStateMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            WarrantyStateMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateWarrantyStateMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新保修状态月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateWarrantyStateMonthSum(WarrantyStateMonthSumTO to, WarrantyStateMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除保修状态月汇总记录
     *
     * @param id 保修状态月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 保修状态月汇总
     *
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<WarrantyStateMonthSumBO> summary() throws SerException {
        return null;
    }

}
package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.RepairStateMonthSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateMonthSum;
import com.bjike.goddess.materialsummary.to.RepairStateMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 维修状态月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ 维修状态月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class RepairStateMonthSumSerImpl extends ServiceImpl<RepairStateMonthSum, RepairStateMonthSumDTO> implements RepairStateMonthSumSer {

    /**
     * 分页查询维修状态月汇总记录
     *
     * @param dto 维修状态月汇总记录dto
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateMonthSumBO> list(RepairStateMonthSumDTO dto) throws SerException {
        List<RepairStateMonthSum> list = super.findByPage(dto);
        List<RepairStateMonthSumBO> listBO = BeanTransform.copyProperties(list, RepairStateMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存维修状态月汇总记录
     *
     * @param to 保存维修状态月汇总记录to
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public RepairStateMonthSumBO save(RepairStateMonthSumTO to) throws SerException {
        RepairStateMonthSum marketServeRecord = BeanTransform.copyProperties(to, RepairStateMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        RepairStateMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, RepairStateMonthSumBO.class);
        return bo;
    }

    /**
     * 更新维修状态月汇总记录
     *
     * @param to 维修状态月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RepairStateMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RepairStateMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateRepairStateMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新维修状态月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRepairStateMonthSum(RepairStateMonthSumTO to, RepairStateMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除维修状态月汇总记录
     *
     * @param id 维修状态月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 维修状态月汇总
     *
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<RepairStateMonthSumBO> summary() throws SerException {
        return null;
    }

}
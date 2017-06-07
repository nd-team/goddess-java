package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.RepairStateWeekSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateWeekSum;
import com.bjike.goddess.materialsummary.to.RepairStateWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 维修状态周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ 维修状态周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class RepairStateWeekSumSerImpl extends ServiceImpl<RepairStateWeekSum, RepairStateWeekSumDTO> implements RepairStateWeekSumSer {

    /**
     * 分页查询维修状态周汇总记录
     *
     * @param dto 维修状态周汇总记录dto
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateWeekSumBO> list(RepairStateWeekSumDTO dto) throws SerException {
        List<RepairStateWeekSum> list = super.findByPage(dto);
        List<RepairStateWeekSumBO> listBO = BeanTransform.copyProperties(list, RepairStateWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存维修状态周汇总记录
     *
     * @param to 保存维修状态周汇总记录to
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public RepairStateWeekSumBO save(RepairStateWeekSumTO to) throws SerException {
        RepairStateWeekSum marketServeRecord = BeanTransform.copyProperties(to, RepairStateWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        RepairStateWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, RepairStateWeekSumBO.class);
        return bo;
    }

    /**
     * 更新维修状态周汇总记录
     *
     * @param to 维修状态周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RepairStateWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RepairStateWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateRepairStateWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新维修状态周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRepairStateWeekSum(RepairStateWeekSumTO to, RepairStateWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除维修状态周汇总记录
     *
     * @param id 维修状态周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 维修状态周汇总
     *
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<RepairStateWeekSumBO> summary() throws SerException {
        return null;
    }

}
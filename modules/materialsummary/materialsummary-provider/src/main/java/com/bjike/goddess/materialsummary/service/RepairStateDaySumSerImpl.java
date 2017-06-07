package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.RepairStateDaySumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateDaySumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateDaySum;
import com.bjike.goddess.materialsummary.to.RepairStateDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 维修状态日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:52 ]
 * @Description: [ 维修状态日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class RepairStateDaySumSerImpl extends ServiceImpl<RepairStateDaySum, RepairStateDaySumDTO> implements RepairStateDaySumSer {

    /**
     * 分页查询维修状态日汇总记录
     *
     * @param dto 维修状态日汇总记录dto
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateDaySumBO> list(RepairStateDaySumDTO dto) throws SerException {
        List<RepairStateDaySum> list = super.findByPage(dto);
        List<RepairStateDaySumBO> listBO = BeanTransform.copyProperties(list, RepairStateDaySumBO.class);
        return listBO;
    }

    /**
     * 保存维修状态日汇总记录
     *
     * @param to 保存维修状态日汇总记录to
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public RepairStateDaySumBO save(RepairStateDaySumTO to) throws SerException {
        RepairStateDaySum marketServeRecord = BeanTransform.copyProperties(to, RepairStateDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        RepairStateDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, RepairStateDaySumBO.class);
        return bo;
    }

    /**
     * 更新维修状态日汇总记录
     *
     * @param to 维修状态日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RepairStateDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RepairStateDaySum model = super.findById(to.getId());
            if (model != null) {
                updateRepairStateDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新维修状态日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRepairStateDaySum(RepairStateDaySumTO to, RepairStateDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除维修状态日汇总记录
     *
     * @param id 维修状态日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 维修状态日汇总
     *
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<RepairStateDaySumBO> summary() throws SerException {
        return null;
    }

}
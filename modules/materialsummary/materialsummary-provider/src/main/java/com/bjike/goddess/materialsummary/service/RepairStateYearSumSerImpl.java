package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.RepairStateYearSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateYearSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateYearSum;
import com.bjike.goddess.materialsummary.to.RepairStateYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 维修状态年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:48 ]
 * @Description: [ 维修状态年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class RepairStateYearSumSerImpl extends ServiceImpl<RepairStateYearSum, RepairStateYearSumDTO> implements RepairStateYearSumSer {

    /**
     * 分页查询维修状态年汇总记录
     *
     * @param dto 维修状态年汇总记录dto
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateYearSumBO> list(RepairStateYearSumDTO dto) throws SerException {
        List<RepairStateYearSum> list = super.findByPage(dto);
        List<RepairStateYearSumBO> listBO = BeanTransform.copyProperties(list, RepairStateYearSumBO.class);
        return listBO;
    }

    /**
     * 保存维修状态年汇总记录
     *
     * @param to 保存维修状态年汇总记录to
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public RepairStateYearSumBO save(RepairStateYearSumTO to) throws SerException {
        RepairStateYearSum marketServeRecord = BeanTransform.copyProperties(to, RepairStateYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        RepairStateYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, RepairStateYearSumBO.class);
        return bo;
    }

    /**
     * 更新维修状态年汇总记录
     *
     * @param to 维修状态年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RepairStateYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RepairStateYearSum model = super.findById(to.getId());
            if (model != null) {
                updateRepairStateYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新维修状态年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRepairStateYearSum(RepairStateYearSumTO to, RepairStateYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除维修状态年汇总记录
     *
     * @param id 维修状态年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 维修状态年汇总
     *
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<RepairStateYearSumBO> summary() throws SerException {
        return null;
    }

}
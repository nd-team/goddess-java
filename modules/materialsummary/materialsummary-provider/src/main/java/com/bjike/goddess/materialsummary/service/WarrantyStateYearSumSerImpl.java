package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.WarrantyStateYearSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateYearSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateYearSum;
import com.bjike.goddess.materialsummary.to.WarrantyStateYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 保修状态年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:16 ]
 * @Description: [ 保修状态年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class WarrantyStateYearSumSerImpl extends ServiceImpl<WarrantyStateYearSum, WarrantyStateYearSumDTO> implements WarrantyStateYearSumSer {

    /**
     * 分页查询保修状态年汇总记录
     *
     * @param dto 保修状态年汇总记录dto
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateYearSumBO> list(WarrantyStateYearSumDTO dto) throws SerException {
        List<WarrantyStateYearSum> list = super.findByPage(dto);
        List<WarrantyStateYearSumBO> listBO = BeanTransform.copyProperties(list, WarrantyStateYearSumBO.class);
        return listBO;
    }

    /**
     * 保存保修状态年汇总记录
     *
     * @param to 保存保修状态年汇总记录to
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public WarrantyStateYearSumBO save(WarrantyStateYearSumTO to) throws SerException {
        WarrantyStateYearSum marketServeRecord = BeanTransform.copyProperties(to, WarrantyStateYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        WarrantyStateYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, WarrantyStateYearSumBO.class);
        return bo;
    }

    /**
     * 更新保修状态年汇总记录
     *
     * @param to 保修状态年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(WarrantyStateYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            WarrantyStateYearSum model = super.findById(to.getId());
            if (model != null) {
                updateWarrantyStateYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新保修状态年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateWarrantyStateYearSum(WarrantyStateYearSumTO to, WarrantyStateYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除保修状态年汇总记录
     *
     * @param id 保修状态年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 保修状态年汇总
     *
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<WarrantyStateYearSumBO> summary() throws SerException {
        return null;
    }

}
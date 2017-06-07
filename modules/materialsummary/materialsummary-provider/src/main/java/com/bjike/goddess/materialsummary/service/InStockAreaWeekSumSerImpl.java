package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockAreaWeekSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaWeekSum;
import com.bjike.goddess.materialsummary.to.InStockAreaWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库地区周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:10 ]
 * @Description: [ 入库地区周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class InStockAreaWeekSumSerImpl extends ServiceImpl<InStockAreaWeekSum, InStockAreaWeekSumDTO> implements InStockAreaWeekSumSer {

    /**
     * 分页查询入库地区周汇总记录
     *
     * @param dto 入库地区周汇总记录dto
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaWeekSumBO> list(InStockAreaWeekSumDTO dto) throws SerException {
        List<InStockAreaWeekSum> list = super.findByPage(dto);
        List<InStockAreaWeekSumBO> listBO = BeanTransform.copyProperties(list, InStockAreaWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存入库地区周汇总记录
     *
     * @param to 保存入库地区周汇总记录to
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public InStockAreaWeekSumBO save(InStockAreaWeekSumTO to) throws SerException {
        InStockAreaWeekSum marketServeRecord = BeanTransform.copyProperties(to, InStockAreaWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        InStockAreaWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, InStockAreaWeekSumBO.class);
        return bo;
    }

    /**
     * 更新入库地区周汇总记录
     *
     * @param to 入库地区周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InStockAreaWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InStockAreaWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateInStockAreaWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新入库地区周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInStockAreaWeekSum(InStockAreaWeekSumTO to, InStockAreaWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除入库地区周汇总记录
     *
     * @param id 入库地区周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 入库地区周汇总
     *
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<InStockAreaWeekSumBO> summary() throws SerException {
        return null;
    }

}
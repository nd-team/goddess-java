package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockAreaMonthSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaMonthSum;
import com.bjike.goddess.materialsummary.to.InStockAreaMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库地区月汇总业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:11 ]
 * @Description: [ 入库地区月汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class InStockAreaMonthSumSerImpl extends ServiceImpl<InStockAreaMonthSum, InStockAreaMonthSumDTO> implements InStockAreaMonthSumSer {

    /**
     * 分页查询入库地区月汇总记录
     *
     * @param dto 入库地区月汇总记录dto
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaMonthSumBO> list(InStockAreaMonthSumDTO dto) throws SerException {
        List<InStockAreaMonthSum> list = super.findByPage(dto);
        List<InStockAreaMonthSumBO> listBO = BeanTransform.copyProperties(list, InStockAreaMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存入库地区月汇总记录
     *
     * @param to 保存入库地区月汇总记录to
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public InStockAreaMonthSumBO save(InStockAreaMonthSumTO to) throws SerException {
        InStockAreaMonthSum marketServeRecord = BeanTransform.copyProperties(to, InStockAreaMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        InStockAreaMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, InStockAreaMonthSumBO.class);
        return bo;
    }

    /**
     * 更新入库地区月汇总记录
     *
     * @param to 入库地区月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InStockAreaMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InStockAreaMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateInStockAreaMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新入库地区月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInStockAreaMonthSum(InStockAreaMonthSumTO to, InStockAreaMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除入库地区月汇总记录
     *
     * @param id 入库地区月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 入库地区月汇总
     *
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<InStockAreaMonthSumBO> summary() throws SerException {
        return null;
    }

}
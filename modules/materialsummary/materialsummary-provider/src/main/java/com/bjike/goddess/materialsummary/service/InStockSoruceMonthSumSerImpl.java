package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockSoruceMonthSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceMonthSum;
import com.bjike.goddess.materialsummary.to.InStockSoruceMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库来源月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:06 ]
 * @Description: [ 入库来源月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class InStockSoruceMonthSumSerImpl extends ServiceImpl<InStockSoruceMonthSum, InStockSoruceMonthSumDTO> implements InStockSoruceMonthSumSer {

    /**
     * 分页查询入库来源月汇总记录
     *
     * @param dto 入库来源月汇总记录dto
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceMonthSumBO> list(InStockSoruceMonthSumDTO dto) throws SerException {
        List<InStockSoruceMonthSum> list = super.findByPage(dto);
        List<InStockSoruceMonthSumBO> listBO = BeanTransform.copyProperties(list, InStockSoruceMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存入库来源月汇总记录
     *
     * @param to 保存入库来源月汇总记录to
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public InStockSoruceMonthSumBO save(InStockSoruceMonthSumTO to) throws SerException {
        InStockSoruceMonthSum marketServeRecord = BeanTransform.copyProperties(to, InStockSoruceMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        InStockSoruceMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, InStockSoruceMonthSumBO.class);
        return bo;
    }

    /**
     * 更新入库来源月汇总记录
     *
     * @param to 入库来源月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InStockSoruceMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InStockSoruceMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateInStockSoruceMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新入库来源月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInStockSoruceMonthSum(InStockSoruceMonthSumTO to, InStockSoruceMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除入库来源月汇总记录
     *
     * @param id 入库来源月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 入库来源月汇总
     *
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<InStockSoruceMonthSumBO> summary() throws SerException {
        return null;
    }

}
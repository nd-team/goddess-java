package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockSoruceWeekSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceWeekSum;
import com.bjike.goddess.materialsummary.to.InStockSoruceWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库来源周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:05 ]
 * @Description: [ 入库来源周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class InStockSoruceWeekSumSerImpl extends ServiceImpl<InStockSoruceWeekSum, InStockSoruceWeekSumDTO> implements InStockSoruceWeekSumSer {

    /**
     * 分页查询入库来源周汇总记录
     *
     * @param dto 入库来源周汇总记录dto
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceWeekSumBO> list(InStockSoruceWeekSumDTO dto) throws SerException {
        List<InStockSoruceWeekSum> list = super.findByPage(dto);
        List<InStockSoruceWeekSumBO> listBO = BeanTransform.copyProperties(list, InStockSoruceWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存入库来源周汇总记录
     *
     * @param to 保存入库来源周汇总记录to
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public InStockSoruceWeekSumBO save(InStockSoruceWeekSumTO to) throws SerException {
        InStockSoruceWeekSum marketServeRecord = BeanTransform.copyProperties(to, InStockSoruceWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        InStockSoruceWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, InStockSoruceWeekSumBO.class);
        return bo;
    }

    /**
     * 更新入库来源周汇总记录
     *
     * @param to 入库来源周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InStockSoruceWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InStockSoruceWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateInStockSoruceWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新入库来源周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInStockSoruceWeekSum(InStockSoruceWeekSumTO to, InStockSoruceWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除入库来源周汇总记录
     *
     * @param id 入库来源周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 入库来源周汇总
     *
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<InStockSoruceWeekSumBO> summary() throws SerException {
        return null;
    }

}
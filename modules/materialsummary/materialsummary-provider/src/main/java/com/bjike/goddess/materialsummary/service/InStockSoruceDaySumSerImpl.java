package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockSoruceDaySumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceDaySumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceDaySum;
import com.bjike.goddess.materialsummary.to.InStockSoruceDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库来源日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:04 ]
 * @Description: [ 入库来源日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class InStockSoruceDaySumSerImpl extends ServiceImpl<InStockSoruceDaySum, InStockSoruceDaySumDTO> implements InStockSoruceDaySumSer {

    /**
     * 分页查询入库来源日汇总记录
     *
     * @param dto 入库来源日汇总记录dto
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceDaySumBO> list(InStockSoruceDaySumDTO dto) throws SerException {
        List<InStockSoruceDaySum> list = super.findByPage(dto);
        List<InStockSoruceDaySumBO> listBO = BeanTransform.copyProperties(list, InStockSoruceDaySumBO.class);
        return listBO;
    }

    /**
     * 保存入库来源日汇总记录
     *
     * @param to 保存入库来源日汇总记录to
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public InStockSoruceDaySumBO save(InStockSoruceDaySumTO to) throws SerException {
        InStockSoruceDaySum marketServeRecord = BeanTransform.copyProperties(to, InStockSoruceDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        InStockSoruceDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, InStockSoruceDaySumBO.class);
        return bo;
    }

    /**
     * 更新入库来源日汇总记录
     *
     * @param to 入库来源日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InStockSoruceDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InStockSoruceDaySum model = super.findById(to.getId());
            if (model != null) {
                updateInStockSoruceDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新入库来源日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInStockSoruceDaySum(InStockSoruceDaySumTO to, InStockSoruceDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除入库来源日汇总记录
     *
     * @param id 入库来源日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 入库来源日汇总
     *
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<InStockSoruceDaySumBO> summary() throws SerException {
        return null;
    }

}
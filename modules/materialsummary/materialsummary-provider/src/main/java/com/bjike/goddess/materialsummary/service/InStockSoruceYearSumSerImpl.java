package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockSoruceYearSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceYearSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceYearSum;
import com.bjike.goddess.materialsummary.to.InStockSoruceYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库来源年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:07 ]
 * @Description: [ 入库来源年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class InStockSoruceYearSumSerImpl extends ServiceImpl<InStockSoruceYearSum, InStockSoruceYearSumDTO> implements InStockSoruceYearSumSer {

    /**
     * 分页查询入库来源年汇总记录
     *
     * @param dto 入库来源年汇总记录dto
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceYearSumBO> list(InStockSoruceYearSumDTO dto) throws SerException {
        List<InStockSoruceYearSum> list = super.findByPage(dto);
        List<InStockSoruceYearSumBO> listBO = BeanTransform.copyProperties(list, InStockSoruceYearSumBO.class);
        return listBO;
    }

    /**
     * 保存入库来源年汇总记录
     *
     * @param to 保存入库来源年汇总记录to
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public InStockSoruceYearSumBO save(InStockSoruceYearSumTO to) throws SerException {
        InStockSoruceYearSum marketServeRecord = BeanTransform.copyProperties(to, InStockSoruceYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        InStockSoruceYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, InStockSoruceYearSumBO.class);
        return bo;
    }

    /**
     * 更新入库来源年汇总记录
     *
     * @param to 入库来源年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InStockSoruceYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InStockSoruceYearSum model = super.findById(to.getId());
            if (model != null) {
                updateInStockSoruceYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新入库来源年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInStockSoruceYearSum(InStockSoruceYearSumTO to, InStockSoruceYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除入库来源年汇总记录
     *
     * @param id 入库来源年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 入库来源年汇总
     *
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<InStockSoruceYearSumBO> summary() throws SerException {
        return null;
    }

}
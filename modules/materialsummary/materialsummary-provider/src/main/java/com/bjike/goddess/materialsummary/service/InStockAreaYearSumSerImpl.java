package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockAreaYearSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaYearSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaYearSum;
import com.bjike.goddess.materialsummary.to.InStockAreaYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库地区年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:11 ]
 * @Description: [ 入库地区年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class InStockAreaYearSumSerImpl extends ServiceImpl<InStockAreaYearSum, InStockAreaYearSumDTO> implements InStockAreaYearSumSer {

    /**
     * 分页查询入库地区年汇总记录
     *
     * @param dto 入库地区年汇总记录dto
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaYearSumBO> list(InStockAreaYearSumDTO dto) throws SerException {
        List<InStockAreaYearSum> list = super.findByPage(dto);
        List<InStockAreaYearSumBO> listBO = BeanTransform.copyProperties(list, InStockAreaYearSumBO.class);
        return listBO;
    }

    /**
     * 保存入库地区年汇总记录
     *
     * @param to 保存入库地区年汇总记录to
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public InStockAreaYearSumBO save(InStockAreaYearSumTO to) throws SerException {
        InStockAreaYearSum marketServeRecord = BeanTransform.copyProperties(to, InStockAreaYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        InStockAreaYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, InStockAreaYearSumBO.class);
        return bo;
    }

    /**
     * 更新入库地区年汇总记录
     *
     * @param to 入库地区年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InStockAreaYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InStockAreaYearSum model = super.findById(to.getId());
            if (model != null) {
                updateInStockAreaYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新入库地区年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInStockAreaYearSum(InStockAreaYearSumTO to, InStockAreaYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除入库地区年汇总记录
     *
     * @param id 入库地区年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 入库地区年汇总
     *
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<InStockAreaYearSumBO> summary() throws SerException {
        return null;
    }

}
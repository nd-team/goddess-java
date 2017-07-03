package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferTypeWeekSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeWeekSum;
import com.bjike.goddess.materialsummary.to.TransferTypeWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调动类型周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class TransferTypeWeekSumSerImpl extends ServiceImpl<TransferTypeWeekSum, TransferTypeWeekSumDTO> implements TransferTypeWeekSumSer {

    /**
     * 分页查询调动类型周汇总记录
     *
     * @param dto 调动类型周汇总记录dto
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeWeekSumBO> list(TransferTypeWeekSumDTO dto) throws SerException {
        List<TransferTypeWeekSum> list = super.findByPage(dto);
        List<TransferTypeWeekSumBO> listBO = BeanTransform.copyProperties(list, TransferTypeWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存调动类型周汇总记录
     *
     * @param to 保存调动类型周汇总记录to
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TransferTypeWeekSumBO save(TransferTypeWeekSumTO to) throws SerException {
        TransferTypeWeekSum marketServeRecord = BeanTransform.copyProperties(to, TransferTypeWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        TransferTypeWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, TransferTypeWeekSumBO.class);
        return bo;
    }

    /**
     * 更新调动类型周汇总记录
     *
     * @param to 调动类型周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TransferTypeWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TransferTypeWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateTransferTypeWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新调动类型周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateTransferTypeWeekSum(TransferTypeWeekSumTO to, TransferTypeWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除调动类型周汇总记录
     *
     * @param id 调动类型周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 调动类型周汇总
     *
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TransferTypeWeekSumBO> summary() throws SerException {
        return null;
    }

}
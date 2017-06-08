package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferHandlerWeekSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerWeekSum;
import com.bjike.goddess.materialsummary.to.TransferHandlerWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调动经手人周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ 调动经手人周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class TransferHandlerWeekSumSerImpl extends ServiceImpl<TransferHandlerWeekSum, TransferHandlerWeekSumDTO> implements TransferHandlerWeekSumSer {

    /**
     * 分页查询调动经手人周汇总记录
     *
     * @param dto 调动经手人周汇总记录dto
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerWeekSumBO> list(TransferHandlerWeekSumDTO dto) throws SerException {
        List<TransferHandlerWeekSum> list = super.findByPage(dto);
        List<TransferHandlerWeekSumBO> listBO = BeanTransform.copyProperties(list, TransferHandlerWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存调动经手人周汇总记录
     *
     * @param to 保存调动经手人周汇总记录to
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TransferHandlerWeekSumBO save(TransferHandlerWeekSumTO to) throws SerException {
        TransferHandlerWeekSum marketServeRecord = BeanTransform.copyProperties(to, TransferHandlerWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        TransferHandlerWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, TransferHandlerWeekSumBO.class);
        return bo;
    }

    /**
     * 更新调动经手人周汇总记录
     *
     * @param to 调动经手人周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TransferHandlerWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TransferHandlerWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateTransferHandlerWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新调动经手人周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateTransferHandlerWeekSum(TransferHandlerWeekSumTO to, TransferHandlerWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除调动经手人周汇总记录
     *
     * @param id 调动经手人周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 调动经手人周汇总
     *
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TransferHandlerWeekSumBO> summary() throws SerException {
        return null;
    }

}
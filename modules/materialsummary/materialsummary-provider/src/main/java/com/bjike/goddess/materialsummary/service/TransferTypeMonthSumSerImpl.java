package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferTypeMonthSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeMonthSum;
import com.bjike.goddess.materialsummary.to.TransferTypeMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调动类型月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class TransferTypeMonthSumSerImpl extends ServiceImpl<TransferTypeMonthSum, TransferTypeMonthSumDTO> implements TransferTypeMonthSumSer {

    /**
     * 分页查询调动类型月汇总记录
     *
     * @param dto 调动类型月汇总记录dto
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeMonthSumBO> list(TransferTypeMonthSumDTO dto) throws SerException {
        List<TransferTypeMonthSum> list = super.findByPage(dto);
        List<TransferTypeMonthSumBO> listBO = BeanTransform.copyProperties(list, TransferTypeMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存调动类型月汇总记录
     *
     * @param to 保存调动类型月汇总记录to
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TransferTypeMonthSumBO save(TransferTypeMonthSumTO to) throws SerException {
        TransferTypeMonthSum marketServeRecord = BeanTransform.copyProperties(to, TransferTypeMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        TransferTypeMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, TransferTypeMonthSumBO.class);
        return bo;
    }

    /**
     * 更新调动类型月汇总记录
     *
     * @param to 调动类型月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TransferTypeMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TransferTypeMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateTransferTypeMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新调动类型月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateTransferTypeMonthSum(TransferTypeMonthSumTO to, TransferTypeMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除调动类型月汇总记录
     *
     * @param id 调动类型月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 调动类型月汇总
     *
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TransferTypeMonthSumBO> summary() throws SerException {
        return null;
    }

}
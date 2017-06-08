package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferHandlerMonthSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerMonthSum;
import com.bjike.goddess.materialsummary.to.TransferHandlerMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调动经手人月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ 调动经手人月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class TransferHandlerMonthSumSerImpl extends ServiceImpl<TransferHandlerMonthSum, TransferHandlerMonthSumDTO> implements TransferHandlerMonthSumSer {

    /**
     * 分页查询调动经手人月汇总记录
     *
     * @param dto 调动经手人月汇总记录dto
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerMonthSumBO> list(TransferHandlerMonthSumDTO dto) throws SerException {
        List<TransferHandlerMonthSum> list = super.findByPage(dto);
        List<TransferHandlerMonthSumBO> listBO = BeanTransform.copyProperties(list, TransferHandlerMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存调动经手人月汇总记录
     *
     * @param to 保存调动经手人月汇总记录to
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TransferHandlerMonthSumBO save(TransferHandlerMonthSumTO to) throws SerException {
        TransferHandlerMonthSum marketServeRecord = BeanTransform.copyProperties(to, TransferHandlerMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        TransferHandlerMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, TransferHandlerMonthSumBO.class);
        return bo;
    }

    /**
     * 更新调动经手人月汇总记录
     *
     * @param to 调动经手人月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TransferHandlerMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TransferHandlerMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateTransferHandlerMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新调动经手人月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateTransferHandlerMonthSum(TransferHandlerMonthSumTO to, TransferHandlerMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除调动经手人月汇总记录
     *
     * @param id 调动经手人月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 调动经手人月汇总
     *
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TransferHandlerMonthSumBO> summary() throws SerException {
        return null;
    }

}
package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferHandlerDaySumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerDaySumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerDaySum;
import com.bjike.goddess.materialsummary.to.TransferHandlerDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调动经手人日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:53 ]
 * @Description: [ 调动经手人日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class TransferHandlerDaySumSerImpl extends ServiceImpl<TransferHandlerDaySum, TransferHandlerDaySumDTO> implements TransferHandlerDaySumSer {

    /**
     * 分页查询调动经手人日汇总记录
     *
     * @param dto 调动经手人日汇总记录dto
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerDaySumBO> list(TransferHandlerDaySumDTO dto) throws SerException {
        List<TransferHandlerDaySum> list = super.findByPage(dto);
        List<TransferHandlerDaySumBO> listBO = BeanTransform.copyProperties(list, TransferHandlerDaySumBO.class);
        return listBO;
    }

    /**
     * 保存调动经手人日汇总记录
     *
     * @param to 保存调动经手人日汇总记录to
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TransferHandlerDaySumBO save(TransferHandlerDaySumTO to) throws SerException {
        TransferHandlerDaySum marketServeRecord = BeanTransform.copyProperties(to, TransferHandlerDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        TransferHandlerDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, TransferHandlerDaySumBO.class);
        return bo;
    }

    /**
     * 更新调动经手人日汇总记录
     *
     * @param to 调动经手人日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TransferHandlerDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TransferHandlerDaySum model = super.findById(to.getId());
            if (model != null) {
                updateTransferHandlerDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新调动经手人日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateTransferHandlerDaySum(TransferHandlerDaySumTO to, TransferHandlerDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除调动经手人日汇总记录
     *
     * @param id 调动经手人日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 调动经手人日汇总
     *
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TransferHandlerDaySumBO> summary() throws SerException {
        return null;
    }

}
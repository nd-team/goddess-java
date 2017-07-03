package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferHandlerYearSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerYearSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerYearSum;
import com.bjike.goddess.materialsummary.to.TransferHandlerYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调动经手人年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:56 ]
 * @Description: [ 调动经手人年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class TransferHandlerYearSumSerImpl extends ServiceImpl<TransferHandlerYearSum, TransferHandlerYearSumDTO> implements TransferHandlerYearSumSer {

    /**
     * 分页查询调动经手人年汇总记录
     *
     * @param dto 调动经手人年汇总记录dto
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerYearSumBO> list(TransferHandlerYearSumDTO dto) throws SerException {
        List<TransferHandlerYearSum> list = super.findByPage(dto);
        List<TransferHandlerYearSumBO> listBO = BeanTransform.copyProperties(list, TransferHandlerYearSumBO.class);
        return listBO;
    }

    /**
     * 保存调动经手人年汇总记录
     *
     * @param to 保存调动经手人年汇总记录to
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TransferHandlerYearSumBO save(TransferHandlerYearSumTO to) throws SerException {
        TransferHandlerYearSum marketServeRecord = BeanTransform.copyProperties(to, TransferHandlerYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        TransferHandlerYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, TransferHandlerYearSumBO.class);
        return bo;
    }

    /**
     * 更新调动经手人年汇总记录
     *
     * @param to 调动经手人年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TransferHandlerYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TransferHandlerYearSum model = super.findById(to.getId());
            if (model != null) {
                updateTransferHandlerYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新调动经手人年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateTransferHandlerYearSum(TransferHandlerYearSumTO to, TransferHandlerYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除调动经手人年汇总记录
     *
     * @param id 调动经手人年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 调动经手人年汇总
     *
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TransferHandlerYearSumBO> summary() throws SerException {
        return null;
    }

}
package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferTypeYearSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeYearSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeYearSum;
import com.bjike.goddess.materialsummary.to.TransferTypeYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调动类型年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:45 ]
 * @Description: [ 调动类型年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class TransferTypeYearSumSerImpl extends ServiceImpl<TransferTypeYearSum, TransferTypeYearSumDTO> implements TransferTypeYearSumSer {

    /**
     * 分页查询调动类型年汇总记录
     *
     * @param dto 调动类型年汇总记录dto
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeYearSumBO> list(TransferTypeYearSumDTO dto) throws SerException {
        List<TransferTypeYearSum> list = super.findByPage(dto);
        List<TransferTypeYearSumBO> listBO = BeanTransform.copyProperties(list, TransferTypeYearSumBO.class);
        return listBO;
    }

    /**
     * 保存调动类型年汇总记录
     *
     * @param to 保存调动类型年汇总记录to
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TransferTypeYearSumBO save(TransferTypeYearSumTO to) throws SerException {
        TransferTypeYearSum marketServeRecord = BeanTransform.copyProperties(to, TransferTypeYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        TransferTypeYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, TransferTypeYearSumBO.class);
        return bo;
    }

    /**
     * 更新调动类型年汇总记录
     *
     * @param to 调动类型年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TransferTypeYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TransferTypeYearSum model = super.findById(to.getId());
            if (model != null) {
                updateTransferTypeYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新调动类型年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateTransferTypeYearSum(TransferTypeYearSumTO to, TransferTypeYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除调动类型年汇总记录
     *
     * @param id 调动类型年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 调动类型年汇总
     *
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TransferTypeYearSumBO> summary() throws SerException {
        return null;
    }


}
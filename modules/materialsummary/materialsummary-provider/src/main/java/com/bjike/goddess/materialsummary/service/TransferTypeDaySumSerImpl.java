package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferTypeDaySumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeDaySumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeDaySum;
import com.bjike.goddess.materialsummary.to.TransferTypeDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调动类型日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:40 ]
 * @Description: [ 调动类型日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class TransferTypeDaySumSerImpl extends ServiceImpl<TransferTypeDaySum, TransferTypeDaySumDTO> implements TransferTypeDaySumSer {

    /**
     * 分页查询调动类型日汇总记录
     *
     * @param dto 调动类型日汇总记录dto
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeDaySumBO> list(TransferTypeDaySumDTO dto) throws SerException {
        List<TransferTypeDaySum> list = super.findByPage(dto);
        List<TransferTypeDaySumBO> listBO = BeanTransform.copyProperties(list, TransferTypeDaySumBO.class);
        return listBO;
    }

    /**
     * 保存调动类型日汇总记录
     *
     * @param to 保存调动类型日汇总记录to
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TransferTypeDaySumBO save(TransferTypeDaySumTO to) throws SerException {
        TransferTypeDaySum marketServeRecord = BeanTransform.copyProperties(to, TransferTypeDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        TransferTypeDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, TransferTypeDaySumBO.class);
        return bo;
    }

    /**
     * 更新调动类型日汇总记录
     *
     * @param to 调动类型日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TransferTypeDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TransferTypeDaySum model = super.findById(to.getId());
            if (model != null) {
                updateTransferTypeDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新调动类型日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateTransferTypeDaySum(TransferTypeDaySumTO to, TransferTypeDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除调动类型日汇总记录
     *
     * @param id 调动类型日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 调动类型日汇总
     *
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TransferTypeDaySumBO> summary() throws SerException {
        return null;
    }

}
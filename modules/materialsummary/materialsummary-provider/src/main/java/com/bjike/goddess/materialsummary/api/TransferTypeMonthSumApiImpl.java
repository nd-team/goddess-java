package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferTypeMonthSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeMonthSum;
import com.bjike.goddess.materialsummary.service.TransferTypeMonthSumSer;
import com.bjike.goddess.materialsummary.to.TransferTypeMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调动类型月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferTypeMonthSumApiImpl")
public class TransferTypeMonthSumApiImpl implements TransferTypeMonthSumAPI {

    @Autowired
    private TransferTypeMonthSumSer transferTypeMonthSumSer;

    /**
     * 根据id查询调动类型月汇总记录
     *
     * @param id 调动类型月汇总记录唯一标识
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    @Override
    public TransferTypeMonthSumBO findById(String id) throws SerException {
        TransferTypeMonthSum model = transferTypeMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, TransferTypeMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 调动类型月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(TransferTypeMonthSumDTO dto) throws SerException {
        return transferTypeMonthSumSer.count(dto);
    }

    /**
     * 分页查询调动类型月汇总记录
     *
     * @param dto 调动类型月汇总记录dto
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeMonthSumBO> list(TransferTypeMonthSumDTO dto) throws SerException {
        return transferTypeMonthSumSer.list(dto);
    }

    /**
     * 保存调动类型月汇总记录
     *
     * @param to 调动类型月汇总记录to
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    @Override
    public TransferTypeMonthSumBO save(TransferTypeMonthSumTO to) throws SerException {
        return transferTypeMonthSumSer.save(to);
    }

    /**
     * 根据id删除调动类型月汇总记录
     *
     * @param id 调动类型月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        transferTypeMonthSumSer.remove(id);
    }

    /**
     * 更新调动类型月汇总记录
     *
     * @param to 调动类型月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(TransferTypeMonthSumTO to) throws SerException {
        transferTypeMonthSumSer.update(to);
    }

    /**
     * 调动类型月汇总
     *
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeMonthSumBO> summary() throws SerException {
        return transferTypeMonthSumSer.summary();
    }
 
}
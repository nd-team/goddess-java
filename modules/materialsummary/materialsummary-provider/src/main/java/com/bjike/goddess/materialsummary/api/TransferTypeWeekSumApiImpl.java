package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferTypeWeekSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeWeekSum;
import com.bjike.goddess.materialsummary.service.TransferTypeWeekSumSer;
import com.bjike.goddess.materialsummary.to.TransferTypeWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调动类型周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferTypeWeekSumApiImpl")
public class TransferTypeWeekSumApiImpl implements TransferTypeWeekSumAPI {

    @Autowired
    private TransferTypeWeekSumSer transferTypeWeekSumSer;

    /**
     * 根据id查询调动类型周汇总记录
     *
     * @param id 调动类型周汇总记录唯一标识
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    @Override
    public TransferTypeWeekSumBO findById(String id) throws SerException {
        TransferTypeWeekSum model = transferTypeWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, TransferTypeWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 调动类型周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(TransferTypeWeekSumDTO dto) throws SerException {
        return transferTypeWeekSumSer.count(dto);
    }

    /**
     * 分页查询调动类型周汇总记录
     *
     * @param dto 调动类型周汇总记录dto
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeWeekSumBO> list(TransferTypeWeekSumDTO dto) throws SerException {
        return transferTypeWeekSumSer.list(dto);
    }

    /**
     * 保存调动类型周汇总记录
     *
     * @param to 调动类型周汇总记录to
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    @Override
    public TransferTypeWeekSumBO save(TransferTypeWeekSumTO to) throws SerException {
        return transferTypeWeekSumSer.save(to);
    }

    /**
     * 根据id删除调动类型周汇总记录
     *
     * @param id 调动类型周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        transferTypeWeekSumSer.remove(id);
    }

    /**
     * 更新调动类型周汇总记录
     *
     * @param to 调动类型周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(TransferTypeWeekSumTO to) throws SerException {
        transferTypeWeekSumSer.update(to);
    }

    /**
     * 调动类型周汇总
     *
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeWeekSumBO> summary() throws SerException {
        return transferTypeWeekSumSer.summary();
    }

}
package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferHandlerWeekSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerWeekSum;
import com.bjike.goddess.materialsummary.service.TransferHandlerWeekSumSer;
import com.bjike.goddess.materialsummary.to.TransferHandlerWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调动经手人周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ 调动经手人周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferHandlerWeekSumApiImpl")
public class TransferHandlerWeekSumApiImpl implements TransferHandlerWeekSumAPI {

    @Autowired
    private TransferHandlerWeekSumSer transferHandlerWeekSumSer;

    /**
     * 根据id查询调动经手人周汇总记录
     *
     * @param id 调动经手人周汇总记录唯一标识
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    @Override
    public TransferHandlerWeekSumBO findById(String id) throws SerException {
        TransferHandlerWeekSum model = transferHandlerWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, TransferHandlerWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 调动经手人周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(TransferHandlerWeekSumDTO dto) throws SerException {
        return transferHandlerWeekSumSer.count(dto);
    }

    /**
     * 分页查询调动经手人周汇总记录
     *
     * @param dto 调动经手人周汇总记录dto
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerWeekSumBO> list(TransferHandlerWeekSumDTO dto) throws SerException {
        return transferHandlerWeekSumSer.list(dto);
    }

    /**
     * 保存调动经手人周汇总记录
     *
     * @param to 调动经手人周汇总记录to
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    @Override
    public TransferHandlerWeekSumBO save(TransferHandlerWeekSumTO to) throws SerException {
        return transferHandlerWeekSumSer.save(to);
    }

    /**
     * 根据id删除调动经手人周汇总记录
     *
     * @param id 调动经手人周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        transferHandlerWeekSumSer.remove(id);
    }

    /**
     * 更新调动经手人周汇总记录
     *
     * @param to 调动经手人周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(TransferHandlerWeekSumTO to) throws SerException {
        transferHandlerWeekSumSer.update(to);
    }

    /**
     * 调动经手人周汇总
     *
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerWeekSumBO> summary() throws SerException {
        return transferHandlerWeekSumSer.summary();
    }

}
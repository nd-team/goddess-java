package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferHandlerMonthSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerMonthSum;
import com.bjike.goddess.materialsummary.service.TransferHandlerMonthSumSer;
import com.bjike.goddess.materialsummary.to.TransferHandlerMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调动经手人月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ 调动经手人月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferHandlerMonthSumApiImpl")
public class TransferHandlerMonthSumApiImpl implements TransferHandlerMonthSumAPI {

    @Autowired
    private TransferHandlerMonthSumSer transferHandlerMonthSumSer;

    /**
     * 根据id查询调动经手人月汇总记录
     *
     * @param id 调动经手人月汇总记录唯一标识
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    @Override
    public TransferHandlerMonthSumBO findById(String id) throws SerException {
        TransferHandlerMonthSum model = transferHandlerMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, TransferHandlerMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 调动经手人月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(TransferHandlerMonthSumDTO dto) throws SerException {
        return transferHandlerMonthSumSer.count(dto);
    }

    /**
     * 分页查询调动经手人月汇总记录
     *
     * @param dto 调动经手人月汇总记录dto
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerMonthSumBO> list(TransferHandlerMonthSumDTO dto) throws SerException {
        return transferHandlerMonthSumSer.list(dto);
    }

    /**
     * 保存调动经手人月汇总记录
     *
     * @param to 调动经手人月汇总记录to
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    @Override
    public TransferHandlerMonthSumBO save(TransferHandlerMonthSumTO to) throws SerException {
        return transferHandlerMonthSumSer.save(to);
    }

    /**
     * 根据id删除调动经手人月汇总记录
     *
     * @param id 调动经手人月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        transferHandlerMonthSumSer.remove(id);
    }

    /**
     * 更新调动经手人月汇总记录
     *
     * @param to 调动经手人月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(TransferHandlerMonthSumTO to) throws SerException {
        transferHandlerMonthSumSer.update(to);
    }

    /**
     * 调动经手人月汇总
     *
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerMonthSumBO> summary() throws SerException {
        return transferHandlerMonthSumSer.summary();
    }

}
package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferHandlerYearSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerYearSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerYearSum;
import com.bjike.goddess.materialsummary.service.TransferHandlerYearSumSer;
import com.bjike.goddess.materialsummary.to.TransferHandlerYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调动经手人年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:56 ]
 * @Description: [ 调动经手人年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferHandlerYearSumApiImpl")
public class TransferHandlerYearSumApiImpl implements TransferHandlerYearSumAPI {

    @Autowired
    private TransferHandlerYearSumSer transferHandlerYearSumSer;

    /**
     * 根据id查询调动经手人年汇总记录
     *
     * @param id 调动经手人年汇总记录唯一标识
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    @Override
    public TransferHandlerYearSumBO findById(String id) throws SerException {
        TransferHandlerYearSum model = transferHandlerYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, TransferHandlerYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 调动经手人年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(TransferHandlerYearSumDTO dto) throws SerException {
        return transferHandlerYearSumSer.count(dto);
    }

    /**
     * 分页查询调动经手人年汇总记录
     *
     * @param dto 调动经手人年汇总记录dto
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerYearSumBO> list(TransferHandlerYearSumDTO dto) throws SerException {
        return transferHandlerYearSumSer.list(dto);
    }

    /**
     * 保存调动经手人年汇总记录
     *
     * @param to 调动经手人年汇总记录to
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    @Override
    public TransferHandlerYearSumBO save(TransferHandlerYearSumTO to) throws SerException {
        return transferHandlerYearSumSer.save(to);
    }

    /**
     * 根据id删除调动经手人年汇总记录
     *
     * @param id 调动经手人年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        transferHandlerYearSumSer.remove(id);
    }

    /**
     * 更新调动经手人年汇总记录
     *
     * @param to 调动经手人年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(TransferHandlerYearSumTO to) throws SerException {
        transferHandlerYearSumSer.update(to);
    }

    /**
     * 调动经手人年汇总
     *
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerYearSumBO> summary() throws SerException {
        return transferHandlerYearSumSer.summary();
    }

}
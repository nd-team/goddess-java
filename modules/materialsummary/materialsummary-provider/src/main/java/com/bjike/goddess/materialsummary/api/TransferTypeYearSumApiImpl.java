package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferTypeYearSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeYearSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeYearSum;
import com.bjike.goddess.materialsummary.service.TransferTypeYearSumSer;
import com.bjike.goddess.materialsummary.to.TransferTypeYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调动类型年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:45 ]
 * @Description: [ 调动类型年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferTypeYearSumApiImpl")
public class TransferTypeYearSumApiImpl implements TransferTypeYearSumAPI {

    @Autowired
    private TransferTypeYearSumSer transferTypeYearSumSer;

    /**
     * 根据id查询调动类型年汇总记录
     *
     * @param id 调动类型年汇总记录唯一标识
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    @Override
    public TransferTypeYearSumBO findById(String id) throws SerException {
        TransferTypeYearSum model = transferTypeYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, TransferTypeYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 调动类型年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(TransferTypeYearSumDTO dto) throws SerException {
        return transferTypeYearSumSer.count(dto);
    }

    /**
     * 分页查询调动类型年汇总记录
     *
     * @param dto 调动类型年汇总记录dto
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeYearSumBO> list(TransferTypeYearSumDTO dto) throws SerException {
        return transferTypeYearSumSer.list(dto);
    }

    /**
     * 保存调动类型年汇总记录
     *
     * @param to 调动类型年汇总记录to
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    @Override
    public TransferTypeYearSumBO save(TransferTypeYearSumTO to) throws SerException {
        return transferTypeYearSumSer.save(to);
    }

    /**
     * 根据id删除调动类型年汇总记录
     *
     * @param id 调动类型年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        transferTypeYearSumSer.remove(id);
    }

    /**
     * 更新调动类型年汇总记录
     *
     * @param to 调动类型年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(TransferTypeYearSumTO to) throws SerException {
        transferTypeYearSumSer.update(to);
    }

    /**
     * 调动类型年汇总
     *
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeYearSumBO> summary() throws SerException {
        return transferTypeYearSumSer.summary();
    }

}
package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferHandlerDaySumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerDaySumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerDaySum;
import com.bjike.goddess.materialsummary.service.TransferHandlerDaySumSer;
import com.bjike.goddess.materialsummary.to.TransferHandlerDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调动经手人日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:53 ]
 * @Description: [ 调动经手人日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferHandlerDaySumApiImpl")
public class TransferHandlerDaySumApiImpl implements TransferHandlerDaySumAPI {

    @Autowired
    private TransferHandlerDaySumSer transferHandlerDaySumSer;

    /**
     * 根据id查询调动经手人日汇总记录
     *
     * @param id 调动经手人日汇总记录唯一标识
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    @Override
    public TransferHandlerDaySumBO findById(String id) throws SerException {
        TransferHandlerDaySum model = transferHandlerDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, TransferHandlerDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 调动经手人日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(TransferHandlerDaySumDTO dto) throws SerException {
        return transferHandlerDaySumSer.count(dto);
    }

    /**
     * 分页查询调动经手人日汇总记录
     *
     * @param dto 调动经手人日汇总记录dto
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerDaySumBO> list(TransferHandlerDaySumDTO dto) throws SerException {
        return transferHandlerDaySumSer.list(dto);
    }

    /**
     * 保存调动经手人日汇总记录
     *
     * @param to 调动经手人日汇总记录to
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    @Override
    public TransferHandlerDaySumBO save(TransferHandlerDaySumTO to) throws SerException {
        return transferHandlerDaySumSer.save(to);
    }

    /**
     * 根据id删除调动经手人日汇总记录
     *
     * @param id 调动经手人日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        transferHandlerDaySumSer.remove(id);
    }

    /**
     * 更新调动经手人日汇总记录
     *
     * @param to 调动经手人日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(TransferHandlerDaySumTO to) throws SerException {
        transferHandlerDaySumSer.update(to);
    }

    /**
     * 调动经手人日汇总
     *
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    @Override
    public List<TransferHandlerDaySumBO> summary() throws SerException {
        return transferHandlerDaySumSer.summary();
    }

}
package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.TransferTypeDaySumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeDaySumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeDaySum;
import com.bjike.goddess.materialsummary.service.TransferTypeDaySumSer;
import com.bjike.goddess.materialsummary.to.TransferTypeDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调动类型日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:40 ]
 * @Description: [ 调动类型日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferTypeDaySumApiImpl")
public class TransferTypeDaySumApiImpl implements TransferTypeDaySumAPI {

    @Autowired
    private TransferTypeDaySumSer transferTypeDaySumSer;

    /**
     * 根据id查询调动类型日汇总记录
     *
     * @param id 调动类型日汇总记录唯一标识
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    @Override
    public TransferTypeDaySumBO findById(String id) throws SerException {
        TransferTypeDaySum model = transferTypeDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, TransferTypeDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 调动类型日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(TransferTypeDaySumDTO dto) throws SerException {
        return transferTypeDaySumSer.count(dto);
    }

    /**
     * 分页查询调动类型日汇总记录
     *
     * @param dto 调动类型日汇总记录dto
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeDaySumBO> list(TransferTypeDaySumDTO dto) throws SerException {
        return transferTypeDaySumSer.list(dto);
    }

    /**
     * 保存调动类型日汇总记录
     *
     * @param to 调动类型日汇总记录to
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    @Override
    public TransferTypeDaySumBO save(TransferTypeDaySumTO to) throws SerException {
        return transferTypeDaySumSer.save(to);
    }

    /**
     * 根据id删除调动类型日汇总记录
     *
     * @param id 调动类型日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        transferTypeDaySumSer.remove(id);
    }

    /**
     * 更新调动类型日汇总记录
     *
     * @param to 调动类型日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(TransferTypeDaySumTO to) throws SerException {
        transferTypeDaySumSer.update(to);
    }

    /**
     * 调动类型日汇总
     *
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    @Override
    public List<TransferTypeDaySumBO> summary() throws SerException {
        return transferTypeDaySumSer.summary();
    }

}
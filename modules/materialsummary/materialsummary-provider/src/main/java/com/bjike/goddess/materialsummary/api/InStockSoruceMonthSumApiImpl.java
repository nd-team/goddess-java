package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockSoruceMonthSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceMonthSum;
import com.bjike.goddess.materialsummary.service.InStockSoruceMonthSumSer;
import com.bjike.goddess.materialsummary.to.InStockSoruceMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库来源月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:06 ]
 * @Description: [ 入库来源月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inStockSoruceMonthSumApiImpl")
public class InStockSoruceMonthSumApiImpl implements InStockSoruceMonthSumAPI {

    @Autowired
    private InStockSoruceMonthSumSer inStockSoruceMonthSumSer;

    /**
     * 根据id查询入库来源月汇总记录
     *
     * @param id 入库来源月汇总记录唯一标识
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    @Override
    public InStockSoruceMonthSumBO findById(String id) throws SerException {
        InStockSoruceMonthSum model = inStockSoruceMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, InStockSoruceMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库来源月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(InStockSoruceMonthSumDTO dto) throws SerException {
        return inStockSoruceMonthSumSer.count(dto);
    }

    /**
     * 分页查询入库来源月汇总记录
     *
     * @param dto 入库来源月汇总记录dto
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceMonthSumBO> list(InStockSoruceMonthSumDTO dto) throws SerException {
        return inStockSoruceMonthSumSer.list(dto);
    }

    /**
     * 保存入库来源月汇总记录
     *
     * @param to 入库来源月汇总记录to
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    @Override
    public InStockSoruceMonthSumBO save(InStockSoruceMonthSumTO to) throws SerException {
        return inStockSoruceMonthSumSer.save(to);
    }

    /**
     * 根据id删除入库来源月汇总记录
     *
     * @param id 入库来源月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        inStockSoruceMonthSumSer.remove(id);
    }

    /**
     * 更新入库来源月汇总记录
     *
     * @param to 入库来源月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(InStockSoruceMonthSumTO to) throws SerException {
        inStockSoruceMonthSumSer.update(to);
    }

    /**
     * 入库来源月汇总
     *
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceMonthSumBO> summary() throws SerException {
        return inStockSoruceMonthSumSer.summary();
    }

}
package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockSoruceDaySumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceDaySumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceDaySum;
import com.bjike.goddess.materialsummary.service.InStockSoruceDaySumSer;
import com.bjike.goddess.materialsummary.to.InStockSoruceDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库来源日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:04 ]
 * @Description: [ 入库来源日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inStockSoruceDaySumApiImpl")
public class InStockSoruceDaySumApiImpl implements InStockSoruceDaySumAPI {

    @Autowired
    private InStockSoruceDaySumSer inStockSoruceDaySumSer;

    /**
     * 根据id查询入库来源日汇总记录
     *
     * @param id 入库来源日汇总记录唯一标识
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    @Override
    public InStockSoruceDaySumBO findById(String id) throws SerException {
        InStockSoruceDaySum model = inStockSoruceDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, InStockSoruceDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库来源日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(InStockSoruceDaySumDTO dto) throws SerException {
        return inStockSoruceDaySumSer.count(dto);
    }

    /**
     * 分页查询入库来源日汇总记录
     *
     * @param dto 入库来源日汇总记录dto
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceDaySumBO> list(InStockSoruceDaySumDTO dto) throws SerException {
        return inStockSoruceDaySumSer.list(dto);
    }

    /**
     * 保存入库来源日汇总记录
     *
     * @param to 入库来源日汇总记录to
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    @Override
    public InStockSoruceDaySumBO save(InStockSoruceDaySumTO to) throws SerException {
        return inStockSoruceDaySumSer.save(to);
    }

    /**
     * 根据id删除入库来源日汇总记录
     *
     * @param id 入库来源日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        inStockSoruceDaySumSer.remove(id);
    }

    /**
     * 更新入库来源日汇总记录
     *
     * @param to 入库来源日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(InStockSoruceDaySumTO to) throws SerException {
        inStockSoruceDaySumSer.update(to);
    }

    /**
     * 入库来源日汇总
     *
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceDaySumBO> summary() throws SerException {
        return inStockSoruceDaySumSer.summary();
    }

}
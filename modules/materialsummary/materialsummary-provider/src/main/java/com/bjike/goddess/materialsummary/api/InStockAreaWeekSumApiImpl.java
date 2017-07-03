package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockAreaWeekSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaWeekSum;
import com.bjike.goddess.materialsummary.service.InStockAreaWeekSumSer;
import com.bjike.goddess.materialsummary.to.InStockAreaWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库地区周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:10 ]
 * @Description: [ 入库地区周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inStockAreaWeekSumApiImpl")
public class InStockAreaWeekSumApiImpl implements InStockAreaWeekSumAPI {

    @Autowired
    private InStockAreaWeekSumSer inStockAreaWeekSumSer;

    /**
     * 根据id查询入库地区周汇总记录
     *
     * @param id 入库地区周汇总记录唯一标识
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    @Override
    public InStockAreaWeekSumBO findById(String id) throws SerException {
        InStockAreaWeekSum model = inStockAreaWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, InStockAreaWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库地区周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(InStockAreaWeekSumDTO dto) throws SerException {
        return inStockAreaWeekSumSer.count(dto);
    }

    /**
     * 分页查询入库地区周汇总记录
     *
     * @param dto 入库地区周汇总记录dto
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaWeekSumBO> list(InStockAreaWeekSumDTO dto) throws SerException {
        return inStockAreaWeekSumSer.list(dto);
    }

    /**
     * 保存入库地区周汇总记录
     *
     * @param to 入库地区周汇总记录to
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    @Override
    public InStockAreaWeekSumBO save(InStockAreaWeekSumTO to) throws SerException {
        return inStockAreaWeekSumSer.save(to);
    }

    /**
     * 根据id删除入库地区周汇总记录
     *
     * @param id 入库地区周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        inStockAreaWeekSumSer.remove(id);
    }

    /**
     * 更新入库地区周汇总记录
     *
     * @param to 入库地区周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(InStockAreaWeekSumTO to) throws SerException {
        inStockAreaWeekSumSer.update(to);
    }

    /**
     * 入库地区周汇总
     *
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaWeekSumBO> summary() throws SerException {
        return inStockAreaWeekSumSer.summary();
    }

}
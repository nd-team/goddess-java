package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockAreaMonthSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaMonthSum;
import com.bjike.goddess.materialsummary.service.InStockAreaMonthSumSer;
import com.bjike.goddess.materialsummary.to.InStockAreaMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库地区月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:11 ]
 * @Description: [ 入库地区月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inStockAreaMonthSumApiImpl")
public class InStockAreaMonthSumApiImpl implements InStockAreaMonthSumAPI {

    @Autowired
    private InStockAreaMonthSumSer inStockAreaDaySumSer;

    /**
     * 根据id查询入库地区月汇总记录
     *
     * @param id 入库地区月汇总记录唯一标识
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    @Override
    public InStockAreaMonthSumBO findById(String id) throws SerException {
        InStockAreaMonthSum model = inStockAreaDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, InStockAreaMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库地区月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(InStockAreaMonthSumDTO dto) throws SerException {
        return inStockAreaDaySumSer.count(dto);
    }

    /**
     * 分页查询入库地区月汇总记录
     *
     * @param dto 入库地区月汇总记录dto
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaMonthSumBO> list(InStockAreaMonthSumDTO dto) throws SerException {
        return inStockAreaDaySumSer.list(dto);
    }

    /**
     * 保存入库地区月汇总记录
     *
     * @param to 入库地区月汇总记录to
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    @Override
    public InStockAreaMonthSumBO save(InStockAreaMonthSumTO to) throws SerException {
        return inStockAreaDaySumSer.save(to);
    }

    /**
     * 根据id删除入库地区月汇总记录
     *
     * @param id 入库地区月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        inStockAreaDaySumSer.remove(id);
    }

    /**
     * 更新入库地区月汇总记录
     *
     * @param to 入库地区月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(InStockAreaMonthSumTO to) throws SerException {
        inStockAreaDaySumSer.update(to);
    }

    /**
     * 入库地区月汇总
     *
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaMonthSumBO> summary() throws SerException {
        return inStockAreaDaySumSer.summary();
    }

}
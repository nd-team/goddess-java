package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockAreaDaySumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaDaySumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaDaySum;
import com.bjike.goddess.materialsummary.service.InStockAreaDaySumSer;
import com.bjike.goddess.materialsummary.to.InStockAreaDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库地区日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:13 ]
 * @Description: [ 入库地区日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inStockAreaDaySumApiImpl")
public class InStockAreaDaySumApiImpl implements InStockAreaDaySumAPI {

    @Autowired
    private InStockAreaDaySumSer inStockAreaDaySumSer;

    /**
     * 根据id查询入库地区日汇总记录
     *
     * @param id 入库地区日汇总记录唯一标识
     * @return class InStockAreaDaySumBO
     * @throws SerException
     */
    @Override
    public InStockAreaDaySumBO findById(String id) throws SerException {
        InStockAreaDaySum model = inStockAreaDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, InStockAreaDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库地区日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(InStockAreaDaySumDTO dto) throws SerException {
        return inStockAreaDaySumSer.count(dto);
    }

    /**
     * 分页查询入库地区日汇总记录
     *
     * @param dto 入库地区日汇总记录dto
     * @return class InStockAreaDaySumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaDaySumBO> list(InStockAreaDaySumDTO dto) throws SerException {
        return inStockAreaDaySumSer.list(dto);
    }

    /**
     * 保存入库地区日汇总记录
     *
     * @param to 入库地区日汇总记录to
     * @return class InStockAreaDaySumBO
     * @throws SerException
     */
    @Override
    public InStockAreaDaySumBO save(InStockAreaDaySumTO to) throws SerException {
        return inStockAreaDaySumSer.save(to);
    }

    /**
     * 根据id删除入库地区日汇总记录
     *
     * @param id 入库地区日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        inStockAreaDaySumSer.remove(id);
    }

    /**
     * 更新入库地区日汇总记录
     *
     * @param to 入库地区日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(InStockAreaDaySumTO to) throws SerException {
        inStockAreaDaySumSer.update(to);
    }

    /**
     * 入库地区日汇总
     *
     * @return class InStockAreaDaySumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaDaySumBO> summary() throws SerException {
        return inStockAreaDaySumSer.summary();
    }

}
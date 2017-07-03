package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockAreaYearSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaYearSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaYearSum;
import com.bjike.goddess.materialsummary.service.InStockAreaYearSumSer;
import com.bjike.goddess.materialsummary.to.InStockAreaYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库地区年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:11 ]
 * @Description: [ 入库地区年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inStockAreaYearSumApiImpl")
public class InStockAreaYearSumApiImpl implements InStockAreaYearSumAPI {

    @Autowired
    private InStockAreaYearSumSer inStockAreaYearSumSer;

    /**
     * 根据id查询入库地区年汇总记录
     *
     * @param id 入库地区年汇总记录唯一标识
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    @Override
    public InStockAreaYearSumBO findById(String id) throws SerException {
        InStockAreaYearSum model = inStockAreaYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, InStockAreaYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库地区年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(InStockAreaYearSumDTO dto) throws SerException {
        return inStockAreaYearSumSer.count(dto);
    }

    /**
     * 分页查询入库地区年汇总记录
     *
     * @param dto 入库地区年汇总记录dto
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaYearSumBO> list(InStockAreaYearSumDTO dto) throws SerException {
        return inStockAreaYearSumSer.list(dto);
    }

    /**
     * 保存入库地区年汇总记录
     *
     * @param to 入库地区年汇总记录to
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    @Override
    public InStockAreaYearSumBO save(InStockAreaYearSumTO to) throws SerException {
        return inStockAreaYearSumSer.save(to);
    }

    /**
     * 根据id删除入库地区年汇总记录
     *
     * @param id 入库地区年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        inStockAreaYearSumSer.remove(id);
    }

    /**
     * 更新入库地区年汇总记录
     *
     * @param to 入库地区年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(InStockAreaYearSumTO to) throws SerException {
        inStockAreaYearSumSer.update(to);
    }

    /**
     * 入库地区年汇总
     *
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    @Override
    public List<InStockAreaYearSumBO> summary() throws SerException {
        return inStockAreaYearSumSer.summary();
    }

}
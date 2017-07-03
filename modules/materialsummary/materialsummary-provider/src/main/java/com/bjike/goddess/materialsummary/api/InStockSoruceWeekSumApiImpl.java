package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockSoruceWeekSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceWeekSum;
import com.bjike.goddess.materialsummary.service.InStockSoruceWeekSumSer;
import com.bjike.goddess.materialsummary.to.InStockSoruceWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库来源周汇总;记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:05 ]
 * @Description: [ 入库来源周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inStockSoruceWeekSumApiImpl")
public class InStockSoruceWeekSumApiImpl implements InStockSoruceWeekSumAPI {

    @Autowired
    private InStockSoruceWeekSumSer inStockSoruceWeekSumSer;

    /**
     * 根据id查询入库来源周汇总记录
     *
     * @param id 入库来源周汇总记录唯一标识
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    @Override
    public InStockSoruceWeekSumBO findById(String id) throws SerException {
        InStockSoruceWeekSum model = inStockSoruceWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, InStockSoruceWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库来源周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(InStockSoruceWeekSumDTO dto) throws SerException {
        return inStockSoruceWeekSumSer.count(dto);
    }

    /**
     * 分页查询入库来源周汇总记录
     *
     * @param dto 入库来源周汇总记录dto
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceWeekSumBO> list(InStockSoruceWeekSumDTO dto) throws SerException {
        return inStockSoruceWeekSumSer.list(dto);
    }

    /**
     * 保存入库来源周汇总记录
     *
     * @param to 入库来源周汇总记录to
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    @Override
    public InStockSoruceWeekSumBO save(InStockSoruceWeekSumTO to) throws SerException {
        return inStockSoruceWeekSumSer.save(to);
    }

    /**
     * 根据id删除入库来源周汇总记录
     *
     * @param id 入库来源周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        inStockSoruceWeekSumSer.remove(id);
    }

    /**
     * 更新入库来源周汇总记录
     *
     * @param to 入库来源周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(InStockSoruceWeekSumTO to) throws SerException {
        inStockSoruceWeekSumSer.update(to);
    }

    /**
     * 入库来源周汇总
     *
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceWeekSumBO> summary() throws SerException {
        return inStockSoruceWeekSumSer.summary();
    }

}
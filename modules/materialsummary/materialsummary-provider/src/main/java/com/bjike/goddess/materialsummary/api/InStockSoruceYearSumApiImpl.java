package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.InStockSoruceYearSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceYearSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceYearSum;
import com.bjike.goddess.materialsummary.service.InStockSoruceYearSumSer;
import com.bjike.goddess.materialsummary.to.InStockSoruceYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库来源年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:07 ]
 * @Description: [ 入库来源年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inStockSoruceYearSumApiImpl")
public class InStockSoruceYearSumApiImpl implements InStockSoruceYearSumAPI {

    @Autowired
    private InStockSoruceYearSumSer inStockSoruceYearSumSer;

    /**
     * 根据id查询入库来源年汇总记录
     *
     * @param id 入库来源年汇总记录唯一标识
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    @Override
    public InStockSoruceYearSumBO findById(String id) throws SerException {
        InStockSoruceYearSum model = inStockSoruceYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, InStockSoruceYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库来源年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(InStockSoruceYearSumDTO dto) throws SerException {
        return inStockSoruceYearSumSer.count(dto);
    }

    /**
     * 分页查询入库来源年汇总记录
     *
     * @param dto 入库来源年汇总记录dto
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceYearSumBO> list(InStockSoruceYearSumDTO dto) throws SerException {
        return inStockSoruceYearSumSer.list(dto);
    }

    /**
     * 保存入库来源年汇总记录
     *
     * @param to 入库来源年汇总记录to
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    @Override
    public InStockSoruceYearSumBO save(InStockSoruceYearSumTO to) throws SerException {
        return inStockSoruceYearSumSer.save(to);
    }

    /**
     * 根据id删除入库来源年汇总记录
     *
     * @param id 入库来源年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        inStockSoruceYearSumSer.remove(id);
    }

    /**
     * 更新入库来源年汇总记录
     *
     * @param to 入库来源年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(InStockSoruceYearSumTO to) throws SerException {
        inStockSoruceYearSumSer.update(to);
    }

    /**
     * 入库来源年汇总
     *
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    @Override
    public List<InStockSoruceYearSumBO> summary() throws SerException {
        return inStockSoruceYearSumSer.summary();
    }

}
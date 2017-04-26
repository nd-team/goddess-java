package com.bjike.goddess.materialinstock.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.bo.StockWarningBO;
import com.bjike.goddess.materialinstock.dto.StockWarningDTO;
import com.bjike.goddess.materialinstock.entity.StockWarning;
import com.bjike.goddess.materialinstock.service.StockWarningSer;
import com.bjike.goddess.materialinstock.to.StockWarningTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存预警业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 05:00 ]
 * @Description: [ 库存预警业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("stockWarningApiImpl")
public class StockWarningApiImpl implements StockWarningAPI {

    @Autowired
    private StockWarningSer stockWarningSer;

    /**
     * 根据id查询库存预警
     *
     * @param id 库存预警唯一标识
     * @return class StockWarningBO
     * @throws SerException
     */
    @Override
    public StockWarningBO findById(String id) throws SerException {
        StockWarning model = stockWarningSer.findById(id);
        return BeanTransform.copyProperties(model, StockWarningBO.class);
    }

    /**
     * 分页查询库存预警
     *
     * @return class StockWarningBO
     * @throws SerException
     */
    @Override
    public List<StockWarningBO> list(StockWarningDTO dto) throws SerException {
        return stockWarningSer.list(dto);
    }

    /**
     * 保存库存预警
     *
     * @param to 库存预警to
     * @return class StockWarningBO
     * @throws SerException
     */
    @Override
    public StockWarningBO save(StockWarningTO to) throws SerException {
        return stockWarningSer.save(to);
    }

    /**
     * 根据id删除库存预警
     *
     * @param id 库存预警唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        stockWarningSer.remove(id);
    }

    /**
     * 更新库存预警
     *
     * @param to 库存预警to
     * @throws SerException
     */
    @Override
    public void update(StockWarningTO to) throws SerException {
        stockWarningSer.update(to);
    }
}
package com.bjike.goddess.coststandard.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.coststandard.bo.CostStandardContrastBO;
import com.bjike.goddess.coststandard.dto.CostStandardContrastDTO;
import com.bjike.goddess.coststandard.entity.CostStandardContrast;
import com.bjike.goddess.coststandard.to.CostStandardContrastTO;

import java.util.List;

/**
 * 费用标准对比业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:15 ]
 * @Description: [ 费用标准对比业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostStandardContrastSer extends Ser<CostStandardContrast, CostStandardContrastDTO> {

    /**
     * 保存
     *
     * @param to 费用标准对比传输对象
     * @return
     * @throws SerException
     */
    default CostStandardContrastBO save(CostStandardContrastTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 费用标准对比传输对象
     * @return
     * @throws SerException
     */
    default CostStandardContrastBO update(CostStandardContrastTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 费用标准对比数据id
     * @return
     * @throws SerException
     */
    default CostStandardContrastBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取费用标准对比数据
     *
     * @param id 费用标准对比数据id
     * @return
     * @throws SerException
     */
    default CostStandardContrastBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 费用标准对比数据传输对象
     * @return
     * @throws SerException
     */
    default List<CostStandardContrastBO> maps(CostStandardContrastDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

}
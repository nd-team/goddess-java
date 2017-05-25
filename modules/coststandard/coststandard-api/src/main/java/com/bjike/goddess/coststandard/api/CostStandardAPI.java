package com.bjike.goddess.coststandard.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.coststandard.bo.CostStandardBO;
import com.bjike.goddess.coststandard.bo.CostStandardContrastBO;
import com.bjike.goddess.coststandard.bo.CostStandardOpinionBO;
import com.bjike.goddess.coststandard.dto.CostStandardContrastDTO;
import com.bjike.goddess.coststandard.dto.CostStandardDTO;
import com.bjike.goddess.coststandard.to.CostStandardContrastTO;
import com.bjike.goddess.coststandard.to.CostStandardTO;

import java.util.List;

/**
 * 费用标准业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:22 ]
 * @Description: [ 费用标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostStandardAPI {


    /**
     * 保存
     *
     * @param to 费用标准传输对象
     * @return
     * @throws SerException
     */
    default CostStandardBO save(CostStandardTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 费用标准传输对象
     * @return
     * @throws SerException
     */
    default CostStandardBO update(CostStandardTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 费用标准数据id
     * @return
     * @throws SerException
     */
    default CostStandardBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 费用标准数据id
     * @return
     * @throws SerException
     */
    default CostStandardBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 费用标准数据id
     * @return
     * @throws SerException
     */
    default CostStandardBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取费用标准数据
     *
     * @param id 费用标准数据id
     * @return
     * @throws SerException
     */
    default CostStandardBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 费用标准数据传输对象
     * @return
     * @throws SerException
     */
    default List<CostStandardBO> maps(CostStandardDTO dto) throws SerException {
        return null;
    }

    /**
     * 查询未冻结的费用标准数据
     *
     * @return
     * @throws SerException
     */
    default List<CostStandardBO> findThaw() throws SerException {
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


    /**
     * 获取标准选项
     *
     * @return
     * @throws SerException
     */
    default List<CostStandardOpinionBO> findOpinion() throws SerException {
        return null;
    }
}
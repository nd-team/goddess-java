package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectcost.bo.OtherExpensesBO;
import com.bjike.goddess.projectcost.dto.OtherExpensesDTO;
import com.bjike.goddess.projectcost.entity.OtherExpenses;
import com.bjike.goddess.projectcost.to.OtherExpensesTO;

import java.util.List;

/**
 * 其他费用业务接口
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:02 ]
 * @Description: [ 其他费用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OtherExpensesSer extends Ser<OtherExpenses, OtherExpensesDTO> {

    /**
     * 保存
     *
     * @param to 其他费用传输对象
     * @return
     * @throws SerException
     */
    default OtherExpensesBO save(OtherExpensesTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 其他费用传输对象
     * @return
     * @throws SerException
     */
    default OtherExpensesBO update(OtherExpensesTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 其他费用数据id
     * @return
     * @throws SerException
     */
    default OtherExpensesBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 其他费用数据传输对象
     * @return
     * @throws SerException
     */
    default List<OtherExpensesBO> maps(OtherExpensesDTO dto) throws SerException {
        return null;
    }

    /**
     * 修改实际车次
     *
     * @param to 其他费用传输对象
     * @return
     * @throws SerException
     */
    default OtherExpensesBO updateActual(OtherExpensesTO to) throws SerException {
        return null;
    }

    /**
     * 根据id查询数据
     *
     * @param id 其他费用数据id
     * @return
     * @throws SerException
     */
    default OtherExpensesBO getById(String id) throws SerException {
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
package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.FinanceInfoBO;
import com.bjike.goddess.qualifications.dto.FinanceInfoDTO;
import com.bjike.goddess.qualifications.to.FinanceInfoTO;

import java.util.List;

/**
 * 财务资料业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:42 ]
 * @Description: [ 财务资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FinanceInfoAPI {

    /**
     * 保存
     *
     * @param to 财务资料传输对象
     * @return
     * @throws SerException
     */
    default FinanceInfoBO save(FinanceInfoTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 财务资料传输对象
     * @return
     * @throws SerException
     */
    default FinanceInfoBO update(FinanceInfoTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 财务资料id
     * @return
     * @throws SerException
     */
    default FinanceInfoBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 获取全部
     *
     * @return
     * @throws SerException
     */
    default List<FinanceInfoBO> all() throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 财务资料数据传输对象
     * @return
     * @throws SerException
     */
    default List<FinanceInfoBO> maps(FinanceInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Integer getTotal() throws SerException {
        return null;
    }

    /**
     * 根据id获取数据
     *
     * @param id 数据id
     * @return
     * @throws SerException
     */
    default FinanceInfoBO getById(String id) throws SerException {
        return null;
    }
}
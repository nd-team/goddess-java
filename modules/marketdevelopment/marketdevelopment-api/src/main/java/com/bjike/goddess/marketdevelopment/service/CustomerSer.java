package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.CustomerBO;
import com.bjike.goddess.marketdevelopment.dto.CustomerDTO;
import com.bjike.goddess.marketdevelopment.entity.Customer;
import com.bjike.goddess.marketdevelopment.excel.CustomerImportExcel;
import com.bjike.goddess.marketdevelopment.to.CustomerTO;

import java.util.List;

/**
* 客户接触阶段业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-29 09:37 ]
* @Description:	[ 客户接触阶段业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CustomerSer extends Ser<Customer, CustomerDTO> {

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CustomerBO> maps(CustomerDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(CustomerTO to) throws SerException {
        return;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void update(CustomerTO to) throws SerException {
        return;
    }

    /**
     * 冻结
     *
     * @param to
     * @throws SerException
     */
    default void congeal(CustomerTO to) throws SerException {
        return;
    }

    /**
     * 解冻
     *
     * @param to
     * @throws SerException
     */
    default void thaw(CustomerTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param to
     * @throws SerException
     */
    default void delete(CustomerTO to) throws SerException {
        return;
    }

    /**
     * 根据id获取客户接触阶段
     *
     * @param id
     * @return
     * @throws SerException
     */
    default CustomerBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(CustomerDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出导入的excel模板
     *
     * @return
     * @throws SerException
     */
    default byte[] templateExcel() throws SerException {
        return null;
    }

    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(CustomerDTO dto) throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param tos
     * @throws SerException
     */
    default void upload(List<CustomerImportExcel> tos) throws SerException {
        return;
    }
}
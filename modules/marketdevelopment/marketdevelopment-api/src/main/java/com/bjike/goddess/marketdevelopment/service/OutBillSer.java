package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.OutBillBO;
import com.bjike.goddess.marketdevelopment.dto.OutBillDTO;
import com.bjike.goddess.marketdevelopment.entity.OutBill;
import com.bjike.goddess.marketdevelopment.to.OutBillTO;

import java.util.List;

/**
* 外出单业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-30 11:12 ]
* @Description:	[ 外出单业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OutBillSer extends Ser<OutBill, OutBillDTO> {

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<OutBillBO> maps(OutBillDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(OutBillTO to) throws SerException {
        return;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void update(OutBillTO to) throws SerException {
        return;
    }

    /**
     * 冻结
     *
     * @param to
     * @throws SerException
     */
    default void congeal(OutBillTO to) throws SerException {
        return;
    }

    /**
     * 解冻
     *
     * @param to
     * @throws SerException
     */
    default void thaw(OutBillTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param to
     * @throws SerException
     */
    default void delete(OutBillTO to) throws SerException {
        return;
    }

    /**
     * 根据id获取客户接触阶段
     *
     * @param id
     * @return
     * @throws SerException
     */
    default OutBillBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(OutBillDTO dto) throws SerException {
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
    default byte[] exportExcel(OutBillDTO dto) throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param tos
     * @throws SerException
     */
    default void upload(List<OutBillImportExcel> tos) throws SerException {
        return;
    }
}
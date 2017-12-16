package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.BusinessBO;
import com.bjike.goddess.marketdevelopment.bo.BusinessReturnBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessDTO;
import com.bjike.goddess.marketdevelopment.excel.BusinessImportExcel;
import com.bjike.goddess.marketdevelopment.to.BusinessTO;

import java.util.List;

/**
 * 业务对象业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessAPI {

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<BusinessBO> maps(BusinessDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(BusinessTO to) throws SerException {
        return;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void update(BusinessTO to) throws SerException {
        return;
    }

    /**
     * 冻结业务对象数据
     *
     * @param to
     * @throws SerException
     */
    default void congeal(BusinessTO to) throws SerException {
        return;
    }

    /**
     * 解冻业务对象数据
     *
     * @param to
     * @throws SerException
     */
    default void thaw(BusinessTO to) throws SerException {
        return;
    }

    /**
     * 删除业务对象数据
     *
     * @param to
     * @throws SerException
     */
    default void delete(BusinessTO to) throws SerException {
        return;
    }

    /**
     * 根据id获取业务对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default BusinessReturnBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(BusinessDTO dto) throws SerException {
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
     * 导入
     *
     * @param tos
     * @throws SerException
     */
    default void upload(List<BusinessImportExcel> tos) throws SerException {
        return;
    }

    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(BusinessDTO dto) throws SerException {
        return null;
    }
}
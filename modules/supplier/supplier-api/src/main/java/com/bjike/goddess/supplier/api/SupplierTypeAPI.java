package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.SupplierTypeBO;
import com.bjike.goddess.supplier.to.SupplierTypeTO;

import java.util.List;

/**
 * 供应商类型管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T14:12:54.984 ]
 * @Description: [ 供应商类型管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupplierTypeAPI {

    /**
     * 查询未冻结的供应商类型
     *
     * @return
     * @throws SerException
     */
    default List<SupplierTypeBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 保存供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return
     * @throws SerException
     */
    default SupplierTypeBO save(SupplierTypeTO to) throws SerException {
        return null;
    }

    /**
     * 修改供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return
     * @throws SerException
     */
    default SupplierTypeBO update(SupplierTypeTO to) throws SerException {
        return null;
    }

    /**
     * 删除供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return
     * @throws SerException
     */
    default SupplierTypeBO delete(SupplierTypeTO to) throws SerException {
        return null;
    }

    /**
     * 冻结供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return
     * @throws SerException
     */
    default SupplierTypeBO congeal(SupplierTypeTO to) throws SerException {
        return null;
    }

    /**
     * 解冻供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return
     * @throws SerException
     */
    default SupplierTypeBO thaw(SupplierTypeTO to) throws SerException {
        return null;
    }

}
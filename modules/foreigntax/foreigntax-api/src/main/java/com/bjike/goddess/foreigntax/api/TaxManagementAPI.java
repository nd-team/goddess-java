package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.bo.VoucherDataBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import com.bjike.goddess.foreigntax.to.VoucherDataTO;

import java.util.List;
import java.util.Map;

/**
 * 税金管理业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TaxManagementAPI {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 税金管理列表总条数
     */
    default Long count(TaxManagementDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个税金管理
     *
     * @return class TaxManagementBO
     */
    default TaxManagementBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 税金管理
     *
     * @param dto 税金管理dto
     * @return class TaxManagementBO
     * @throws SerException
     */
    default List<TaxManagementBO> list(TaxManagementDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加税金管理
     *
     * @param to 税金管理数据to
     * @return class TaxManagementBO
     * @throws SerException
     */
    default TaxManagementBO insert(TaxManagementTO to) throws SerException {
        return null;
    }

    /**
     * 编辑税金管理
     *
     * @param to 税金管理数据to
     * @return class TaxManagementBO
     * @throws SerException
     */
    default TaxManagementBO edit(TaxManagementTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除税金管理
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 生成记账凭证
     *
     * @param ids
     * @return class VoucherDataBO
     * @throws SerException
     */
    default VoucherDataBO vGenerate(String[] ids) throws SerException {
        return null;
    }
    /**
     * 提交记账凭证
     *
     * @param to
     * @return class VoucherDataBO
     * @throws SerException
     */
    default VoucherDataBO generate(VoucherDataTO to) throws SerException {
        return null;
    }
    /**
     * 根据税款所属期止获得申报期限
     *
     * @param taxEnd
     * @throws SerException
     */
    default Map<String, String> getDead(String taxEnd) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @param dto
     * @return class
     * @throws SerException
     */
    default List<TaxManagementBO> collect(TaxManagementDTO dto) throws SerException {
        return null;

    }

    /**
     * 获取所有公司
     *
     * @throws SerException
     */
    default List<String> getCompany() throws SerException {
        return null;
    }

    /**
     * 获取所有税种
     *
     * @throws SerException
     */
    default List<String> getTaxType() throws SerException {
        return null;
    }


    /**
     * 根据公司和时间查询
     *
     * @return class TaxManagementBO
     */
    default List<TaxManagementBO> listByCompany(String company, String monthStart, String monthEnd) throws SerException {
        return null;

    }

}
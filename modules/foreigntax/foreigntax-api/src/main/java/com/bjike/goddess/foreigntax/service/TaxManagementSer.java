package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.foreigntax.bo.TaxCollectBO;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.entity.TaxManagement;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.to.CollectTo;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;

import java.util.List;

/**
 * 税金管理业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TaxManagementSer extends Ser<TaxManagement, TaxManagementDTO> {
    /**
     * 税金管理列表总条数
     */
    default Long countTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
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
     * @param taxManagementDTO 税金管理dto
     * @return class TaxManagementBO
     * @throws SerException
     */
    default List<TaxManagementBO> findListTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        return null;
    }

    /**
     * 添加税金管理
     *
     * @param taxManagementTO 税金管理数据to
     * @return class TaxManagementBO
     * @throws SerException
     */
    default TaxManagementBO insertTaxManagement(TaxManagementTO taxManagementTO) throws SerException {
        return null;
    }

    /**
     * 编辑税金管理
     *
     * @param taxManagementTO 税金管理数据to
     * @return class TaxManagementBO
     * @throws SerException
     */
    default TaxManagementBO editTaxManagement(TaxManagementTO taxManagementTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除税金管理
     *
     * @param id
     * @throws SerException
     */
    default void removeTaxManagement(String id) throws SerException {

    }
    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }
    /**
     * 查看功能
     * @return class TaxManagementBO
     */
    default List<TaxManagementBO> viewTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        return null;

    }
    /**
     * 汇总功能
     * @return class TaxCollectBO
     */
    default List<TaxCollectBO> collectTaxManagement(String []  company) throws SerException {
        return null;

    }
    /**
     * 获取公司
     *
     * @return class String
     */
    default List<String> getCompany() throws SerException {
        return null;
    }

    /**
     * 根据公司和时间查询
     * @return class TaxManagementBO
     */
    default List<TaxManagementBO> listByCompany(String company,String monthStart,String monthEnd) throws SerException {
        return null;

    }

}
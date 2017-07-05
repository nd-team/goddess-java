package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.excel.SonPermissionObject;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;

import java.util.List;

/**
 * 合同单价资料信息业务接口
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.316 ]
 * @Description: [ 合同单价资料信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractQuoteDataSer extends Ser<ContractQuoteData, ContractQuoteDataDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 添加合同单价资料信息
     *
     * @param to 合同单价资料信息to
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    default ContractQuoteDataBO save(ContractQuoteDataTO to) throws SerException {
        return null;
    }

    /**
     * 分页查询合同单价资料信息
     *
     * @param dto 合同单价资料信息dto
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    default List<ContractQuoteDataBO> list(ContractQuoteDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 编辑合同单价资料信息
     *
     * @param to 合同单价资料信息to
     * @throws SerException
     */
    default void update(ContractQuoteDataTO to) throws SerException {

    }

    /**
     * 根据id删除合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 冻结合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    default void congealStatus(String id) throws SerException {

    }

    /**
     * 解冻合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    default void thawStatus(String id) throws SerException {

    }

    /**
     * 汇总合同单价资料信息
     *
     * @param dto 合同单价资料信息dto
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    default List<ContractQuoteDataBO> collect(ContractQuoteDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据地区和项目组查询合同单价资料信息
     *
     * @param area 地区
     * @param project 项目组
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    default List<ContractQuoteDataBO> searchs(String area, String project) throws SerException {
        return null;
    }

}
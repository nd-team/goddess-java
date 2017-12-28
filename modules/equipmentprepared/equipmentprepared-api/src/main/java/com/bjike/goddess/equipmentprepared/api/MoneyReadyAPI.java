package com.bjike.goddess.equipmentprepared.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.equipmentprepared.bo.MoneyReadyBO;
import com.bjike.goddess.equipmentprepared.bo.MoneyReadyCountBO;
import com.bjike.goddess.equipmentprepared.dto.MoneyReadyDTO;
import com.bjike.goddess.equipmentprepared.to.GuidePermissionTO;
import com.bjike.goddess.equipmentprepared.to.MoneyReadyTO;
import com.bjike.goddess.equipmentprepared.vo.SonPermissionObject;

import java.util.List;

/**
 * 资金准备审核业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-16 11:15 ]
 * @Description: [ 资金准备审核业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyReadyAPI {
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
     * 添加
     *
     * @param to 资金准备审核信息
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default MoneyReadyBO save(MoneyReadyTO to) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param to 资金准备审核信息
     * @throws SerException
     */
    default void edit(MoneyReadyTO to) throws SerException {
    }

    /**
     * 删除
     *
     * @param id 资金准备审核信息id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 查找
     *
     * @param dto 资金准备审核分页信息
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default List<MoneyReadyBO> list(MoneyReadyDTO dto) throws SerException {
        return null;

    }

    /**
     * 通过id查找
     *
     * @param id 资金准备审核信息id
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default MoneyReadyBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 汇总对比
     *
     * @param month 汇总的月份
     * @return class MoneyReadyCountBO
     * @throws SerException
     */
    default List<MoneyReadyCountBO> count(Integer month) throws SerException {
        return null;
    }

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @return class Long
     * @throws SerException
     */
    default Long countSum(MoneyReadyDTO dto) throws SerException {
        return null;
    }
}
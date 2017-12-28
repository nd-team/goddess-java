package com.bjike.goddess.outcarfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.outcarfare.bo.MoneyReadyBO;
import com.bjike.goddess.outcarfare.bo.MoneyReadyCountBO;
import com.bjike.goddess.outcarfare.dto.MoneyReadyDTO;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.MoneyReadyTO;
import com.bjike.goddess.outcarfare.vo.SonPermissionObject;

import java.util.List;
import java.util.Set;

/**
 * 资金准备审核业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
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
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long countSum(MoneyReadyDTO dto) throws SerException;

    /**
     * 删除列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<MoneyReadyBO> delList(MoneyReadyDTO dto) throws SerException;

    /**
     * 删除列表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long delCount(MoneyReadyDTO dto) throws SerException;

    /**
     * 定时检测删除
     *
     * @throws SerException
     */
    void quartz() throws SerException;

    /**
     * 部门汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<MoneyReadyCountBO> departCount(MoneyReadyDTO dto) throws SerException;

    /**
     * 地区汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<MoneyReadyCountBO> areaCount(MoneyReadyDTO dto) throws SerException;

    /**
     * 撤销删除
     *
     * @param id
     * @throws SerException
     */
    void reback(String id) throws SerException;

    /**
     * 汇总明细
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<MoneyReadyBO> details(MoneyReadyDTO dto) throws SerException;

    /**
     * 所有部门
     *
     * @return
     * @throws SerException
     */
    Set<String> findAllGroupTeams() throws SerException;

    /**
     * 所有地区
     *
     * @return
     * @throws SerException
     */
    Set<String> areas() throws SerException;
}
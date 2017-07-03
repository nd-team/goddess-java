package com.bjike.goddess.account.api;

import com.bjike.goddess.account.bo.AccountCollectBO;
import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.excel.SonPermissionObject;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.account.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 明细账信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountInfoAPI {
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
     * 明细账信息列表总条数
     */
    default Long countAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        return null;
    }

    /**
     * 一个明细账信息
     *
     * @return class AccountInfoBO
     */
    default AccountInfoBO getOne(String id) throws SerException {
        return null;
    }
    /**
     * 明细账信息
     *
     * @param accountInfoDTO 明细账信息dto
     * @return class accountInfoBO
     * @throws SerException
     */
    default List<AccountInfoBO> findListAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加明细账信息
     *
     * @param accountInfoTO 明细账信息数据to
     * @throws SerException
     */
    default AccountInfoBO insertAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑明细账信息
     *
     * @param accountInfoTO 明细账信息数据to
     * @return class accountInfoBO
     * @throws SerException
     */
    default AccountInfoBO editAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除明细账信息
     *
     * @param id
     * @throws SerException
     */
    default void removeAccountInfo(String id) throws SerException {

    }
    /**
     * 科目汇总
     *
     * @return class AccountCollectBO
     */
    default List<AccountCollectBO> subCollect(AccountInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 地区汇总
     *
     * @return class AccountCollectBO
     */
    default List<AccountCollectBO> areaCollect(AccountInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目组汇总
     *
     * @return class AccountCollectBO
     */
    default List<AccountCollectBO> groupCollect(AccountInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目名称汇总
     *
     * @return class AccountCollectBO
     */
    default List<AccountCollectBO> nameCollect(AccountInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 地区
     *
     * @return class String
     */
    default List<String> getArea() throws SerException {
        return null;
    }
    /**
     * 项目名称
     *
     * @return class String
     */
    default List<String> getProjectName() throws SerException {
        return null;
    }
    /**
     * 项目组/部门
     *
     * @return class String
     */
    default List<String> getProjectGroup() throws SerException {
        return null;
    }
    /**
     * 查询所有一级科目
     *
     * @return
     * @throws SerException
     */
    default List<String> listFirstSubject() throws SerException {
        return null;
    }

    /**
     * 根据一级科目查询二级科目
     *
     * @param firstSub 一级科目
     * @return
     * @throws SerException
     */
    default List<String> listSubByFirst(String firstSub) throws SerException {
        return null;
    }

    /**
     * 根据一级二级查询三级科目
     *
     * @param firstSub  一级科目
     * @param secondSub 二级科目
     * @return
     * @throws SerException
     */
    default List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return null;
    }




}
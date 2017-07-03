package com.bjike.goddess.incomecheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.incomecheck.bo.CheckIncomeBO;
import com.bjike.goddess.incomecheck.dto.CheckIncomeDTO;
import com.bjike.goddess.incomecheck.to.CheckIncomeTO;
import com.bjike.goddess.incomecheck.to.GuidePermissionTO;
import com.bjike.goddess.incomecheck.vo.SonPermissionObject;

import java.util.List;

/**
 * 收入核算资金回笼业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:54 ]
 * @Description: [ 收入核算资金回笼业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CheckIncomeAPI {


    /**
     * 收入核算资金回笼列表总条数
     */
    default Long countCheckIncome(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return null;
    }


    /**
     * 收入核算资金回笼列表id
     *
     * @return class CheckIncomeBO
     */
    default CheckIncomeBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 收入核算资金回笼列表
     *
     * @return class CheckIncomeBO
     */
    default List<CheckIncomeBO> listCheckIncome(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param checkIncomeTO 收入核算资金回笼信息
     * @return class CheckIncomeBO
     */
    default CheckIncomeBO addCheckIncome(CheckIncomeTO checkIncomeTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param checkIncomeTO 收入核算资金回笼信息
     * @return class CheckIncomeBO
     */
    default CheckIncomeBO editCheckIncome(CheckIncomeTO checkIncomeTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteCheckIncome(String id) throws SerException {
        return;
    }


    /**
     * 根据地区汇总
     *
     * @param checkIncomeDTO checkIncomeDTO
     */
    default List<CheckIncomeBO> collectArea(CheckIncomeDTO checkIncomeDTO) throws SerException {

        return null;
    }

    ;

    /**
     * 根据项目组汇总
     *
     * @param checkIncomeDTO checkIncomeDTO
     */
    default List<CheckIncomeBO> collectGroup(CheckIncomeDTO checkIncomeDTO) throws SerException {

        return null;
    }

    ;

    /**
     * 根据项目汇总
     *
     * @param checkIncomeDTO checkIncomeDTO
     */
    default List<CheckIncomeBO> collectProject(CheckIncomeDTO checkIncomeDTO) throws SerException {

        return null;
    }

    ;

    /**
     * 根据地区差异分析
     *
     * @param checkIncomeDTO checkIncomeDTO
     */
    default List<CheckIncomeBO> areaDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {

        return null;
    }

    ;

    /**
     * 根据项目组差异分析
     *
     * @param checkIncomeDTO checkIncomeDTO
     */
    default List<CheckIncomeBO> groupDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {

        return null;
    }

    ;

    /**
     * 根据项目差异分析
     *
     * @param checkIncomeDTO checkIncomeDTO
     */
    default List<CheckIncomeBO> projectDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {

        return null;
    }

    ;


    /**
     * 获取所有地区
     */
    default List<String> areaList() throws SerException {

        return null;
    }

    ;

    /**
     * 获取所有项目组
     */
    default List<String> groupList() throws SerException {

        return null;
    }

    ;

    /**
     * 获取所有项目
     */
    default List<String> projectList() throws SerException {

        return null;
    }

    ;


    /**
     * 下拉导航权限
     */
    List<SonPermissionObject> sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;


}
package com.bjike.goddess.moneyprepare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyprepare.bo.*;
import com.bjike.goddess.moneyprepare.dto.FundPrepareDTO;
import com.bjike.goddess.moneyprepare.entity.FundPrepare;
import com.bjike.goddess.moneyprepare.to.*;

import java.util.List;

/**
 * 资金准备业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FundPrepareSer extends Ser<FundPrepare, FundPrepareDTO> {


    /**
     * 资金准备列表总条数
     */
    default Long countFundPrepare(FundPrepareDTO fundPrepareDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取资金准备列表
     *
     * @return class FundPrepareBO
     */
    default List<ProportionBO> listDetail(String id) throws SerException {
        return null;
    }


    /**
     * 资金准备信息列表
     *
     * @return class FundPrepareBO
     */
    default List<FundPrepareBO> listFundPrepare(FundPrepareDTO fundPrepareDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param fundPrepareObjectTO 资金准备信息
     * @return class FundPrepareBO
     */
    default List<FundPrepareBO> addFundPrepare(FundPrepareObjectTO fundPrepareObjectTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param fundPrepareTO 资金准备信息
     * @return class FundPrepareBO
     */
    default FundPrepareBO editFundPrepare(FundPrepareTO fundPrepareTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteFundPrepare(String id) throws SerException {
        return;
    }

    /**
     * 获得一级科目
     */
    List<String> findFirstSubject() throws SerException;

    /**
     * 获得二级科目
     */
    List<String> findSecondSubject(String firstSubject) throws SerException;

    /**
     * 根据id获取资金准备列表
     *
     * @return class FundPrepareBO
     */
    default FundPrepareBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 周汇总
     */
    default List<FundPrepareWeekBO> weekCollect() throws SerException {
        return null;
    }

    /**
     * 月汇总
     */
    default List<FundPrepareMonthBO> monthCollect() throws SerException {
        return null;
    }

    /**
     * 年汇总
     */
    default List<FundPrepareYearBO> yearCollect() throws SerException {
        return null;
    }

    /**
     * 资金准备对比分析
     */
    default List<FundPrepareanAlysisBO> analysis() throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 项目分配汇总
     */
    default List<ProjectCollectBO> projectCollect() throws SerException {
        return null;
    }

    /**
     * 比例分配修改
     */
    default List<ProportionBO> editProportion(ProportionObjectTO proportionObjectTO) throws SerException {
        return null;
    }
}
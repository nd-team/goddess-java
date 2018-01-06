package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.DemandBO;
import com.bjike.goddess.businessinteraction.bo.OptionBO;
import com.bjike.goddess.businessinteraction.bo.SummationBO;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.entity.Demand;
import com.bjike.goddess.businessinteraction.to.DemandTO;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 需求信息业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:18 ]
 * @Description: [ 需求信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DemandSer extends Ser<Demand, DemandDTO> {

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
     * 需求信息列表总条数
     */
    default Long countInter(DemandDTO demandDTO) throws SerException {
        return null;
    }

    /**
     * 一个需求信息
     *
     * @return class DemandBO
     */
    default DemandBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 需求信息列表
     *
     * @return class DemandBO
     */
    default List<DemandBO> listIntera(DemandDTO demandDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param demandTO 需求信息
     * @return class DemandBO
     */
    default DemandBO addIntera(DemandTO demandTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param demandTO 需求信息
     * @return class LeavingMessageBO
     */
    default DemandBO editIntera(DemandTO demandTO) throws SerException {
        return null;
    }

    /**
     * 删除需求信息
     *
     * @param id id
     */
    default void deleteIntera(String id) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 导入
     *
     * @param demandTOS 需求信息
     */
    void importExcel(List<DemandTO> demandTOS) throws SerException;

    /**
     * 商业能力互动信息管理日汇总
     *
     * @param summDate 日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaDay(String summDate) throws SerException {
        return null;
    }

    /**
     * 商业能力互动信息管理周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }


    /**
     * 商业能力互动信息管理月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        return null;
    }


    /**
     * 商业能力互动信息管理季度汇总
     *
     * @param quarter 季度
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        return null;
    }


    /**
     * 商业能力互动信息管理年度汇总
     *
     * @param year 年度
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaYear(Integer year) throws SerException {
        return null;
    }


    /**
     * 商业能力互动信息管理累计汇总
     *
     * @param endDate 截止日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaTotal(String endDate) throws SerException {
        return null;
    }

    /**
     * 商业能力互动信息管理图形展示日汇总数据
     *
     * @param summDate 日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowDay(String summDate) throws SerException {
        return null;
    }

    /**
     * 商业能力互动信息管理图形展示周汇总数据
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 商业能力互动信息管理图形展示月汇总数据
     *
     * @param year  年份
     * @param month 月份
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 商业能力互动信息管理图形展示季度汇总数据
     *
     * @param year    年份
     * @param quarter 季度
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 商业能力互动信息管理图形展示年度汇总数据
     *
     * @param year 年份
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 商业能力互动信息管理图形展示累计汇总数据
     *
     * @param endDate 截止日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowTotal(String endDate) throws SerException {
        return null;
    }

    /**
     * 获取所有的业务类型
     *
     * @throws SerException
     */
    default List<String> findBussType() throws SerException {
        return null;
    }

}
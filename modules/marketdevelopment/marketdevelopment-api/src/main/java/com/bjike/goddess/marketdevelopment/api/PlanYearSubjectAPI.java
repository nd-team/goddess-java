package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.PlanYearSubjectUpdateBO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearmapsBO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearSubjectDTO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearTypeDTO;
import com.bjike.goddess.marketdevelopment.excel.PlanYearmapsImportExcel;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearSubjectUpdateTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearmapsTO;

import java.util.List;

/**
 * 年计划(科目方向)业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-08 03:34 ]
 * @Description: [ 年计划(科目方向)业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PlanYearSubjectAPI {

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<PlanYearmapsBO> maps(PlanYearSubjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(PlanYearmapsTO to) throws SerException {
        return;
    }

    /**
     * 修改年计划数据
     *
     * @param to
     * @throws SerException
     */
    default void update(PlanYearSubjectUpdateTO to) throws SerException {
        return;
    }

    /**
     * 删除年计划数据
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(PlanYearTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取年计划数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default PlanYearSubjectUpdateBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id
     * @throws SerException
     */
    default void congeal(String id) throws SerException {
        return;
    }

    /**
     * 解冻
     *
     * @param id
     * @throws SerException
     */
    default void thaw(String id) throws SerException {
        return;
    }

    /**
     * 导入
     *
     * @throws SerException
     */
    default void importExcel(List<PlanYearmapsImportExcel> tos) throws SerException {
        return;
    }

    /**
     * excel模板导出
     *
     * @return
     * @throws SerException
     */
    default byte[] exportTempExcel() throws SerException {
        return null;
    }

    /**
     * 导出
     *
     * @return
     * @throws SerException
     */
    default byte[] export() throws SerException {
        return null;
    }

    /**
     * 根据年份获取商务合同的派工金额
     *
     * @param year
     * @return
     * @throws SerException
     */
    default Double findMoney(String year) throws SerException {
        return null;
    }

    /**
     * 根据业务类型和年份获取各业务类型实际金额
     *
     * @param businessType
     * @param year
     * @return
     * @throws SerException
     */
    default Double moneyByType(String businessType, String year) throws SerException {
        return null;
    }

    /**
     * 计算各业务科目年度占比
     *
     * @param workWeight
     * @param proportion
     * @return
     * @throws SerException
     */
    default Double countYearProportion(Double workWeight, Double proportion) throws SerException {
        return null;
    }

    /**
     * 现有业务可发展对象
     *
     * @param course
     * @return
     * @throws SerException
     */
    default Integer findDeveBusiness(String course) throws SerException {
        return null;
    }
}
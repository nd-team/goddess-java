package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.PlanYearBO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearUpdateBO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearTypeDTO;
import com.bjike.goddess.marketdevelopment.excel.PlanYearImportExcel;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearUpdateTO;

import java.util.List;

/**
 * 年计划业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:32 ]
 * @Description: [ 年计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PlanYearTypeAPI {

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
    default List<PlanYearBO> maps(PlanYearTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(PlanYearTO to) throws SerException {
        return;
    }

    /**
     * 修改年计划数据
     *
     * @param to
     * @throws SerException
     */
    default void update(PlanYearUpdateTO to) throws SerException {
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
     * 根据id获取年计划数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default PlanYearUpdateBO getById(String id) throws SerException {
        return null;
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
     * @param tos
     * @throws SerException
     */
    default void importExcel(List<PlanYearImportExcel> tos) throws SerException {
        return;
    }

    /**
     * 导出excel模板
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
}
package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.MarkProblemAcceBO;
import com.bjike.goddess.marketdevelopment.bo.PlanDayBO;
import com.bjike.goddess.marketdevelopment.dto.PlanDayDTO;
import com.bjike.goddess.marketdevelopment.entity.PlanDay;
import com.bjike.goddess.marketdevelopment.excel.PlanDayImportExcel;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanDayTO;

import java.util.List;

/**
 * 日计划业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 03:55 ]
 * @Description: [ 日计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PlanDaySer extends Ser<PlanDay, PlanDayDTO> {

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
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<PlanDayBO> maps(PlanDayDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(PlanDayTO to) throws SerException {
        return;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void update(PlanDayTO to) throws SerException {
        return;
    }

    /**
     * 冻结
     *
     * @param to
     * @throws SerException
     */
    default void congeal(PlanDayTO to) throws SerException {
        return;
    }

    /**
     * 解冻
     *
     * @param to
     * @throws SerException
     */
    default void thaw(PlanDayTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param to
     * @throws SerException
     */
    default void delete(PlanDayTO to) throws SerException {
        return;
    }

    /**
     * 根据id获取客户接触阶段
     *
     * @param id
     * @return
     * @throws SerException
     */
    default PlanDayBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(PlanDayDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出导入的excel模板
     *
     * @return
     * @throws SerException
     */
    default byte[] templateExcel() throws SerException {
        return null;
    }

    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(PlanDayDTO dto) throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param tos
     * @throws SerException
     */
    default void upload(List<PlanDayImportExcel> tos) throws SerException {
        return;
    }

    /**
     * 获取业务方向类型
     *
     * @return
     * @throws SerException
     */
    default List<String> findBusinessType() throws SerException {
        return null;
    }

    /**
     * 获取业务方向科目
     *
     * @return
     * @throws SerException
     */
    default List<String> findBusinessSub() throws SerException {
        return null;
    }

    /**
     * 获取问题受理编号（对内）
     *
     * @return
     * @throws SerException
     */
    default List<String> findInterCode() throws SerException {
        return null;
    }

    /**
     * 根据问题受理编号获取地区项目组问题归类
     *
     * @param interCode
     * @return
     * @throws SerException
     */
    default MarkProblemAcceBO findProblemAcce(String interCode) throws SerException {
        return null;
    }

    /**
     * 获取市场信息编号
     *
     * @return
     * @throws SerException
     */
    default List<String> findMarkCode() throws SerException {
        return null;
    }

    /**
     * 根据市场信息编号获取内部项目名称
     *
     * @param marketNum
     * @return
     * @throws SerException
     */
    default String findInnerProject(String marketNum) throws SerException {
        return null;
    }
}
package com.bjike.goddess.staffing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffing.bo.SalaryBO;
import com.bjike.goddess.staffing.dto.SalaryDTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import com.bjike.goddess.staffing.to.SalaryTO;

import java.util.List;
import java.util.Set;

/**
 * 工资区间业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 01:50 ]
 * @Description: [ 工资区间业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SalaryAPI {
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
    List<SalaryBO> list(SalaryDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    SalaryBO save(SalaryTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(SalaryTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    SalaryBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(SalaryDTO dto) throws SerException;

    /**
     * 获取所有薪资区间
     *
     * @return
     * @throws SerException
     */
    Set<String> sal() throws SerException;
}
package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.salarymanage.bo.SalaryCalculateDetailBO;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateDetailDTO;
import com.bjike.goddess.salarymanage.excel.SonPermissionObject;
import com.bjike.goddess.salarymanage.to.ExportSalaryCalculateTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryCalculateDetailTO;

import java.util.List;

/**
* 薪资测算明细表业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-16 10:45 ]
* @Description:	[ 薪资测算明细表业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryCalculateDetailAPI  {


    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 新增
     */
    void add(SalaryCalculateDetailTO to) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 修改
     */
    void modify(SalaryCalculateDetailTO to) throws SerException;

    /**
     * 列表
     */
    List<SalaryCalculateDetailBO> findList(SalaryCalculateDetailDTO dto) throws SerException;

    /**
     * 从招聘管理获取岗位和姓名和地区项目组和部门等信息
     */
    List<InterviewInforBO> findInteview() throws SerException;

    /**
     * 根据id查询单条数据
     */
    SalaryCalculateDetailBO findOne(String id) throws SerException;

    /**
     * 列表总条数
     */
    Long count(SalaryCalculateDetailDTO dto) throws SerException;

    /**
     * 导入
     *
     * @param toList
     * @throws SerException
     */
    default void leadExcel(List<SalaryCalculateDetailTO> toList) throws SerException {
        return;
    }

    /**
     * 导出
     *
     * @param to
     * @return
     * @throws SerException
     */
    byte[] exportExcel(ExportSalaryCalculateTO to) throws SerException;

    /**
     * 导出Excel模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;
}
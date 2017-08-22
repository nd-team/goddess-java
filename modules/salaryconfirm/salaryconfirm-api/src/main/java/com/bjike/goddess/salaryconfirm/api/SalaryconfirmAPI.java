package com.bjike.goddess.salaryconfirm.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salaryconfirm.bo.AnalyzeBO;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salaryconfirm.dto.SalaryconfirmDTO;
import com.bjike.goddess.salaryconfirm.to.ConditionTO;
import com.bjike.goddess.salaryconfirm.to.GuidePermissionTO;
import com.bjike.goddess.salaryconfirm.to.SalaryconfirmTO;
import com.bjike.goddess.salaryconfirm.vo.AreaAnalyzeVO;
import com.bjike.goddess.salaryconfirm.vo.DepartmentAnalyzeVO;
import com.bjike.goddess.salaryconfirm.vo.UserAnalyzeVO;

import java.util.List;

/**
 * 薪资核算确认业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 薪资核算确认业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SalaryconfirmAPI {

    /**
     * 新增薪资核算确认
     *
     * @param to 薪资核算确认
     * @return 薪资核算确认
     */
    SalaryconfirmBO add(SalaryconfirmTO to) throws SerException;

    /**
     * 编辑薪资核算确认
     *
     * @param to 薪资核算确认
     * @return 薪资核算确认
     */
    SalaryconfirmBO edit(SalaryconfirmTO to) throws SerException;

    /**
     * 删除薪资核算确认
     *
     * @param id 薪资核算确认id
     */
    void delete(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<SalaryconfirmBO> pageList(SalaryconfirmDTO dto) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(SalaryconfirmDTO dto) throws SerException;

    /**
     * 根据id查询薪资核算确认
     *
     * @param id 薪资核算确认id
     * @return 薪资核算确认
     */
    SalaryconfirmBO findById(String id) throws SerException;

    /**
     * 确认薪资
     *
     * @param id 薪资核算确认id
     * @throws SerException
     */
    void confirm(String id) throws SerException;

    /**
     * 第一次付款
     *
     * @param id 薪资核算确认id
     */
    void firstPay(String id) throws SerException;

    /**
     * 第二次付款
     *
     * @param id 薪资核算确认id
     */
    void secondPay(String id) throws SerException;

    /**
     * 第一次收款确认
     *
     * @param id 薪资核算确认id
     */
    void firstConfirm(String id) throws SerException;

    /**
     * 第二次收款确认
     *
     * @param id 薪资核算确认id
     */
    void secondConfirm(String id) throws SerException;

    /**
     * 汇总
     *
     * @param to 条件
     * @return 汇总结果集
     */
    List<SalaryconfirmBO> collectByCondition(ConditionTO to) throws SerException;

    /**
     * 地区分析
     *
     * @param to 条件
     * @return 分析结果集
     */
    List<AnalyzeBO> analyzeByArea(ConditionTO to, String type) throws SerException;

    /**
     * 部门分析
     *
     * @param to 条件
     * @return 分析结果集
     */
    List<AnalyzeBO> analyzeByDepart(ConditionTO to, String type) throws SerException;

    /**
     * 个人分析
     *
     * @param to 条件
     * @return 分析结果集
     */
    List<AnalyzeBO> analyzeByName(ConditionTO to, String type) throws SerException;

    void importExcel(List<SalaryconfirmTO> toList) throws SerException;

    byte[] exportExcel(Integer year, Integer month) throws SerException;

    byte[] exportExcelModule() throws SerException;

    void sendEmail() throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 根据计薪周期和员工姓名来查询薪资确认情况
     */
    SalaryconfirmBO findSalary(String salaryStart,String salaryEnd,String name) throws SerException;
}
package com.bjike.goddess.salaryconfirm.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.salaryconfirm.bo.AnalyzeBO;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salaryconfirm.dto.SalaryconfirmDTO;
import com.bjike.goddess.salaryconfirm.entity.Salaryconfirm;
import com.bjike.goddess.salaryconfirm.to.ConditionTO;
import com.bjike.goddess.salaryconfirm.to.GuidePermissionTO;
import com.bjike.goddess.salaryconfirm.to.SalaryconfirmTO;

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
public interface SalaryconfirmSer extends Ser<Salaryconfirm, SalaryconfirmDTO> {

    /**
     * 新增薪资确认核算
     *
     * @param to 薪资确认核算
     * @return 薪资确认核算
     */
    SalaryconfirmBO insertModel(SalaryconfirmTO to) throws SerException;

    /**
     * 编辑薪资确认核算
     *
     * @param to 薪资确认核算
     * @return 薪资确认核算
     */
    SalaryconfirmBO editModel(SalaryconfirmTO to) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<SalaryconfirmBO> pageList(SalaryconfirmDTO dto) throws SerException;

    /**
     * 确认薪资
     *
     * @param id 薪资核算确认id
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
     * 分析
     *
     * @param to 条件
     * @return 分析结果集
     */
    List<AnalyzeBO> analyzeByCondition(ConditionTO to, String type) throws SerException;

    /**
     * 导入Excel数据
     *
     * @param toList excel数据集
     */
    void importExcel(List<SalaryconfirmTO> toList) throws SerException;

    /**
     * 导出excel数据
     *
     * @param year  年份
     * @param month 月份
     * @return 导出结果集字节数组
     */
    byte[] exportExcel(Integer year, Integer month) throws SerException;

    byte[] exportExcelModule() throws SerException;

    void sendEmail() throws SerException;

    Boolean guidePermission(GuidePermissionTO to) throws SerException;

    Boolean sonPermission() throws SerException;

    /**
     * 根据计薪周期和员工姓名来查询薪资确认情况
     */
    SalaryconfirmBO findSalary(String salaryStart,String salaryEnd,String name) throws SerException;

    /**
     * 导出－"已确认薪资"
     *
     * @return class
     * @version v1
     */
    byte[] exportConfirmedExcel(Integer year, Integer month) throws SerException;

    /**
     * 正常的个人汇总
     */
//    List<SalaryconfirmBO> findByGood(Integer year, Integer mouth, String name) throws SerException;
}
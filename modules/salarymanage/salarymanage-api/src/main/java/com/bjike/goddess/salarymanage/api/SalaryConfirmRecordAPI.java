package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salarymanage.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.salarymanage.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.enums.Probation;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryConfirmRecordTO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;

/**
* 招聘面谈薪资确认记录业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-15 02:20 ]
* @Description:	[ 招聘面谈薪资确认记录业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryConfirmRecordAPI  {

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
     * 新增
     */
    void add (SalaryConfirmRecordTO to) throws SerException;


    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 修改
     */
    void edit(SalaryConfirmRecordTO to) throws SerException;

    /**
     * 列表查询
     */
    List<SalaryConfirmRecordBO> pageList(SalaryConfirmRecordDTO dto) throws SerException;

    /**
     * 列表总条数
     */
    Long count(SalaryConfirmRecordDTO dto) throws SerException;

    /**
     * 根据id查询单条数据
     */
    SalaryConfirmRecordBO findOne(String id) throws SerException;


    /**
     * 获取协半人和存档人
     */
    List<UserBO> findUserList() throws SerException;

    /**
     * 薪资确认
     */
    void confirm(String id,Boolean ifConfirm) throws SerException;
    /**
     * 根据员工编号获取同意试用期时长
     */
    Probation findProbationById(String employeeID) throws SerException;
}
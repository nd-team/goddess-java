package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.bo.ComputerAssistBO;
import com.bjike.goddess.assistance.bo.HotAssistBO;
import com.bjike.goddess.assistance.bo.HouseAssistBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.entity.SalaryInformation;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;

import java.util.List;

/**
 * 薪资管理业务接口
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-31 01:45 ]
 * @Description: [ 薪资管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SalaryInformationSer extends Ser<SalaryInformation, SalaryInformationDTO> {


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
     * 查询列表
     *
     * @param dto
     * @throws SerException
     */
    default List<SalaryInformationBO> pageList(SalaryInformationDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default SalaryInformationBO addSalaryInformation(SalaryInformationTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to
     * @throws SerException
     */
    default SalaryInformationBO editSalaryInformation(SalaryInformationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void deleteSalaryInformation(String id) throws SerException {
        return;
    }

    /**
     * 导入
     *
     * @param toList
     * @throws SerException
     */
    default void leadExcel(List<SalaryInformationTO> toList) throws SerException {
        return;
    }

    ;

    /**
     * 导出
     *
     * @param to
     * @return
     * @throws SerException
     */
    byte[] exportExcel(ExportSalaryInformationTO to) throws SerException;

    /**
     * 获取所有的计薪周期开始时间和计薪周期结束时间
     *
     * @throws SerException
     */
    default List<String> findTime() throws SerException {
        return null;
    }

    /**
     * 导出Excel模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 通过员工编号来查询管理等级
     *
     * @param employeeId
     * @throws SerException
     */
    LevelShow findByEmployeeId(String employeeId) throws SerException;

    /**
     * 根据id来查询入职基本信息
     *
     * @param employeeId
     * @return
     * @throws SerException
     */
    List<EntryBasicInfoBO> getByEmpNumber(String employeeId) throws SerException;

    /**
     * 查询总条数
     *
     * @param dto
     * @throws SerException
     */
    Long count(SalaryInformationDTO dto) throws SerException;

    /**
     * 根据id来查询单个薪资资料
     *
     * @param id
     * @throws SerException
     */
    SalaryInformationBO findOne(String id) throws SerException;

    /**
     * 根据计薪周期查询高温补助
     *
     * @throws SerException
     */
    HotAssistBO findHotAssist(SalaryInformationDTO dto) throws SerException;

    /**
     * 根据计薪周期查询住宿补助
     *
     * @throws SerException
     */
    HouseAssistBO findHouseAssist(SalaryInformationDTO dto) throws SerException;

    /**
     * 根据计薪周期查看电脑补助
     */
    ComputerAssistBO findComputerAssist(SalaryInformationDTO dto) throws SerException;

    /**
     * 根据计薪周期查看工龄补助
     */
    AgeAssistBO findAgeAssist(SalaryInformationDTO dto) throws SerException;

    /**
     * 查询入职时间和离职时间和身份证号码和银行卡号
     *
     * @return
     * @throws SerException
     */
    StaffRecordsBO findStaff(String employeeNumber) throws SerException;

    /**
     * 根据员工编号id查找转正时间
     */
    String findPositiveDate(String employeeId) throws SerException;

    /**
     * 根据员工姓名获取技能等级获取时间
     */
    OverviewSkillLevelBO findSkill(String employeeName) throws SerException;

    /**
     * 根据计薪周期和员工姓名获取旷工扣款和事病假扣款
     */
    SalaryconfirmBO findSalaryConfirm(SalaryInformationDTO dto) throws SerException;

    /**
     * 根据员工姓名查看扣社保情况
     */
    AttachedBO findAttached(SalaryInformationDTO dto) throws SerException;

    /**
     * chenjunhao
     * 通过姓名获取
     *
     * @param name
     * @return
     * @throws SerException
     */
    SalaryInformationBO findByName(String name) throws SerException;
}
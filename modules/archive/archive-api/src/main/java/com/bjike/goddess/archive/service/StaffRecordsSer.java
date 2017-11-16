package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.*;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.StaffRecords;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.StaffRecords1ExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 员工档案业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffRecordsSer extends Ser<StaffRecords, StaffRecordsDTO> {


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
     * 上传数据
     *
     * @throws SerException
     */
    void upload(List<StaffRecordsExcelTO> toList) throws SerException;

    /**
     * 根据姓名查询员工档案
     *
     * @param username 姓名
     * @return
     * @throws SerException
     */
    default StaffRecordsBO findByName(String username) throws SerException {
        return null;
    }

    /**
     * 根据编号查询员工档案
     *
     * @param serialNumber
     * @return
     * @throws SerException
     */
    default StaffRecordsBO findByNumber(String serialNumber) throws SerException {
        return null;
    }


    /**
     * 列表
     *
     * @param dto 员工档案数据传输对象
     * @return
     * @throws SerException
     */
    default List<StaffRecordsBO> maps(StaffRecordsDTO dto) throws SerException {
        return null;
    }


    /**
     * 根据id获取员工档案数据
     *
     * @param id 员工档案数据id
     * @return
     * @throws SerException
     */
    default StaffRecordsBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal(StaffRecordsDTO dto) throws SerException {
        return null;
    }

    default List<StaffNameBO> getName() throws SerException {
        return null;
    }

    default List<PerBO> getPerBO(String name) throws SerException {
        return null;
    }

    /**
     * 在职员工基本信息
     *
     * @return
     * @throws SerException
     */
    default List<StaffRecordsBO> listEmployee() throws SerException {
        return null;
    }

    /**
     * 导出模板excel
     *
     * @return
     * @throws SerException
     */
    byte[] templateExcel() throws SerException;

    /**
     * 离职人员信息导入
     *
     * @param toList
     * @throws SerException
     */
    void dimissionUpload(List<StaffRecords1ExcelTO> toList) throws SerException;

    /**
     * 离职人员列表
     */
    List<StaffRecords1BO> dimissionMaps(StaffRecordsDTO dto) throws SerException;

    /**
     * 离职人员总条数
     *
     * @return
     */
    Long count(StaffRecordsDTO dto) throws SerException;

    /**
     * 离职人员模板导出
     *
     * @return
     */
    byte[] templateDimissionExcel() throws SerException;

    /**
     * 根据员工生日月份来查找员工数据　——jiangzaixuan 2017/8/17 10:31
     */

    List<StaffRecordsBO> findByMonth(Integer month) throws SerException;

    /**
     * chenjunhao
     * 通过姓名获取员工档案
     *
     * @param name
     * @return
     * @throws SerException
     */
    StaffRecordsBO getByName(String name) throws SerException;

    /**
     * 获取当前员工的信息(在职状态为true,在职,反之,离职)
     */
    default CurrentMessageBO findCurrentMessage() throws SerException{
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void add(StaffRecordsTO to) throws SerException {
        return;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void edit(StaffRecordsTO to) throws SerException {
        return;
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default StaffRecordsBO findEntity(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(StaffRecordsDTO dto) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id
     * @throws SerException
     */
    default void freeze(String id) throws SerException {
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
     * 员工信息管理进度汇总
     *
     * @param day
     * @return
     * @throws SerException
     */
    default List<StaffRecordsCollectBO> dayCollect(String day) throws SerException {
        return null;
    }

    /**
     * 周员工信息管理汇总
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    default List<StaffRecordsCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 月员工信息管理汇总
     *
     * @param month
     * @return
     * @throws SerException
     */
    default List<StaffRecordsCollectBO> monthCollect(String month) throws SerException {
        return null;
    }

    /**
     * 累计员工信息管理汇总
     *
     * @return
     * @throws SerException
     */
    default List<StaffRecordsCollectBO> totalCollect() throws SerException {
        return null;
    }

    /**
     * 员工信息管理日汇总柱状图
     *
     * @param day
     * @return
     * @throws SerException
     */
    default OptionBO figureShowDay(String day) throws SerException {
        return null;
    }


    /**
     * 员工信息管理周汇总柱状图
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    default OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 员工信息管理月汇总图形化
     *
     * @param month
     * @return
     * @throws SerException
     */
    default OptionBO figureShowMonth(String month) throws SerException {
        return null;
    }

    /**
     * 员工信息管理累计汇总柱状图
     *
     * @return
     * @throws SerException
     */
    default OptionBO figureShowAll() throws SerException {
        return null;
    }

    /**
     * 根据名字获取数据
     *
     * @param name
     * @throws SerException
     */
    default StaffRecordsDataBO findDataByName(String name) throws SerException {
        return null;
    }
}
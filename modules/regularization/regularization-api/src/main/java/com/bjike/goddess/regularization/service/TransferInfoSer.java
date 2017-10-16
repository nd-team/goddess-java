package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.regularization.bo.OptionBO;
import com.bjike.goddess.regularization.bo.SummationBO;
import com.bjike.goddess.regularization.bo.TransferInfoBO;
import com.bjike.goddess.regularization.dto.TransferInfoDTO;
import com.bjike.goddess.regularization.entity.TransferInfo;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.TransferInfoTO;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * 转正人员信息业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 转正人员信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferInfoSer extends Ser<TransferInfo, TransferInfoDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }


    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 转正人员信息总条数
     * @throws SerException
     */
    default Long countTrans(TransferInfoDTO transferInfoDTO) throws SerException {
        return null;
    }

    /**
     * 一个转正人员信息
     * @param id
     * @return class TransferInfoBO
     * @throws SerException
     */
    default TransferInfoBO getOne(String id) throws SerException{
        return null;
    }
    /**
     * 分页查询转正人员信息
     *
     * @return class TransferInfoBO
     * @throws SerException
     */
    List<TransferInfoBO> list(TransferInfoDTO dto) throws SerException;
    /**
     * 根据员工编号获取转正人员信息
     * @param empNo
     * @return TransferInfoBO
     * @throws SerException
     */
    default TransferInfoBO findByEmpNo(String empNo) throws SerException{
        return null;
    }

    /**
     * 手动生成数据
     * @param transferInfoTO
     * @throws SerException
     */
    default void saveTransferInfo(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }

    /**
     * 跟进
     * @param transferInfoTO
     * @throws SerException
     */
    default void followUp(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
    /**
     * 福利模块考核
     * @param transferInfoTO
     * @throws SerException
     */
    default void welfareAssess(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
    /**
     * 规划模块考察
     * @param transferInfoTO
     * @throws SerException
     */
    default void planAssess(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
    /**
     * 预算模块考察填写
     * @param transferInfoTO
     * @throws SerException
     */
    default void budgetAssess(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
    /**
     * 模块负责人审核
     * @param transferInfoTO
     * @throws SerException
     */
    default void moduleRespon(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
    /**
     * 项目经理审核
     * @param transferInfoTO
     * @throws SerException
     */
    default void projectManage(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
    /**
     * 总经理审核
     * @param transferInfoTO
     * @throws SerException
     */
    default void genManage(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }
    /**
     * 面谈记录填写
     * @param transferInfoTO
     * @throws SerException
     */
    default void interview(TransferInfoTO transferInfoTO) throws SerException{
        return;
    }

    /**
     * 定时检测所有在试用期的员工是否到期未申请转正
     * @throws SerException
     */
    default void check() throws SerException{
        return;
    }

    /**
     * 转正管理日汇总
     * @param summDate 日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaDay(String summDate) throws SerException{
        return null;
    }
    /**
     * 转正管理周汇总
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaWeek(Integer year,Integer month,Integer week) throws SerException{
        return null;
    }
    /**
     * 转正管理月汇总
     * @param year 年份
     * @param month 月份
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaMonth(Integer year,Integer month) throws SerException{
        return null;
    }
    /**
     * 转正管理累计汇总
     * @param endDate 截止日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaTotal(String endDate) throws SerException{
        return null;
    }

    /**
     * 获取所有的地区
     * @throws SerException
     */
    default List<String> findArea() throws SerException{
        return null;
    }

    /**
     * 根据地区获取部门项目组
     * @param area
     * @throws SerException
     */
    default List<String> findDepByArea(String area) throws SerException{
        return null;
    }

    /**
     * 获取所有的部门
     * @return
     * @throws SerException
     */
    default List<String> findDepartment() throws SerException{
        return null;
    }

    /**
     * 转正管理图形展示日汇总数据
     * @param summDate 日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowDay(String summDate) throws SerException{
        return null;
    }
    /**
     * 转正管理图形展示周汇总数据
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowWeek(Integer year,Integer month,Integer week) throws SerException{
        return null;
    }
    /**
     * 转正管理图形展示月汇总数据
     * @param year 年份
     * @param month 月份
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowMonth(Integer year,Integer month) throws SerException{
        return null;
    }
    /**
     * 转正管理图形展示累计汇总数据
     * @param endDate 截止日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowTotal(String endDate) throws SerException{
        return null;
    }

}
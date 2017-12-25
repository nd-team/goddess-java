package com.bjike.goddess.courier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCountBO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.entity.Courier;
import com.bjike.goddess.courier.to.CourierTO;
import com.bjike.goddess.courier.to.GuidePermissionTO;
import com.bjike.goddess.courier.vo.SonPermissionObject;

import java.util.List;
import java.util.Set;

/**
 * 快递收发业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:25 ]
 * @Description: [ 快递收发业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CourierSer extends Ser<Courier, CourierDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to 快递收发信息
     * @return class CourierBO
     * @throws SerException
     */
    default CourierBO save(CourierTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 快递收发信息
     * @return class CourierBO
     * @throws SerException
     */
    default CourierBO edit(CourierTO to) throws SerException {
        return null;
    }

    /**
     * 分页查找
     *
     * @param dto 快递收发分页信息
     * @return class CourierBO
     * @throws SerException
     */
    default List<CourierBO> list(CourierDTO dto) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 快递收发id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 通过id查找
     *
     * @param id 快递收发id
     * @return class CourierBO
     * @throws SerException
     */
    default CourierBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 通过部门名称查找员工姓名集合
     *
     * @param departmentName 部门数组
     * @return class String
     * @throws SerException
     */
    default List<String> findNameByDepartment(String[] departmentName) throws SerException {
        return null;
    }

    /**
     * 通过项目组名词查找员工姓名集合
     *
     * @param groupName 项目组数组
     * @return class String
     * @throws SerException
     */
    default List<String> findNameByGroup(String[] groupName) throws SerException {
        return null;
    }

    /**
     * 查询上条记录的余额
     *
     * @return class Double
     * @throws SerException
     */
    default Double findRemainSum() throws SerException {
        return null;
    }

    /**
     * 日汇总
     *
     * @param dto1
     * @return
     * @throws SerException
     */
    List<CourierCountBO> dayCount(CourierDTO dto1) throws SerException;

    /**
     * 周汇总
     *
     * @param dto1
     * @return
     * @throws SerException
     */
    List<CourierCountBO> weekCount(CourierDTO dto1) throws SerException;

    /**
     * 月汇总
     *
     * @param dto1
     * @return
     * @throws SerException
     */
    List<CourierCountBO> monthCount(CourierDTO dto1) throws SerException;

    /**
     * 年汇总
     *
     * @param dto1
     * @return
     * @throws SerException
     */
    List<CourierCountBO> yearCount(CourierDTO dto1) throws SerException;

    /**
     * 查找所有寄件地和收件地
     *
     * @return class String
     * @throws SerException
     */
    default Set<String> findAllAreas() throws SerException {
        return null;
    }

    /**
     * 查找所有收件人
     *
     * @return class String
     * @throws SerException
     */
    default List<String> findAllNames() throws SerException {
        return null;
    }

    /**
     * 获取上条记录的快递费总额
     *
     * @return
     * @throws SerException
     */
    Double lastCourierSum() throws SerException;

    /**
     * 获取所有年份
     *
     * @return
     * @throws SerException
     */
    Set<Integer> allYear() throws SerException;

    /**
     * 获取所有月份
     *
     * @return
     * @throws SerException
     */
    Set<Integer> allMonth() throws SerException;

    /**
     * 获取总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(CourierDTO dto) throws SerException;

    /**
     * 发送公告
     *
     * @throws SerException
     */
    void send() throws SerException;
}
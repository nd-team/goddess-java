package com.bjike.goddess.courier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.courier.bo.CourierBO;
import com.bjike.goddess.courier.bo.CourierCountBO;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.to.CourierTO;

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
public interface CourierAPI {
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
     * @param arrival        是否汇地区
     * @param sendTime       汇总的日期
     * @param courierCompany 是否汇快递公司
     * @param department     是否汇部门
     * @return class CourierCountVO
     * @throws SerException
     */
    default List<CourierCountBO> dayCount(boolean arrival, String sendTime, boolean courierCompany, boolean department) throws SerException {
        return null;
    }

    /**
     * 周汇总
     *
     * @param arrival        是否回地区
     * @param courierCompany 是否汇快递公司
     * @param department     是否汇部门
     * @param lastWeek       是否为上周汇总
     * @return class CourierCountVO
     * @throws SerException
     */
    default List<CourierCountBO> weekCount(boolean arrival, boolean courierCompany, boolean department, boolean lastWeek) throws SerException {
        return null;
    }

    /**
     * 月汇总
     *
     * @param arrival        是否汇总地区
     * @param courierCompany 是否汇总快递公司
     * @param department     是否汇总部门
     * @param month          汇总的月份
     * @return class CourierCountVO
     * @throws SerException
     */
    default List<CourierCountBO> monthCount(boolean arrival, boolean courierCompany, boolean department, Integer
            month) throws SerException {
        return null;
    }

    /**
     * 年汇总
     *
     * @param arrival        是否汇地区
     * @param courierCompany 是否汇快递公司
     * @param department     是否汇部门
     * @param year           汇总的年份
     * @return class CourierCountVO
     * @throws SerException
     */
    default List<CourierCountBO> yearCount(boolean arrival, boolean courierCompany, boolean department, Integer year) throws SerException {
        return null;
    }

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
     * 查找所有快递公司
     *
     * @return class String
     * @throws SerException
     */
    default List<String> findAllCompanys() throws SerException {
        return null;
    }
}
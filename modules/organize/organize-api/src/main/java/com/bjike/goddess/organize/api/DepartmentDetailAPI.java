package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.DepartmentPeopleBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.to.DepartmentDetailTO;

import java.util.List;
import java.util.Set;

/**
 * 对外部门详细业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DepartmentDetailAPI {

    /**
     * 部门详细列表
     *
     * @param dto 部门详细数据传输
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> view(DepartmentDetailDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据体系ID查询部门详细
     *
     * @param hierarchy_id 体系ID
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findByHierarchy(String hierarchy_id) throws SerException {
        return null;
    }

    /**
     * 查询未冻结部门
     *
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 根据部门id集合查询部门详细信息
     *
     * @param ids 部门id集合
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findByDepartmentIds(List<String> ids) throws SerException {
        return null;
    }

    /**
     * 根据ID获取部门详细传输对象
     *
     * @param id 部门项目组传输对象
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO findBOById(String id) throws SerException {
        return null;
    }

    /**
     * 保存部门/项目组详细信息
     *
     * @param to 部门项目组传输对象
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO save(DepartmentDetailTO to) throws SerException {
        return null;
    }

    /**
     * 修改部门/项目组详细信息
     *
     * @param to 部门/项目组传输对象
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO update(DepartmentDetailTO to) throws SerException {
        return null;
    }


    /**
     * 冻结
     *
     * @param id 部门项目组数据id
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 部门项目组数据id
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 刪除
     *
     * @param id 部门项目组数据id
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取部门项目组详细数据
     *
     * @param id 部门项目组数据id
     * @return
     * @throws SerException
     */
    default DepartmentDetailBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 查询所有地区
     *
     * @return
     * @throws SerException
     */
    default List<AreaBO> findArea() throws SerException {
        return null;
    }

    /**
     * 根据地区查询
     *
     * @param area
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findByArea(String area) throws SerException {
        return null;
    }

    /**
     * 根据地区查询部门
     * tanghaixiang
     *
     * @param area
     * @return
     * @throws SerException
     */
    default List<String> findDepartByArea(String area) throws SerException {
        return null;
    }

    /**
     * 根据地区和部门查询项目名称
     * tanghaixiang
     *
     * @param area
     * @return
     * @throws SerException
     */
    default List<String> findPnameByAreaAndDepart(String area, String depart) throws SerException {
        return null;
    }

    /**
     * 获取所有内部项目名称
     * tanghaixiang
     *
     * @return
     * @throws SerException
     */
    default List<String> findAllProject() throws SerException {
        return null;
    }

    /**
     * 根据id查询部门项目组详细数据
     *
     * @param ids 部门项目组详细数据id数组
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findByIds(String... ids) throws SerException {
        return null;
    }

    /**
     * 查询未冻结部门选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findThawOpinion() throws SerException {
        return null;
    }

    /**
     * 获取全部部门选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findAllOpinion() throws SerException {
        return null;
    }

    /**
     * 真实编号
     *
     * @param to
     * @return
     * @throws SerException
     */
    String number(DepartmentDetailTO to) throws SerException;

    /**
     * 总条数
     *
     * @return
     * @throws SerException
     */
    Long getTotal() throws SerException;

    /**
     * 一个部门的总人数
     *
     * @param department department
     * @return
     * @throws SerException
     */
    Integer departmentTotalPeople(String department) throws SerException;

    /**
     * 根据时间获取地区个数
     */
    default Integer getAreaNum(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 获取某部门下的所有员工
     *
     * @param departId
     * @return
     * @throws SerException
     */
    Set<String> departPersons(String departId) throws SerException;

    /**
     * chenjunhao
     * 通过部门名称查找部门信息
     *
     * @param departs 部门数组
     * @return
     * @throws SerException
     */
    List<DepartmentDetailBO> departByName(String[] departs) throws SerException;

    /**
     * 查询所部的部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findAllDepartment() throws SerException {
        return null;
    }

    /**
     * 查询所有部门下的人数
     *
     * @return
     * @throws SerException
     */
    default List<DepartmentPeopleBO> peopleByDepartment() throws SerException {
        return null;
    }

    /**
     * 查询部门下所有人的信息
     *
     * @return
     * @throws SerException
     */
    default List<DepartmentPeopleBO> infoByDepartment(String dep) throws SerException {
        return null;
    }

    /**
     * 根据姓名查询所在部门
     *
     * @param name
     * @return class DepartmentPeopleBO
     * @throws SerException
     */
    default List<DepartmentPeopleBO> departmentByName(String name) throws SerException {
        return null;
    }
}

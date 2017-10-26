package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.entity.PositionDetailUser;
import com.bjike.goddess.organize.enums.StaffStatus;
import com.bjike.goddess.organize.to.PhoneLoginUserInfoTO;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;
import java.util.Set;

/**
 * 用户职位业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PositionDetailUserAPI {
    /**
     * 总条数
     *
     * @return
     * @throws SerException
     */
    Long getTotal() throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    PositionDetailUserBO findById(String id) throws SerException;

    /**
     * 保存
     *
     * @param to 用户职位传输对象
     * @return
     * @throws SerException
     */
    void save(PositionDetailUserTO to) throws SerException;

    /**
     * 修改
     *
     * @param to 用户职位传输对象
     * @return
     * @throws SerException
     */
    void update(PositionDetailUserTO to) throws SerException;

    /**
     * 删除
     *
     * @param id 用户职位数据id
     * @return
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 根据姓名查询职位详细数据
     *
     * @param name name
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findPositionByUser(String name) throws SerException {
        return null;
    }

    /**
     * 获取用户职位数据
     *
     * @param name name
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO findOneByUser(String name) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定职位中
     *
     * @param name      name
     * @param position_ids 职位id数组
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserPosition(String name, String[] position_ids) throws SerException {
        return null;
    }
    /**
     * 检测用户是否在指定职位中
     *
     * @param name      name
     * @param position_ids 职位id数组
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserPosit2(String name, String[] position_ids) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定层级中
     *
     * @param name         name
     * @param arrangement_ids 层级id
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserArrangement(String name, String... arrangement_ids) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定部门中
     *
     * @param name        name
     * @param departmentIds 部门id
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserDepartment(String name, String... departmentIds) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定模块中
     *
     * @param name    name
     * @param module_ids 模块id
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserModule(String name, String... module_ids) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 用户职位数据传输对象
     * @return
     * @throws SerException
     */
    default List<PositionDetailUserBO> maps(PositionDetailUserDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id查询用户职位数据
     *
     * @param id 用户职位数据id
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 根据职位id查询用户信息
     *
     * @param position_id 职位id
     * @return
     * @throws SerException
     */
    default List<UserBO> findByPosition(String position_id) throws SerException {
        return null;
    }

    /**
     * 获取用户列表
     *
     * @return
     * @throws SerException
     */
    default List<UserBO> findUserList() throws SerException {
        return null;
    }

    /**
     * 获取组织结构中的用户列表
     * tanghaixiang
     *
     * @return
     * @throws SerException
     */
    default List<UserBO> findUserListInOrgan() throws SerException {
        return null;
    }

    /**
     * 根据名字获取岗位名称
     */
    default List<String> getPosition(String name) throws SerException {
        return null;
    }

    /**
     * 获取全部的岗位
     */
    default List<String> getAllPositions() throws SerException {
        return null;
    }

    /**
     * 获取全部的岗位
     */
    default List<String[]> getAllPosition() throws SerException {
        return null;
    }

    /**
     * 获取全部的部门
     */
    default List<String> getAllDepartment() throws SerException {
        return null;
    }

    /**
     * 根据名字获取岗位详细(地区，部门，模块，层级，岗位)
     */
    default List<PositionDetailBO> getPositionDetail(String name) throws SerException {
        return null;
    }

    /**
     * 获取用户名获取该员工状态
     * lijuntao
     *
     * @return
     * @throws SerException
     */
    StaffStatus statusByName(String name) throws SerException;

    /**
     * 获取所有部门下的职位
     *
     * @return
     * @throws SerException
     */
    List<DepartPositionBO> departPositions() throws SerException;

    /**
     * 检测是否在该部门
     *
     * @param entity
     * @param positions
     * @return
     * @throws SerException
     */
    PositionDetailUserBO bo(PositionDetailUser entity, Set<String> positions) throws SerException;

    /**
     * chenjunhao
     * 根据姓名获取该用户所属的部门地区
     *
     * @param name name
     * @return
     * @throws SerException
     */
    DepartmentDetailBO areaAndDepart(String name) throws SerException;
/*
     * 判断是否是市场专业人员
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default Boolean isMarker(String userId) throws SerException {
        return null;
    }

    /**
     * 手机端根据用户名获取体系和员工编号和性别
     *
     * @param phoneLoginUserInfoTO
     * @return
     * @throws SerException
     */
    default PhoneLoginUserInfoBO userLoginInfoByUserName(PhoneLoginUserInfoTO phoneLoginUserInfoTO) throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 获取入职了没有职位的员工
     *
     * @return
     * @throws SerException
     */
    List<String> names() throws SerException;

    /**
     * lijuntao
     * 获取客户模块负责人
     *
     * @return
     * @throws SerException
     */
    String customRepPerson() throws SerException;
    /**
     * xiazhili
     * 获取预算模块负责人
     *
     * @return
     * @throws SerException
     */
    String[] budgetPerson() throws SerException;
    /**
     * xiazhili
     * 获取规划模块负责人
     *
     * @return
     * @throws SerException
     */
    String[] planPerson() throws SerException;
    /**
     * xiazhili
     * 获取项目经理
     *
     * @return
     * @throws SerException
     */
    String[] managerPerson() throws SerException;
    /**
     * xiazhili
     * 获取总经理
     *
     * @return
     * @throws SerException
     */
    String[] generPerson() throws SerException;
}
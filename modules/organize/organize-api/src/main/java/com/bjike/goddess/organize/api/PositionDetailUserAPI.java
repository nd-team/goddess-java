package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;

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
     * 保存
     *
     * @param to 用户职位传输对象
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO save(PositionDetailUserTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 用户职位传输对象
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO update(PositionDetailUserTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 用户职位数据id
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据用户id查询职位详细数据
     *
     * @param user_id 用户id
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findPositionByUser(String user_id) throws SerException {
        return null;
    }

    /**
     * 获取用户职位数据
     *
     * @param user_id 用户id
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO findOneByUser(String user_id) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定职位中
     *
     * @param user_id      用户id
     * @param position_ids 职位id数组
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserPosition(String user_id, String[] position_ids) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定层级中
     *
     * @param user_id        用户id
     * @param arrangement_id 层级id
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserArrangement(String user_id, String... arrangement_id) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定模块中
     *
     * @param user_id   用户id
     * @param module_id 模块id
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserModule(String user_id, String... module_id) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定部门中
     *
     * @param userId        用户id
     * @param departmentIds 部门id
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserDepartment(String userId, String... departmentIds) throws SerException {
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
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 根据id查询用户职位数据
     *
     * @param id 用户职位数据id
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO findById(String id) throws SerException {
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
     * 根据名字获取所在岗位
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
}
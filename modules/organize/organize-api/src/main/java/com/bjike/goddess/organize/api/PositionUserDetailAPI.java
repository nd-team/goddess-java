package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ike on 17-9-6.
 */
public interface PositionUserDetailAPI {

    /**
     * 查询主职状态人员
     * zhuangkaiqin
     */
    default List<String> findMainUser() throws SerException {
        return null;
    }

    /**
     * 查询代理岗位人员
     * zhuangkaiqin
     */
    default List<String> findAgentUser() throws SerException {
        return null;
    }

    /**
     * 获取岗位
     * zhuangkaiqin
     *
     * @return
     * @throws SerException
     */
    default List<String> getPosition() throws SerException {
        return null;
    }

    /**
     * 根据时间获取地区个数
     */
    default Long getAreaNum(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 查询某人的主职位信息
     *
     * @param name
     * @return
     * @throws SerException
     */
    PositionDetailBO getPosition(String name) throws SerException;

    /**
     * chenjunhao
     * 获取某员工所属的部门和岗位
     *
     * @param name
     * @return
     * @throws SerException
     */
    Map<String, String> departPosition(String name) throws SerException;

    /**
     * chenjunhoa
     * 查找某请假人所属的层级和部门
     * @param name
     * @return
     * @throws SerException
     */
    List<String> arrangementAndDepartId(String name) throws SerException;

    /**
     * chenjunhao
     * 获取某请假人的主送人
     *
     * @param name
     * @return
     * @throws SerException
     */
    Set<String> findMains(String name) throws SerException;

    /**
     * chenjunhao
     * 获取某请假人的抄送人
     * @param name
     * @return
     * @throws SerException
     */
    Set<String> findCarbons(String name) throws SerException;

    /**
     * chenjunhao
     * 查找总经理
     * @return
     * @throws SerException
     */
    List<PositionDetailUserBO> findManager() throws SerException;
}

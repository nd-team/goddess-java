package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.InterviewBO;
import com.bjike.goddess.dimission.dto.InterviewDTO;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.InterviewTO;

import java.util.List;

/**
 * 离职管理面谈业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:39 ]
 * @Description: [ 离职管理面谈业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InterviewAPI {

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
     * 保存
     *
     * @param to
     * @return
     * @throws SerException
     */
    default InterviewBO save(InterviewTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to
     * @return
     * @throws SerException
     */
    default InterviewBO update(InterviewTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws SerException
     */
    default InterviewBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     */
    default List<InterviewBO> list(InterviewDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default InterviewBO findById(String id) throws SerException {
        return null;
    }

    /**
     * 是否需项目经理信息离职面谈
     *
     * @param name
     * @return
     * @throws SerException
     */
    default Boolean judgeManager(String name) throws SerException {
        return null;
    }

    /**
     * 是否需模块负责人离职面谈
     *
     * @param name
     * @return
     * @throws SerException
     */
    default Boolean judgePrincipal(String name) throws SerException {
        return null;
    }

    /**
     * 是否需福利模块离职面谈
     *
     * @param name
     * @return
     * @throws SerException
     */
    default Boolean judgeWelfare(String name) throws SerException {
        return null;
    }

    /**
     * 是否需要总经办离职面谈
     *
     * @param name
     * @return
     * @throws SerException
     */
    default Boolean judgeOffice(String name) throws SerException {
        return null;
    }

    /**
     * 是否可挽留
     *
     * @param name
     * @return
     * @throws SerException
     */
    default Boolean detainment(String name) throws SerException {
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
}
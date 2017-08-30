package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfare.bo.PersonalFestivalBO;
import com.bjike.goddess.staffwelfare.bo.ThankStatementBO;
import com.bjike.goddess.staffwelfare.dto.PersonalFestivalDTO;
import com.bjike.goddess.staffwelfare.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.PersonalFestivalTO;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;

/**
 * 个人节日业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 01:56 ]
 * @Description: [ 个人节日业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PersonalFestivalAPI {

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
     * 新增个人节日
     *
     * @param to 个人节日
     * @return 个人节日
     */
    PersonalFestivalBO addModel(PersonalFestivalTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 个人节日
     * @return 个人节日
     */
    PersonalFestivalBO editModel(PersonalFestivalTO to) throws SerException;

    /**
     * 删除个人节日
     *
     * @param id 个人节日ID
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询个人节日
     *
     * @param dto 分页条件
     * @return 个人节日结果集
     */
    List<PersonalFestivalBO> pageList(PersonalFestivalDTO dto) throws SerException;

    /**
     * 一声祝福
     *
     * @param id 个人节日id
     * @param wishStatement 祝福语
     * @throws SerException
     */
    void wish(String id, String wishStatement) throws SerException;

    /**
     * 查询总条数
     * @throws SerException
     */
    Long count(PersonalFestivalDTO dto) throws SerException;

    /**
     * 根据id来查询个人节日数据
     * @param id
     * @return
     * @throws SerException
     */
    PersonalFestivalBO findOne(String id) throws SerException;

    /**
     * 查询所有员工
     */
    List<UserBO> findUserListInOrgan() throws SerException;

    /**
     * 查询所有答谢语
     */
    List<ThankStatementBO> findThank() throws SerException;
}
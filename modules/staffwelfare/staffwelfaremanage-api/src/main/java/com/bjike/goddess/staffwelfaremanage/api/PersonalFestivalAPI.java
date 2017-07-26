package com.bjike.goddess.staffwelfaremanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfaremanage.bo.PersonalFestivalBO;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalDTO;
import com.bjike.goddess.staffwelfaremanage.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfaremanage.to.PersonalFestivalTO;

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
}
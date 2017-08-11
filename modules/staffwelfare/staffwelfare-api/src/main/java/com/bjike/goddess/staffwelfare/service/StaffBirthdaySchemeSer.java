package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffwelfare.bo.StaffBirthdaySchemeBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthdaySchemeDTO;
import com.bjike.goddess.staffwelfare.entity.StaffBirthdayScheme;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.StaffBirthdaySchemeTO;

import java.util.List;

/**
 * 员工生日福利方案业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-05 10:17 ]
 * @Description: [ 员工生日福利方案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffBirthdaySchemeSer extends Ser<StaffBirthdayScheme, StaffBirthdaySchemeDTO> {
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
     * 新增员工生日福利方案
     *
     * @param to 员工生日福利方案
     * @return 员工生日福利方案
     */
    StaffBirthdaySchemeBO insertModel(StaffBirthdaySchemeTO to) throws SerException;

    /**
     * 更新员工生日福利方案
     *
     * @param to 员工生日福利方案
     * @return 员工生日福利方案
     */
    StaffBirthdaySchemeBO updateModel(StaffBirthdaySchemeTO to) throws SerException;

    /**
     * 分页查询员工生日福利方案
     *
     * @param dto 分页条件
     * @return 员工生日福利方案结果集
     */
    List<StaffBirthdaySchemeBO> pageList(StaffBirthdaySchemeDTO dto) throws SerException;

    /**
     * 汇总
     *
     * @param dto 汇总条件
     * @return 员工生日福利方案结果集
     */
    List<StaffBirthdaySchemeBO> collect(StaffBirthdaySchemeDTO dto) throws SerException;

    /**
     * 通过员工生日福利方案
     *
     * @param id 员工生日福利方案Id
     */
    void pass(String id) throws SerException;

    /**
     * 领取福利
     *
     * @param id     方案id
     * @param remark 备注
     */
    void receive(String id, String remark) throws SerException;


    /**
     * 查询员工所属部门和所属地区
     * @return
     * @throws SerException
     */
    List<EntryBasicInfoBO> findEntry() throws SerException;


}
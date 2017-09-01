package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayWelfareBO;
import com.bjike.goddess.staffwelfare.bo.ThankStatementBO;
import com.bjike.goddess.staffwelfare.bo.WishesStatementBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfare.entity.StaffBirthDayWelfare;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;

import java.util.List;

/**
 * 员工生日福利记录业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 10:49 ]
 * @Description: [ 员工生日福利记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffBirthDayWelfareSer extends Ser<StaffBirthDayWelfare, StaffBirthDayWelfareDTO> {
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
     * 分页查询员工生日福利记录
     * @param dto 分页条件
     * @return
     * @throws SerException
     */
    List<StaffBirthDayWelfareBO> pageList(StaffBirthDayWelfareDTO dto) throws SerException;


    /**
     * 查询列表总条数
     * @param dayWelfareDTO
     * @throws SerException
     */
    Long count(StaffBirthDayWelfareDTO dayWelfareDTO) throws SerException;

    /**
     * 查询生日祝福语
     * @param employeeName
     * @throws SerException
     */
    List<WishesStatementBO> findWish(String employeeName) throws SerException;

    /**
     * 查询生日感谢语
     * @param employeeName
     * @throws SerException
     */
    List<ThankStatementBO> findThank(String employeeName) throws SerException;

    /**
     * 一声祝福
     */
    List<String[]> findOnewish(String employeeName) throws SerException;
}
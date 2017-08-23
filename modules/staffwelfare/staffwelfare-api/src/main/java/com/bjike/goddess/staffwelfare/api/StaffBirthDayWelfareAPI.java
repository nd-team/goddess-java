package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayWelfareBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfare.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;

import java.util.List;

/**
* 员工生日福利记录业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-04-07 10:49 ]
* @Description:	[ 员工生日福利记录业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface StaffBirthDayWelfareAPI  {
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
     * 分页查询列表
     * @param dto
     * @throws SerException
     */
    List<StaffBirthDayWelfareBO> pageList(StaffBirthDayWelfareDTO dto) throws SerException;


    /**
     * 查询列表总条数
     * @param dayWelfareDTO
     * @throws SerException
     */
    Long count(StaffBirthDayWelfareDTO dayWelfareDTO) throws SerException;

//    List<> birthdayDetail(String userID) throws SerException;
}
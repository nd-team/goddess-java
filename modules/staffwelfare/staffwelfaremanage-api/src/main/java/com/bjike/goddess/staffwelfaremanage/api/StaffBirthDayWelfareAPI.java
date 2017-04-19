package com.bjike.goddess.staffwelfaremanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfaremanage.bo.StaffBirthDayWelfareBO;
import com.bjike.goddess.staffwelfaremanage.dto.StaffBirthDayWelfareDTO;

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

    List<StaffBirthDayWelfareBO> pageList(StaffBirthDayWelfareDTO dto) throws SerException;

//    List<> birthdayDetail(String userID) throws SerException;
}
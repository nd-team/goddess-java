package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.AssistanceStandardBO;
import com.bjike.goddess.assistance.dto.AssistanceStandardDTO;
import com.bjike.goddess.assistance.to.AssistanceStandardTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 补助标准业务接口
 * @Author:			[ tanghaixiang ]
 * @Date:			[  2017-04-13 09:29 ]
 * @Description:	[ 补助标准业务接口 ]
 * @Version:		[ v1.0.0 ]
 * @Copy:   		[ com.bjike ]
 */
public interface AssistanceStandardAPI  {
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
     * 补助标准列表总条数
     *
     */
    default Long countAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO) throws SerException {
        return null;
    }
    /**
     * 一个补助标准
     * @return class AssistanceStandardBO
     */
    default AssistanceStandardBO getOneById(String id ) throws SerException {return null;}

    /**
     * 补助标准列表
     * @return class AssistanceStandardBO
     */
    default List<AssistanceStandardBO> listAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO) throws SerException {return null;}
    /**
     *  添加
     * @param assistanceStandardTO 补助标准信息
     * @return class AssistanceStandardBO
     */
    default AssistanceStandardBO addAssistanceStandard(AssistanceStandardTO assistanceStandardTO) throws SerException { return null;}

    /**
     *  编辑
     * @param assistanceStandardTO 补助标准信息
     * @return class AssistanceStandardBO
     */
    default AssistanceStandardBO editAssistanceStandard(AssistanceStandardTO assistanceStandardTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteAssistanceStandard(String id ) throws SerException {return;};

    /**
     * 获取工龄标准
     */
    default List<AssistanceStandardBO> getAgeStands( ) throws SerException {return null;};



}
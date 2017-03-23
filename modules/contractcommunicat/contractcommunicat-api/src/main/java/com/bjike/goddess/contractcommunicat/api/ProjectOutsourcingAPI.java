package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;

import java.util.List;

/**
* 项目外包洽谈业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-03-18T09:24:12.802 ]
* @Description:	[ 项目外包洽谈业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProjectOutsourcingAPI  {

    /**
     * 新增项目外包洽谈
     * @param to 项目外包洽谈
     * @return 项目外包洽谈
     */
    ProjectOutsourcingBO saveProjectOutsource(ProjectOutsourcingTO to) throws SerException;

    /**
     * 编辑项目外包洽谈
     * @param to 项目外包洽谈
     * @return 项目外包洽谈
     */
    ProjectOutsourcingBO editProjectOutsource(ProjectOutsourcingTO to) throws SerException;

    /**
     * 删除 项目外包洽谈信息
     * @param id 项目外包洽谈ID
     */
    void delete(String id) throws SerException;

    /**
     * 项目外包洽谈分页查询
     * @param dto 分页条件
     * @return 项目外包洽谈结果集
     */
    List<ProjectOutsourcingBO> pageList(ProjectOutsourcingDTO dto) throws SerException;

    /**
     * 项目外包洽谈汇总
     * @param dto
     * @return 汇总条件
     */
    List<ProjectOutsourcingBO> collect(ProjectOutsourcingDTO dto) throws SerException;

    /**
     * 定时发送汇总
     * @param cycleType 定时器条件
     */
    void setCollectSend(QuartzCycleType cycleType) throws SerException;
}
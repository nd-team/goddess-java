package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;

import java.util.List;

/**
 * 确认问题处理结果业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemHandlingResultAPI {
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
     * 确认问题处理结果列表总条数
     */
    default Long countProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        return null;
    }

    /**
     * 一个确认问题处理结果
     *
     * @return class ProblemHandlingResultBO
     */
    default ProblemHandlingResultBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 确认问题处理结果
     *
     * @param problemHandlingResultDTO 确认问题处理结果dto
     * @return class ProblemHandlingResultBO
     * @throws SerException
     */
    default List<ProblemHandlingResultBO> findListProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        return null;
    }

    /**
     * 添加确认问题处理结果
     *
     * @param problemHandlingResultTO 确认问题处理结果数据to
     * @throws SerException
     */
    default ProblemHandlingResultBO insertProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        return null;
    }

    /**
     * 编辑确认问题处理结果
     *
     * @param problemHandlingResultTO 确认问题处理结果数据to
     * @return class ProblemHandlingResultBO
     * @throws SerException
     */
    default ProblemHandlingResultBO editProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除确认问题处理结果
     *
     * @param id
     * @throws SerException
     */
    default void removeProblemHandlingResult(String id) throws SerException {

    }

    /**
     * 搜索
     *
     * @throws SerException
     */
    default List<ProblemHandlingResultBO> searchProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(ProblemHandlingResultDTO dto) throws SerException;

    /**
     * 获取内部项目名称
     *
     * @return class String
     */
    default List<String> getName() throws SerException {
        return null;
    }

    /**
     * 获取工程类型
     *
     * @return class String
     */
    default List<String> getType() throws SerException {
        return null;
    }

    /**
     * 获取问题对象
     *
     * @return class String
     */
    default List<String> getObject() throws SerException {
        return null;
    }

    /**
     * 获取全部的问题处理人员
     * zhuangkaiqin
     */
    default List<String> getProblemHandler() throws SerException {
        return null;
    }
}
package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectissuehandle.bo.CollectBO;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.entity.ProblemHandlingResult;
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
public interface ProblemHandlingResultSer extends Ser<ProblemHandlingResult, ProblemHandlingResultDTO> {
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
     * 导出
     *
     * @throws SerException
     */
    default String exportExcel(String internalProjectName, String projectType) throws SerException {
        return null;
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
     * 汇总
     *
     * @param areas
     * @return class CollectBO
     */
    default List<CollectBO> collect(String[] areas) throws SerException {
        return null;
    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getArea() throws SerException {
        return null;
    }


}
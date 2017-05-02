package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.entity.ProblemAccept;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;

import java.util.List;

/**
 * 项目执行中的问题受理业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemAcceptSer extends Ser<ProblemAccept, ProblemAcceptDTO> {
    /**
     * 项目执行中的问题受理列表总条数
     */
    default Long countProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        return null;
    }

    /**
     * 一个项目执行中的问题受理
     *
     * @return class ProblemAcceptBO
     */
    default ProblemAcceptBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 项目执行中的问题受理
     *
     * @param problemAcceptDTO 项目执行中的问题受理dto
     * @return class problemAcceptBO
     * @throws SerException
     */
    default List<ProblemAcceptBO> findListProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        return null;
    }

    /**
     * 添加项目执行中的问题受理
     *
     * @param problemAcceptTO 项目执行中的问题受理数据to
     * @throws SerException
     */
    default ProblemAcceptBO insertProblemAccept(ProblemAcceptTO problemAcceptTO) throws SerException {
        return null;
    }

    /**
     * 编辑项目执行中的问题受理
     *
     * @param problemAcceptTO 项目执行中的问题受理数据to
     * @return class problemAcceptBO
     * @throws SerException
     */
    default ProblemAcceptBO editProblemAccept(ProblemAcceptTO problemAcceptTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除项目执行中的问题受理
     *
     * @param id
     * @throws SerException
     */
    default void removeProblemAccept(String id) throws SerException {

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
    default List<ProblemAcceptBO> searchProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        return null;
    }

    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getProblemAcceptArea() throws SerException {
        return null;
    }

    /**
     * 获取外部合同项目名称
     *
     * @return class String
     */
    default List<String> getProblemAcceptExternalContractProjectName() throws SerException {
        return null;
    }

    /**
     * 获取内部项目名称
     *
     * @return class String
     */
    default List<String> getProblemAcceptInternalProjectName() throws SerException {
        return null;
    }


}
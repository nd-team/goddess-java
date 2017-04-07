package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.ProblemDisposeBO;
import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.to.ProblemDisposeTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 项目问题受理和处理业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemDisposeAPI {

    /**
     * 添加项目问题受理和处理
     *
     * @param to 项目问题受理和处理
     * @return 项目问题受理和处理
     */
    ProblemDisposeBO addModel(ProblemDisposeTO to) throws SerException;

    /**
     * 编辑项目问题受理和处理
     *
     * @param to 项目问题受理和处理
     * @return 项目问题受理和处理
     */
    ProblemDisposeBO editModel(ProblemDisposeTO to) throws SerException;

    /**
     * 删除项目问题受理和处理
     *
     * @param id 项目问题受理和处理id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 项目问题受理和处理结果集
     */
    List<ProblemDisposeBO> pageList(ProblemDisposeDTO dto) throws SerException;
}
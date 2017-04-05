package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.ProblemDisposeBO;
import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.entity.ProblemDispose;
import com.bjike.goddess.businessevaluate.to.ProblemDisposeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

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
public interface ProblemDisposeSer extends Ser<ProblemDispose, ProblemDisposeDTO> {

    /**
     * 添加项目问题受理和处理
     *
     * @param to 项目问题受理和处理
     * @return 项目问题受理和处理
     */
    ProblemDisposeBO insertModel(ProblemDisposeTO to) throws SerException;

    /**
     * 编辑项目问题受理和处理
     *
     * @param to 项目问题受理和处理
     * @return 项目问题受理和处理
     */
    ProblemDisposeBO updateModel(ProblemDisposeTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 项目问题受理和处理结果集
     */
    List<ProblemDisposeBO> pageList(ProblemDisposeDTO dto) throws SerException;
}
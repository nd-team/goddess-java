package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.EvaluateProjectInfoBO;
import com.bjike.goddess.businessevaluate.bo.ProjectProfitRateBO;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.to.EvaluateProjectInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 项目基本信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EvaluateProjectInfoSer extends Ser<EvaluateProjectInfo, EvaluateProjectInfoDTO> {

    /**
     * 添加项目评估基本信息
     *
     * @param to 项目评估基本信息
     * @return 项目评估基本信息
     */
    EvaluateProjectInfoBO addModel(EvaluateProjectInfoTO to) throws SerException;

    /**
     * 编辑项目评估基本信息
     *
     * @param to 项目评估基本信息
     * @return 项目评估基本信息
     */
    EvaluateProjectInfoBO editModel(EvaluateProjectInfoTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 项目评估基本信息结果集
     */
    List<EvaluateProjectInfoBO> pageList(EvaluateProjectInfoDTO dto) throws SerException;

    /**
     * 查询项目利润率
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProjectProfitRateBO> profitPageList(EvaluateProjectInfoDTO dto) throws SerException;

    /**
     * 查询利润率最高最低项目信息
     *
     * @return
     * @throws SerException
     */
    List<ProjectProfitRateBO> profitScope() throws SerException;
}
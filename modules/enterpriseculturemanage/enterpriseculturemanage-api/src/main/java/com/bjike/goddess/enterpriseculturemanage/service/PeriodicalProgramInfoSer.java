package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.PeriodicalProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.PeriodicalProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.to.PeriodicalProgramInfoTO;

import java.util.List;

/**
 * 刊物方案信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-01 09:07 ]
 * @Description: [ 刊物方案信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PeriodicalProgramInfoSer extends Ser<PeriodicalProgramInfo, PeriodicalProgramInfoDTO> {

    /**
     * 添加刊物方案信息
     *
     * @param to 刊物方案信息
     * @return 刊物方案信息
     */
    PeriodicalProgramInfoBO insertModel(PeriodicalProgramInfoTO to) throws SerException;

    /**
     * 编辑刊物方案信息
     *
     * @param to 刊物方案信息
     * @return 刊物方案信息
     */
    PeriodicalProgramInfoBO updateModel(PeriodicalProgramInfoTO to) throws SerException;

    /**
     * 审核刊物方案信息
     *
     * @param to 刊物方案信息
     */
    void audit(PeriodicalProgramInfoTO to) throws SerException;

    /**
     * 刊物方案信息分页查询
     *
     * @param dto 分页条件
     * @return 刊物方案信息结果集
     */
    List<PeriodicalProgramInfoBO> pageList(PeriodicalProgramInfoDTO dto) throws SerException;
}
package com.bjike.goddess.enterpriseculturemanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.enterpriseculturemanage.bo.EnterpriseCultureInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.PeriodicalProgramInfoDTO;
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
public interface PeriodicalProgramInfoAPI {

    /**
     * 查询企业文化信息
     *
     * @return 企业文化信息
     */
    List<EnterpriseCultureInfoBO> findInfo() throws SerException;

    /**
     * 添加刊物方案信息
     *
     * @param to 刊物方案信息
     * @return 刊物方案信息
     */
    PeriodicalProgramInfoBO addModel(PeriodicalProgramInfoTO to) throws SerException;

    /**
     * 编辑刊物方案信息
     *
     * @param to 刊物方案信息
     * @return 刊物方案信息
     */
    PeriodicalProgramInfoBO editModel(PeriodicalProgramInfoTO to) throws SerException;

    /**
     * 删除刊物方案信息
     *
     * @param id 刊物方案信息id
     */
    void delete(String id) throws SerException;

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
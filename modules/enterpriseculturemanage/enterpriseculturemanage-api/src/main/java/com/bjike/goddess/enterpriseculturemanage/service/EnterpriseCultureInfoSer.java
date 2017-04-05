package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.enterpriseculturemanage.bo.EnterpriseCultureInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.EnterpriseCultureInfo;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoTO;

import java.util.List;

/**
 * 企业文化信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:05 ]
 * @Description: [ 企业文化信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EnterpriseCultureInfoSer extends Ser<EnterpriseCultureInfo, EnterpriseCultureInfoDTO> {

    /**
     * 添加企业文化信息
     *
     * @param to 企业文化信息
     * @return 企业文化信息
     */
    EnterpriseCultureInfoBO insertModel(EnterpriseCultureInfoTO to) throws SerException;

    /**
     * 编辑企业文化信息
     *
     * @param to 企业文化信息
     * @return 企业文化信息
     */
    EnterpriseCultureInfoBO updateModel(EnterpriseCultureInfoTO to) throws SerException;

    /**
     * 企业文化信息分页查询
     *
     * @param dto 分页条件
     * @return 企业文化信息结果集
     */
    List<EnterpriseCultureInfoBO> pageList(EnterpriseCultureInfoDTO dto) throws SerException;

    /**
     * 根据企业文化信息id查询宣传方案
     *
     * @param id 企业文化信息id
     * @return 宣传方案
     */
    PublicizeProgramInfoBO findPublicize(String id) throws SerException;

    /**
     * 根据企业文化信息id查询刊物方案
     *
     * @param id 企业文化信息id
     * @return 刊物方案
     */
    PeriodicalProgramInfoBO findPeriodical(String id) throws SerException;

    /**
     * 查询解冻状态的企业文化信息
     *
     * @return
     */
    List<EnterpriseCultureInfoBO> findThawAll() throws SerException;
}
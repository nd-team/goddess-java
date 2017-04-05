package com.bjike.goddess.enterpriseculturemanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.enterpriseculturemanage.bo.EnterpriseCultureInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
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
public interface EnterpriseCultureInfoAPI {

    /**
     * 添加企业文化信息
     *
     * @param to 企业文化信息
     * @return 企业文化信息
     */
    EnterpriseCultureInfoBO addModel(EnterpriseCultureInfoTO to) throws SerException;

    /**
     * 企业文化信息
     *
     * @param to 企业文化信息
     * @return 企业文化信息
     */
    EnterpriseCultureInfoBO editModel(EnterpriseCultureInfoTO to) throws SerException;

    /**
     * 删除企业文化信息
     *
     * @param id 企业文化信息id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询企业文化信息
     *
     * @param dto 分页条件
     * @return 企业文化信息结果集
     */
    List<EnterpriseCultureInfoBO> pageList(EnterpriseCultureInfoDTO dto) throws SerException;

    /**
     * 查询宣传方案信息
     *
     * @param id
     * @return
     * @throws SerException
     */
    PublicizeProgramInfoBO findPublicize(String id) throws SerException;

    /**
     * 查询刊物方案信息
     *
     * @param id
     * @return
     * @throws SerException
     */
    PeriodicalProgramInfoBO findPeriodical(String id) throws SerException;
}
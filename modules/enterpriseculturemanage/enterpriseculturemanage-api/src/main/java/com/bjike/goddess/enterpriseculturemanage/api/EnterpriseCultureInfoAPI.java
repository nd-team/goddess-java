package com.bjike.goddess.enterpriseculturemanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.enterpriseculturemanage.bo.EnterpriseCultureInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.dto.PeriodicalProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.dto.PublicizeProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoEditTO;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoTO;
import com.bjike.goddess.enterpriseculturemanage.to.GuidePermissionTO;

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
    EnterpriseCultureInfoBO editModel(EnterpriseCultureInfoEditTO to) throws SerException;

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
     */
    List<PublicizeProgramInfoBO> findPublicize(String id) throws SerException;

    /**
     * 查询刊物方案信息
     *
     * @param id
     * @return
     */
    PeriodicalProgramInfoBO findPeriodical(String id) throws SerException;

    /**
     * 根据ID查询
     * @param id id
     */
    EnterpriseCultureInfoBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(EnterpriseCultureInfoDTO dto) throws SerException;

    List<EnterpriseCultureInfoBO> infos() throws SerException;
}
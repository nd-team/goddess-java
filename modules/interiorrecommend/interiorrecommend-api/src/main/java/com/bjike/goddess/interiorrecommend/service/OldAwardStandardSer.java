package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.interiorrecommend.bo.OldAwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.OldAwardStandardDTO;
import com.bjike.goddess.interiorrecommend.entity.OldAwardStandard;
import com.bjike.goddess.interiorrecommend.to.OldAwardStandardTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;

import java.util.List;

/**
 * 推荐奖励要求标准业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 11:39 ]
 * @Description: [ 推荐奖励要求标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OldAwardStandardSer extends Ser<OldAwardStandard, OldAwardStandardDTO> {

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
     * 新增推荐奖励要求
     *
     * @param to 推荐奖励要求
     * @return 推荐奖励要求
     */
    OldAwardStandardBO insertModel(OldAwardStandardTO to) throws SerException;

    /**
     * 编辑推荐奖励要求
     *
     * @param to 推荐奖励要求
     * @return 推荐奖励要求
     */
    OldAwardStandardBO updateModel(OldAwardStandardTO to) throws SerException;


    /**
     * 分页查询推荐奖励要求
     *
     * @param dto 分页条件
     * @return 推荐奖励要求结果集
     */
    List<OldAwardStandardBO> pageList(OldAwardStandardDTO dto) throws SerException;

    /**
     * 根据id来查询推荐奖励要求标准
     * @param id
     * @throws SerException
     */
    OldAwardStandardBO findOne(String id) throws SerException;


    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     */
    Long count(OldAwardStandardDTO dto) throws SerException;

}
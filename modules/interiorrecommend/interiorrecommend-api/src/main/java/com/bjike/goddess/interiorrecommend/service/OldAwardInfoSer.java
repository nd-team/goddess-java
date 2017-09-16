package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.interiorrecommend.bo.OldAwardInfoBO;
import com.bjike.goddess.interiorrecommend.bo.OldRecommendInfoBO;
import com.bjike.goddess.interiorrecommend.dto.OldAwardInfoDTO;
import com.bjike.goddess.interiorrecommend.dto.OldRecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.entity.OldAwardInfo;
import com.bjike.goddess.interiorrecommend.excel.SonPermissionObject;
import com.bjike.goddess.interiorrecommend.to.OldAwardInfoTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;

import java.util.List;

/**
 * 推荐奖励信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OldAwardInfoSer extends Ser<OldAwardInfo, OldAwardInfoDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑奖励信息
     *
     * @param to 奖励信息
     * @return 奖励信息
     */
    OldAwardInfoBO updateModel(OldAwardInfoTO to) throws SerException;

    /**
     * 分页查询
     * @param dto 分页条件
     */
    List<OldRecommendInfoBO> pageList(OldRecommendInfoDTO dto) throws SerException;

    /**
     * 根据id来查询推荐奖励信息
     * @param id
     * @throws SerException
     */
    OldAwardInfoBO findOne(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     */
    Long count(OldAwardInfoDTO dto) throws SerException;


    /**
     * 根据id来查询单条数据
     * @param id
     * @throws SerException
     */
    OldRecommendInfoBO finOne(String id) throws SerException;


}
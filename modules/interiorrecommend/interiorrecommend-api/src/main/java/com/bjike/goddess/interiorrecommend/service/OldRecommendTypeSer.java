package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.interiorrecommend.bo.OldRecommendTypeBO;
import com.bjike.goddess.interiorrecommend.dto.OldRecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.entity.OldRecommendType;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.OldRecommendTypeTO;

import java.util.List;

/**
 * 推荐类型设定业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 02:10 ]
 * @Description: [ 推荐类型设定业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OldRecommendTypeSer extends Ser<OldRecommendType, OldRecommendTypeDTO> {

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
     * 新增推荐类型设定
     *
     * @param to 推荐类型设定
     * @return 推荐类型设定
     */
    OldRecommendTypeBO insertModel(OldRecommendTypeTO to) throws SerException;

    /**
     * 编辑推荐类型设定
     *
     * @param to 推荐类型设定
     * @return 推荐类型设定
     */
    OldRecommendTypeBO updateModel(OldRecommendTypeTO to) throws SerException;

    /**
     * 分页查询推荐类型设定
     *
     * @param dto 分页条件
     * @return 推荐类型设定结果集
     */
    List<OldRecommendTypeBO> pageList(OldRecommendTypeDTO dto) throws SerException;

    /**
     * 删除推荐类型设定
     *
     * @param id 推荐类型设定id
     */
    void delete(String id) throws SerException;
}
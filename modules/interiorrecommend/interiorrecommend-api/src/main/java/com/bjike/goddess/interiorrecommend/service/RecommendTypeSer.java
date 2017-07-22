package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.interiorrecommend.bo.RecommendTypeBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendType;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendTypeTO;

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
public interface RecommendTypeSer extends Ser<RecommendType, RecommendTypeDTO> {

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
    RecommendTypeBO insertModel(RecommendTypeTO to) throws SerException;

    /**
     * 编辑推荐类型设定
     *
     * @param to 推荐类型设定
     * @return 推荐类型设定
     */
    RecommendTypeBO updateModel(RecommendTypeTO to) throws SerException;

    /**
     * 分页查询推荐类型设定
     *
     * @param dto 分页条件
     * @return 推荐类型设定结果集
     */
    List<RecommendTypeBO> pageList(RecommendTypeDTO dto) throws SerException;
}
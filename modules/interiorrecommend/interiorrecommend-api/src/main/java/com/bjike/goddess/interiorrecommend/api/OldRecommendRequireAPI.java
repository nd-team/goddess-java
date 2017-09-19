package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.*;
import com.bjike.goddess.interiorrecommend.dto.OldRecommendRequireDTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.OldRecommendRequireTO;

import java.util.List;

/**
 * 推荐要求业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OldRecommendRequireAPI {

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
     * 新增推荐要求
     *
     * @param to 推荐要求
     * @return 推荐要求
     */
    OldRecommendRequireBO addModel(OldRecommendRequireTO to) throws SerException;

    /**
     * 编辑推荐要求
     *
     * @param to 推荐要求
     * @return 推荐要求
     */
    OldRecommendRequireBO editModel(OldRecommendRequireTO to) throws SerException;

    /**
     * 删除推荐要求
     *
     * @param id 推荐要求id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询推荐要求
     *
     * @param dto 推荐要求
     * @return 推荐要求结果集
     */
    List<OldRecommendRequireBO> pageList(OldRecommendRequireDTO dto) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     */
    Long count(OldRecommendRequireDTO dto) throws SerException;

    /**
     * 根据id查询单个数据
     *
     * @param id id
     */
    OldRecommendRequireBO findOne(String id) throws SerException;

    /**
     * 查询所有正常状态的推荐方案
     * @throws SerException
     */
    List<RecommendSchemeBO> findRecommend() throws SerException;

    /**
     * 查询所有推荐类型
     * @throws SerException
     */
    List<OldRecommendTypeBO> findType() throws SerException;

    /**
     * 查询所有推荐考核内容
     * @return
     * @throws SerException
     */
    List<OldRecommendAssessDetailBO> findAssess() throws SerException;

    /**
     * 查询所有推荐内容
     * @return
     * @throws SerException
     */
    List<OldRecommendContentBO> findContent() throws SerException;
}
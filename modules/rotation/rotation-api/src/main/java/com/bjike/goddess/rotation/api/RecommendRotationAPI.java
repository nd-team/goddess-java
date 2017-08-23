package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.RecommendRotationBO;
import com.bjike.goddess.rotation.dto.RecommendRotationDTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.RecommendRotationTO;

import java.util.List;

/**
 * 岗位轮换推荐业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:28 ]
 * @Description: [ 岗位轮换推荐业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RecommendRotationAPI {

    /**
     * 保存
     *
     * @param to 岗位轮换推荐传输对象
     * @return
     * @throws SerException
     */
    default RecommendRotationBO save(RecommendRotationTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 岗位轮换推荐传输对象
     * @return
     * @throws SerException
     */
    default RecommendRotationBO update(RecommendRotationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 岗位轮换推荐数据id
     * @return
     * @throws SerException
     */
    default RecommendRotationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 总经办意见
     *
     * @param to 岗位轮换推荐传输对象
     * @return
     * @throws SerException
     */
    default RecommendRotationBO opinion(RecommendRotationTO to) throws SerException {
        return null;
    }

    /**
     * 根据id查询岗位轮换推荐数据
     *
     * @param id 岗位轮换推荐数据id
     * @return
     * @throws SerException
     */
    default RecommendRotationBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 岗位轮换推荐数据传输对象
     * @return
     * @throws SerException
     */
    default List<RecommendRotationBO> maps(RecommendRotationDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

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

}
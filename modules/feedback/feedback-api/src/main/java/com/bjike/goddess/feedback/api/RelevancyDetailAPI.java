package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.RelevancyDetailBO;
import com.bjike.goddess.feedback.dto.RelevancyDetailDTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.RelevancyDetailTO;

import java.util.List;

/**
 * 各模块关联明细业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:23 ]
 * @Description: [ 各模块关联明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RelevancyDetailAPI {
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
     * 各模块关联明细列表总条数
     */
    default Long count(RelevancyDetailDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个各模块关联明细
     *
     * @return class RelevancyDetailBO
     */
    default RelevancyDetailBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 各模块关联明细
     *
     * @param dto 各模块关联明细dto
     * @return class RelevancyDetailBO
     * @throws SerException
     */
    default List<RelevancyDetailBO> list(RelevancyDetailDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加各模块关联明细
     *
     * @param to 各模块关联明细数据to
     * @return class RelevancyDetailBO
     * @throws SerException
     */
    default RelevancyDetailBO insert(RelevancyDetailTO to) throws SerException {
        return null;
    }

    /**
     * 编辑各模块关联明细
     *
     * @param to 各模块关联明细数据to
     * @return class RelevancyDetailBO
     * @throws SerException
     */
    default RelevancyDetailBO edit(RelevancyDetailTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除各模块关联明细
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 获取所有主功能
     *
     * @throws SerException
     */
    default List<String> getMainFunction() throws SerException {
        return null;
    }
}
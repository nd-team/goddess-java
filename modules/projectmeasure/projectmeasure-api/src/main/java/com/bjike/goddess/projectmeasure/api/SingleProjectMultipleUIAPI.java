package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.SingleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectMultipleUITO;

import java.util.List;

/**
 * 单个项目多个界面业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SingleProjectMultipleUIAPI {

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
     * 根据id查询单个项目多个界面
     *
     * @param id 单个项目多个界面唯一标识
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    SingleProjectMultipleUIBO getOne(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 单个项目多个界面dto
     * @throws SerException
     */
    Long count(SingleProjectMultipleUIDTO dto) throws SerException;

    /**
     * 分页查询单个项目多个界面
     *
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    List<SingleProjectMultipleUIBO> list(SingleProjectMultipleUIDTO dto) throws SerException;

    /**
     * 保存单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    void save(SingleProjectMultipleUITO to) throws SerException;

    /**
     * 根据id删除单个项目多个界面
     *
     * @param id 单个项目多个界面唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @throws SerException
     */
    void update(SingleProjectMultipleUITO to) throws SerException;

}
package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmeasure.bo.SingleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectSingleUI;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectSingleUITO;

import java.util.List;

/**
 * 单个项目单个界面业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:48 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SingleProjectSingleUISer extends Ser<SingleProjectSingleUI, SingleProjectSingleUIDTO> {

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
     * 分页查询单个项目单个界面
     *
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    List<SingleProjectSingleUIBO> list(SingleProjectSingleUIDTO dto) throws SerException;

    /**
     * 保存单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    SingleProjectSingleUIBO save(SingleProjectSingleUITO to) throws SerException;

    /**
     * 根据id删除单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @throws SerException
     */
    void update(SingleProjectSingleUITO to) throws SerException;

    /**
     * 根据id查询单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    default SingleProjectSingleUIBO getOne(String id) throws SerException{
        return null;
    }
}
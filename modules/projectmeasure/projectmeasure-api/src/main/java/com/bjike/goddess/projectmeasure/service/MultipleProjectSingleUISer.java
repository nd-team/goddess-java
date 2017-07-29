package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBBO;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIBDTO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUI;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUIB;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUIBTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;

import java.util.List;

/**
 * 多项目单个界面业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MultipleProjectSingleUISer extends Ser<MultipleProjectSingleUI, MultipleProjectSingleUIDTO> {

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
     * 分页查询多项目单个界面
     *
     * @return class MultipleProjectSingleUIBBO
     * @throws SerException
     */
    List<MultipleProjectSingleUIBBO> list(MultipleProjectSingleUIBDTO dto) throws SerException;

    /**
     * 保存多项目单个界面
     *
     * @param to 多项目多个界面to
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    void save(MultipleProjectSingleUIBTO to) throws SerException;

    /**
     * 根据id删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新多项目单个界面
     *
     * @param to 多项目单个界面to
     * @throws SerException
     */
    void update(MultipleProjectSingleUIBTO to) throws SerException;

    /**
     * 根据id查询多项目多个界面
     *
     * @param id 多项目多个界面唯一标识
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    default MultipleProjectSingleUIBO getOne(String id) throws SerException{
        return null;
    }
}
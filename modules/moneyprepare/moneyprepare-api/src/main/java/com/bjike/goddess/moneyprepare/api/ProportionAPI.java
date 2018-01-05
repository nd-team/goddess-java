package com.bjike.goddess.moneyprepare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyprepare.bo.ProportionBO;
import com.bjike.goddess.moneyprepare.dto.ProportionDTO;
import com.bjike.goddess.moneyprepare.to.GuidePermissionTO;
import com.bjike.goddess.moneyprepare.to.ProportionObjectTO;
import com.bjike.goddess.moneyprepare.to.ProportionTO;

import java.util.List;

/**
 * 比例分配业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProportionAPI {


    /**
     * 比例分配列表总条数
     */
    default Long countProportion(ProportionDTO proportionDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取比例分配列表
     *
     * @return class ProportionBO
     */
    default ProportionBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 比例分配信息列表
     *
     * @return class ProportionBO
     */
    default List<ProportionBO> listProportion(ProportionDTO proportionDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param proportionObjectTO 比例分配信息
     * @return class ProportionBO
     */
    default List<ProportionBO> addProportion(ProportionObjectTO proportionObjectTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param proportionTO 比例分配信息
     * @return class ProportionBO
     */
    default ProportionBO editProportion(ProportionTO proportionTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteProportion(String id) throws SerException {
        return;
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

    /**
     * 项目详情编辑
     */
    default List<ProportionBO> editDetail(ProportionTO proportionTO) throws SerException {
        return null;
    }
}
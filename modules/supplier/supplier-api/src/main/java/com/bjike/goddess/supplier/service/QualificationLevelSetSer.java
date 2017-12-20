package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.QualificationLevelSetBO;
import com.bjike.goddess.supplier.dto.QualificationLevelSetDTO;
import com.bjike.goddess.supplier.entity.QualificationLevelSet;
import com.bjike.goddess.supplier.to.QualificationLevelSetTO;
import com.bjike.goddess.supplier.to.SupplierTypeSetTO;

import java.util.List;

/**
 * 资质等级设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:08 ]
 * @Description: [ 资质等级设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QualificationLevelSetSer extends Ser<QualificationLevelSet, QualificationLevelSetDTO> {
    /**
     * 资质等级设置总条数
     */
    default Long countLevelSet(QualificationLevelSetDTO qualificationLevelSetDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取资质等级设置
     *
     * @return class QualificationLevelSetBO
     */
    default QualificationLevelSetBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 资质等级设置列表
     *
     * @return class QualificationLevelSetBO
     */
    default List<QualificationLevelSetBO> listLevelSet(QualificationLevelSetDTO qualificationLevelSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param qualificationLevelSetTO 资质等级设置
     * @return class QualificationLevelSetBO
     */
    default QualificationLevelSetBO addLevelSet(QualificationLevelSetTO qualificationLevelSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param qualificationLevelSetTO 资质等级设置
     * @return class QualificationLevelSetBO
     */
    default QualificationLevelSetBO editLevelSet(QualificationLevelSetTO qualificationLevelSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteLevelSet(String id) throws SerException {
        return;
    }

    /**
     * 获取所有的资质等级
     */
    default List<String> findAllLevel() throws SerException {
        return null;
    }
}
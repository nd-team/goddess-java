package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.IndexNameSetBO;
import com.bjike.goddess.balancecard.dto.IndexNameSetDTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.IndexNameSetTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 指标名称设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:02 ]
 * @Description: [ 指标名称设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndexNameSetAPI {

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
     * 指标名称列表总条数
     */
    default Long countIndexNameSet(IndexNameSetDTO indexNameSetDTO) throws SerException {
        return null;
    }

    /**
     * 指标名称列表id
     * @return class IndexNameSetBO
     */
    default IndexNameSetBO getOneById (String id) throws SerException {return null;}


    /**
     * 指标名称列表
     *
     * @return class IndexNameSetBO
     */
    default List<IndexNameSetBO> listIndexNameSet(IndexNameSetDTO indexNameSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param indexNameSetTO 指标名称信息
     * @return class IndexNameSetBO
     */
    default IndexNameSetBO addIndexNameSet(IndexNameSetTO indexNameSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param indexNameSetTO 指标名称信息
     * @return class IndexNameSetBO
     */
    default IndexNameSetBO editIndexNameSet(IndexNameSetTO indexNameSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteIndexNameSet(String id) throws SerException {
        return;
    }

    ;


    /**
     * 获取所有指标名称
     *
     *
     */
    default List<String> listName() throws SerException {
        return null;
    }


    ;

}
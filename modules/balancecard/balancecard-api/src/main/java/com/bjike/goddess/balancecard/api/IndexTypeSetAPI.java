package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.IndexTypeSetBO;
import com.bjike.goddess.balancecard.dto.IndexTypeSetDTO;
import com.bjike.goddess.balancecard.to.IndexTypeSetTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 指标类型业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 指标类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndexTypeSetAPI {


    /**
     * 指标类型列表总条数
     */
    default Long countIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO) throws SerException {
        return null;
    }

    /**
     * 指标类型列表id
     * @return class IndexTypeSetBO
     */
    default IndexTypeSetBO getOneById (String id) throws SerException {return null;}


    /**
     * 指标类型列表
     *
     * @return class IndexTypeSetBO
     */
    default List<IndexTypeSetBO> listIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param indexTypeSetTO 指标类型信息
     * @return class IndexTypeSetBO
     */
    default IndexTypeSetBO addIndexTypeSet(IndexTypeSetTO indexTypeSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param indexTypeSetTO 指标类型信息
     * @return class IndexTypeSetBO
     */
    default IndexTypeSetBO editIndexTypeSet(IndexTypeSetTO indexTypeSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteIndexTypeSet(String id) throws SerException {
        return;
    }

    ;

    /**
     * 获取所有指标类型
     *
     */
    default List<String> listName(   ) throws SerException {
        return null;
    }


    ;
    
}
package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.RecommendTypeBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.to.RecommendTypeTO;

import java.util.List;

/**
 * 推荐类型设定业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 02:10 ]
 * @Description: [ 推荐类型设定业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RecommendTypeAPI {

    /**
     * 新增推荐类型设定
     *
     * @param to 推荐类型设定
     * @return 推荐类型设定
     */
    RecommendTypeBO addModel(RecommendTypeTO to) throws SerException;

    /**
     * 编辑推荐类型设定
     *
     * @param to 推荐类型设定
     * @return 推荐类型设定
     */
    RecommendTypeBO editModel(RecommendTypeTO to) throws SerException;

    /**
     * 删除推荐类型设定
     *
     * @param id 推荐类型设定id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询推荐类型设定
     *
     * @param dto 分页条件
     * @return 推荐类型设定结果集
     */
    List<RecommendTypeBO> pageList(RecommendTypeDTO dto) throws SerException;

    /**
     * 根据Id查询
     *
     * @param id
     */
    RecommendTypeBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     */
    Long count(RecommendTypeDTO dto) throws SerException;
}
package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.RecommendContentBO;
import com.bjike.goddess.interiorrecommend.bo.RecommendInfoBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.to.RecommendInfoTO;

import java.util.List;

/**
 * 推荐信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RecommendInfoAPI {

    /**
     * 新增推荐信息
     *
     * @param to 推荐信息
     * @return 推荐信息
     */
    RecommendInfoBO addModel(RecommendInfoTO to) throws SerException;

    /**
     * 编辑推荐信息
     *
     * @param to 推荐信息
     * @return 推荐信息
     */
    RecommendInfoBO editModel(RecommendInfoTO to) throws SerException;

    /**
     * 删除推荐信息
     *
     * @param id 推荐信息id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询推荐信息
     *
     * @param dto 分页条件
     * @return 推荐信息结果集
     */
    List<RecommendInfoBO> pageList(RecommendInfoDTO dto) throws SerException;

    /**
     * 采纳审核
     *
     * @param id     推荐信息id
     * @param reason 原因
     * @param accept 是否采纳
     * @version v1
     */
    void acceptAudit(String id, String reason, Boolean accept) throws SerException;

    /**
     * 奖励审核
     *
     * @param id 推荐信息id
     * @param id 推荐信息id
     * @version v1
     */
    void conformAudit(String id, Boolean conform) throws SerException;
}
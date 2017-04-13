package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.interiorrecommend.bo.RecommendSchemeBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendSchemeDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendScheme;
import com.bjike.goddess.interiorrecommend.to.RecommendSchemeTO;

import java.util.List;

/**
 * 推荐方案业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RecommendSchemeSer extends Ser<RecommendScheme, RecommendSchemeDTO> {

    /**
     * 新增推荐方案
     *
     * @param to 推荐方案
     * @return 推荐方案
     */
    RecommendSchemeBO insertModel(RecommendSchemeTO to) throws SerException;

    /**
     * 编辑推荐方案
     *
     * @param to 推荐方案
     * @return 推荐方案
     */
    RecommendSchemeBO updateModel(RecommendSchemeTO to) throws SerException;


    /**
     * 分页查询推荐方案
     *
     * @param dto 推荐方案
     * @return 推荐方案结果集
     */
    List<RecommendSchemeBO> pageList(RecommendSchemeDTO dto) throws SerException;

    /**
     * 综合资源部意见
     *
     * @param to 推荐方案
     */
    void resourcesAudit(RecommendSchemeTO to) throws SerException;

    /**
     * 运营商务部意见
     *
     * @param to 推荐方案
     */
    void operateAudit(RecommendSchemeTO to) throws SerException;

    /**
     * 总经办意见
     *
     * @param to 推荐方案
     */
    void generalAudit(RecommendSchemeTO to) throws SerException;
}
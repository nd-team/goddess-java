package com.bjike.goddess.competitormanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.competitormanage.bo.CompetitorBO;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.to.CompetitorTO;

import java.util.List;

/**
 * 竞争对手信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompetitorAPI {

    /**
     * 新增竞争对手
     *
     * @param to 竞争对手信息
     */
    CompetitorBO saveCompetitor(CompetitorTO to) throws SerException;

    /**
     * 编辑竞争对手
     *
     * @param to 竞争对手信息
     */
    CompetitorBO editCompetitor(CompetitorTO to) throws SerException;

    /**
     * 删除竞争对手
     *
     * @param id 竞争对手信息Id
     */
    void delete(String id) throws SerException;

    /**
     * 编辑竞争对手组织结构信息
     *
     * @param to 竞争对手组织结构信息
     */
    CompetitorBO editOrganization(CompetitorTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     */
    List<CompetitorBO> pageList(CompetitorDTO dto) throws SerException;
}
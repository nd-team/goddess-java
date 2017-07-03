package com.bjike.goddess.competitormanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.competitormanage.bo.CollectionTotalBO;
import com.bjike.goddess.competitormanage.bo.CompetitorCollectBO;
import com.bjike.goddess.competitormanage.dto.CompetitorCollectDTO;
import com.bjike.goddess.competitormanage.to.CompetitorCollectTO;
import com.bjike.goddess.competitormanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 竞争对手汇总业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompetitorCollectAPI {

    /**
     * 新增竞争对手汇总
     *
     * @param to 竞争对手汇总信息
     */
    CompetitorCollectBO save(CompetitorCollectTO to) throws SerException;

    /**
     * 编辑竞争对手汇总
     *
     * @param to 编辑竞争对手汇总信息
     */
    CompetitorCollectBO edit(CompetitorCollectTO to) throws SerException;

    /**
     * 删除竞争对手汇总
     *
     * @param id 竞争对手汇总ID
     */
    void delete(String id) throws SerException;

    /**
     * 冻结竞争对手汇总
     *
     * @param id 结竞争对手汇总id
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻竞争对手汇总
     *
     * @param id 结竞争对手汇总id
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 汇总合计
     *
     * @return 汇总合计结果集
     */
    List<CollectionTotalBO> collectionTotal() throws SerException;

    List<CompetitorCollectBO> pageList(CompetitorCollectDTO dto) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(CompetitorCollectDTO dto) throws SerException;

    /**
     * 根据Id查询汇总条件
     * @param id 汇总id
     * @return 定时器汇总条件
     * @throws SerException
     */
    CompetitorCollectBO findById(String id) throws SerException;

    /**
     * 定时器，每10s轮询一次该接口
     */
    void sendCollectEmail() throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
}
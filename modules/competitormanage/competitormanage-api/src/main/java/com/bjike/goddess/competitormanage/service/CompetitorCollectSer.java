package com.bjike.goddess.competitormanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.competitormanage.bo.CollectionTotalBO;
import com.bjike.goddess.competitormanage.bo.CompetitorCollectBO;
import com.bjike.goddess.competitormanage.dto.CompetitorCollectDTO;
import com.bjike.goddess.competitormanage.entity.CompetitorCollect;
import com.bjike.goddess.competitormanage.to.CompetitorCollectTO;

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
public interface CompetitorCollectSer extends Ser<CompetitorCollect, CompetitorCollectDTO> {

    /**
     * 保存竞争对手汇总
     * @param to 竞争对手汇总信息
     */
    CompetitorCollectBO saveModel(CompetitorCollectTO to) throws SerException;

    /**
     * 编辑竞争对手汇总
     * @param to 竞争对手汇总信息
     */
    CompetitorCollectBO editModel(CompetitorCollectTO to) throws SerException;

    /**
     * 冻结
     * @param id 竞争对手汇总信息id
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻
     * @param id 竞争对手汇总信息id
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 汇总
     * @return 汇总
     */
    List<CollectionTotalBO> collectionTotal() throws SerException;

    /**
     * 分页查询
     * @param dto 分页条件
     */
    List<CompetitorCollectBO> pageList(CompetitorCollectDTO dto) throws SerException;

    /**
     * 发送汇总邮件
     * @throws SerException
     */
    void sendCollectEmail() throws SerException;
}
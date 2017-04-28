package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.bo.ServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeSummaryDTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeSummaryTO;

import java.util.List;

/**
 * 市场招待汇总邮件发送业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketServeSummaryAPI {

    /**
     * 根据id查询市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    MarketServeSummaryBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 市场招待汇总邮件发送dto
     * @throws SerException
     */
    Long count(MarketServeSummaryDTO dto) throws SerException;

    /**
     * 分页查询市场招待汇总邮件发送
     *
     * @param dto 市场招待汇总dto
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    List<MarketServeSummaryBO> list(MarketServeSummaryDTO dto) throws SerException;

    /**
     * 保存市场招待汇总
     *
     * @param to 市场招待汇总to
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    MarketServeSummaryBO save(MarketServeSummaryTO to) throws SerException;

    /**
     * 根据id删除市场招待汇总
     *
     * @param id 市场招待汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 编辑市场招待汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    void update(MarketServeSummaryTO to) throws SerException;

    /**
     * 解冻市场招待汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    void thaw(MarketServeSummaryTO to) throws SerException;

    /**
     * 冻结市场招待汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    void congeal(MarketServeSummaryTO to) throws SerException;

    /**
     * 市场招待汇总
     *
     * @param to 市场招待汇总to
     * @return class ServeSummaryBO
     * @throws SerException
     */
    List<ServeSummaryBO> summarize(MarketServeSummaryTO to) throws SerException;
}
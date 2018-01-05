package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.bo.ServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeSummaryDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeSummary;
import com.bjike.goddess.marketactivitymanage.excel.SonPermissionObject;
import com.bjike.goddess.marketactivitymanage.service.MarketServeSummarySer;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeSummaryTO;
import com.bjike.goddess.marketactivitymanage.to.SummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场招待汇总业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketServeSummaryApiImpl")
public class MarketServeSummaryApiImpl implements MarketServeSummaryAPI {

    @Autowired
    private MarketServeSummarySer marketServeSummarySer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return marketServeSummarySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return marketServeSummarySer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    @Override
    public MarketServeSummaryBO findById(String id) throws SerException {
        MarketServeSummary model = marketServeSummarySer.findById(id);
        return BeanTransform.copyProperties(model, MarketServeSummaryBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 市场招待汇总邮件发送dto
     * @throws SerException
     */
    @Override
    public Long count(MarketServeSummaryDTO dto) throws SerException {
        return marketServeSummarySer.count(dto);
    }

    /**
     * 分页查询市场招待汇总
     *
     * @param dto 市场招待汇总dto
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    @Override
    public List<MarketServeSummaryBO> list(MarketServeSummaryDTO dto) throws SerException {
        return marketServeSummarySer.list(dto);
    }

    /**
     * 保存市场招待汇总
     *
     * @param to 市场招待汇总to
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    @Override
    public MarketServeSummaryBO save(MarketServeSummaryTO to) throws SerException {
        return marketServeSummarySer.save(to);
    }

    /**
     * 删除市场招待汇总
     *
     * @param id 市场招待汇总唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        marketServeSummarySer.remove(id);
    }

    /**
     * 编辑市场招待汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    @Override
    public void update(MarketServeSummaryTO to) throws SerException {
        marketServeSummarySer.update(to);
    }

    /**
     * 解冻市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @throws SerException
     */
    @Override
    public void thaw(String id) throws SerException {
        marketServeSummarySer.thaw(id);
    }

    /**
     * 冻结市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @throws SerException
     */
    @Override
    public void congeal(String id) throws SerException {
        marketServeSummarySer.congeal(id);
    }

    /**
     * 市场招待汇总
     *
     * @param summaryTO
     * @return class MarketServeSummaryVO
     * @throws SerException
     */
    @Override
    public List<ServeSummaryBO> summarize(SummaryTO summaryTO) throws SerException {
        return marketServeSummarySer.summarize(summaryTO);
    }

    @Override
    public void checkSendEmail() throws SerException {
        marketServeSummarySer.checkSendEmail();
    }
}
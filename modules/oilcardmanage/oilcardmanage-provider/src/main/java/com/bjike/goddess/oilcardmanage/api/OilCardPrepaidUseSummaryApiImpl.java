package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardmanage.bo.OilCardPrepaidAreaBO;
import com.bjike.goddess.oilcardmanage.bo.OptionBO;
import com.bjike.goddess.oilcardmanage.service.OilCardPrepaidUseSummarySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 图形化业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-16 09:21 ]
* @Description:	[ 图形化业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("oilCardPrepaidUseSummaryApiImpl")
public class OilCardPrepaidUseSummaryApiImpl implements OilCardPrepaidUseSummaryAPI  {
    @Autowired
    private OilCardPrepaidUseSummarySer oilCardPrepaidUseSummarySer;

    @Override
    public List<OilCardPrepaidAreaBO> dayCollect(String day) throws SerException {
        return oilCardPrepaidUseSummarySer.dayCollect(day);
    }

    @Override
    public List<OilCardPrepaidAreaBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return oilCardPrepaidUseSummarySer.weekCollect(year,month,week);
    }

    @Override
    public List<OilCardPrepaidAreaBO> monthCollect(Integer year, Integer month) throws SerException {
        return oilCardPrepaidUseSummarySer.monthCollect(year,month);
    }

    @Override
    public List<OilCardPrepaidAreaBO> allCollect(String day) throws SerException {
        return oilCardPrepaidUseSummarySer.allCollect(day);
    }

    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        return oilCardPrepaidUseSummarySer.figureShowDay(day);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return oilCardPrepaidUseSummarySer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return oilCardPrepaidUseSummarySer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figrueShowTotal(String endDate) throws SerException {
        return oilCardPrepaidUseSummarySer.figrueShowTotal(endDate);
    }
}
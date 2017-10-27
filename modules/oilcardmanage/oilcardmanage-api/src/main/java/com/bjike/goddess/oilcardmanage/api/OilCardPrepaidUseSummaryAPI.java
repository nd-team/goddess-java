package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardmanage.bo.OilCardPrepaidAreaBO;
import com.bjike.goddess.oilcardmanage.bo.OptionBO;

import java.util.List;

/**
* 图形化业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-16 09:21 ]
* @Description:	[ 图形化业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OilCardPrepaidUseSummaryAPI  {
    /**
     * 管理日汇总
     */
    List<OilCardPrepaidAreaBO> dayCollect(String day) throws SerException;


    /**
     * 管理周汇总
     */
    List<OilCardPrepaidAreaBO> weekCollect(Integer year,Integer month,Integer week) throws SerException;

    /**
     * 管理月汇总
     */
    List<OilCardPrepaidAreaBO> monthCollect(Integer year,Integer month) throws SerException;

    /**
     * 管理累计汇总
     */
    List<OilCardPrepaidAreaBO> allCollect(String day) throws SerException;

    /**
     * 图形化油卡信息管理日汇总
     */
    OptionBO figureShowDay(String day) throws SerException;

    /**
     * 图形化油卡信息管理周汇总
     */
    OptionBO figureShowWeek(Integer year,Integer month,Integer week) throws SerException;

    /**
     * 图形化油卡信息管理月汇总
     */
    OptionBO figureShowMonth(Integer year,Integer month) throws SerException;

    /**
     * 图形化油卡信息管理累计汇总
     */
    OptionBO figrueShowTotal(String endDate) throws SerException;

}
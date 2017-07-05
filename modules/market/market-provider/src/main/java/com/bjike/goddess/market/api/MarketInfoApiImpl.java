package com.bjike.goddess.market.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.market.bo.MarketInfoBO;
import com.bjike.goddess.market.dto.MarketInfoDTO;
import com.bjike.goddess.market.entity.MarketInfo;
import com.bjike.goddess.market.excel.SonPermissionObject;
import com.bjike.goddess.market.service.MarketInfoSer;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 市场信息管理业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.566 ]
 * @Description: [ 市场信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketInfoApiImpl")
public class MarketInfoApiImpl implements MarketInfoAPI {
    @Autowired
    private MarketInfoSer marketInfoSer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return marketInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return marketInfoSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long countMarketInfo(MarketInfoDTO marketInfoDTO) throws SerException {
        return marketInfoSer.countMarketInfo(marketInfoDTO);
    }
    @Override
    public MarketInfoBO getOne(String id) throws SerException {
        return marketInfoSer.getOne(id);
    }
    @Override
    public List<MarketInfoBO> findListMarketInfo(MarketInfoDTO marketInfoDTO) throws SerException {
        return marketInfoSer.findListMarketInfo(marketInfoDTO);
    }

    @Override
    public MarketInfoBO insertMarketInfo(MarketInfoTO marketInfoTO) throws SerException {
//        marketInfoTO.setInfoCollectionDate(DateUtil.dateToString(LocalDate.now()));
//        marketInfoTO.setStartTime(DateUtil.dateToString(LocalDateTime.now()));
//        marketInfoTO.setImportantPoint(DateUtil.dateToString(LocalDateTime.now()));
//        marketInfoTO.setEndTime(DateUtil.dateToString(LocalDateTime.now()));
        return marketInfoSer.insertMarketInfo(marketInfoTO);
    }

    @Override
    public MarketInfoBO editMarketInfo(MarketInfoTO marketInfoTO) throws SerException {

        return marketInfoSer.editMarketInfo(marketInfoTO);
    }

    @Override
    public void removeMarketInfo(String id) throws SerException {
        marketInfoSer.remove(id);
    }
    @Override
    public List<String> getCustomerName() throws SerException {
        return marketInfoSer.getCustomerName();
    }
    @Override
    public byte[] exportExcel(MarketInfoDTO dto) throws SerException{
        return marketInfoSer.exportExcel(dto);
    }

    @Override
    public List<MarketInfoBO> findByOriganizion(String origanizion) throws SerException {
        return marketInfoSer.findByOriganizion(origanizion);
    }
    @Override
    //zhuangkaiqin
    public List<MarketInfoBO> getCollecting(String area, String projectName) throws SerException {
        return marketInfoSer.getCollecting(area,projectName);
    }
}
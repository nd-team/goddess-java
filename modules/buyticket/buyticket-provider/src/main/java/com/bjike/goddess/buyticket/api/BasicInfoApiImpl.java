package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BasicInfoBO;
import com.bjike.goddess.buyticket.dto.BasicInfoDTO;
import com.bjike.goddess.buyticket.service.BasicInfoSer;
import com.bjike.goddess.buyticket.to.BasicInfoTO;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基本信息设置业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:14 ]
 * @Description: [ 基本信息设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("basicInfoApiImpl")
public class BasicInfoApiImpl implements BasicInfoAPI {
    @Autowired
    private BasicInfoSer basicInfoSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return basicInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(BuyGuidePermissionTO guidePermissionTO) throws SerException {
        return basicInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countBasicInfo(BasicInfoDTO basicInfoDTO) throws SerException {
        return basicInfoSer.countBasicInfo(basicInfoDTO);
    }
    @Override
    public BasicInfoBO getOne(String id) throws SerException {
        return basicInfoSer.getOne(id);
    }

    @Override
    public List<BasicInfoBO> findListBasicInfo(BasicInfoDTO basicInfoDTO) throws SerException {
        return basicInfoSer.findListBasicInfo(basicInfoDTO);
    }

    @Override
    public BasicInfoBO insertBasicInfo(BasicInfoTO basicInfoTO) throws SerException {
        return basicInfoSer.insertBasicInfo(basicInfoTO);
    }

    @Override
    public BasicInfoBO editBasicInfo(BasicInfoTO basicInfoTO) throws SerException {
        return basicInfoSer.editBasicInfo(basicInfoTO);
    }

    @Override
    public void removeBasicInfo(String id) throws SerException {
        basicInfoSer.removeBasicInfo(id);
    }

    @Override
    public List<String> findAllTicketCause() throws SerException {
        return basicInfoSer.findAllTicketCause();
    }

    @Override
    public List<String> findAllTicketType() throws SerException {
        return basicInfoSer.findAllTicketType();
    }

    @Override
    public List<String> findAllBuyPattern() throws SerException {
        return basicInfoSer.findAllBuyPattern();
    }

    @Override
    public List<String> findAllSummaryType() throws SerException {
        return basicInfoSer.findAllSummaryType();
    }

    @Override
    public List<String> findAllDataAggregationType() throws SerException {
        return basicInfoSer.findAllDataAggregationType();
    }
}
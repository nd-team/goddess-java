package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.TenderInfoBO;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.service.TenderInfoSer;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.to.TenderInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标书资料业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.333 ]
 * @Description: [ 标书资料业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("tenderInfoApiImpl")
public class TenderInfoApiImpl implements TenderInfoAPI {
    @Autowired
    private TenderInfoSer tenderInfoSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return tenderInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return tenderInfoSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long countTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        return tenderInfoSer.countTenderInfo(tenderInfoDTO);
    }
    @Override
    public TenderInfoBO getOne(String id) throws SerException {
        return tenderInfoSer.getOne(id);
    }

    public TenderInfoBO insertTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        return tenderInfoSer.insertTenderInfo(tenderInfoTO);
    }

    @Override
    public TenderInfoBO editTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        return tenderInfoSer.editTenderInfo(tenderInfoTO);
    }

    @Override
    public void removeTenderInfo(String id) throws SerException {
        tenderInfoSer.removeTenderInfo(id);
    }

    @Override
    public List<TenderInfoBO> findListTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        return tenderInfoSer.findListTenderInfo(tenderInfoDTO);
    }

    @Override
    public byte[] exportExcel(TenderInfoDTO dto) throws SerException{
        return tenderInfoSer.exportExcel(dto);
    }

}
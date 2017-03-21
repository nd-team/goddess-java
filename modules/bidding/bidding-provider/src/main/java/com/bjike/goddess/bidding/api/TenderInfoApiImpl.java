package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.TenderInfoBO;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.entity.TenderInfo;
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
    private TenderInfoAPI tenderInfoAPI;

    public TenderInfoBO insertTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        return tenderInfoAPI.insertTenderInfo(tenderInfoTO);
    }

    @Override
    public TenderInfoBO editTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        return tenderInfoAPI.editTenderInfo(tenderInfoTO);
    }

    @Override
    public void removeTenderInfo(String id) throws SerException {
        tenderInfoAPI.removeTenderInfo(id);
    }

    @Override
    public List<TenderInfo> findListTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        return tenderInfoAPI.findListTenderInfo(tenderInfoDTO);
    }

    @Override
    public String exportExcel(String projectName) throws SerException {
        return tenderInfoAPI.exportExcel(projectName);
    }

    @Override
    public void upload() throws SerException {
        tenderInfoAPI.upload();

    }

    @Override
    public void uploadAttachments() throws SerException {
        tenderInfoAPI.uploadAttachments();
    }

}
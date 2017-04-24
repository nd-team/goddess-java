package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.InsureRecordBO;
import com.bjike.goddess.businsurance.dto.InsureRecordDTO;
import com.bjike.goddess.businsurance.service.InsureRecordSer;
import com.bjike.goddess.businsurance.to.InsureRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 意外险记录业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 意外险记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("insureRecordApiImpl")
public class InsureRecordApiImpl implements InsureRecordAPI {


    @Autowired
    private InsureRecordSer insureRecordSer;

    @Override
    public Long countInsureRecord(InsureRecordDTO insureRecordDTO) throws SerException {
        return insureRecordSer.count(insureRecordDTO);
    }

    @Override
    public List<InsureRecordBO> listInsureRecord(InsureRecordDTO insureRecordDTO) throws SerException {
        return insureRecordSer.listInsureRecord(insureRecordDTO);
    }

    @Override
    public InsureRecordBO addInsureRecord(InsureRecordTO insureRecordTO) throws SerException {
        return insureRecordSer.addInsureRecord(insureRecordTO);
    }

    @Override
    public InsureRecordBO editInsureRecord(InsureRecordTO insureRecordTO) throws SerException {
        return insureRecordSer.editInsureRecord(insureRecordTO);
    }

    @Override
    public void deleteInsureRecord(String id) throws SerException {
        insureRecordSer.deleteInsureRecord(id);
    }



    @Override
    public InsureRecordBO getInsureRecord(String id) throws SerException {
        return insureRecordSer.getInsureRecord(id);
    }
    
}
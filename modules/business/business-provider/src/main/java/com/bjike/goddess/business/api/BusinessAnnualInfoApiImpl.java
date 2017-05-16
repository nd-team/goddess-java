package com.bjike.goddess.business.api;

import com.bjike.goddess.business.bo.BusinessAnnualInfoBO;
import com.bjike.goddess.business.dto.BusinessAnnualInfoDTO;
import com.bjike.goddess.business.service.BusinessAnnualInfoSer;
import com.bjike.goddess.business.to.BusinessAnnualInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 工商年检信息业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessAnnualInfoApiImpl")
public class BusinessAnnualInfoApiImpl implements BusinessAnnualInfoAPI {
    @Autowired
    private BusinessAnnualInfoSer businessAnnualInfoSer;
    @Override
    public Long countBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        return businessAnnualInfoSer.countBusinessAnnualInfo(businessAnnualInfoDTO);
    }
    @Override
    public BusinessAnnualInfoBO getOne(String id) throws SerException {
        return businessAnnualInfoSer.getOne(id);
    }

    @Override
    public List<BusinessAnnualInfoBO> findListBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        return businessAnnualInfoSer.findListBusinessAnnualInfo(businessAnnualInfoDTO);
    }

    @Override
    public BusinessAnnualInfoBO insertBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        businessAnnualInfoTO.setSubmitDate(DateUtil.dateToString(LocalDate.now()));
        return businessAnnualInfoSer.insertBusinessAnnualInfo(businessAnnualInfoTO);
    }

    @Override
    public BusinessAnnualInfoBO editBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        return businessAnnualInfoSer.editBusinessAnnualInfo(businessAnnualInfoTO);
    }

    @Override
    public void removeBusinessAnnualInfo(String id) throws SerException {
        businessAnnualInfoSer.removeBusinessAnnualInfo(id);
    }

    @Override
    public void upload() throws SerException {
        businessAnnualInfoSer.upload();

    }

    @Override
    public void download() throws SerException {
        businessAnnualInfoSer.download();

    }

}
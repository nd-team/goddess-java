package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.FacilityInformationBO;
import com.bjike.goddess.qualifications.service.FacilityInformationSer;
import com.bjike.goddess.qualifications.to.FacilityInformationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备信息业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("facilityInformationApiImpl")
public class FacilityInformationApiImpl implements FacilityInformationAPI {

    @Autowired
    private FacilityInformationSer facilityInformationSer;

    @Override
    public FacilityInformationBO save(FacilityInformationTO to) throws SerException {
        return facilityInformationSer.save(to);
    }

    @Override
    public FacilityInformationBO update(FacilityInformationTO to) throws SerException {
        return facilityInformationSer.update(to);
    }

    @Override
    public FacilityInformationBO delete(String id) throws SerException {
        return facilityInformationSer.delete(id);
    }

    @Override
    public List<FacilityInformationBO> all() throws SerException {
        return facilityInformationSer.all();
    }
}
package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.EnterpriseQualificationBO;
import com.bjike.goddess.supplier.service.EnterpriseQualificationSer;
import com.bjike.goddess.supplier.to.EnterpriseQualificationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业资质业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:47:02.263 ]
 * @Description: [ 企业资质业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("enterpriseQualificationApiImpl")
public class EnterpriseQualificationApiImpl implements EnterpriseQualificationAPI {

    @Autowired
    private EnterpriseQualificationSer enterpriseQualificationSer;

    @Override
    public List<EnterpriseQualificationBO> findByInformation(String info_id) throws SerException {
        return enterpriseQualificationSer.findByInformation(info_id);
    }

    @Override
    public EnterpriseQualificationBO save(EnterpriseQualificationTO to) throws SerException {
        return enterpriseQualificationSer.save(to);
    }

    @Override
    public EnterpriseQualificationBO update(EnterpriseQualificationTO to) throws SerException {
        return enterpriseQualificationSer.update(to);
    }

    @Override
    public EnterpriseQualificationBO delete(String id) throws SerException {
        return enterpriseQualificationSer.delete(id);
    }
}
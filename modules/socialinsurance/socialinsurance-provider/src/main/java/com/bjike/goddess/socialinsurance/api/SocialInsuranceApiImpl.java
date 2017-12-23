package com.bjike.goddess.socialinsurance.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceDTO;
import com.bjike.goddess.socialinsurance.service.SocialInsuranceSer;
import com.bjike.goddess.socialinsurance.to.SocialInsuranceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社会保险管理业务接口实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-21 04:23 ]
 * @Description: [ 社会保险管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("socialInsuranceApiImpl")
public class SocialInsuranceApiImpl implements SocialInsuranceAPI {

    @Autowired
    SocialInsuranceSer socialInsuranceSer;

    @Override
    public Long count(SocialInsuranceDTO dto) throws SerException {
        return socialInsuranceSer.count(dto);
    }

    @Override
    public List<SocialInsuranceBO> list(SocialInsuranceDTO dto) throws SerException {
        return socialInsuranceSer.list(dto);
    }

    @Override
    public SocialInsuranceBO getOne(String id) throws SerException {
        return socialInsuranceSer.getOne(id);
    }

    @Override
    public void add(SocialInsuranceTO to) throws SerException {
        socialInsuranceSer.add(to);
    }

    @Override
    public void update(SocialInsuranceTO to) throws SerException {
        socialInsuranceSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        socialInsuranceSer.delete(id);
    }

    @Override
    public void importExcel(List<SocialInsuranceTO> tos) throws SerException {
        socialInsuranceSer.importExcel(tos);
    }

    @Override
    public byte[] exportExcel(SocialInsuranceDTO dto) throws SerException {
        return socialInsuranceSer.exportExcel(dto);
    }
}
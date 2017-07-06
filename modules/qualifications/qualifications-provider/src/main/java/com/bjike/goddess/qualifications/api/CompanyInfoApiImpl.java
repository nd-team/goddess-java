package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.CompanyInfoBO;
import com.bjike.goddess.qualifications.dto.CompanyInfoDTO;
import com.bjike.goddess.qualifications.service.CompanyInfoSer;
import com.bjike.goddess.qualifications.to.CompanyInfoTO;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司基本信息业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:38 ]
 * @Description: [ 公司基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("companyInfoApiImpl")
public class CompanyInfoApiImpl implements CompanyInfoAPI {

    @Autowired
    private CompanyInfoSer companyInfoSer;

    @Override
    public CompanyInfoBO save(CompanyInfoTO to) throws SerException {
        return companyInfoSer.save(to);
    }

    @Override
    public CompanyInfoBO update(CompanyInfoTO to) throws SerException {
        return companyInfoSer.update(to);
    }

    @Override
    public CompanyInfoBO delete(String id) throws SerException {
        return companyInfoSer.delete(id);
    }

    @Override
    public List<CompanyInfoBO> all() throws SerException {
        return companyInfoSer.all();
    }

    @Override
    public List<CompanyInfoBO> maps(CompanyInfoDTO dto) throws SerException {
        return companyInfoSer.maps(dto);
    }

    @Override
    public Integer getTotal() throws SerException {
        return companyInfoSer.getTotal();
    }

    @Override
    public CompanyInfoBO getById(String id) throws SerException {
        return companyInfoSer.getById(id);
    }
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return companyInfoSer.guidePermission( guidePermissionTO );
    }
}
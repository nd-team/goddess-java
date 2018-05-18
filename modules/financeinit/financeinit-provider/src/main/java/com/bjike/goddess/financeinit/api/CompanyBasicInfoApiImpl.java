package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.CompanyBasicInfoBO;
import com.bjike.goddess.financeinit.dto.CompanyBasicInfoDTO;
import com.bjike.goddess.financeinit.service.CompanyBasicInfoSer;
import com.bjike.goddess.financeinit.to.CompanyBasicInfoTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司基本信息业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:08 ]
 * @Description: [ 公司基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("companyBasicInfoApiImpl")
public class CompanyBasicInfoApiImpl implements CompanyBasicInfoAPI {
    @Autowired
    private CompanyBasicInfoSer companyBasicInfoSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return companyBasicInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return companyBasicInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countBasicInfo(CompanyBasicInfoDTO companyBasicInfoDTO) throws SerException {
        return companyBasicInfoSer.countBasicInfo(companyBasicInfoDTO);
    }

    @Override
    public CompanyBasicInfoBO getOneById(String id) throws SerException {
        return companyBasicInfoSer.getOneById(id);
    }

    @Override
    public List<CompanyBasicInfoBO> listBaseInfo(CompanyBasicInfoDTO companyBasicInfoDTO) throws SerException {
        return companyBasicInfoSer.listBaseInfo(companyBasicInfoDTO);
    }

    @Override
    public CompanyBasicInfoBO addBaseInfo(CompanyBasicInfoTO companyBasicInfoTO) throws SerException {
        return companyBasicInfoSer.addBaseInfo(companyBasicInfoTO);
    }

    @Override
    public CompanyBasicInfoBO editBaseInfo(CompanyBasicInfoTO companyBasicInfoTO) throws SerException {
        return companyBasicInfoSer.editBaseInfo(companyBasicInfoTO);
    }

    @Override
    public void deleteBaseInfo(String id) throws SerException {
        companyBasicInfoSer.deleteBaseInfo(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return companyBasicInfoSer.exportExcel();
    }

    @Override
    public List<String> findCompanyName() throws SerException {
        return companyBasicInfoSer.findCompanyName();
    }

    @Override
    public CompanyBasicInfoBO findByCompanyName(String companyName) throws SerException {
        return companyBasicInfoSer.findByCompanyName(companyName);
    }
}
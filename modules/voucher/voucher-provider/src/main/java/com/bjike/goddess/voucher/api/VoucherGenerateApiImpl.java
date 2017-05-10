package com.bjike.goddess.voucher.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.service.VoucherGenerateSer;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 记账凭证生成业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("voucherGenerateApiImpl")
public class VoucherGenerateApiImpl implements VoucherGenerateAPI {

    @Autowired
    private VoucherGenerateSer voucherGenerateSer;

    @Override
    public VoucherGenerateBO getById(String id) throws SerException {
        return voucherGenerateSer.getById(id);
    }

    @Override
    public Long countVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.count(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listVoucherGenerate(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listNoPage(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listNoPage(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> addVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.addVoucherGenerate(voucherGenerateTO);
    }

    @Override
    public VoucherGenerateBO editVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.editVoucherGenerate(voucherGenerateTO);
    }

    @Override
    public void deleteVoucherGenerate(String id) throws SerException {
        voucherGenerateSer.deleteVoucherGenerate(id);
    }

    @Override
    public Long countAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countAudit(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listAudit(voucherGenerateDTO);
    }

    @Override
    public VoucherGenerateBO split(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.split(voucherGenerateTO);
    }

    @Override
    public VoucherGenerateBO audit(String id) throws SerException {
        return voucherGenerateSer.audit(id);
    }

    @Override
    public Long countAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countAudited(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listAudited(voucherGenerateDTO);
    }

    @Override
    public VoucherGenerateBO posting(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.posting(voucherGenerateTO);
    }

    @Override
    public VoucherGenerateBO antiAudit(String id) throws SerException {
        return voucherGenerateSer.antiAudit(id);
    }

    @Override
    public List<VoucherGenerateBO> collectSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.collectSub(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> collectArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.collectArea(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> collectGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.collectGroup(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> collectPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.collectPname(voucherGenerateDTO);
    }

    @Override
    public Long countChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countChecked(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listChecked(voucherGenerateDTO);
    }

    @Override
    public VoucherGenerateBO antiPosting(String id) throws SerException {
        return voucherGenerateSer.antiPosting(id);
    }

    @Override
    public VoucherGenerateBO checkAccount(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.checkAccount(voucherGenerateTO);
    }

    @Override
    public List<VoucherGenerateBO> ctTransSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctTransSub(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctTransArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctTransArea(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctTransGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctTransGroup(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctTransPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctTransPname(voucherGenerateDTO);
    }

    @Override
    public Long countCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countCkRecord(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listCkRecord(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctCkSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctCkSub(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctCkArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctCkArea(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctCkGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctCkGroup(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctCkPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctCkPname(voucherGenerateDTO);
    }

    @Override
    public Long countRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countRecord(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listRecord(voucherGenerateDTO);
    }

    @Override
    public List<String> listFirstSubject() throws SerException {
        return voucherGenerateSer.listFirstSubject();
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
        return voucherGenerateSer.listSubByFirst(firstSub);
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return voucherGenerateSer.listTubByFirst(firstSub, secondSub);
    }

    @Override
    public List<String> listArea() throws SerException {
        return voucherGenerateSer.listArea();
    }

    @Override
    public List<String> listProject() throws SerException {
        return voucherGenerateSer.listProject();
    }

    @Override
    public List<String> listGroup() throws SerException {
        return voucherGenerateSer.listGroup();
    }

    @Override
    public List<VoucherGenerateBO> findFundRecord(VoucherGenerateDTO dto) throws SerException {
        return voucherGenerateSer.findFundRecord(dto);
    }

    @Override
    public List<VoucherGenerateBO> listStatistic(VoucherGenerateDTO voucherGenerateDTO, String condition) throws SerException {
        return voucherGenerateSer.listStatistic(voucherGenerateDTO, condition);
    }
}
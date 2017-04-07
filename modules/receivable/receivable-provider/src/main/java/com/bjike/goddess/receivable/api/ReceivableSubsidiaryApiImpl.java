package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.bo.ReceivableSubsidiaryBO;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.service.ReceivableSubsidiarySer;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 回款明细业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("receivableSubsidiaryApiImpl")
public class ReceivableSubsidiaryApiImpl implements ReceivableSubsidiaryAPI {
    @Autowired
    private ReceivableSubsidiarySer receivableSubsidiarySer;
    @Override
    public List<ReceivableSubsidiaryBO> findListReceivableSubsidiary(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws SerException {
        return receivableSubsidiarySer.findListReceivableSubsidiary(receivableSubsidiaryDTO);
    }

    @Override
    public ReceivableSubsidiaryBO insertReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return receivableSubsidiarySer.insertReceivableSubsidiary(receivableSubsidiaryTO);
    }

    @Override
    public ReceivableSubsidiaryBO editReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return receivableSubsidiarySer.editReceivableSubsidiary(receivableSubsidiaryTO);
    }

    @Override
    public void removeReceivableSubsidiary(String id) throws SerException {
        receivableSubsidiarySer.removeReceivableSubsidiary(id);
    }
    @Override
    public void editDate(ReceivableSubsidiary receivableSubsidiary, String auditStatusStr, String countStatusStr, String billStatusStr, String planStatusStr) throws SerException {
        receivableSubsidiarySer.editDate(receivableSubsidiary, auditStatusStr, countStatusStr, billStatusStr, planStatusStr);
    }
    @Override
    public String exportExcel(String area,String start,String end) throws SerException {
        return receivableSubsidiarySer.exportExcel(area, start, end);
    }
    @Override
    public void input() throws SerException {
        receivableSubsidiarySer.input();

    }
    @Override
    public List<ReceivableSubsidiaryBO> collectArea(String[] area) throws SerException {
        return receivableSubsidiarySer.collectArea(area);
    }
    @Override
    public List<ReceivableSubsidiaryBO> collectAreaDetail(String[] area) throws SerException {
        return receivableSubsidiarySer.collectAreaDetail(area);
    }
    @Override
    public List<ReceivableSubsidiaryBO> collectInnerName(String[] innerName) throws SerException {
        return receivableSubsidiarySer.collectInnerName(innerName);
    }
    @Override
    public List<ReceivableSubsidiaryBO> collectInnerNameDetail(String[] innerName) throws SerException {
        return receivableSubsidiarySer.collectInnerNameDetail(innerName);
    }
    @Override
    public List<ReceivableSubsidiaryBO> collectContractor(String[] contractor) throws SerException {
        return receivableSubsidiarySer.collectContractor(contractor);
    }
    @Override
    public List<ReceivableSubsidiaryBO> collectContractorDetail(String[] contractor) throws SerException {
        return receivableSubsidiarySer.collectContractorDetail(contractor);
    }
    @Override
    public List<ReceivableSubsidiaryBO> collectCompare(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return receivableSubsidiarySer.collectCompare(receivableSubsidiaryTO);
    }
    @Override
    public ReceivableSubsidiaryBO sendReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return receivableSubsidiarySer.sendReceivableSubsidiary(receivableSubsidiaryTO);
    }



}
package com.bjike.goddess.businesscommission.api;

import com.bjike.goddess.businesscommission.bo.QuotaBO;
import com.bjike.goddess.businesscommission.bo.QuotaCollectBO;
import com.bjike.goddess.businesscommission.dto.QuotaDTO;
import com.bjike.goddess.businesscommission.service.QuotaSer;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.QuotaTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务提成权重分配表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-26 11:38 ]
 * @Description: [ 业务提成权重分配表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("quotaApiImpl")
public class QuotaApiImpl implements QuotaAPI {
    @Autowired
    private QuotaSer quotaSer;

    @Override
    public void addQuota(QuotaTO to) throws SerException {
        quotaSer.addQuota(to);
    }

    @Override
    public void editQuota(QuotaTO to) throws SerException {
        quotaSer.editQuota(to);
    }

    @Override
    public void deleteQuota(String id) throws SerException {
        quotaSer.deleteQuota(id);
    }

    @Override
    public Long countQuota(QuotaDTO dto) throws SerException {
        return quotaSer.countQuota(dto);
    }

    @Override
    public QuotaBO getOneById(String id) throws SerException {
        return quotaSer.getOneById(id);
    }

    @Override
    public List<QuotaBO> listQuota(QuotaDTO dto) throws SerException {
        return quotaSer.listQuota(dto);
    }

    @Override
    public byte[] exportExcel(QuotaDTO dto) throws SerException {
        return quotaSer.exportExcel(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return quotaSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return quotaSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<QuotaCollectBO> dayCollect(String day) throws SerException {
        return quotaSer.dayCollect(day);
    }

    @Override
    public List<QuotaCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return quotaSer.weekCollect(year, month, week);
    }

    @Override
    public List<QuotaCollectBO> monthCollect(String month) throws SerException {
        return quotaSer.monthCollect(month);
    }

    @Override
    public List<QuotaCollectBO> totalCollect() throws SerException {
        return quotaSer.totalCollect();
    }
}
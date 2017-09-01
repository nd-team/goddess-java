package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ShareOutBonusCaseBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareOutBonusManageBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareRosterBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareRosterDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusManageDTO;
import com.bjike.goddess.shareholdersmanage.service.ShareOutBonusManageSer;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOutBonusManageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分红管理业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("shareOutBonusManageApiImpl")
public class ShareOutBonusManageApiImpl implements ShareOutBonusManageAPI {
    @Autowired
    private ShareOutBonusManageSer shareOutBonusManageSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return shareOutBonusManageSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return shareOutBonusManageSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countOutBonus(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return shareOutBonusManageSer.countOutBonus(shareOutBonusManageDTO);
    }

    @Override
    public ShareOutBonusManageBO getOne(String id) throws SerException {
        return shareOutBonusManageSer.getOne(id);
    }

    @Override
    public List<ShareOutBonusManageBO> findList(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return shareOutBonusManageSer.findList(shareOutBonusManageDTO);
    }

    @Override
    public ShareOutBonusManageBO save(ShareOutBonusManageTO shareOutBonusManageTO) throws SerException {
        return shareOutBonusManageSer.save(shareOutBonusManageTO);
    }

    @Override
    public ShareOutBonusManageBO edit(ShareOutBonusManageTO shareOutBonusManageTO) throws SerException {
        return shareOutBonusManageSer.edit(shareOutBonusManageTO);
    }

    @Override
    public void delete(String id) throws SerException {
        shareOutBonusManageSer.delete(id);
    }

    @Override
    public List<ShareOutBonusCaseBO> findShareCase(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return shareOutBonusManageSer.findShareCase(shareOutBonusManageDTO);
    }

    @Override
    public List<ShareOutBonusCaseBO> summShareCase(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return shareOutBonusManageSer.summShareCase(shareOutBonusManageDTO);
    }

    @Override
    public List<ShareRosterBO> findShareRoster(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return shareOutBonusManageSer.findShareRoster(shareOutBonusManageDTO);
    }

    @Override
    public List<ShareRosterDetailBO> findShareRosterDetail(String shareholderName) throws SerException {
        return shareOutBonusManageSer.findShareRosterDetail(shareholderName);
    }

    @Override
    public List<ShareRosterBO> summShareRoster(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return shareOutBonusManageSer.summShareRoster(shareOutBonusManageDTO);
    }

    @Override
    public byte[] exportExcel(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return shareOutBonusManageSer.exportExcel(shareOutBonusManageDTO);
    }
}
package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ShareOutBonusDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusDetailDTO;
import com.bjike.goddess.shareholdersmanage.service.ShareOutBonusDetailSer;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOutBonusDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分红明细业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:55 ]
 * @Description: [ 分红明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("shareOutBonusDetailApiImpl")
public class ShareOutBonusDetailApiImpl implements ShareOutBonusDetailAPI {
    @Autowired
    private ShareOutBonusDetailSer shareOutBonusDetailSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return shareOutBonusDetailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return shareOutBonusDetailSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countOutBonus(ShareOutBonusDetailDTO shareOutBonusDetailDTO) throws SerException {
        return shareOutBonusDetailSer.countOutBonus(shareOutBonusDetailDTO);
    }

    @Override
    public ShareOutBonusDetailBO getOne(String id) throws SerException {
        return shareOutBonusDetailSer.getOne(id);
    }

    @Override
    public List<ShareOutBonusDetailBO> findListBySharId(String ShareOutBonusManageId) throws SerException {
        return shareOutBonusDetailSer.findListBySharId(ShareOutBonusManageId);
    }

    @Override
    public ShareOutBonusDetailBO save(ShareOutBonusDetailTO shareOutBonusDetailTO) throws SerException {
        return shareOutBonusDetailSer.save(shareOutBonusDetailTO);
    }

    @Override
    public ShareOutBonusDetailBO edit(ShareOutBonusDetailTO shareOutBonusDetailTO) throws SerException {
        return shareOutBonusDetailSer.edit(shareOutBonusDetailTO);
    }

    @Override
    public void delete(String id) throws SerException {
        shareOutBonusDetailSer.delete(id);
    }

    @Override
    public void deleteByShareId(String shareOutBonusManageId) throws SerException {
        shareOutBonusDetailSer.deleteByShareId(shareOutBonusManageId);
    }

    @Override
    public Double computAmount(String ShareOutBonusManageId, Double Propor) throws SerException {
        return shareOutBonusDetailSer.computAmount(ShareOutBonusManageId, Propor);
    }

    @Override
    public Double computIncomeTax(Double shareOutBonusAmount, Double incomeTaxPropor) throws SerException {
        return shareOutBonusDetailSer.computIncomeTax(shareOutBonusAmount,incomeTaxPropor);
    }
}
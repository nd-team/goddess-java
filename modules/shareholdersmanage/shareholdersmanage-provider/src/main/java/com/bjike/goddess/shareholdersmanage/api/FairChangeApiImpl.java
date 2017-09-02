package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.FairChangeBO;
import com.bjike.goddess.shareholdersmanage.bo.FairTypeAndPerBO;
import com.bjike.goddess.shareholdersmanage.dto.FairChangeDTO;
import com.bjike.goddess.shareholdersmanage.service.FairChangeSer;
import com.bjike.goddess.shareholdersmanage.to.FairChangeTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公允值变动业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fairChangeApiImpl")
public class FairChangeApiImpl implements FairChangeAPI {
    @Autowired
    private FairChangeSer fairChangeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return fairChangeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return fairChangeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countFair(FairChangeDTO fairChangeDTO) throws SerException {
        return fairChangeSer.countFair(fairChangeDTO);
    }

    @Override
    public FairChangeBO getOne(String id) throws SerException {
        return fairChangeSer.getOne(id);
    }

    @Override
    public List<FairChangeBO> findList(FairChangeDTO fairChangeDTO) throws SerException {
        return fairChangeSer.findList(fairChangeDTO);
    }

    @Override
    public FairChangeBO save(FairChangeTO fairChangeTO) throws SerException {
        return fairChangeSer.save(fairChangeTO);
    }

    @Override
    public FairChangeBO edit(FairChangeTO fairChangeTO) throws SerException {
        return fairChangeSer.edit(fairChangeTO);
    }

    @Override
    public List<FairTypeAndPerBO> typeAndPer() throws SerException {
        return fairChangeSer.typeAndPer();
    }

    @Override
    public Double findPriceByType(String equityType) throws SerException {
        return fairChangeSer.findPriceByType(equityType);
    }
}
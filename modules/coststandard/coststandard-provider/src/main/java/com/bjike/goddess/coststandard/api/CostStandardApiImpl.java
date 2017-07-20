package com.bjike.goddess.coststandard.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.coststandard.bo.CostStandardBO;
import com.bjike.goddess.coststandard.bo.CostStandardOpinionBO;
import com.bjike.goddess.coststandard.dto.CostStandardDTO;
import com.bjike.goddess.coststandard.excel.SonPermissionObject;
import com.bjike.goddess.coststandard.service.CostStandardSer;
import com.bjike.goddess.coststandard.to.CostStandardTO;
import com.bjike.goddess.coststandard.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 费用标准业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:22 ]
 * @Description: [ 费用标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("costStandardApiImpl")
public class CostStandardApiImpl implements CostStandardAPI {

    @Autowired
    private CostStandardSer costStandardSer;

    @Override
    public CostStandardBO save(CostStandardTO to) throws SerException {
        return costStandardSer.save(to);
    }

    @Override
    public CostStandardBO update(CostStandardTO to) throws SerException {
        return costStandardSer.update(to);
    }

    @Override
    public CostStandardBO delete(String id) throws SerException {
        return costStandardSer.delete(id);
    }

    @Override
    public CostStandardBO congeal(String id) throws SerException {
        return costStandardSer.congeal(id);
    }

    @Override
    public CostStandardBO thaw(String id) throws SerException {
        return costStandardSer.thaw(id);
    }

    @Override
    public CostStandardBO getById(String id) throws SerException {
        return costStandardSer.getById(id);
    }

    @Override
    public List<CostStandardBO> maps(CostStandardDTO dto) throws SerException {
        return costStandardSer.maps(dto);
    }

    @Override
    public List<CostStandardBO> findThaw() throws SerException {
        return costStandardSer.findThaw();
    }

    @Override
    public Long getTotal() throws SerException {
        return costStandardSer.getTotal();
    }

    @Override
    public List<CostStandardOpinionBO> findOpinion() throws SerException {
        return costStandardSer.findOpinion1();
    }
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return costStandardSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return costStandardSer.guidePermission( guidePermissionTO );
    }
}
package com.bjike.goddess.regionalprogresscollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regionalprogresscollect.bo.ReferenceTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.ReferenceTargetDTO;
import com.bjike.goddess.regionalprogresscollect.service.ReferenceTargetSer;
import com.bjike.goddess.regionalprogresscollect.to.FindTO;
import com.bjike.goddess.regionalprogresscollect.to.GuidePermissionTO;
import com.bjike.goddess.regionalprogresscollect.to.ReferenceTargetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参考指标业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 02:56 ]
 * @Description: [ 参考指标业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("referenceTargetApiImpl")
public class ReferenceTargetApiImpl implements ReferenceTargetAPI {

    @Autowired
    private ReferenceTargetSer referenceTargetSer;

    @Override
    public ReferenceTargetBO save(ReferenceTargetTO to) throws SerException {
        return referenceTargetSer.save(to);
    }

    @Override
    public ReferenceTargetBO update(ReferenceTargetTO to) throws SerException {
        return referenceTargetSer.update(to);
    }

    @Override
    public ReferenceTargetBO delete(String id) throws SerException {
        return referenceTargetSer.delete(id);
    }

    @Override
    public ReferenceTargetBO getById(String id) throws SerException {
        return referenceTargetSer.getById(id);
    }

    @Override
    public List<ReferenceTargetBO> maps(ReferenceTargetDTO dto) throws SerException {
        return referenceTargetSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return referenceTargetSer.getTotal();
    }

    @Override
    public ReferenceTargetBO findByTO(FindTO to) throws SerException {
        return referenceTargetSer.findByTO(to);
    }

    @Override
    public List<ReferenceTargetBO> findListByTO(FindTO to) throws SerException {
        return referenceTargetSer.findListByTO(to);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return referenceTargetSer.guidePermission( guidePermissionTO );
    }
}
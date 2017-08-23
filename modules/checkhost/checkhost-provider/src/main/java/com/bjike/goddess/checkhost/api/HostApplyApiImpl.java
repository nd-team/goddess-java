package com.bjike.goddess.checkhost.api;

import com.bjike.goddess.checkhost.bo.HostApplyBO;
import com.bjike.goddess.checkhost.dto.HostApplyDTO;
import com.bjike.goddess.checkhost.entity.HostApply;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.service.HostApplySer;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.HostApplyTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 离宿申请业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 04:51 ]
 * @Description: [ 离宿申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("hostApplyApiImpl")
public class HostApplyApiImpl implements HostApplyAPI {
    @Autowired
    private HostApplySer hostApplySer;
    @Override
    public Long countHostApply(HostApplyDTO hostApplyDTO) throws SerException {
        return hostApplySer.countHostApply(hostApplyDTO);
    }
    @Override
    public HostApplyBO getOne(String id) throws SerException {
        return hostApplySer.getOne(id);
    }
    @Override
    public List<HostApplyBO> findListHostApply(HostApplyDTO hostApplyDTO) throws SerException {
        return hostApplySer.findListHostApply(hostApplyDTO);
    }

    @Override
    public HostApplyBO insertHostApply(HostApplyTO hostApplyTO) throws SerException {
        return hostApplySer.insertHostApply(hostApplyTO);
    }

    @Override
    public HostApplyBO editHostApply(HostApplyTO hostApplyTO) throws SerException {
        return hostApplySer.editHostApply(hostApplyTO);
    }

    @Override
    public void removeHostApply(String id) throws SerException {
        hostApplySer.removeHostApply(id);
    }

    @Override
    public HostApplyBO auditHostApply(String id, HostApplyDTO dto) throws SerException {
        return hostApplySer.auditHostApply(id,dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return hostApplySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return hostApplySer.guidePermission(guidePermissionTO);
    }
}
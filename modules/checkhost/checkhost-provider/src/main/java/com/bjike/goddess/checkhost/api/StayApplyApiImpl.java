package com.bjike.goddess.checkhost.api;

import com.bjike.goddess.checkhost.bo.StayApplyBO;
import com.bjike.goddess.checkhost.dto.StayApplyDTO;
import com.bjike.goddess.checkhost.entity.StayApply;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.service.StayApplySer;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.StayApplyTO;
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
 * 住宿申请业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("stayApplyApiImpl")
public class StayApplyApiImpl implements StayApplyAPI {
    @Autowired
    private StayApplySer stayApplySer;
    @Override
    public Long countStayApply(StayApplyDTO stayApplyDTO) throws SerException {
        return stayApplySer.countStayApply(stayApplyDTO);
    }
    @Override
    public StayApplyBO getOne(String id) throws SerException {
        return stayApplySer.getOne(id);
    }
    @Override
    public List<StayApplyBO> findListStayApply(StayApplyDTO stayApplyDTO) throws SerException {
        return stayApplySer.findListStayApply(stayApplyDTO);
    }

    @Override
    public StayApplyBO insertStayApply(StayApplyTO stayApplyTO) throws SerException {
        return stayApplySer.insertStayApply(stayApplyTO);
    }

    @Override
    public StayApplyBO editStayApply(StayApplyTO stayApplyTO) throws SerException {
        return stayApplySer.editStayApply(stayApplyTO);
    }

    @Override
    public void removeStayApply(String id) throws SerException {
        stayApplySer.removeStayApply(id);
    }

    @Override
    public StayApplyBO manageAudit(StayApplyTO to) throws SerException {
        return stayApplySer.manageAudit(to);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return stayApplySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return stayApplySer.guidePermission(guidePermissionTO);
    }
}
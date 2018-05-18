package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.ResponsibleIdeaBO;
import com.bjike.goddess.feedback.dto.ResponsibleIdeaDTO;
import com.bjike.goddess.feedback.service.ResponsibleIdeaSer;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ResponsibleIdeaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 非责任相关人意见业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:47 ]
 * @Description: [ 非责任相关人意见业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("responsibleIdeaApiImpl")
public class ResponsibleIdeaApiImpl implements ResponsibleIdeaAPI {
    @Autowired
    private ResponsibleIdeaSer responsibleIdeaSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return responsibleIdeaSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return responsibleIdeaSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(ResponsibleIdeaDTO dto) throws SerException {
        return responsibleIdeaSer.count(dto);
    }

    @Override
    public ResponsibleIdeaBO getId(String id) throws SerException {
        return responsibleIdeaSer.getId(id);
    }

    @Override
    public List<ResponsibleIdeaBO> list(ResponsibleIdeaDTO dto) throws SerException {
        return responsibleIdeaSer.list(dto);
    }

    @Override
    public ResponsibleIdeaBO insert(ResponsibleIdeaTO to) throws SerException {
        return responsibleIdeaSer.insert(to);
    }

    @Override
    public ResponsibleIdeaBO adopt(ResponsibleIdeaTO to) throws SerException {
        return responsibleIdeaSer.adopt(to);
    }
}
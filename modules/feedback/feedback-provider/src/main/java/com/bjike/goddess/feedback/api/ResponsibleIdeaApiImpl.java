package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.ResponsibleIdeaBO;
import com.bjike.goddess.feedback.dto.ResponsibleIdeaDTO;
import com.bjike.goddess.feedback.entity.ResponsibleIdea;
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
    private ResponsibleIdeaAPI responsibleIdeaAPI;
    @Override
    public Long count(ResponsibleIdeaDTO dto) throws SerException {
        return responsibleIdeaAPI.count(dto);
    }

    @Override
    public List<ResponsibleIdeaBO> list(ResponsibleIdeaDTO dto) throws SerException {
        return responsibleIdeaAPI.list(dto);
    }
    @Override
    public ResponsibleIdeaBO insert(ResponsibleIdeaTO to) throws SerException {
        return responsibleIdeaAPI.insert(to);
    }
    @Override
    public ResponsibleIdeaBO adopt(ResponsibleIdeaTO to) throws SerException {
        return responsibleIdeaAPI.adopt(to);
    }
}
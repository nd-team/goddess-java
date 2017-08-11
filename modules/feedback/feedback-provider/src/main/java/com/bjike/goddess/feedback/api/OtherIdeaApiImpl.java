package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.OtherIdeaBO;
import com.bjike.goddess.feedback.dto.OtherIdeaDTO;
import com.bjike.goddess.feedback.entity.OtherIdea;
import com.bjike.goddess.feedback.service.OtherIdeaSer;
import com.bjike.goddess.feedback.to.OtherIdeaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 其他模块意见业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:48 ]
 * @Description: [ 其他模块意见业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("otherIdeaApiImpl")
public class OtherIdeaApiImpl implements OtherIdeaAPI {
    @Autowired
    private OtherIdeaSer otherIdeaSer;
    @Override
    public Long count(OtherIdeaDTO dto) throws SerException {
        return otherIdeaSer.count(dto);
    }

    @Override
    public List<OtherIdeaBO> list(OtherIdeaDTO dto) throws SerException {
        return otherIdeaSer.list(dto);
    }

    @Override
    public OtherIdeaBO insert(OtherIdeaTO to) throws SerException {
        return otherIdeaSer.insert(to);
    }
}
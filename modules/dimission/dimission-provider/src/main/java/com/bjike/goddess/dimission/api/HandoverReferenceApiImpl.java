package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.HandoverReferenceBO;
import com.bjike.goddess.dimission.dto.HandoverReferenceDTO;
import com.bjike.goddess.dimission.service.HandoverReferenceSer;
import com.bjike.goddess.dimission.to.HandoverReferenceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交接信息参考业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:36 ]
 * @Description: [ 交接信息参考业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("handoverReferenceApiImpl")
public class HandoverReferenceApiImpl implements HandoverReferenceAPI {

    @Autowired
    private HandoverReferenceSer handoverReferenceSer;

    @Override
    public HandoverReferenceBO save(HandoverReferenceTO to) throws SerException {
        return handoverReferenceSer.save(to);
    }

    @Override
    public HandoverReferenceBO update(HandoverReferenceTO to) throws SerException {
        return handoverReferenceSer.update(to);
    }

    @Override
    public HandoverReferenceBO delete(String id) throws SerException {
        return handoverReferenceSer.delete(id);
    }

    @Override
    public List<HandoverReferenceBO> maps(HandoverReferenceDTO dto) throws SerException {
        return handoverReferenceSer.maps(dto);
    }
}
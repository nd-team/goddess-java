package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.RelevancyDetailBO;
import com.bjike.goddess.feedback.dto.RelevancyDetailDTO;
import com.bjike.goddess.feedback.service.RelevancyDetailSer;
import com.bjike.goddess.feedback.to.RelevancyDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 各模块关联明细业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:23 ]
 * @Description: [ 各模块关联明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("relevancyDetailApiImpl")
public class RelevancyDetailApiImpl implements RelevancyDetailAPI {

    @Autowired
    private RelevancyDetailSer relevancyDetailSer;
    @Override
    public Long count(RelevancyDetailDTO dto) throws SerException {
        return relevancyDetailSer.count(dto);
    }

    @Override
    public RelevancyDetailBO getOne(String id) throws SerException {
        return relevancyDetailSer.getOne(id);
    }

    @Override
    public List<RelevancyDetailBO> list(RelevancyDetailDTO dto) throws SerException {
        return relevancyDetailSer.list(dto);
    }

    @Override
    public RelevancyDetailBO insert(RelevancyDetailTO to) throws SerException {
        return relevancyDetailSer.insert(to);
    }

    @Override
    public RelevancyDetailBO edit(RelevancyDetailTO to) throws SerException {
        return relevancyDetailSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        relevancyDetailSer.remove(id);

    }
    @Override
    public List<String> getMainFunction() throws SerException {
        return relevancyDetailSer.getMainFunction();
    }
}
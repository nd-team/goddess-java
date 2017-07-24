package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.FeedbackComplainBO;
import com.bjike.goddess.staffmeeting.dto.FeedbackComplainDTO;
import com.bjike.goddess.staffmeeting.entity.FeedbackComplain;
import com.bjike.goddess.staffmeeting.excel.SonPermissionObject;
import com.bjike.goddess.staffmeeting.service.FeedbackComplainSer;
import com.bjike.goddess.staffmeeting.to.FeedbackComplainTO;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通告反馈投诉业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 04:23 ]
 * @Description: [ 通告反馈投诉业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("feedbackComplainApiImpl")
public class FeedbackComplainApiImpl implements FeedbackComplainAPI {

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return feedbackComplainSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return feedbackComplainSer.guidePermission(guidePermissionTO);
    }

    @Autowired
    private FeedbackComplainSer feedbackComplainSer;

    @Override
    public FeedbackComplainBO add(FeedbackComplainTO to) throws SerException {
        return feedbackComplainSer.insertModel(to);
    }

    @Override
    public FeedbackComplainBO edit(FeedbackComplainTO to) throws SerException {
        return feedbackComplainSer.updateModel(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        feedbackComplainSer.freeze(id);
    }

    @Override
    public List<FeedbackComplainBO> pageList(FeedbackComplainDTO dto) throws SerException {
        return feedbackComplainSer.pageList(dto);
    }

    @Override
    public Long count(FeedbackComplainDTO dto) throws SerException {
        return feedbackComplainSer.count(dto);
    }

    @Override
    public FeedbackComplainBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(feedbackComplainSer.findById(id), FeedbackComplainBO.class);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        feedbackComplainSer.unfreeze(id);
    }
}
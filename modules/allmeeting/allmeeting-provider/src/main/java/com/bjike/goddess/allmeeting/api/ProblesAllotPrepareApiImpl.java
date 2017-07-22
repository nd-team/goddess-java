package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ProblesAllotPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesAllotPrepareDTO;
import com.bjike.goddess.allmeeting.service.ProblesAllotPrepareSer;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.ProblesAllotPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题分配责任模块议题准备信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 04:55 ]
 * @Description: [ 问题分配责任模块议题准备信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("problesAllotPrepareApiImpl")
public class ProblesAllotPrepareApiImpl implements ProblesAllotPrepareAPI {

    @Autowired
    ProblesAllotPrepareSer problesAllotPrepareSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return problesAllotPrepareSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return problesAllotPrepareSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ProblesAllotPrepareBO add(ProblesAllotPrepareTO to) throws SerException {
        return problesAllotPrepareSer.insertModel(to);
    }

    @Override
    public ProblesAllotPrepareBO edit(ProblesAllotPrepareTO to) throws SerException {
        return problesAllotPrepareSer.updateModel(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        problesAllotPrepareSer.freeze(id);
    }

    @Override
    public List<ProblesAllotPrepareBO> pageList(ProblesAllotPrepareDTO dto) throws SerException {
        return problesAllotPrepareSer.pageList(dto);
    }

    @Override
    public Long count(ProblesAllotPrepareDTO dto) throws SerException {
        return problesAllotPrepareSer.count(dto);
    }

    @Override
    public ProblesAllotPrepareBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(problesAllotPrepareSer.findById(id),ProblesAllotPrepareBO.class);
    }
}
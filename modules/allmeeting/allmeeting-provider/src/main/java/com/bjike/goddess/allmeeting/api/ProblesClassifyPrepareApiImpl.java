package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ProblesClassifyPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesClassifyPrepareDTO;
import com.bjike.goddess.allmeeting.service.ProblesClassifyPrepareSer;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.ProblesClassifyPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题分类议题准备信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("problesClassifyPrepareApiImpl")
public class ProblesClassifyPrepareApiImpl implements ProblesClassifyPrepareAPI {

    @Autowired
    private ProblesClassifyPrepareSer problesClassifyPrepareSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return problesClassifyPrepareSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return problesClassifyPrepareSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ProblesClassifyPrepareBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(problesClassifyPrepareSer.findById(id),ProblesClassifyPrepareBO.class);
    }

    @Override
    public Long count(ProblesClassifyPrepareDTO dto) throws SerException {
        return problesClassifyPrepareSer.count(dto);
    }

    @Override
    public ProblesClassifyPrepareBO add(ProblesClassifyPrepareTO to) throws SerException {
        return problesClassifyPrepareSer.insertModel(to);
    }

    @Override
    public ProblesClassifyPrepareBO edit(ProblesClassifyPrepareTO to) throws SerException {
        return problesClassifyPrepareSer.editModel(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        problesClassifyPrepareSer.freeze(id);
    }

    @Override
    public List<ProblesClassifyPrepareBO> pageList(ProblesClassifyPrepareDTO dto) throws SerException {
        return problesClassifyPrepareSer.pageList(dto);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        problesClassifyPrepareSer.unfreeze(id);
    }
}
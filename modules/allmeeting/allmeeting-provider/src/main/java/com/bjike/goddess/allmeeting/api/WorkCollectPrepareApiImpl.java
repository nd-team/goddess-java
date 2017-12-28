package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.WorkCollectPrepareBO;
import com.bjike.goddess.allmeeting.dto.WorkCollectPrepareDTO;
import com.bjike.goddess.allmeeting.service.WorkCollectPrepareSer;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.WorkCollectPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作汇总议题准备信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 03:44 ]
 * @Description: [ 工作汇总议题准备信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("workCollectPrepareApiImpl")
public class WorkCollectPrepareApiImpl implements WorkCollectPrepareAPI {


    @Autowired
    private WorkCollectPrepareSer workCollectPrepareSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return workCollectPrepareSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return workCollectPrepareSer.guidePermission(guidePermissionTO);
    }

    @Override
    public WorkCollectPrepareBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(workCollectPrepareSer.findById(id),WorkCollectPrepareBO.class);
    }

    @Override
    public Long count(WorkCollectPrepareDTO dto) throws SerException {
        return workCollectPrepareSer.count(dto);
    }

    @Override
    public List<WorkCollectPrepareBO> pageList(WorkCollectPrepareDTO dto) throws SerException {
        return workCollectPrepareSer.pageList(dto);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        workCollectPrepareSer.unfreeze(id);
    }

    @Override
    public void freeze(String id) throws SerException {
        workCollectPrepareSer.freeze(id);
    }

    @Override
    public WorkCollectPrepareBO edit(WorkCollectPrepareTO to) throws SerException {
        return workCollectPrepareSer.updateModel(to);
    }

    @Override
    public WorkCollectPrepareBO add(WorkCollectPrepareTO to) throws SerException {
        return workCollectPrepareSer.insertModel(to);
    }
}
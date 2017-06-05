package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.WorkCollectPrepareBO;
import com.bjike.goddess.allmeeting.dto.AllMeetingOrganizeDTO;
import com.bjike.goddess.allmeeting.dto.WorkCollectPrepareDTO;
import com.bjike.goddess.allmeeting.entity.AllMeetingOrganize;
import com.bjike.goddess.allmeeting.entity.WorkCollectPrepare;
import com.bjike.goddess.allmeeting.to.WorkCollectPrepareTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作汇总议题准备信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 03:44 ]
 * @Description: [ 工作汇总议题准备信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class WorkCollectPrepareSerImpl extends ServiceImpl<WorkCollectPrepare, WorkCollectPrepareDTO> implements WorkCollectPrepareSer {

    @Autowired
    private AllMeetingOrganizeSer allMeetingOrganizeSer;

    @Override
    public List<WorkCollectPrepareBO> pageList(WorkCollectPrepareDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        return BeanTransform.copyProperties(super.findByPage(dto), WorkCollectPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void unfreeze(String id) throws SerException {
        WorkCollectPrepare model = super.findById(id);
        if (model != null) {
            model.setModifyTime(LocalDateTime.now());
            model.setStatus(Status.THAW);
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        WorkCollectPrepare model = super.findById(id);
        if (model != null) {
            model.setModifyTime(LocalDateTime.now());
            model.setStatus(Status.CONGEAL);
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public WorkCollectPrepareBO updateModel(WorkCollectPrepareTO to) throws SerException {

        //校验会议编号，防止非法数据
        allMeetingOrganizeSer.validNum(to.getMeetingNum());

        WorkCollectPrepare model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, WorkCollectPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public WorkCollectPrepareBO insertModel(WorkCollectPrepareTO to) throws SerException {
        //校验会议编号，防止非法数据
        allMeetingOrganizeSer.validNum(to.getMeetingNum());

        WorkCollectPrepare model = BeanTransform.copyProperties(to, WorkCollectPrepare.class, true);
        model.setStatus(Status.THAW);
        super.save(model);
        return BeanTransform.copyProperties(model, WorkCollectPrepareBO.class);
    }

}
package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.ProblesAllotPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesAllotPrepareDTO;
import com.bjike.goddess.allmeeting.entity.ProblesAllotPrepare;
import com.bjike.goddess.allmeeting.to.ProblesAllotPrepareTO;
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
 * 问题分配责任模块议题准备信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 04:55 ]
 * @Description: [ 问题分配责任模块议题准备信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class ProblesAllotPrepareSerImpl extends ServiceImpl<ProblesAllotPrepare, ProblesAllotPrepareDTO> implements ProblesAllotPrepareSer {

    @Autowired
    private AllMeetingOrganizeSer allMeetingOrganizeSer;

    @Override
    public List<ProblesAllotPrepareBO> pageList(ProblesAllotPrepareDTO dto) throws SerException {
        dto.getSorts().add("createTime");
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        return BeanTransform.copyProperties(super.findByPage(dto), ProblesAllotPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProblesAllotPrepareBO insertModel(ProblesAllotPrepareTO to) throws SerException {
        //校验会议编号,防止非法数据
        allMeetingOrganizeSer.validNum(to.getMeetingNum());
        ProblesAllotPrepare model = BeanTransform.copyProperties(to, ProblesAllotPrepare.class, true);
        model.setStatus(Status.THAW);
        super.save(model);
        return BeanTransform.copyProperties(model, ProblesAllotPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProblesAllotPrepareBO updateModel(ProblesAllotPrepareTO to) throws SerException {
        //校验会议编号,防止非法数据
        allMeetingOrganizeSer.validNum(to.getMeetingNum());
        ProblesAllotPrepare model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, ProblesAllotPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        ProblesAllotPrepare model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.CONGEAL);
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }
}
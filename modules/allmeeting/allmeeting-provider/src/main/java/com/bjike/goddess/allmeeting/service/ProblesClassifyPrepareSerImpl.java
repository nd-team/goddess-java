package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.ProblesClassifyPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesClassifyPrepareDTO;
import com.bjike.goddess.allmeeting.entity.ProblesClassifyPrepare;
import com.bjike.goddess.allmeeting.to.ProblesClassifyPrepareTO;
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
 * 问题分类议题准备信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class ProblesClassifyPrepareSerImpl extends ServiceImpl<ProblesClassifyPrepare, ProblesClassifyPrepareDTO> implements ProblesClassifyPrepareSer {

    @Autowired
    private AllMeetingOrganizeSer allMeetingOrganizeSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProblesClassifyPrepareBO insertModel(ProblesClassifyPrepareTO to) throws SerException {
        //校验会议编号,防止非法数据
        allMeetingOrganizeSer.validNum(to.getMeetingNum());
        ProblesClassifyPrepare model = BeanTransform.copyProperties(to, ProblesClassifyPrepare.class, true);
        model.setStatus(Status.THAW);
        super.save(model);
        return BeanTransform.copyProperties(model, ProblesClassifyPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProblesClassifyPrepareBO editModel(ProblesClassifyPrepareTO to) throws SerException {
        //校验会议编号,防止非法数据
        allMeetingOrganizeSer.validNum(to.getMeetingNum());
        ProblesClassifyPrepare model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, ProblesClassifyPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        ProblesClassifyPrepare model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("该记录无需重复冻结");
            }
        } else {
            throw new SerException("非法Id，冻结对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProblesClassifyPrepareBO> pageList(ProblesClassifyPrepareDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        return BeanTransform.copyProperties(super.findByPage(dto), ProblesClassifyPrepareBO.class);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        ProblesClassifyPrepare model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("该记录无需重复解冻");
            }
        } else {
            throw new SerException("非法Id，解冻对象不能为空");
        }
    }
}
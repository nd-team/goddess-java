package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.dto.RecruitProDTO;
import com.bjike.goddess.recruit.entity.RecruitPro;
import com.bjike.goddess.recruit.to.RecruitProTO;
import com.bjike.goddess.recruit.type.AuditType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitProSerImpl extends ServiceImpl<RecruitPro, RecruitProDTO> implements RecruitProSer {

    /**
     * 分页查询招聘方案
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitProBO> list(RecruitProDTO dto) throws SerException {
        List<RecruitPro> list =  super.findByPage(dto);
        List<RecruitProBO> listBO = BeanTransform.copyProperties(list, RecruitProBO.class);
        return listBO;
    }

    /**
     * 保存招聘方案
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public RecruitProBO save(RecruitProTO to) throws SerException {
        RecruitPro model = BeanTransform.copyProperties(to, RecruitPro.class, true);
        RecruitPro entity = super.save(model);
        RecruitProBO bo = BeanTransform.copyProperties(entity, RecruitProBO.class);
        return bo;
    }

    /**
     * 更新招聘方案
     *
     * @param to 招聘方案to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RecruitProTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RecruitPro model = super.findById(to.getId());
            if (model != null) {
                updateRecruitPro(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新招聘方案
     *
     * @param to 招聘方案to
     * @param model 招聘方案实体
     */
    private void updateRecruitPro(RecruitProTO to, RecruitPro model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 运营商务部审核
     *
     * @param id 招聘方案唯一标识
     * @param yy_Opinion 运营商务部意见
     * @param pass 是否通过
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void yyEdit (String id, String yy_Opinion, Boolean pass) throws SerException {
        RecruitPro editObj = super.findById(id);
        editObj.setYy_Opinion(yy_Opinion);
        editObj.setAuditType(pass? AuditType.NONE:AuditType.DENIED);
        super.update(editObj);
    }

    /**
     * 总经办审核
     *
     * @param id 招聘方案唯一标识
     * @param zjb_Opnion 总经办意见
     * @param pass 是否通过
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void managerEdit (String id, String zjb_Opnion, Boolean pass) throws SerException {
        RecruitPro editObj = super.findById(id);
        editObj.setZjb_Opnion(zjb_Opnion);
        editObj.setAuditType(Boolean.TRUE == pass? AuditType.ALLOWED:AuditType.DENIED);
        super.update(editObj);
    }

    /**
     * 根据id删除招聘方案
     *
     * @param id 招聘方案唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}

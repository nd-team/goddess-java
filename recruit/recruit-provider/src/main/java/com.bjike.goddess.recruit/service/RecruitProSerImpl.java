package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.dto.RecruitProDTO;
import com.bjike.goddess.recruit.entity.RecruitPro;
import com.bjike.goddess.recruit.to.RecruitProTO;
import com.bjike.goddess.recruit.type.AuditType;
import org.springframework.stereotype.Service;

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
        List<RecruitPro> proList =  super.findByCis(dto);
        List<RecruitProBO> recruitProBOList = BeanTransform.copyProperties(proList, RecruitProBO.class, true);
        return recruitProBOList;
    }

    /**
     * 保存招聘方案
     *
     * @param recruitProTO
     * @return
     * @throws SerException
     */
    @Override
    public RecruitProBO save(RecruitProTO recruitProTO) throws SerException {
        RecruitPro recruitPro = BeanTransform.copyProperties(recruitProTO, RecruitPro.class, true);
        RecruitPro entity = super.save(recruitPro);
        RecruitProBO recruitProBO = BeanTransform.copyProperties(entity, RecruitProBO.class, true);
        return recruitProBO;
    }

    /**
     * 更新招聘方案
     *
     * @param recruitProTO
     * @throws SerException
     */
    @Override
    public void update(RecruitProTO recruitProTO) throws SerException {
        RecruitPro original = super.findById(recruitProTO.getId());
        original.setAuditType(original.getAuditType());
        original.setStatus(original.getStatus());
        super.update(original);
    }

    /**
     * 运营商务部审核
     *
     * @param recruitProTO
     * @param pass
     * @throws SerException
     */
    @Override
    public void yyEdit (RecruitProTO recruitProTO, Boolean pass) throws SerException {
        RecruitPro editObj = super.findById(recruitProTO.getId());
        editObj.setYy_Opinion(recruitProTO.getYy_Opinion());
        editObj.setAuditType(pass? AuditType.NONE:AuditType.DENIED);
        super.update(editObj);
    }

    /**
     * 总经办审核
     *
     * @param recruitProTO
     * @param pass
     * @throws SerException
     */
    @Override
    public void managerEdit (RecruitProTO recruitProTO, Boolean pass) throws SerException {
        RecruitPro editObj = super.findById(recruitProTO.getId());
        editObj.setZjb_Opnion(recruitProTO.getZjb_Opnion());
        editObj.setAuditType(Boolean.TRUE == pass? AuditType.ALLOWED:AuditType.DENIED);
        super.update(editObj);
    }

}

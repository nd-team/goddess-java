package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.entity.RecruitDemand;
import com.bjike.goddess.recruit.to.RecruitDemandTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitDemandSerImpl extends ServiceImpl<RecruitDemand, RecruitDemandDTO> implements RecruitDemandSer {

    /**
     * 分页查询招聘需求
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitDemandBO> list(RecruitDemandDTO dto) throws SerException {
        List<RecruitDemand> recruitDemandList = super.findByPage(dto);
        List<RecruitDemandBO> recruitDemandBOList = BeanTransform.copyProperties(recruitDemandList, RecruitDemandBO.class);
        return recruitDemandBOList;
    }

    /**
     * 保存招聘需求
     *
     * @param recruitDemandTO
     * @return
     * @throws SerException
     */
    @Override
    public RecruitDemandBO save(RecruitDemandTO recruitDemandTO) throws SerException {
        RecruitDemand recruitDemand = BeanTransform.copyProperties(recruitDemandTO, RecruitDemand.class, true);
        recruitDemand = super.save(recruitDemand);
        RecruitDemandBO recruitDemandBO = BeanTransform.copyProperties(recruitDemand, RecruitDemandBO.class);
        return recruitDemandBO;
    }

    /**
     * 更新招聘需求
     *
     * @param recruitDemandTO
     * @throws SerException
     */
    @Override
    public void update(RecruitDemandTO recruitDemandTO) throws SerException {
        RecruitDemand recruitDemand = BeanTransform.copyProperties(recruitDemandTO, RecruitDemandTO.class);
        super.update(recruitDemand);
    }
}

package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.dto.RecruitProDTO;
import com.bjike.goddess.recruit.entity.RecruitPro;
import com.bjike.goddess.recruit.service.RecruitProSer;
import com.bjike.goddess.recruit.to.RecruitProTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("recruitProApiImpl")
public class RecruitProApiImpl implements RecruitProAPI {

    @Autowired
    private RecruitProSer recruitProSer;

    /**
     * 根据id查询招聘方案
     *
     * @param id 招聘方案唯一标识
     * @return class RecruitProBO
     * @throws SerException
     */
    @Override
    public RecruitProBO findById(String id) throws SerException {
        RecruitPro model = recruitProSer.findById(id);
        return BeanTransform.copyProperties(model, RecruitProBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 招聘方案dto
     * @throws SerException
     */
    @Override
    public Long count(RecruitProDTO dto) throws SerException {
        return recruitProSer.count(dto);
    }

    /**
     * 分页查询招聘方案
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitProBO> list(RecruitProDTO dto) throws SerException {
        return recruitProSer.list(dto);
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
        return recruitProSer.save(recruitProTO);
    }

    /**
     * 根据id删除招聘方案
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        recruitProSer.remove(id);
    }

    /**
     * 更新招聘方案
     *
     * @param recruitProTO
     * @throws SerException
     */
    @Override
    public void update(RecruitProTO recruitProTO) throws SerException {
        recruitProSer.update(recruitProTO);
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
    public void yyEdit(String id, String yy_Opinion, Boolean pass) throws SerException {
        recruitProSer.yyEdit(id, yy_Opinion, pass);
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
    public void managerEdit(String id, String zjb_Opnion, Boolean pass) throws SerException {
        recruitProSer.managerEdit(id, zjb_Opnion, pass);
    }
}

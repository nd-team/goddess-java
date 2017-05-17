package com.bjike.goddess.projectmarketfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmarketfee.bo.GradeBO;
import com.bjike.goddess.projectmarketfee.dto.GradeDTO;
import com.bjike.goddess.projectmarketfee.service.GradeSer;
import com.bjike.goddess.projectmarketfee.to.GradeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 等级设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:55 ]
 * @Description: [ 等级设计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("gradeApiImpl")
public class GradeApiImpl implements GradeAPI {
    @Autowired
    private GradeSer gradeSer;

    @Override
    public GradeBO save(GradeTO to) throws SerException {
        return gradeSer.save(to);
    }

    @Override
    public void edit(GradeTO to) throws SerException {
        gradeSer.edit(to);
    }

    @Override
    public List<GradeBO> list(GradeDTO dto) throws SerException {
        return gradeSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        gradeSer.delete(id);
    }

    @Override
    public GradeBO findByID(String id) throws SerException {
        return gradeSer.findByID(id);
    }

    @Override
    public GradeBO countNum(GradeDTO dto) throws SerException {
        return gradeSer.countNum(dto);
    }

}
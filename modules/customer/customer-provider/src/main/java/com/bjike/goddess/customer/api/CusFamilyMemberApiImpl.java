package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.service.CusFamilyMemberSer;
import com.bjike.goddess.customer.to.CusFamilyMemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户家庭成员业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:48:29.112 ]
 * @Description: [ 客户家庭成员业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cusFamilyMemberApiImpl")
public class CusFamilyMemberApiImpl implements CusFamilyMemberAPI {

    @Autowired
    private CusFamilyMemberSer cusFamilyMemberSer;

    @Override
    public List<CusFamilyMemberBO> listCusFamilyMember(CusFamilyMemberDTO cusFamilyMemberDTO) throws SerException {
        return cusFamilyMemberSer.listCusFamilyMember(cusFamilyMemberDTO);
    }

    @Override
    public CusFamilyMemberBO addCusFamilyMember(CusFamilyMemberTO cusFamilyMemberTO) throws SerException {
        return cusFamilyMemberSer.addCusFamilyMember(cusFamilyMemberTO);
    }

    @Override
    public CusFamilyMemberBO editCusFamilyMember(CusFamilyMemberTO cusFamilyMemberTO) throws SerException {
        return cusFamilyMemberSer.editCusFamilyMember(cusFamilyMemberTO);
    }

    @Override
    public void deleteCusFamilyMember(String id) throws SerException {
        cusFamilyMemberSer.deleteCusFamilyMember(id);
    }

    @Override
    public void congealCusFamilyMember(String id) throws SerException {
        cusFamilyMemberSer.congealCusFamilyMember(id);
    }

    @Override
    public void thawCusFamilyMember(String id) throws SerException {
        cusFamilyMemberSer.thawCusFamilyMember(id);
    }
}
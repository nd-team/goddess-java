package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.to.CusFamilyMemberTO;

import java.util.List;

/**
 * 客户家庭成员业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:48:29.107 ]
 * @Description: [ 客户家庭成员业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CusFamilyMemberAPI {

    /**
     * 客户家庭成员列表
     * @return class CusFamilyMemberBO
     */
    default List<CusFamilyMemberBO> listCusFamilyMember(CusFamilyMemberDTO cusFamilyMemberDTO) throws SerException {return null;}
    /**
     *  添加
     * @param cusFamilyMemberTO 客户家庭成员信息
     * @return class CusFamilyMemberBO
     */
    default CusFamilyMemberBO addCusFamilyMember(CusFamilyMemberTO cusFamilyMemberTO) throws SerException { return null;}

    /**
     *  编辑
     * @param cusFamilyMemberTO 客户家庭成员信息
     * @return class CusFamilyMemberBO
     */
    default CusFamilyMemberBO editCusFamilyMember(CusFamilyMemberTO cusFamilyMemberTO) throws SerException { return null;}

    /**
     * 删除家庭成员
     * @param id id
     */
    default void deleteCusFamilyMember(String id ) throws SerException {return;};

    /**
     * 冻结客户家庭成员
     * @param id id
     */
    default void congealCusFamilyMember(String id ) throws SerException {return;};

    /**
     * 解冻客户家庭成员
     * @param id id
     */
    default void thawCusFamilyMember(String id ) throws SerException {return;};
}
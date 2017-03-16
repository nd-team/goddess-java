package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.bo.FamilyMemberBO;
import com.bjike.goddess.staffentry.dto.FamilyMemberDTO;
import com.bjike.goddess.staffentry.entity.FamilyMember;
import com.bjike.goddess.staffentry.to.FamilyMemberTO;

import java.util.List;

/**
 * 家庭成员业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 15:56]
 * @Description: [家庭成员业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FamilyMemberSer extends Ser<FamilyMember,FamilyMemberDTO> {

    /**
     * 添加家庭成员
     * @param familyMemberTO 家庭成员数据to
     * @return class familyMemberBO
     * @throws SerException
     */
    default FamilyMemberBO insertFamily (FamilyMemberTO familyMemberTO) throws SerException {return null;}

    /**
     * 批量添加家庭成员
     * @param familyMemberTOs
     * @return
     * @throws SerException
     */
    default void insertFamilys (List<FamilyMemberTO> familyMemberTOs) throws SerException {return ;}
}

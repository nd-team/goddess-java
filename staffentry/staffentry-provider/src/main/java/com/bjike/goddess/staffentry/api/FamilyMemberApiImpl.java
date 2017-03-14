package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.FamilyMemberBO;
import com.bjike.goddess.staffentry.service.FamilyMemberSer;
import com.bjike.goddess.staffentry.to.FamilyMemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 家庭成员业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 15:56]
 * @Description: [家庭成员业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("familyMemberApiImpl")
public class FamilyMemberApiImpl implements FamilyMemberAPI{

    @Autowired
    private FamilyMemberSer familyMemberSer;

    @Override
    public FamilyMemberBO insertFamily(FamilyMemberTO familyMemberTO) throws SerException {
        return familyMemberSer.insertFamily( familyMemberTO);
    }

    @Override
    public void insertFamilys(List<FamilyMemberTO> familyMemberTOs) throws SerException {
        familyMemberSer.insertFamilys( familyMemberTOs);
    }
}

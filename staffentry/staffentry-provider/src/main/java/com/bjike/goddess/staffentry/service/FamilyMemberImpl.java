package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.FamilyMemberBO;
import com.bjike.goddess.staffentry.dto.FamilyMemberDTO;
import com.bjike.goddess.staffentry.entity.FamilyMember;
import com.bjike.goddess.staffentry.to.FamilyMemberTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 家庭成员业务实现
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 16:01]
 * @Description: [家庭成员业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class FamilyMemberImpl extends ServiceImpl<FamilyMember, FamilyMemberDTO> implements FamilyMemberSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void insertFamilys(List<FamilyMemberTO> familyMemberTOs) throws SerException {

        List<FamilyMember> familyMembers = new ArrayList<>(0);
        familyMemberTOs.stream().forEach(family -> {

            FamilyMember familyMember = BeanTransform.copyProperties(family, FamilyMember.class, true);
            familyMember.setCreateTime( LocalDateTime.now());
            familyMembers.add(familyMember);
        });
        if (familyMembers != null && familyMembers.size() > 0) {
            super.save(familyMembers);
        }
    }
}

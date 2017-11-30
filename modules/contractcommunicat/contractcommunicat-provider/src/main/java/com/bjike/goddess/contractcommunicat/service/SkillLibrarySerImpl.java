package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.contractcommunicat.bo.SkillLibraryBO;
import com.bjike.goddess.contractcommunicat.dto.SkillLibraryDTO;
import com.bjike.goddess.contractcommunicat.entity.SkillLibrary;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.SkillLibraryTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 谈判技巧库业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class SkillLibrarySerImpl extends ServiceImpl<SkillLibrary, SkillLibraryDTO> implements SkillLibrarySer {
    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    @Override
    public Long count(SkillLibraryDTO dto) throws SerException {
        return null;
    }

    @Override
    public SkillLibraryBO getOne(String id) throws SerException {
        return null;
    }

    @Override
    public List<SkillLibraryBO> list(SkillLibraryDTO dto) throws SerException {
        return null;
    }

    @Override
    public SkillLibraryBO insert(SkillLibraryTO to) throws SerException {
        return null;
    }

    @Override
    public SkillLibraryBO edit(SkillLibraryTO to) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {
    }

    @Override
    public void appraise(SkillLibraryTO to) throws SerException {

    }
}
package com.bjike.goddess.contractcommunicat.api;

<<<<<<< Updated upstream
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.HistoryAppraiseBO;
import com.bjike.goddess.contractcommunicat.bo.SkillLibraryBO;
import com.bjike.goddess.contractcommunicat.dto.SkillLibraryDTO;
import com.bjike.goddess.contractcommunicat.service.SkillLibrarySer;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.SkillLibraryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

=======
import org.springframework.stereotype.Service;

>>>>>>> Stashed changes
/**
 * 谈判技巧库业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("skillLibraryApiImpl")
public class SkillLibraryApiImpl implements SkillLibraryAPI {
<<<<<<< Updated upstream
    @Autowired
    private SkillLibrarySer skillLibrarySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return skillLibrarySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return skillLibrarySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(SkillLibraryDTO dto) throws SerException {
        return skillLibrarySer.count(dto);
    }

    @Override
    public SkillLibraryBO getOne(String id) throws SerException {
        return skillLibrarySer.getOne(id);
    }

    @Override
    public List<SkillLibraryBO> list(SkillLibraryDTO dto) throws SerException {
        return skillLibrarySer.list(dto);
    }

    @Override
    public SkillLibraryBO insert(SkillLibraryTO to) throws SerException {
        return skillLibrarySer.insert(to);
    }

    @Override
    public SkillLibraryBO edit(SkillLibraryTO to) throws SerException {
        return skillLibrarySer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        skillLibrarySer.remove(id);
    }

    @Override
    public SkillLibraryBO appraise(SkillLibraryTO to) throws SerException {
        return skillLibrarySer.appraise(to);
    }

    @Override
    public List<HistoryAppraiseBO> historyAppraise(String id) throws SerException {
        return skillLibrarySer.historyAppraise(id);
    }
=======

>>>>>>> Stashed changes
}
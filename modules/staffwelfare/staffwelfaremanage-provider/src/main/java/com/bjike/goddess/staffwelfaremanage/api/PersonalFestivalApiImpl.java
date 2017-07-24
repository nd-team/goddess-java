package com.bjike.goddess.staffwelfaremanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfaremanage.bo.PersonalFestivalBO;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalDTO;
import com.bjike.goddess.staffwelfaremanage.service.PersonalFestivalSer;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfaremanage.to.PersonalFestivalTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人节日业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 01:56 ]
 * @Description: [ 个人节日业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("personalFestivalApiImpl")
public class PersonalFestivalApiImpl implements PersonalFestivalAPI {

    @Autowired
    private PersonalFestivalSer personalFestivalSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return personalFestivalSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return personalFestivalSer.guidePermission(guidePermissionTO);
    }

    @Override
    public PersonalFestivalBO addModel(PersonalFestivalTO to) throws SerException {
        return personalFestivalSer.insertModel(to);
    }

    @Override
    public PersonalFestivalBO editModel(PersonalFestivalTO to) throws SerException {
        return personalFestivalSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        personalFestivalSer.remove(id);
    }

    @Override
    public List<PersonalFestivalBO> pageList(PersonalFestivalDTO dto) throws SerException {
        return personalFestivalSer.pageList(dto);
    }

    @Override
    public void wish(String id, String wishStatement) throws SerException {
        personalFestivalSer.wish(id,wishStatement);
    }
}
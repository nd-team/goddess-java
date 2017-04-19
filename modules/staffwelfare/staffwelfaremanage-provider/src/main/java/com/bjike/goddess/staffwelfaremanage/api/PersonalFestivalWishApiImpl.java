package com.bjike.goddess.staffwelfaremanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfaremanage.bo.PersonalFestivalWishBO;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalWishDTO;
import com.bjike.goddess.staffwelfaremanage.service.PersonalFestivalWishSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节日祝福语业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 03:08 ]
 * @Description: [ 节日祝福语业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("personalFestivalWishApiImpl")
public class PersonalFestivalWishApiImpl implements PersonalFestivalWishAPI {

    @Autowired
    private PersonalFestivalWishSer personalFestivalWishSer;

    @Override
    public List<PersonalFestivalWishBO> pageList(PersonalFestivalWishDTO dto) throws SerException {
        return personalFestivalWishSer.pageList(dto);
    }
}
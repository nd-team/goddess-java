package com.bjike.goddess.staffwelfaremanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffwelfaremanage.bo.PersonalFestivalBO;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalDTO;
import com.bjike.goddess.staffwelfaremanage.entity.PersonalFestival;
import com.bjike.goddess.staffwelfaremanage.to.PersonalFestivalTO;

import java.util.List;

/**
 * 个人节日业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 01:56 ]
 * @Description: [ 个人节日业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PersonalFestivalSer extends Ser<PersonalFestival, PersonalFestivalDTO> {
    /**
     * 新增个人节日
     *
     * @param to 个人节日
     * @return 个人节日
     */
    PersonalFestivalBO insertModel(PersonalFestivalTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 个人节日
     * @return 个人节日
     */
    PersonalFestivalBO updateModel(PersonalFestivalTO to) throws SerException;

    /**
     * 分页查询个人节日
     *
     * @param dto 分页条件
     * @return 个人节日结果集
     */
    List<PersonalFestivalBO> pageList(PersonalFestivalDTO dto) throws SerException;

    /**
     * 一声祝福
     *
     * @param id 个人节日id
     * @param wishStatement 祝福语
     * @throws SerException
     */
    void wish(String id, String wishStatement) throws SerException;
}
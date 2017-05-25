package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.PersonnelQualificationBO;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.to.PersonnelQualificationTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 人员资质业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PersonnelQualificationAPI {

    /**
     * 保存
     *
     * @param to 人员资质传输对象
     * @return
     * @throws SerException
     */
    default PersonnelQualificationBO save(PersonnelQualificationTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 人员资质传输对象
     * @return
     * @throws SerException
     */
    default PersonnelQualificationBO update(PersonnelQualificationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 人员资质数据id
     * @return
     * @throws SerException
     */
    default PersonnelQualificationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 人员资质数据传输对象
     * @return
     * @throws SerException
     */
    default List<PersonnelQualificationBO> maps(PersonnelQualificationDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取人员资质数据
     *
     * @param id 人员资质数据id
     * @return
     * @throws SerException
     */
    default PersonnelQualificationBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }
}
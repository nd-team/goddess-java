package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.MobileVersionBO;
import com.bjike.goddess.organize.dto.MobileVersionDTO;
import com.bjike.goddess.organize.entity.MobileVersion;
import com.bjike.goddess.organize.to.MobileVersionTO;

import java.util.List;

/**
 * 移动端版本业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-07 01:54 ]
 * @Description: [ 移动端版本业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MobileVersionSer extends Ser<MobileVersion, MobileVersionDTO> {

    /**
     * 移动端版本列表总条数
     */
    default Long count(MobileVersionDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个移动端版本
     *
     * @return class MobileVersionBO
     */
    default MobileVersionBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 移动端版本
     *
     * @param dto 移动端版本dto
     * @return class MobileVersionBO
     * @throws SerException
     */
    default List<MobileVersionBO> list(MobileVersionDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加移动端版本
     *
     * @param to 移动端版本数据to
     * @return class MobileVersionBO
     * @throws SerException
     */
    default MobileVersionBO insert(MobileVersionTO to) throws SerException {
        return null;
    }

    /**
     * 编辑移动端版本
     *
     * @param to 移动端版本数据to
     * @return class MobileVersionBO
     * @throws SerException
     */
    default MobileVersionBO edit(MobileVersionTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除移动端版本
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
}
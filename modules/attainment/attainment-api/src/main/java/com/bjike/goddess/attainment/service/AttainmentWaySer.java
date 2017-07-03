package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.AttainmentWayBO;
import com.bjike.goddess.attainment.dto.AttainmentWayDTO;
import com.bjike.goddess.attainment.entity.AttainmentWay;
import com.bjike.goddess.attainment.to.AttainmentWayTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研方式业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:51 ]
 * @Description: [ 调研方式业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttainmentWaySer extends Ser<AttainmentWay, AttainmentWayDTO> {

    /**
     * 保存
     *
     * @param to 调研方式传输对象
     * @return
     * @throws SerException
     */
    default AttainmentWayBO save(AttainmentWayTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研方式传输对象
     * @return
     * @throws SerException
     */
    default AttainmentWayBO update(AttainmentWayTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研方式数据id
     * @return
     * @throws SerException
     */
    default AttainmentWayBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 调研方式数据id
     * @return
     * @throws SerException
     */
    default AttainmentWayBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 调研方式数据id
     * @return
     * @throws SerException
     */
    default AttainmentWayBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 查询未冻结数据
     *
     * @return
     * @throws SerException
     */
    default List<AttainmentWayBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 调研方式数据传输对象
     * @return
     * @throws SerException
     */
    default List<AttainmentWayBO> maps(AttainmentWayDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取调研方式数据
     *
     * @param id 调研方式数据id
     * @return
     * @throws SerException
     */
    default AttainmentWayBO getById(String id) throws SerException {
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
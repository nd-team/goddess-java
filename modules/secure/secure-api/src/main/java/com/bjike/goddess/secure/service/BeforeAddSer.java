package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.entity.BeforeAdd;
import com.bjike.goddess.secure.to.BeforeAddTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;

import java.util.List;

/**
 * 增员前业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BeforeAddSer extends Ser<BeforeAdd, BeforeAddDTO> {
    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 添加
     *
     * @param to 增员前信息
     * @return class BeforeAddBO
     * @throws SerException
     */
    default BeforeAddBO save(BeforeAddTO to) throws SerException {
        return null;
    }


    /**
     * 删除
     *
     * @param id 增员前id
     * @return class BeforeAddBO
     * @throws SerException
     */
    default BeforeAddBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 增员分页信息
     * @return class BeforeAddBO
     * @throws SerException
     */
    default List<BeforeAddBO> find(BeforeAddDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 增员前id
     * @return class BeforeAddBO
     * @throws SerException
     */
    default BeforeAddBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 定时方法，查找新转正员工，通知福利模块
     *
     * @throws SerException
     */
    void send() throws SerException;

//    /**
//     * 启动定时方法
//     *
//     * @throws SerException
//     */
//    void quartz() throws SerException;

    /**
     * 补全信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    BeforeAddBO complete(BeforeAddTO to) throws SerException;

    /**
     * 新增社保
     *
     * @param id id
     * @throws SerException
     */
    void add(AddEmployeeDTO dto, String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(BeforeAddDTO dto) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(BeforeAddTO to) throws SerException;
}
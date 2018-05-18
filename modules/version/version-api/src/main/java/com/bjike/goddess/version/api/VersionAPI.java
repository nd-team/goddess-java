package com.bjike.goddess.version.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.version.bo.VersionBO;
import com.bjike.goddess.version.dto.VersionDTO;
import com.bjike.goddess.version.to.HelpTO;
import com.bjike.goddess.version.to.VersionTO;

import java.util.List;

/**
 * 版本信息业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:03 ]
 * @Description: [ 版本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VersionAPI {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VersionBO> list(VersionDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    VersionBO save(VersionTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(VersionTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    VersionBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(VersionDTO dto) throws SerException;

    /**
     * 我要发问
     *
     * @param id
     * @param helpTO
     * @throws SerException
     */
    void ask(String id, HelpTO helpTO) throws SerException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws SerException
     */
    VersionBO findDetail(String id) throws SerException;
}
package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.CommunicationPathBO;
import com.bjike.goddess.intromanage.dto.CommunicationPathDTO;
import com.bjike.goddess.intromanage.to.CommunicationPathTO;

import java.util.List;
import java.util.Set;

/**
 * 通讯途径业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 06:02 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommunicationPathAPI {

    /**
     * 根据id查询通讯途径
     *
     * @param id 通讯途径唯一标识
     * @return class CommunicationPathBO
     * @throws SerException
     */
    CommunicationPathBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 通讯途径dto
     * @throws SerException
     */
    Long count(CommunicationPathDTO dto) throws SerException;

    /**
     * 分页查询通讯途径
     *
     * @return class CommunicationPathBO
     * @throws SerException
     */
    List<CommunicationPathBO> list(CommunicationPathDTO dto) throws SerException;

    /**
     * 保存通讯途径
     *
     * @param to 通讯途径to
     * @return class CommunicationPathBO
     * @throws SerException
     */
    CommunicationPathBO save(CommunicationPathTO to) throws SerException;

    /**
     * 根据id删除通讯途径
     *
     * @param id 通讯途径唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新通讯途径
     *
     * @param to 通讯途径to
     * @throws SerException
     */
    void update(CommunicationPathTO to) throws SerException;

    /**
     * chenjunhao
     * 获取所有公司地址
     *
     * @return
     * @throws SerException
     */
    Set<String> address() throws SerException;
}
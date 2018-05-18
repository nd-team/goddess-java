package com.bjike.goddess.communicatemeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.communicatemeeting.bo.CommunicateBO;
import com.bjike.goddess.communicatemeeting.dto.CommunicateDTO;
import com.bjike.goddess.communicatemeeting.entity.Communicate;
import com.bjike.goddess.communicatemeeting.to.CommunicateTO;

import java.util.List;

/**
 * 交流讨论业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-27 02:00 ]
 * @Description: [ 交流讨论业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommunicateSer extends Ser<Communicate, CommunicateDTO> {
    /**
     * 一轮交流
     *
     * @param to 交流讨论to
     * @return
     * @throws SerException
     */
    CommunicateBO oneRound(CommunicateTO to) throws SerException;

    /**
     * 二轮交流
     *
     * @param dto 交流讨论dto
     * @return
     * @throws SerException
     */
    List<CommunicateBO> twoRound(CommunicateDTO dto) throws SerException;

    /**
     * 补充一轮意见
     *
     * @param to 交流讨论to
     * @throws SerException
     */
    void replenish(CommunicateTO to) throws SerException;

    /**
     * 投票
     *
     * @param id 交流讨论id
     * @throws SerException
     */
    void vote(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id 交流讨论id
     * @return
     * @throws SerException
     */
    CommunicateBO findByID(String id) throws SerException;

    /**
     * 查看投票结果
     *
     * @param dto 交流讨论dto
     * @return
     * @throws SerException
     */
    List<CommunicateBO> showVote(CommunicateDTO dto) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    Long countNum(CommunicateDTO dto) throws SerException;
}
package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.businessinteraction.bo.ContactObjectBO;
import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 洽谈详情业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:27 ]
 * @Description: [ 洽谈详情业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TalkDetailAPI {

    /**
     * 洽谈详情列表总条数
     *
     */
    default Long countInter(TalkDetailDTO talkDetailDTO) throws SerException {
        return null;
    }
    /**
     * 洽谈详情列表id
     * @return class TalkDetailBO
     */
    default TalkDetailBO getOneById (String id) throws SerException {return null;}

    /**
     * 洽谈详情列表
     * @return class TalkDetailBO
     */
    default List<TalkDetailBO> listTalkDetail(TalkDetailDTO talkDetailDTO) throws SerException {return null;}
    /**
     *  添加
     * @param talkDetailTO 洽谈详情信息
     * @return class TalkDetailBO
     */
    default TalkDetailBO addTalkDetail(TalkDetailTO talkDetailTO) throws SerException { return null;}

    /**
     *  编辑
     * @param talkDetailTO 洽谈详情信息
     * @return class TalkDetailBO
     */
    default TalkDetailBO editTalkDetail(TalkDetailTO talkDetailTO) throws SerException { return null;}

    /**
     * 删除洽谈详情信息
     * @param id id
     */
    default void deleteTalkDetail(String id ) throws SerException {return;};

    /**
     * 获取合作对象联系方式
     * @param talkDetailDTO talkDetailDTO
     */
    default List<ContactObjectBO> getContactWays(TalkDetailDTO talkDetailDTO ) throws SerException {return null;};

}
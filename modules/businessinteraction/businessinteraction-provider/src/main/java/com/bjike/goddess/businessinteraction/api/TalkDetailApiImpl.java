package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.businessinteraction.bo.ContactObjectBO;
import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.TalkDetail;
import com.bjike.goddess.businessinteraction.service.TalkDetailSer;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 洽谈详情业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:27 ]
 * @Description: [ 洽谈详情业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("talkDetailApiImpl")
public class TalkDetailApiImpl implements TalkDetailAPI {

    @Autowired
    private TalkDetailSer talkDetailSer;


    @Override
    public List<TalkDetailBO> listTalkDetail(TalkDetailDTO talkDetailDTO) throws SerException {
        return talkDetailSer.listTalkDetail(talkDetailDTO);
    }

    @Override
    public TalkDetailBO addTalkDetail(TalkDetailTO talkDetailTO) throws SerException {
        return talkDetailSer.addTalkDetail(talkDetailTO);
    }

    @Override
    public TalkDetailBO editTalkDetail(TalkDetailTO talkDetailTO) throws SerException {
        return talkDetailSer.editTalkDetail(talkDetailTO);
    }

    @Override
    public void deleteTalkDetail(String id) throws SerException {
        talkDetailSer.deleteTalkDetail(id);
    }

    @Override
    public List<ContactObjectBO> getContactWays(TalkDetailDTO talkDetailDTO) throws SerException {
        return  talkDetailSer.getContactWays(talkDetailDTO);
    }
}
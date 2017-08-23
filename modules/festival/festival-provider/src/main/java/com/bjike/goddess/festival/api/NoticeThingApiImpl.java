package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.NoticeThingBO;
import com.bjike.goddess.festival.dto.NoticeThingDTO;
import com.bjike.goddess.festival.service.NoticeThingSer;
import com.bjike.goddess.festival.to.NoticeThingTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注意事项业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:19 ]
 * @Description: [ 注意事项业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("noticeThingApiImpl")
public class NoticeThingApiImpl implements NoticeThingAPI {

    @Autowired
    private NoticeThingSer noticeThingSer;

    @Override
    public Long countNoticeThing(NoticeThingDTO noticeThingDTO) throws SerException {
        return noticeThingSer.countNoticeThing(noticeThingDTO);
    }

    @Override
    public List<NoticeThingBO> listNoticeThing(NoticeThingDTO noticeThingDTO) throws SerException {
        return noticeThingSer.listNoticeThing(noticeThingDTO);
    }

    @Override
    public NoticeThingBO addNoticeThing(NoticeThingTO noticeThingTO) throws SerException {
        return noticeThingSer.addNoticeThing(noticeThingTO);
    }

    @Override
    public NoticeThingBO editNoticeThing(NoticeThingTO noticeThingTO) throws SerException {
        return noticeThingSer.editNoticeThing(noticeThingTO);
    }

    @Override
    public void deleteNoticeThing(String id) throws SerException {
        noticeThingSer.deleteNoticeThing(id);
    }

    @Override
    public  List<NoticeThingBO> getNoticeThing(String holidayProgrammeId) throws SerException {
        return noticeThingSer.getNoticeThing(holidayProgrammeId);
    }
}
package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.festival.bo.NoticeThingBO;
import com.bjike.goddess.festival.entity.NoticeThing;
import com.bjike.goddess.festival.dto.NoticeThingDTO;
import com.bjike.goddess.festival.to.NoticeThingTO;

import java.util.List;

/**
 * 注意事项业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:19 ]
 * @Description: [ 注意事项业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NoticeThingSer extends Ser<NoticeThing, NoticeThingDTO> {
    /**
     * 注意事项列表总条数
     *
     */
    default Long countNoticeThing(NoticeThingDTO noticeThingDTO) throws SerException {
        return null;
    }
    /**
     * 注意事项列表
     * @return class NoticeThingBO
     */
    default List<NoticeThingBO> listNoticeThing(NoticeThingDTO noticeThingDTO) throws SerException {return null;}
    /**
     *  添加
     * @param noticeThingTO 注意事项信息
     * @return class NoticeThingBO
     */
    default NoticeThingBO addNoticeThing(NoticeThingTO noticeThingTO) throws SerException { return null;}

    /**
     *  编辑
     * @param noticeThingTO 注意事项信息
     * @return class NoticeThingBO
     */
    default NoticeThingBO editNoticeThing(NoticeThingTO noticeThingTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteNoticeThing(String id ) throws SerException {return;};


    /**
     * 根据节日方案查询注意事项
     * @return class NoticeThingBO
     */
    default List<NoticeThingBO> getNoticeThing(String holidayProgrammeId) throws SerException {return null;}


}
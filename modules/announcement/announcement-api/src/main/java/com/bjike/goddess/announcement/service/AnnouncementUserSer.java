package com.bjike.goddess.announcement.service;

import com.bjike.goddess.announcement.bo.AnnouncementUserBO;
import com.bjike.goddess.announcement.dto.AnnouncementUserDTO;
import com.bjike.goddess.announcement.entity.AnnouncementUser;
import com.bjike.goddess.announcement.to.AnnouncementUserTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

/**
 * 公告对应的发布对象业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-12 10:34 ]
 * @Description: [ 公告对应的发布对象业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnnouncementUserSer extends Ser<AnnouncementUser, AnnouncementUserDTO> {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    AnnouncementUserBO save(AnnouncementUserTO to) throws SerException;

    /**
     * 读取公告
     *
     * @param announcementId
     * @param userId
     * @throws SerException
     */
    void read(String announcementId, String userId) throws SerException;

    /**
     * 查看该公告是否已经读取
     *
     * @param announcementId
     * @param userId
     * @return
     * @throws SerException
     */
    boolean check(String announcementId, String userId) throws SerException;

    /**
     * 查找
     *
     * @param announcementId
     * @param userId
     * @return
     * @throws SerException
     */
    AnnouncementUser find(String announcementId, String userId) throws SerException;
}
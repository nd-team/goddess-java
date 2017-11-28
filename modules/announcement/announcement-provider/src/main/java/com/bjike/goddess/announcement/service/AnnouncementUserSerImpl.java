package com.bjike.goddess.announcement.service;

import com.bjike.goddess.announcement.bo.AnnouncementUserBO;
import com.bjike.goddess.announcement.dto.AnnouncementUserDTO;
import com.bjike.goddess.announcement.entity.AnnouncementUser;
import com.bjike.goddess.announcement.to.AnnouncementUserTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告对应的发布对象业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-12 10:34 ]
 * @Description: [ 公告对应的发布对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "announcementSerCache")
@Service
public class AnnouncementUserSerImpl extends ServiceImpl<AnnouncementUser, AnnouncementUserDTO> implements AnnouncementUserSer {
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AnnouncementUserBO save(AnnouncementUserTO to) throws SerException {
        AnnouncementUser entity = BeanTransform.copyProperties(to, AnnouncementUser.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AnnouncementUserBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void read(String announcementId, String name) throws SerException {
        AnnouncementUser announcementUser = find(announcementId, name);
        if (announcementUser != null) {
            AnnouncementUser entity = super.findById(announcementUser.getId());
            entity.setHaveRead(true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }
    }

    @Override
    public Boolean check(String announcementId, String name) throws SerException {
        AnnouncementUser announcementUser = find1(announcementId, name);
        if(announcementUser!=null){
            AnnouncementUser entity = super.findById(announcementUser.getId());
            if ((entity.getHaveRead() != null) && (entity.getHaveRead())) {
                return true;
            }
            return false;
        }
        return null;
    }

    @Override
    public AnnouncementUser find(String announcementId, String name) throws SerException {
        String[] announcements = new String[]{announcementId};
        String[] users = new String[]{name};
        List<AnnouncementUser> list = null;
        for (int i = 0; i < announcements.length; i++) {
            String sql = "select id from announcement_announcementuser where " +
                    "announcement_id='" + announcements[i] + "' and name='" + users[i] + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, AnnouncementUser.class, fileds);
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private AnnouncementUser find1(String announcementId, String name) throws SerException {
        String[] announcements = new String[]{announcementId};
        String[] users = new String[]{name};
        List<AnnouncementUser> list = null;
        for (int i = 0; i < announcements.length; i++) {
            String sql = "select id from announcement_announcementuser where " +
                    "announcement_id='" + announcements[i] + "' and name='" + users[i] + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, AnnouncementUser.class, fileds);
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
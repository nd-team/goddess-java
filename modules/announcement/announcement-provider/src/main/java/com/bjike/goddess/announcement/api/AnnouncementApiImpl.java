package com.bjike.goddess.announcement.api;

import com.bjike.goddess.announcement.bo.AnnouncementBO;
import com.bjike.goddess.announcement.dto.AnnouncementDTO;
import com.bjike.goddess.announcement.service.AnnouncementSer;
import com.bjike.goddess.announcement.to.AnnouncementTO;
import com.bjike.goddess.announcement.to.GuidePermissionTO;
import com.bjike.goddess.announcement.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 公告业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:37 ]
 * @Description: [ 公告业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("announcementApiImpl")
public class AnnouncementApiImpl implements AnnouncementAPI {
    @Autowired
    private AnnouncementSer announcementSer;

    @Override
    public List<AnnouncementBO> list(AnnouncementDTO dto) throws SerException {
        return announcementSer.list(dto);
    }

    @Override
    public List<AnnouncementBO> currentList(AnnouncementDTO dto) throws SerException {
        return announcementSer.currentList(dto);
    }

    @Override
    public Long currentListCount(AnnouncementDTO dto) throws SerException {
        return announcementSer.currentListCount(dto);
    }

    @Override
    public AnnouncementBO save(AnnouncementTO to) throws SerException {
        return announcementSer.save(to);
    }

    @Override
    public void edit(AnnouncementTO to) throws SerException {
        announcementSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        announcementSer.delete(id);
    }

    @Override
    public AnnouncementBO findByID(String id) throws SerException {
        return announcementSer.findByID(id);
    }

    @Override
    public Long count(AnnouncementDTO dto) throws SerException {
        return announcementSer.count(dto);
    }


    @Override
    public List<AnnouncementBO> requiredReads(AnnouncementDTO dto) throws SerException {
        return announcementSer.requiredReads(dto);
    }

    @Override
    public AnnouncementBO read(String id) throws SerException {
        return announcementSer.read(id);
    }

    @Override
    public Set<String> allNumbers() throws SerException {
        return announcementSer.allNumbers();
    }

    @Override
    public Set<String> allClass() throws SerException {
        return announcementSer.allClass();
    }

    @Override
    public Set<String> allAuthors() throws SerException {
        return announcementSer.allAuthors();
    }

    @Override
    public List<AnnouncementBO> search(AnnouncementDTO dto) throws SerException {
        return announcementSer.search(dto);
    }

    @Override
    public List<AnnouncementBO> listByClass(String classify) throws SerException {
        return announcementSer.listByClass(classify);
    }

    @Override
    public AnnouncementBO addPerson(AnnouncementTO to) throws SerException {
        return announcementSer.addPerson(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        announcementSer.freeze(id);
    }

    @Override
    public void thaw(String id) throws SerException {
        announcementSer.thaw(id);
    }

    @Override
    public Long requiredCount() throws SerException {
        return announcementSer.requiredCount();
    }

    @Override
    public boolean checkRequired() throws SerException {
        return announcementSer.checkRequired();
    }

    @Override
    public boolean checkByClass(String classify) throws SerException {
        return announcementSer.checkByClass(classify);
    }

    @Override
    public List<UserBO> allUsers() throws SerException {
        return announcementSer.allUsers();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return announcementSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return announcementSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<AnnouncementBO> phoneList() throws SerException {
        return announcementSer.phoneList();
    }
}
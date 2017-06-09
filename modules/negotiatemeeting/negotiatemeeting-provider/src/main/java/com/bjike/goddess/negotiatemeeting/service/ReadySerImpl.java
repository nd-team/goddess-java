package com.bjike.goddess.negotiatemeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.negotiatemeeting.bo.OrganizationBO;
import com.bjike.goddess.negotiatemeeting.bo.ReadyBO;
import com.bjike.goddess.negotiatemeeting.dto.ReadyDTO;
import com.bjike.goddess.negotiatemeeting.entity.Organization;
import com.bjike.goddess.negotiatemeeting.entity.Ready;
import com.bjike.goddess.negotiatemeeting.to.ReadyTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 协商前准备信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:39 ]
 * @Description: [ 协商前准备信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "negotiatemeetingSerCache")
@Service
public class ReadySerImpl extends ServiceImpl<Ready, ReadyDTO> implements ReadySer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private OrganizationSer organizationSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ReadyBO save(ReadyTO to) throws SerException {
        Ready entity = BeanTransform.copyProperties(to, Ready.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ReadyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ReadyTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        Ready entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        String userId = entity.getUserId();
        String organizationId = entity.getOrganizationId();
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Ready.class, true);
        entity.setCreateTime(a);
        entity.setAttend(name);
        entity.setUserId(userId);
        entity.setOrganizationId(organizationId);
        entity.setModifyTime(LocalDateTime.now());
        entity.setOrganizationId(organizationId);
        entity.setUserId(userId);
        super.update(entity);
        //todo:发邮件给主持人
        Organization organization = findByOrganizationId(entity.getOrganizationId());
        if (organization != null) {
            String hostId = organization.getHost();
            if (hostId != null) {
                String title = "协商会议";
                //todo:表格发送,会议组织内容、协商前准备信息
            }
        }
    }

    @Override
    public List<ReadyBO> currentList() throws SerException {
        String name = userAPI.currentUser().getUsername();
        String userId = findUserId(name);
        ReadyDTO dto = new ReadyDTO();
        List<ReadyBO> boList=new ArrayList<ReadyBO>();
        if (userId != null) {
            dto.getSorts().add("createTime=desc");
            dto.getConditions().add(Restrict.eq("userId", userId));
            List<Ready> list = super.findByCis(dto);
            for (Ready r:list){
                ReadyBO bo = BeanTransform.copyProperties(r, ReadyBO.class);
                OrganizationBO organizationBO = organizationSer.findByID(r.getOrganizationId());
                bo.setMeetingTopic(organizationBO.getMeetingTopic());
                bo.setReason(organizationBO.getReason());
                bo.setAttend(findUserName(r.getUserId()));
                bo.setMeetingNumber(organizationBO.getMeetingNumber());
                boList.add(bo);
            }
        }
        return boList;
    }

    @Override
    public List<ReadyBO> list(ReadyDTO dto) throws SerException {
        List<Ready> list = super.findByCis(dto, true);
        List<ReadyBO> boList = new ArrayList<ReadyBO>();
        for (Ready r : list) {
            ReadyBO bo = BeanTransform.copyProperties(r, ReadyBO.class);
            OrganizationBO organizationBO = organizationSer.findByID(r.getOrganizationId());
            bo.setMeetingTopic(organizationBO.getMeetingTopic());
            bo.setReason(organizationBO.getReason());
            bo.setAttend(findUserName(r.getUserId()));
            bo.setMeetingNumber(organizationBO.getMeetingNumber());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public Long count(ReadyDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        dto.getConditions().add(Restrict.eq("attend",name));
        return super.count(dto);
    }

    @Override
    public ReadyBO findByID(String id) throws SerException {
        Ready entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ReadyBO.class);
    }

    /**
     * 通过用户名查找用户id
     *
     * @param name 用户名
     * @return
     * @throws SerException
     */
    private String findUserId(String name) throws SerException {
        List<User> list = null;
        String[] names = new String[]{name};
        for (String s : names) {
            String sql = "SELECT id FROM user\n" +
                    "where username='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, User.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0).getId();
        }
        return null;
    }

    /**
     * 通过会议组织id查找具体的会议信息
     *
     * @param id 会议组织id
     * @return
     * @throws SerException
     */
    private Organization findByOrganizationId(String id) throws SerException {
        String[] ids = new String[]{id};
        List<Organization> list = null;
        for (String s : ids) {
            String sql = "SELECT meetingNumber,meetingFormat,meetingArea,meetingTopic,content,host,organization\n" +
                    "FROM negotiatemeeting_organization\n" +
                    "WHERE id='" + s + "'";
            String[] fileds = new String[]{"meetingNumber", "meetingFormat", "meetingArea", "meetingTopic", "content", "host", "organization"};
            list = super.findBySql(sql, Organization.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 发邮件给个人
     *
     * @param content   内容
     * @param title     标题
     * @param receivers 接收者
     * @throws SerException
     */
    private void sendPerson(String content, String title, String[] receivers) throws SerException {
        MessageTO messageTO = new MessageTO();
        messageTO.setContent(content);
        messageTO.setTitle(title);
        messageTO.setReceivers(receivers);
        messageAPI.send(messageTO);
    }

    private String findUserName(String id) throws SerException {
        String[] ids = new String[]{id};
        List<UserBO> list = null;
        for (String s : ids) {
            String sql = "SELECT username FROM user " +
                    "where id='" + s + "'";
            String[] fileds = new String[]{"username"};
            list = super.findBySql(sql, UserBO.class, fileds);
        }
        if (list != null && !list.isEmpty()) {
            return list.get(0).getUsername();
        }
        return null;
    }
}
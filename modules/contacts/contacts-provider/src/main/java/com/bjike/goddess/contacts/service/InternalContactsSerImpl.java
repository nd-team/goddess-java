package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.entity.InternalContacts;
import com.bjike.goddess.contacts.to.InternalContactsTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 内部通讯录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class InternalContactsSerImpl extends ServiceImpl<InternalContacts, InternalContactsDTO> implements InternalContactsSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private static final String foot = "（正确可忽略这个邮件，否则请发邮件到综合资源部。）";
    private static final String title = "关于通讯录信息正确性";

    /**
     * 转换内部通讯录传输对象
     *
     * @param entity 内部通讯录实体对象
     * @return
     * @throws SerException
     */
    private InternalContactsBO transformBO(InternalContacts entity) throws SerException {
        InternalContactsBO bo = BeanTransform.copyProperties(entity, InternalContactsBO.class);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
        List<UserBO> user = userAPI.findByCis(userDTO);
        if (user.size() != 0) {
            bo.setUsername(user.get(0).getUsername());
            bo.setNumber(user.get(0).getEmployeeNumber());
            PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.get(0).getId());
            bo.setArea("");
            bo.setPosition("");
            bo.setDepartment("");
            if (null != detailBO)
                for (String id : detailBO.getPositionIds().split(",")) {
                    PositionDetailBO position = positionDetailAPI.findBOById(id);
                    bo.setPosition(bo.getPosition() + "," + position.getPosition());
                    bo.setDepartment(bo.getDepartment() + "," + position.getDepartmentName());
                    bo.setArea(bo.getArea() + "," + position.getArea());
                }
        }
        return bo;
    }

    /**
     * 转换内部通讯录传输对象集合
     *
     * @param list 内部通讯录实体对象集合
     * @return
     * @throws SerException
     */
    private List<InternalContactsBO> transformBOList(List<InternalContacts> list) throws SerException {
        List<InternalContactsBO> bos = new ArrayList<>(list.size());
        for (InternalContacts entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InternalContactsBO save(InternalContactsTO to) throws SerException {
        InternalContacts entity = BeanTransform.copyProperties(to, InternalContacts.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InternalContactsBO update(InternalContactsTO to) throws SerException {
        InternalContacts entity = BeanTransform.copyProperties(to, InternalContacts.class), contacts = super.findById(to.getId());
        entity.setCreateTime(contacts.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InternalContactsBO delete(InternalContactsTO to) throws SerException {
        InternalContacts entity = super.findById(to.getId());
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<InternalContactsBO> findEmailNotNull() throws SerException {
        InternalContactsDTO dto = new InternalContactsDTO();
        dto.getConditions().add(Restrict.isNotNull("email"));
        List<InternalContacts> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public InternalContactsBO findByUser(String user_id) throws SerException {
        InternalContactsDTO dto = new InternalContactsDTO();
        dto.getConditions().add(Restrict.eq("user_id", user_id));
        InternalContacts entity = super.findOne(dto);
        return this.transformBO(entity);
    }

    @Override
    public List<InternalContactsBO> maps(InternalContactsDTO dto) throws SerException {
        List<InternalContacts> list = super.findByPage(dto);
        return this.transformBOList(list);
    }

    @Override
    public void sendEmail() throws SerException {
        List<InternalContactsBO> bos = this.findEmailNotNull();
        for (InternalContactsBO bo : bos) {
            try {
                StringBuilder content = new StringBuilder(0);
                if (StringUtils.isNotBlank(bo.getDepartment()))
                    content.append("部门/项目组:").append(bo.getDepartment()).append(",");
                if (StringUtils.isNotBlank(bo.getPosition()))
                    content.append("岗位:").append(bo.getPosition()).append(",");
                if (StringUtils.isNotBlank(bo.getUsername()))
                    content.append("姓名:").append(bo.getUsername()).append(",");
                if (StringUtils.isNotBlank(bo.getPhone()))
                    content.append("联系电话:").append(bo.getPhone()).append(",");
                if (StringUtils.isNotBlank(bo.getQq()))
                    content.append("QQ号:").append(bo.getQq()).append(",");
                if (StringUtils.isNotBlank(bo.getWeChat()))
                    content.append("微信号:").append(bo.getWeChat()).append(",");
                String[] receivers = {bo.getUserId()};
                MessageTO message = new MessageTO(title, content.toString());
                message.setMsgType(MsgType.SYS);
                message.setRangeType(RangeType.SPECIFIED);
                message.setSendType(SendType.EMAIL);
                message.setReceivers(receivers);
                messageAPI.send(message);
            } catch (SerException e) {
                //错误邮箱抛出继续
            }
        }
    }
}
package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.bo.ExternalContactsBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.entity.ExternalContacts;
import com.bjike.goddess.contacts.enums.GuideAddrStatus;
import com.bjike.goddess.contacts.to.ExternalContactsTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 外部通讯录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class ExternalContactsSerImpl extends ServiceImpl<ExternalContacts, ExternalContactsDTO> implements ExternalContactsSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ContactPermissionSer cusPermissionSer;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;



    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExternalContactsBO save(ExternalContactsTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        ExternalContacts entity = BeanTransform.copyProperties(to, ExternalContacts.class, true);
        entity.setWriter(user.getUsername());
        entity.setWriteNumber(user.getEmployeeNumber());
        entity.setWriteTime(LocalDateTime.now());
        super.save(entity);
        if (to.isSend()) {
            //发送邮件进行公示
            send(to);
        }
        return BeanTransform.copyProperties(entity, ExternalContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExternalContactsBO update(ExternalContactsTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        ExternalContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setWriter(user.getUsername());
        entity.setWriteNumber(user.getEmployeeNumber());
        entity.setWriteTime(LocalDateTime.now());
        super.update(entity);
        if (to.isSend()) {
            //发送邮件进行公示
            send(to);
        }
        return BeanTransform.copyProperties(entity, ExternalContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExternalContactsBO delete(ExternalContactsTO to) throws SerException {
        ExternalContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ExternalContactsBO.class);
    }

    @Override
    public List<ExternalContactsBO> findByArea(String area) throws SerException {
        ExternalContactsDTO dto = new ExternalContactsDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        List<ExternalContacts> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ExternalContactsBO.class);
    }

    @Override
    public List<ExternalContactsBO> maps(ExternalContactsDTO dto) throws SerException {
        dto.getSorts().add("writeTime=desc");
        List<ExternalContacts> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ExternalContactsBO.class);
    }

    @Override
    public ExternalContactsBO getById(String id) throws SerException {
        ExternalContacts entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, ExternalContactsBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        ExternalContactsDTO dto = new ExternalContactsDTO();
        return super.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExternalContactsBO importExcel(List<ExternalContactsTO> externalContactsTO) throws SerException {
        List<ExternalContacts> externalContacts = BeanTransform.copyProperties(externalContactsTO, ExternalContacts.class, true);
        externalContacts.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(externalContacts);

        ExternalContactsBO externalContactsBO = BeanTransform.copyProperties(new ExternalContacts(), ExternalContactsBO.class);
        return externalContactsBO;
    }

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 增加或更新时发送邮件
     */
    private void send(ExternalContactsTO to) throws SerException{
        //获得综合资源部,商务发展部,总经办的邮箱
        String[] allEmails = null;
        //从公共邮箱中得到部门的邮箱
        CommonalityDTO commonalityDTO = new CommonalityDTO();
        List<CommonalityBO> commonalityBOList = commonalityAPI.maps(commonalityDTO);
        List<String> stringList = new ArrayList<>();
        for(CommonalityBO commonalityBO : commonalityBOList){
            if (commonalityBO.getDepartmentId().equals(this.getDepartment("综合资源部"))) {
                stringList.add(commonalityBO.getEmail());
            }
            if (commonalityBO.getDepartmentId().equals(this.getDepartment("商务发展部"))) {
                stringList.add(commonalityBO.getEmail());
            }
            if (commonalityBO.getDepartmentId().equals(this.getDepartment("总经办"))) {
                stringList.add(commonalityBO.getEmail());
            }
        }
        allEmails = (String[]) stringList.toArray(new String[stringList.size()]);

        String content = html(to);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("外部通讯录");
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setContent(content);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);
        messageTO.setReceivers(allEmails);
        messageAPI.send(messageTO);
    }

    private String html(ExternalContactsTO to) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>外部通讯录</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>地区</td>");
        sb.append("<td>项目组</td>");
        sb.append("<td>姓名</td>");
        sb.append("<td>单位名称</td>");
        sb.append("<td>岗位</td>");
        sb.append("<td>联系电话</td>");
        sb.append("<td>邮箱</td>");
        sb.append("<td>主要负责内容</td>");
        sb.append("<td>其他关联项目</td>");
        sb.append("<td>对外联系信息</td>");
        sb.append("<td>联系时间频率</td>");
        sb.append("<td>填写人</td>");
        sb.append("<td>填写人编号</td>");
        sb.append("<td>录入时间</td>");
        sb.append("<td>备注</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + to.getArea() + "</td>");
        sb.append("<td>" + to.getProject() + "</td>");
        sb.append("<td>" + to.getUsername() + "</td>");
        sb.append("<td>" + to.getUnit() + "</td>");
        sb.append("<td>" + to.getPosition() + "</td>");
        sb.append("<td>" + to.getPhone() + "</td>");
        sb.append("<td>" + to.getEmail() + "</td>");
        sb.append("<td>" + to.getResponsible() + "</td>");
        sb.append("<td>" + to.getOther() + "</td>");
        sb.append("<td>" + to.getExternal() + "</td>");
        sb.append("<td>" + to.getFrequency() + "</td>");
        sb.append("<td>" + to.getWriter() + "</td>");
        sb.append("<td>" + to.getWriteNumber() + "</td>");
        sb.append("<td>" + to.getWriteTime() + "</td>");
        sb.append("<td>" + to.getRemark() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }

    //得到对应部门的id号
    private String getDepartment(String department) throws SerException{
        //根据组织结构中的部门名称查询部门id
        DepartmentDetailDTO departmentDetailDTO = new DepartmentDetailDTO();
        departmentDetailDTO.getConditions().add(Restrict.eq("department", department));
        List<DepartmentDetailBO> departmentDetailBOList = departmentDetailAPI.view(departmentDetailDTO);
        String departmentId = departmentDetailBOList.get(0).getId();
        return departmentId;
    }

}
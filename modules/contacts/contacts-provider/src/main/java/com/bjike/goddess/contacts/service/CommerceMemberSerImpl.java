package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommerceMemberBO;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.dto.CommerceMemberDTO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.entity.CommerceMember;
import com.bjike.goddess.contacts.enums.GuideAddrStatus;
import com.bjike.goddess.contacts.to.CommerceMemberTO;
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
 * 商务会员卡业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class CommerceMemberSerImpl extends ServiceImpl<CommerceMember, CommerceMemberDTO> implements CommerceMemberSer {

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
    public CommerceMemberBO save(CommerceMemberTO to) throws SerException {
        CommerceMember entity = BeanTransform.copyProperties(to, CommerceMember.class);
        CommerceMemberDTO dto = new CommerceMemberDTO();
        dto.getConditions().add(Restrict.eq("number", to.getNumber()));
        if (super.count(dto) != 0)
            throw new SerException(to.getNumber() + ":该编号以已存在");
        super.save(entity);
        if (to.isSend()) {
            //发送邮件进行公示
            send(to);
        }
        return BeanTransform.copyProperties(entity, CommerceMemberBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceMemberBO update(CommerceMemberTO to) throws SerException {
        CommerceMember entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        if (!to.getNumber().equals(entity.getNumber())) {
            CommerceMemberDTO dto = new CommerceMemberDTO();
            dto.getConditions().add(Restrict.eq("number", to.getNumber()));
            if (super.count(dto) != 0)
                throw new SerException(to.getNumber() + ":该编号以已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        if (to.isSend()) {
            //发送邮件进行公示
            send(to);
        }
        return BeanTransform.copyProperties(entity, CommerceMemberBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceMemberBO delete(CommerceMemberTO to) throws SerException {
        CommerceMember entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CommerceMemberBO.class);
    }

    @Override
    public List<CommerceMemberBO> maps(CommerceMemberDTO dto) throws SerException {
        List<CommerceMember> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, CommerceMemberBO.class);
    }

    @Override
    public CommerceMemberBO getById(String id) throws SerException {
        CommerceMember entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, CommerceMemberBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        CommerceMemberDTO dto = new CommerceMemberDTO();
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
    private void send(CommerceMemberTO to) throws SerException {
        //获得综合资源部,商务发展部,总经办的邮箱
        String[] allEmails = null;
        //从公共邮箱中得到部门的邮箱
        CommonalityDTO commonalityDTO = new CommonalityDTO();
        List<CommonalityBO> commonalityBOList = commonalityAPI.maps(commonalityDTO);
        List<String> stringList = new ArrayList<>();
        for (CommonalityBO commonalityBO : commonalityBOList) {
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
        messageTO.setTitle("商务会员卡");
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setContent(content);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);
        messageTO.setReceivers(allEmails);
        messageAPI.send(messageTO);
    }

    private String html(CommerceMemberTO to) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>商务会员卡</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>开卡名称</td>");
        sb.append("<td>卡号</td>");
        sb.append("<td>开卡人联系方式</td>");
        sb.append("<td>用途</td>");
        sb.append("<td>类别</td>");
        sb.append("<td>备注</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + to.getName() + "</td>");
        sb.append("<td>" + to.getNumber() + "</td>");
        sb.append("<td>" + to.getPhone() + "</td>");
        sb.append("<td>" + to.getPurpose() + "</td>");
        sb.append("<td>" + to.getType() + "</td>");
        sb.append("<td>" + to.getRemark() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }

    //得到对应部门的id号
    private String getDepartment(String department) throws SerException {
        //根据组织结构中的部门名称查询部门id
        DepartmentDetailDTO departmentDetailDTO = new DepartmentDetailDTO();
        departmentDetailDTO.getConditions().add(Restrict.eq("department", department));
        List<DepartmentDetailBO> departmentDetailBOList = departmentDetailAPI.view(departmentDetailDTO);
        String departmentId = departmentDetailBOList.get(0).getId();
        return departmentId;
    }


}
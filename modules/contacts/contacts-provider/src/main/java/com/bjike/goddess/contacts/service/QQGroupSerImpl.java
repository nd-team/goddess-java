package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.bo.QQGroupBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.dto.QQGroupDTO;
import com.bjike.goddess.contacts.entity.QQGroup;
import com.bjike.goddess.contacts.enums.GuideAddrStatus;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.QQGroupTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * QQ群管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class QQGroupSerImpl extends ServiceImpl<QQGroup, QQGroupDTO> implements QQGroupSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ContactPermissionSer cusPermissionSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private MessageAPI messageAPI;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QQGroupBO save(QQGroupTO to) throws SerException {
        QQGroup entity = BeanTransform.copyProperties(to, QQGroup.class);
        entity.isStatus(Boolean.TRUE);
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        super.save(entity);

        //发送对象的邮箱地址
        String email = null;
        //是否发送邮件
        if (to.isSend()) {
            String sendObject = to.getSendObject();
            if (StringUtils.isNotBlank(sendObject)) {
                List<OpinionBO> opinionBOList = departmentDetailAPI.findThawOpinion();
                for (OpinionBO opinionBO : opinionBOList) {
                    if (sendObject.equals(opinionBO.getValue())) {
                        //根据组织结构中的部门名称查询部门id
                        DepartmentDetailDTO departmentDetailDTO = new DepartmentDetailDTO();
                        departmentDetailDTO.getConditions().add(Restrict.eq("department",sendObject));
                        List<DepartmentDetailBO> departmentDetailBOList =departmentDetailAPI.view(departmentDetailDTO);
                        String departmentId = departmentDetailBOList.get(0).getId();
                        //从公邮中得到部门的邮箱
                        CommonalityDTO dto = new CommonalityDTO();
                        List<CommonalityBO> commonalityBOList = commonalityAPI.maps(dto);
                        for (CommonalityBO commonalityBO : commonalityBOList) {
                            if (departmentId.equals(commonalityBO.getDepartmentId())) {
                                email = commonalityBO.getEmail();
                                String content = html(to);

                                //调用发送邮箱接口
                                MessageTO messageTO = new MessageTO();
                                messageTO.setTitle("QQ群");
                                messageTO.setMsgType(MsgType.SYS);
                                messageTO.setContent(content);
                                messageTO.setSendType(SendType.EMAIL);
                                messageTO.setRangeType(RangeType.SPECIFIED);
                                messageTO.setReceivers(email.split(";"));
                                messageAPI.send(messageTO);
                            }
                        }
                    }
                }
            } else {
                throw new SerException("发送对象不能为空");
            }
        }

        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    private String html(QQGroupTO to) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>QQ群新加人员</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>Q群号</td>");
        sb.append("<td>Q群名称</td>");
        sb.append("<td>Q群对象</td>");
        sb.append("<td>Q群管理人</td>");
        sb.append("<td>Q群状态</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + to.getNumber() + "</td>");
        sb.append("<td>" + to.getName() + "</td>");
        sb.append("<td>" + to.getObject() + "</td>");
        sb.append("<td>" + to.getManager() + "</td>");
        sb.append("<td>" + to.getStatus() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public QQGroupBO update(QQGroupTO to) throws SerException {
        QQGroup entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.isStatus(Boolean.TRUE);
        super.update(entity);
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QQGroupBO delete(QQGroupTO to) throws SerException {
        QQGroup entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QQGroupBO close(QQGroupTO to) throws SerException {
        QQGroup entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.isStatus(Boolean.FALSE);
        super.update(entity);
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Override
    public List<QQGroupBO> maps(QQGroupDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        List<QQGroup> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QQGroupBO.class);
    }

    @Override
    public QQGroupBO getById(String id) throws SerException {
        QQGroup entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        QQGroupDTO dto = new QQGroupDTO();
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
    public QQGroupBO importExcel(List<QQGroupTO> qqGroupTO) throws SerException {

        List<QQGroup> qqGroup = BeanTransform.copyProperties(qqGroupTO, QQGroup.class, true);
        qqGroup.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(qqGroup);

        QQGroupBO qqGroupBO = BeanTransform.copyProperties(new QQGroup(), QQGroupBO.class);
        return qqGroupBO;
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
}
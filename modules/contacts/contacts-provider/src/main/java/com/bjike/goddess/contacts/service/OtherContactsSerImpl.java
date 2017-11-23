package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.bo.OtherContactsBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.dto.OtherContactsDTO;
import com.bjike.goddess.contacts.entity.OtherContacts;
import com.bjike.goddess.contacts.enums.GuideAddrStatus;
import com.bjike.goddess.contacts.excel.OtherContactsTemplateExport;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.OtherContactsTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 其他通讯录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:44 ]
 * @Description: [ 其他通讯录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class OtherContactsSerImpl extends ServiceImpl<OtherContacts, OtherContactsDTO> implements OtherContactsSer {

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
    public OtherContactsBO save(OtherContactsTO to) throws SerException {
        OtherContacts entity = BeanTransform.copyProperties(to, OtherContacts.class, true);
        super.save(entity);

        //发送对象的邮箱地址
        String email = null;
        //是否发送邮件
        if (to.isSend()) {
            String sendObject = "综合资源部";
            if (StringUtils.isNotBlank(sendObject)) {
                List<OpinionBO> opinionBOList = departmentDetailAPI.findThawOpinion();
                for (OpinionBO opinionBO : opinionBOList) {
                    if (sendObject.equals(opinionBO.getValue())) {
                        //根据组织结构中的部门名称查询部门id
                        DepartmentDetailDTO departmentDetailDTO = new DepartmentDetailDTO();
                        departmentDetailDTO.getConditions().add(Restrict.eq("department", sendObject));
                        List<DepartmentDetailBO> departmentDetailBOList = departmentDetailAPI.view(departmentDetailDTO);
                        String departmentId = departmentDetailBOList.get(0).getId();
                        //从公邮中得到部门的邮箱
                        CommonalityDTO dto = new CommonalityDTO();
                        List<CommonalityBO> commonalityBOList = commonalityAPI.findAll();
                        for (CommonalityBO commonalityBO : commonalityBOList) {
                            if (departmentId.equals(commonalityBO.getDepartmentId())) {
                                email = commonalityBO.getEmail();
                                String content = html(to);
                                String[] email1 = new String[1];
                                email1[0] = email;
                                //调用发送邮箱接口
                                MessageTO messageTO = new MessageTO();
                                messageTO.setTitle("其他通讯录");
                                messageTO.setMsgType(MsgType.SYS);
                                messageTO.setContent(content);
                                messageTO.setSendType(SendType.EMAIL);
                                messageTO.setRangeType(RangeType.SPECIFIED);
                                messageTO.setReceivers(email1);
                                messageAPI.send(messageTO);
                            }
                        }
                    }
                }
            } else {
                throw new SerException("发送对象不能为空");
            }
        }

        return BeanTransform.copyProperties(entity, OtherContactsBO.class);
    }


    private String html(OtherContactsTO to) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>其他通讯录新加人员</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>服务类别</td>");
        sb.append("<td>公司名称</td>");
        sb.append("<td>联系电话</td>");
        sb.append("<td>公司地址</td>");
        sb.append("<td>使用人</td>");
        sb.append("<td>使用日期</td>");
        sb.append("<td>评价</td>");
        sb.append("<td>备注</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + to.getType() + "</td>");
        sb.append("<td>" + to.getName() + "</td>");
        sb.append("<td>" + to.getPhone() + "</td>");
        sb.append("<td>" + to.getAddress() + "</td>");
        sb.append("<td>" + to.getUser() + "</td>");
        sb.append("<td>" + to.getUseDate() + "</td>");
        sb.append("<td>" + to.getEvaluate() + "</td>");
        sb.append("<td>" + to.getRemark() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherContactsBO update(OtherContactsTO to) throws SerException {
        OtherContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, OtherContactsBO.class, false);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherContactsBO delete(OtherContactsTO to) throws SerException {
        OtherContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return null;
    }

    @Override
    public List<OtherContactsBO> maps(OtherContactsDTO dto) throws SerException {
        search(dto);
        List<OtherContacts> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, OtherContactsBO.class);
    }

    private List<OtherContactsBO> search(OtherContactsDTO dto) throws SerException {
        //公司名称
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.like("name", dto.getName()));
        }
        List<OtherContacts> otherContacts = super.findByCis(dto);
        List<OtherContactsBO> otherContactsBOS = BeanTransform.copyProperties(otherContacts, OtherContactsBO.class);
        return otherContactsBOS;
    }

    @Override
    public OtherContactsBO getById(String id) throws SerException {
        OtherContacts entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        OtherContactsBO bo = BeanTransform.copyProperties(entity, OtherContactsBO.class);
        return bo;
    }

    @Override
    public Long getTotal() throws SerException {
        OtherContactsDTO dto = new OtherContactsDTO();
        search(dto);
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
    public OtherContactsBO importExcel(List<OtherContactsTO> otherContactsTO) throws SerException {

        List<OtherContacts> otherContacts = BeanTransform.copyProperties(otherContactsTO, OtherContacts.class, true);
        otherContacts.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(otherContacts);

        OtherContactsBO otherContactsBO = BeanTransform.copyProperties(new OtherContacts(), OtherContactsBO.class);
        return otherContactsBO;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<OtherContactsTemplateExport> commerceContactsExports = new ArrayList<>();

        OtherContactsTemplateExport excel = new OtherContactsTemplateExport();
        excel.setType("移动通信类");
        excel.setName("test");
        excel.setPhone("jkj");
        excel.setAddress("jkj");
        excel.setUser("jkj");
        excel.setUseDate(LocalDate.now());
        excel.setEvaluate("jkj");
        excel.setRemark("jkj");
        commerceContactsExports.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(commerceContactsExports, exce);
        return bytes;
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
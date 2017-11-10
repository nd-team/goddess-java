package com.bjike.goddess.contacts.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
import com.bjike.goddess.contacts.bo.MobileInternalContactsBO;
import com.bjike.goddess.contacts.bo.NameAndIdBO;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.entity.InternalContacts;
import com.bjike.goddess.contacts.enums.GuideAddrStatus;
import com.bjike.goddess.contacts.enums.Status;
import com.bjike.goddess.contacts.excel.InternalContactsTemplateExport;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.InternalContactsTO;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.InternalContactsConditionBO;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.bo.UserNameSexBO;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.enums.SexType;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private ContactPermissionSer cusPermissionSer;
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
    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;

    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

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
        bo.setUsername(entity.getName());
        bo.setNumber(entity.getEmployeeNum());
//        UserDTO userDTO = new UserDTO();
//        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
//
//        String userToken = RpcTransmit.getUserToken();
//        List<UserBO> userBOList = userAPI.findByCis(userDTO);
//        RpcTransmit.transmitUserToken(userToken);
//
//        if (!CollectionUtils.isEmpty(userBOList)) {
//            UserBO user = userBOList.get(0);
//            EntryRegisterDTO entryBasicInfoDTO = new EntryRegisterDTO();
//            entryBasicInfoDTO.getConditions().add(Restrict.eq("empNumber", user.getEmployeeNumber()));
//            List<EntryRegisterBO> entryBasicInfoBOs = entryRegisterAPI.map(entryBasicInfoDTO);
//            RpcTransmit.transmitUserToken(userToken);
//            if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
//                bo.setUsername(entryBasicInfoBOs.get(0).getUsername());
//            }
//
//            RpcTransmit.transmitUserToken(userToken);
//            if (moduleAPI.isCheck("organize")) {
//                RpcTransmit.transmitUserToken(userToken);
//                PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.getId());
//                RpcTransmit.transmitUserToken(userToken);
//                if (null != detailBO) {
//                    bo.setUsername(detailBO.getName());
//                    bo.setNumber(detailBO.getNumber());
//                    List<PositionUserDetailBO> list = detailBO.getDetailS();
//                    for (PositionUserDetailBO p : list) {
//                        PositionDetailBO position = positionDetailAPI.findBOById(p.getPositionId());
//                        RpcTransmit.transmitUserToken(userToken);
//                        bo.setPosition(position.getPosition());
//                        bo.setDepartment(position.getDepartmentName());
//                        bo.setArea(position.getArea());
//                        break;
//                    }
//                }
//            }
//        }
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
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isNotBlank(to.getEmail())) {
            if (!Validator.isEmail(to.getEmail())) {
                throw new SerException("输入的邮箱格式不正确");
            }
        }
        InternalContacts entity = BeanTransform.copyProperties(to, InternalContacts.class);
        InternalContactsDTO dto = new InternalContactsDTO();
        dto.getConditions().add(Restrict.eq("name", to.getName()));
        if (super.count(dto) != 0)
            throw new SerException("该用户数据已存在");

        entity.setStatus(Status.CONGEAL);
        super.save(entity);
        String[] emails = new String[1];
        emails[0] = to.getEmail();
        if (to.isSend()) {
            //发送邮件测试邮箱是否有误
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("测试邮箱");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setContent("测试,收到此邮箱信息请回复,谢谢");
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);
            messageTO.setReceivers(emails);
            messageAPI.send(messageTO);
        }
        RpcTransmit.transmitUserToken(userToken);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InternalContactsBO update(InternalContactsTO to) throws SerException {
        InternalContacts entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("该数据不存在");
        }
        if (!to.getName().equals(entity.getName())) {
            InternalContactsDTO dto = new InternalContactsDTO();
            dto.getConditions().add(Restrict.eq("name", to.getName()));
            if (super.count(dto) != 0)
                throw new SerException("该用户数据已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setStatus(Status.THAW);
        super.update(entity);

        if (to.isSend()) {
            //获得内部人员全体员工的邮箱地址
            InternalContactsDTO internalContactsDTO = new InternalContactsDTO();
            String allEmails[] = null;
            List<InternalContactsBO> internalContactsBOList = this.maps(internalContactsDTO);
            List<String> stringList = new ArrayList<String>();
            for (InternalContactsBO bo : internalContactsBOList) {
                stringList.add(bo.getEmail());
            }
            allEmails = (String[]) stringList.toArray(new String[stringList.size()]);
            //成员信息变更发送邮件
            String content = html(to);
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("内部通讯录");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setContent(content);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);
            messageTO.setReceivers(allEmails);
            messageAPI.send(messageTO);
        }
        return this.transformBO(entity);
    }

    private String html(InternalContactsTO to) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>内部通讯录成员信息变更</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>用户姓名</td>");
        sb.append("<td>员工编号</td>");
        sb.append("<td>地区</td>");
        sb.append("<td>部门</td>");
        sb.append("<td>岗位</td>");
        sb.append("<td>联系电话</td>");
        sb.append("<td>邮箱</td>");
        sb.append("<td>集团号</td>");
        sb.append("<td>联系电话2</td>");
        sb.append("<td>QQ号</td>");
        sb.append("<td>微信号</td>");
        sb.append("<td>备注</td>");
        sb.append("<td>状态</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + to.getName() + "</td>");
        sb.append("<td>" + to.getEmployeeNum() + "</td>");
        sb.append("<td>" + to.getArea() + "</td>");
        sb.append("<td>" + to.getDepartment() + "</td>");
        sb.append("<td>" + to.getPosition() + "</td>");
        sb.append("<td>" + to.getPhone() + "</td>");
        sb.append("<td>" + to.getEmail() + "</td>");
        sb.append("<td>" + to.getBloc() + "</td>");
        sb.append("<td>" + to.getPhoneNumber() + "</td>");
        sb.append("<td>" + to.getQq() + "</td>");
        sb.append("<td>" + to.getWeChat() + "</td>");
        sb.append("<td>" + to.getRemark() + "</td>");
        sb.append("<td>" + to.getStatus() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public InternalContactsBO delete(InternalContactsTO to) throws SerException {
        InternalContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
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
        if (StringUtils.isBlank(user_id))
            return null;
        InternalContactsDTO dto = new InternalContactsDTO();
        dto.getConditions().add(Restrict.eq("userId", user_id));
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
                String[] receivers = {bo.getUsername()};
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

    @Override
    public InternalContactsBO getById(String id) throws SerException {
        InternalContacts entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        InternalContactsDTO dto = new InternalContactsDTO();
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
    public InternalContactsBO importExcel(List<InternalContactsTO> internalContactsTO) throws SerException {

        for (InternalContactsTO to : internalContactsTO) {
            if (StringUtils.isNotBlank(to.getEmail()) && !Validator.isEmail(to.getEmail())) {
                throw new SerException("输入的邮箱格式不正确");
            }
            InternalContacts entity = BeanTransform.copyProperties(to, InternalContacts.class);
            InternalContactsDTO dto = new InternalContactsDTO();
            dto.getConditions().add(Restrict.eq("name", to.getName()));
            if (super.count(dto) != 0) {
                throw new SerException("该用户数据已存在");
            }
            //判断是否是入职员工
            EntryRegisterDTO entryBasicInfoDTO = new EntryRegisterDTO();
            entryBasicInfoDTO.getConditions().add(Restrict.eq("name", entity.getName()));
            String userToken = RpcTransmit.getUserToken();
            List<EntryRegisterBO> user = entryRegisterAPI.map(entryBasicInfoDTO);
            if (null == user || user.size() < 1) {
                throw new SerException("导入的员工应该为已入职员工");
            }
        }
        List<InternalContacts> internalContacts = BeanTransform.copyProperties(internalContactsTO, InternalContacts.class, true);
        internalContacts.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(internalContacts);

        InternalContactsBO internalContactsBO = BeanTransform.copyProperties(new InternalContacts(), InternalContactsBO.class);
        return internalContactsBO;
    }

    //定时器检测并删除离职员工通讯录
    @Override
    public void checkDimissionInfo() throws SerException {
        //// TODO: 17-7-7
        //每天18:00自动删除离职员工的通讯录
        //从离职模块获得已确认离职的员工的信息
        DimissionInfoDTO dto = new DimissionInfoDTO();
        List<DimissionInfoBO> bos = dimissionInfoAPI.maps(dto);
        //得到内部通讯录的全部信息
        InternalContactsDTO internalContactsDTO = new InternalContactsDTO();
        List<InternalContactsBO> internalContactsBOList = this.maps(internalContactsDTO);
        if (null != internalContactsBOList && internalContactsBOList.size() > 0 && null != bos && bos.size() > 0) {
            for (DimissionInfoBO dimissionInfoBO : bos) {
                if (dimissionInfoBO.getHandle().toString().equals("AFFIRM")) {
                    for (InternalContactsBO internalContactsBO : internalContactsBOList) {
                        if (dimissionInfoBO.getUsername().equals(internalContactsBO.getUsername())) {
                            //将离职员工的通讯录信息冻结
                            internalContactsBO.setStatus(Status.CONGEAL);
                            InternalContacts entity = BeanTransform.copyProperties(internalContactsBO, InternalContacts.class);
                            InternalContacts module = super.findById(internalContactsBO.getId());
                            entity.setCreateTime(module.getCreateTime());
                            entity.setModifyTime(LocalDateTime.now());
                            super.save(entity);

                            //发送离职员工信息公示全体员工邮件
                            //获得内部人员全体员工的邮箱地址
                            InternalContactsDTO interDTO = new InternalContactsDTO();
                            interDTO.getConditions().add(Restrict.eq("status", Status.THAW));
                            String allEmails[] = null;
                            List<InternalContactsBO> boList = this.maps(interDTO);
                            List<String> stringList = new ArrayList<String>();
                            for (InternalContactsBO bo : boList) {
                                stringList.add(bo.getEmail());
                            }
                            allEmails = (String[]) stringList.toArray(new String[stringList.size()]);

                            String content = htmlDimissionInfo(entity);
                            MessageTO messageTO = new MessageTO();
                            messageTO.setTitle("离职员工信息");
                            messageTO.setMsgType(MsgType.SYS);
                            messageTO.setContent(content);
                            messageTO.setSendType(SendType.EMAIL);
                            messageTO.setRangeType(RangeType.SPECIFIED);
                            messageTO.setReceivers(allEmails);
                            messageAPI.send(messageTO);
                        }
                    }
                }
            }
        }
    }

    private Logger log = Logger.getLogger(InternalContactsSerImpl.class);

    @Override
    public List<NameAndIdBO> getUserName() throws SerException {
        String token = RpcTransmit.getUserToken();
        EntryRegisterDTO entryBasicInfoDTO = new EntryRegisterDTO();
        Set<String> bos = entryRegisterAPI.names();
        List<NameAndIdBO> nameAndIdBOs = new ArrayList<>(0);
        bos.stream().forEach(obj -> {
            NameAndIdBO nameAndIdBO = new NameAndIdBO();
            nameAndIdBO.setName(obj);
            nameAndIdBOs.add(nameAndIdBO);
        });

        return nameAndIdBOs;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<InternalContactsTemplateExport> commerceContactsExports = new ArrayList<>();

        InternalContactsTemplateExport excel = new InternalContactsTemplateExport();
        excel.setName("test");
        excel.setEmployeeNum("0012");
        excel.setArea("广州");
        excel.setDepartment("研发部");
        excel.setPosition("数据分析师");
        excel.setPhone("test");
        excel.setEmail("7878121@qq.com");
        excel.setBloc("jkj");
        excel.setPhoneNumber("jkj");
        excel.setQq("jkj");
        excel.setWeChat("jkj");
        excel.setEmergency("jkj");
        excel.setEmergencyPhone("jkj");
        excel.setRemark("jkj");
        excel.setStatus(Status.THAW);
        commerceContactsExports.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(commerceContactsExports, exce);
        return bytes;
    }

    @Override
    public List<String> getEmails(String[] names) throws SerException {
        List<String> strings = new ArrayList<>();
        if (0 < names.length) {
            for (String name : names) {
                try {
//                    List<EntryRegisterBO> entryBasicInfoBOs = entryRegisterAPI.getEntryRegisterByName(name);
//                    if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
//                        for (EntryRegisterBO bo : entryBasicInfoBOs) {
//                            String number = bo.getEmpNumber();
//                            UserDTO userDTO = new UserDTO();
//                            userDTO.getConditions().add(Restrict.eq("empNumber", number));
//                            List<UserBO> userBOs = userAPI.findByCis(userDTO);
//                            if (null != userBOs && userBOs.size() > 0) {
//                                InternalContactsDTO internalContactsDTO = new InternalContactsDTO();
//                                internalContactsDTO.getConditions().add(Restrict.in("userId", userBOs.get(0).getId()));
//
//                                List<InternalContacts> internalContactses = super.findByCis(internalContactsDTO);
////                            List<InternalContactsBO> internalContactsBOs = maps(internalContactsDTO);
//                                if (null != internalContactses && internalContactses.size() > 0) {
//                                    InternalContacts internalContacts = internalContactses.get(0);
//                                    String str = internalContacts.getEmail();
//                                    strings.add(str);
//                                }
//                            }
//                        }
//                    }
                    InternalContactsDTO dto = new InternalContactsDTO();
                    dto.getConditions().add(Restrict.eq("name", name));
                    List<InternalContacts> list = super.findByCis(dto);
                    if (!list.isEmpty()) {
                        strings.add(list.get(0).getEmail());
                    }
                } catch (Exception e) {
                    throw new SerException(e.getMessage());
                }
            }
        }
        return strings;
    }

    @Override
    public String getEmail(String name) throws SerException {
        if (StringUtils.isNotBlank(name)) {
//            List<EntryRegisterBO> entryBasicInfoBOs = entryRegisterAPI.getEntryRegisterByName(name);
//            if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
//                for (EntryRegisterBO bo : entryBasicInfoBOs) {
//                    InternalContactsDTO internalContactsDTO = new InternalContactsDTO();
//                    internalContactsDTO.getConditions().add(Restrict.in("name", bo.getUsername()));
//                    List<InternalContactsBO> internalContactsBOs = maps(internalContactsDTO);
//                    if (null != internalContactsBOs && internalContactsBOs.size() > 0) {
//                        return maps(internalContactsDTO).get(0).getEmail();
//                    }
//                }
//            }
            InternalContactsDTO dto = new InternalContactsDTO();
            dto.getConditions().add(Restrict.eq("name", name));
            List<InternalContacts> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                return list.get(0).getEmail();
            }
        }
        return null;
    }

    @Override
    public List<MobileInternalContactsBO> mobileList(InternalContactsDTO dto) throws SerException {
        log.info("查询列表开始....");
        searchMobileCondition(dto);
        List<InternalContacts> list = super.findByCis(dto);
//        List<InternalContactsBO> bos = this.transformBOList(list);
        if (!CollectionUtils.isEmpty(list)) {
            List<MobileInternalContactsBO> mobileInternalContactsBOs = new ArrayList<>();
            log.info("查询列表开始1...." + JSON.toJSONString(mobileInternalContactsBOs));

            List<String> userNames = list.stream().map(InternalContacts::getName).collect(Collectors.toList());
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.in("username", userNames));
            List<UserBO> userBOs = userAPI.findByCis(userDTO);

            //查员工入职
            EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
            entryRegisterDTO.getConditions().add(Restrict.in("", userNames));
            List<UserNameSexBO> userNameSexBOs = entryRegisterAPI.findSexByUserName((String[]) userNames.toArray(new String[userNames.size()]));

            for (InternalContacts bo : list) {
                MobileInternalContactsBO mobIn = new MobileInternalContactsBO();
                if (null != userBOs && userBOs.size() > 0) {
                    mobIn.setHeadSculpture(userBOs.get(0).getHeadSculpture());
                }
                mobIn.setUserId(bo.getId());
                mobIn.setUsername(bo.getName());
                mobIn.setNumber(bo.getEmployeeNum());
                mobIn.setArea(bo.getArea());
                mobIn.setBloc(bo.getBloc());
                mobIn.setEmail(bo.getEmail());
                mobIn.setDepartment(bo.getDepartment());
                mobIn.setPhone(bo.getPhone());
                mobIn.setPosition(bo.getPosition());
                mobIn.setQq(bo.getQq());
                mobIn.setStatus(bo.getStatus());
                mobIn.setRemark(bo.getRemark());
                mobIn.setWeChat(bo.getWeChat());
                if (null != userNameSexBOs && userNameSexBOs.size() > 0) {
                    List<Integer> integerList = userNameSexBOs.stream().filter(str -> bo.getName().equals(str.getUsername())).map(UserNameSexBO::getGender).collect(Collectors.toList());
                    if (null != integerList && integerList.size() > 0) {
                        if (0 == integerList.get(0)) {
                            mobIn.setSex(SexType.MAN);
                        } else {
                            mobIn.setSex(SexType.WOMAN);
                        }
                    }
//                UserDTO userDTO = new UserDTO();
//                userDTO.getConditions().add(Restrict.eq("username", bo.getName()));
//                UserBO userBO = userAPI.findOne(userDTO);
//                if (null != userBO) {
//                    mobIn.setHeadSculpture(userBO.getHeadSculpture());
//                    mobIn.setUsername(userBO.getUsername());
////                UserDetailBO userDetailBO = userDetailAPI.findByUserId(bo.getUserId());
//                    String sex = entryRegisterAPI.getGender(bo.getName());
//                    if ("男".equals(sex)) {
//                        mobIn.setSex(SexType.MAN);
//                    } else if ("女".equals(sex)) {
//                        mobIn.setSex(SexType.WOMAN);
//                    } else {
//                        mobIn.setSex(SexType.NONE);
//                    }
//                }
                }
                mobileInternalContactsBOs.add(mobIn);
            }
            log.info("查询列表结束....");
            return mobileInternalContactsBOs;
        }
        return null;
    }

    @Override
    public Long getMobileTotal(InternalContactsDTO dto) throws SerException {
        searchMobileCondition(dto);
        return super.count(dto);
    }

    @Override
    public MobileInternalContactsBO findByMobileID(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        InternalContactsDTO dto = new InternalContactsDTO();
        dto.getConditions().add(Restrict.eq("id", id));
        InternalContacts entity = super.findOne(dto);
        if(null == entity){
            throw new SerException("目标数据对象不能为空");
        }
        InternalContactsBO bo = this.transformBO(entity);
        if (null != bo) {
            MobileInternalContactsBO mobileInternalContactsBO = BeanTransform.copyProperties(bo, MobileInternalContactsBO.class, "headSculpture", "sex", "phoneNumber", "emergency", "emergencyPhone");
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.eq("username", mobileInternalContactsBO.getUsername()));
            UserBO userBO = userAPI.findOne(userDTO);
            if (null != userBO) {
                mobileInternalContactsBO.setHeadSculpture(userBO.getHeadSculpture());
            }
            if ("男".equals(entryRegisterAPI.getGender(bo.getUsername()))) {
                mobileInternalContactsBO.setSex(SexType.MAN);
            } else if ("女".equals(entryRegisterAPI.getGender(bo.getUsername()))) {
                mobileInternalContactsBO.setSex(SexType.WOMAN);
            } else {
                mobileInternalContactsBO.setSex(SexType.NONE);
            }

            return mobileInternalContactsBO;
        }
        return null;
    }

    @Override
    public void test(List<InternalContactsTO> tocs) throws SerException {
        List<InternalContacts> internalContacts = BeanTransform.copyProperties(tocs, InternalContacts.class, true);
        if (null != internalContacts && internalContacts.size() > 0) {
            for (InternalContacts entity : internalContacts) {
                InternalContactsConditionBO bo = positionDetailUserAPI.getByName(entity.getName());
                if (null != bo) {
                    entity.setEmployeeNum(bo.getNumber());
                    entity.setDepartment(bo.getDepartment());
                    entity.setArea(bo.getArea());
                    entity.setPosition(bo.getPosition());
                } else {
                    entity.setEmployeeNum(" ");
                    entity.setDepartment(" ");
                    entity.setArea(" ");
                    entity.setPosition(" ");

                }
            }
        }


//        for (InternalContacts internalContacts1 : internalContacts) {
//            UserDTO userDTO = new UserDTO();
//            userDTO.getConditions().add(Restrict.eq("username", internalContacts1.getName()));
//            List<UserBO> users = userAPI.findByCis(userDTO);
//            if (null != users && users.size() > 0) {
//                internalContacts1.setName(users.get(0).getUsername());
//            }
//        }

        super.save(internalContacts);
    }

    @Override
    public InternalContactsConditionBO getByName(String name) throws SerException {
//        if (moduleAPI.isCheck("organize")) {
            return positionDetailUserAPI.getByName(name);
//        }
//        return null;
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

    //每周一定时发送邮箱提醒以确认是否正确
    private void sendEmailWeek(InternalContactsTO to) throws SerException {
        // TODO: 17-7-6  定时任务
        //获得每一个人的邮箱并发送
        InternalContactsDTO internalContactsDTO = new InternalContactsDTO();
        List<InternalContactsBO> internalContactsBOList = this.maps(internalContactsDTO);

        for (InternalContactsBO bo : internalContactsBOList) {
            String[] personEmail = null;
            personEmail[0] = bo.getEmail();

            String content = htmlWeek(to);
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("商务通讯录");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setContent(content);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);

            //定时发送必须写
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");

            messageTO.setReceivers(personEmail);
            messageAPI.send(messageTO);
        }
    }

    private String htmlWeek(InternalContactsTO to) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>内部通讯录</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>用户姓名</td>");
        sb.append("<td>员工编号</td>");
        sb.append("<td>地区</td>");
        sb.append("<td>部门</td>");
        sb.append("<td>岗位</td>");
        sb.append("<td>联系电话</td>");
        sb.append("<td>邮箱</td>");
        sb.append("<td>集团号</td>");
        sb.append("<td>联系电话2</td>");
        sb.append("<td>QQ号</td>");
        sb.append("<td>微信号</td>");
        sb.append("<td>紧急联系人</td>");
        sb.append("<td>紧急联系人电话</td>");
        sb.append("<td>备注</td>");
        sb.append("<td>状态</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + to.getName() + "</td>");
        sb.append("<td>" + to.getEmployeeNum() + "</td>");
        sb.append("<td>" + to.getArea() + "</td>");
        sb.append("<td>" + to.getDepartment() + "</td>");
        sb.append("<td>" + to.getPosition() + "</td>");
        sb.append("<td>" + to.getPhone() + "</td>");
        sb.append("<td>" + to.getEmail() + "</td>");
        sb.append("<td>" + to.getBloc() + "</td>");
        sb.append("<td>" + to.getPhoneNumber() + "</td>");
        sb.append("<td>" + to.getQq() + "</td>");
        sb.append("<td>" + to.getWeChat() + "</td>");
        sb.append("<td>" + to.getEmergency() + "</td>");
        sb.append("<td>" + to.getEmergencyPhone() + "</td>");
        sb.append("<td>" + to.getRemark() + "</td>");
        sb.append("<td>" + to.getStatus() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }

    //离职员工信息
    private String htmlDimissionInfo(InternalContacts to) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>离职员工</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>用户姓名</td>");
        sb.append("<td>员工编号</td>");
        sb.append("<td>地区</td>");
        sb.append("<td>部门</td>");
        sb.append("<td>岗位</td>");
        sb.append("<td>联系电话</td>");
        sb.append("<td>邮箱</td>");
        sb.append("<td>集团号</td>");
        sb.append("<td>联系电话2</td>");
        sb.append("<td>QQ号</td>");
        sb.append("<td>微信号</td>");
        sb.append("<td>紧急联系人</td>");
        sb.append("<td>紧急联系人电话</td>");
        sb.append("<td>备注</td>");
        sb.append("<td>状态</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + to.getName() + "</td>");
        sb.append("<td>" + to.getEmployeeNum() + "</td>");
        sb.append("<td>" + to.getArea() + "</td>");
        sb.append("<td>" + to.getDepartment() + "</td>");
        sb.append("<td>" + to.getPosition() + "</td>");
        sb.append("<td>" + to.getPhone() + "</td>");
        sb.append("<td>" + to.getEmail() + "</td>");
        sb.append("<td>" + to.getBloc() + "</td>");
        sb.append("<td>" + to.getPhoneNumber() + "</td>");
        sb.append("<td>" + to.getQq() + "</td>");
        sb.append("<td>" + to.getWeChat() + "</td>");
        sb.append("<td>" + to.getEmergency() + "</td>");
        sb.append("<td>" + to.getEmergencyPhone() + "</td>");
        sb.append("<td>" + to.getRemark() + "</td>");
        sb.append("<td>" + to.getStatus() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }

    private void searchMobileCondition(InternalContactsDTO dto) throws SerException {
        /**
         * 用户名
         */
        if (StringUtils.isNotBlank(dto.getUserName())) {
            //根据用户名查询用户id
            List<UserBO> userBOList = positionDetailUserAPI.findUserListInOrgan();
            List<InternalContacts> internalContactses = super.findAll();
            List<String> list = new ArrayList<>(0);
            if (!CollectionUtils.isEmpty(internalContactses)) {
                list = internalContactses.stream().map(InternalContacts::getName).distinct().collect(Collectors.toList());
            }
            if (!CollectionUtils.isEmpty(userBOList) && !CollectionUtils.isEmpty(list)) {
                for (UserBO userBO : userBOList) {
                    if (userBO.getUsername().equals(dto.getUserName()) && list.contains(userBO.getUsername())) {
                        dto.getConditions().add(Restrict.eq("name", userBO.getUsername()));
                    }
                }
            }
        }
//        /**
//         * 用户性别
//         */
//        if (dto.getSex() != null) {
//            dto.getConditions().add(Restrict.eq("sex", dto.getSex()));
//        }
//        /**
//         * 地区
//         */
//        if (StringUtils.isNotBlank(dto.getArea())) {
//            dto.getConditions().add(Restrict.eq("area", dto.getArea()));
//        }
//        /**
//         * 员工编号
//         */
//        if (StringUtils.isNotBlank(dto.getNumber())) {
//            dto.getConditions().add(Restrict.eq("number", dto.getNumber()));
//        }
//        /**
//         * 部门/项目组
//         */
//        if (StringUtils.isNotBlank(dto.getDepartment())) {
//            dto.getConditions().add(Restrict.eq("department", dto.getDepartment()));
//        }
//        /**
//         * 职位
//         */
//        if (StringUtils.isNotBlank(dto.getPosition())) {
//            dto.getConditions().add(Restrict.eq("position", dto.getPosition()));
//        }
//        /**
//         * 联系电话
//         */
//        if (StringUtils.isNotBlank(dto.getPhone())) {
//            dto.getConditions().add(Restrict.eq("phone", dto.getPhone()));
//        }
//        /**
//         * 邮箱
//         */
//        if (StringUtils.isNotBlank(dto.getEmail())) {
//            dto.getConditions().add(Restrict.eq("email", dto.getEmail()));
//        }
//        /**
//         * 集团号
//         */
//        if (StringUtils.isNotBlank(dto.getBloc())) {
//            dto.getConditions().add(Restrict.eq("bloc", dto.getBloc()));
//        }
//        /**
//         * QQ号
//         */
//        if (StringUtils.isNotBlank(dto.getQq())) {
//            dto.getConditions().add(Restrict.eq("qq", dto.getQq()));
//        }
//        /**
//         * 微信号
//         */
//        if (StringUtils.isNotBlank(dto.getWeChat())) {
//            dto.getConditions().add(Restrict.eq("weChat", dto.getWeChat()));
//        }
//        /**
//         * 备注
//         */
//        if (StringUtils.isNotBlank(dto.getRemark())) {
//            dto.getConditions().add(Restrict.eq("remark", dto.getRemark()));
//        }
//        /**
//         * 状态
//         */
//        if (dto.getStatus() != null) {
//            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
//        }
    }

}

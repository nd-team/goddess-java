package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
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
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
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
    private EntryBasicInfoAPI entryBasicInfoAPI;

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
//        UserDTO userDTO = new UserDTO();
//        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
//        List<UserBO> user = userAPI.findByCis(userDTO);
//        if (null != user) {
//            if (0 != user.size()) {
//                bo.setUsername(user.get(0).getUsername());
//                bo.setNumber(user.get(0).getEmployeeNumber());
//                PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.get(0).getId());
//                bo.setArea("");
//                bo.setPosition("");
//                bo.setDepartment("");
//                if (null != detailBO)
//                    for (String id : detailBO.getPositionIds().split(",")) {
//                        PositionDetailBO position = positionDetailAPI.findBOById(id);
//                        bo.setPosition(bo.getPosition() + "," + position.getPosition());
//                        bo.setDepartment(bo.getDepartment() + "," + position.getDepartmentName());
//                        bo.setArea(bo.getArea() + "," + position.getArea());
//                    }
//            }
//        }
        UserDTO userDTO = new UserDTO();
        EntryBasicInfoDTO entryBasicInfoDTO = new EntryBasicInfoDTO();
        entryBasicInfoDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
        String userToken = RpcTransmit.getUserToken();
        List<EntryBasicInfoBO> user = entryBasicInfoAPI.listEntryBasicInfo(entryBasicInfoDTO);
        RpcTransmit.transmitUserToken(userToken);

        if (null != user) {
            if (0 != user.size()) {
                bo.setUsername(user.get(0).getName());
                bo.setNumber(user.get(0).getEmployeeID());
                userToken = RpcTransmit.getUserToken();
                PositionDetailUserBO detailBO = positionDetailUserAPI.findOneByUser(user.get(0).getId());
                RpcTransmit.transmitUserToken(userToken);
                bo.setArea(user.get(0).getArea());
                bo.setPosition(user.get(0).getPosition());
                bo.setDepartment(user.get(0).getDepartment());
//                if (null != detailBO)
//                    for (String id : detailBO.getPositionIds().split(",")) {
//                        PositionDetailBO position = positionDetailAPI.findBOById(id);
//                        bo.setPosition(bo.getPosition() + "," + position.getPosition());
//                        bo.setDepartment(bo.getDepartment() + "," + position.getDepartmentName());
//                        bo.setArea(bo.getArea() + "," + position.getArea());
//                    }
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
        if (StringUtils.isNotBlank(to.getEmail())) {
            if (!Validator.isEmail(to.getEmail())) {
                throw new SerException("输入的邮箱格式不正确");
            }
        }
        InternalContacts entity = BeanTransform.copyProperties(to, InternalContacts.class);
        InternalContactsDTO dto = new InternalContactsDTO();
        dto.getConditions().add(Restrict.eq("userId", to.getUserId()));
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
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InternalContactsBO update(InternalContactsTO to) throws SerException {
        InternalContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        if (!to.getUserId().equals(entity.getUserId())) {
            InternalContactsDTO dto = new InternalContactsDTO();
            dto.getConditions().add(Restrict.eq("userId", to.getUserId()));
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
        sb.append("<td>用户ID</td>");
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
        sb.append("<td>" + to.getUserId() + "</td>");
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
            if(StringUtils.isNotBlank(to.getEmail()) && !Validator.isEmail(to.getEmail())){
                throw new SerException("输入的邮箱格式不正确");
            }
            InternalContacts entity = BeanTransform.copyProperties(to, InternalContacts.class);
            InternalContactsDTO dto = new InternalContactsDTO();
            dto.getConditions().add(Restrict.eq("userId", to.getUserId()));
            if (super.count(dto) != 0) {
                throw new SerException("该用户数据已存在");
            }
            //判断是否是入职员工
            EntryBasicInfoDTO entryBasicInfoDTO = new EntryBasicInfoDTO();
            entryBasicInfoDTO.getConditions().add(Restrict.eq("name", entity.getUserId()));
            String userToken = RpcTransmit.getUserToken();
            List<EntryBasicInfoBO> user = entryBasicInfoAPI.listEntryBasicInfo(entryBasicInfoDTO);
            if(null == user || user.size() < 1){
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
                        if (dimissionInfoBO.getUsername().equals(internalContactsBO.getUserId())) {
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

    @Override
    public List<NameAndIdBO> getUserName() throws SerException {
        String token = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(token);
        EntryBasicInfoDTO entryBasicInfoDTO = new EntryBasicInfoDTO();
        List<EntryBasicInfoBO> bos = entryBasicInfoAPI.listEntryBasicInfo(entryBasicInfoDTO);
        List<NameAndIdBO> userNameList = new ArrayList<>();
        if (null != bos && bos.size() > 0) {
            for (EntryBasicInfoBO bo : bos) {
                NameAndIdBO nameAndIdBO = new NameAndIdBO();
                nameAndIdBO.setUserId(bo.getId());
                nameAndIdBO.setName(bo.getName());
                userNameList.add(nameAndIdBO);
            }
        }

        return userNameList;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<InternalContactsTemplateExport> commerceContactsExports = new ArrayList<>();

        InternalContactsTemplateExport excel = new InternalContactsTemplateExport();
        excel.setUserId("test");
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
                List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.getEntryBasicInfoByName(name);
                if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
                    for (EntryBasicInfoBO bo : entryBasicInfoBOs) {
                        InternalContactsDTO internalContactsDTO = new InternalContactsDTO();
                        internalContactsDTO.getConditions().add(Restrict.in("userId", bo.getId()));
                        List<InternalContactsBO> internalContactsBOs = maps(internalContactsDTO);
                        if (null != internalContactsBOs && internalContactsBOs.size() > 0) {
                            InternalContactsBO internalContactsBO = internalContactsBOs.get(0);
                            String str = bo.getEmail();
                            strings.add(str);
                        }
                    }
                }
            }
        }
        return strings;
    }

    @Override
    public String getEmail(String name) throws SerException {
        if (StringUtils.isNotBlank(name)) {
            List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.getEntryBasicInfoByName(name);
            if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
                for (EntryBasicInfoBO bo : entryBasicInfoBOs) {
                    InternalContactsDTO internalContactsDTO = new InternalContactsDTO();
                    internalContactsDTO.getConditions().add(Restrict.in("userId", bo.getId()));
                    List<InternalContactsBO> internalContactsBOs = maps(internalContactsDTO);
                    if (null != internalContactsBOs && internalContactsBOs.size() > 0) {
                        return maps(internalContactsDTO).get(0).getEmail();
                    }
                }
            }
        }
        return null;
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
        sb.append("<td>用户ID</td>");
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
        sb.append("<td>" + to.getUserId() + "</td>");
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
        sb.append("<td>用户ID</td>");
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
        sb.append("<td>" + to.getUserId() + "</td>");
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

}
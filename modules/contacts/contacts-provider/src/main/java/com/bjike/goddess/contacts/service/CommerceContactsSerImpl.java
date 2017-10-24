package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommerceContactsBO;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.bo.MobileCommerceContactsBO;
import com.bjike.goddess.contacts.dto.CommerceContactsDTO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.entity.CommerceContacts;
import com.bjike.goddess.contacts.enums.GuideAddrStatus;
import com.bjike.goddess.contacts.excel.CommerceContactsTemplateExport;
import com.bjike.goddess.contacts.excel.SonPermissionObject;
import com.bjike.goddess.contacts.to.CommerceContactsTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 商务通讯录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 06:04 ]
 * @Description: [ 商务通讯录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class CommerceContactsSerImpl extends ServiceImpl<CommerceContacts, CommerceContactsDTO> implements CommerceContactsSer {

    @Autowired
    private CustomerBaseInfoAPI customerBaseInfoAPI;
    @Autowired
    private InternalContactsSer internalContactsSer;
    @Autowired
    private ExternalContactsSer externalContactsSer;
    @Autowired
    private CommerceMemberSer commerceMemberSer;
    @Autowired
    private CommonalitySer commonalitySer;
    @Autowired
    private OtherContactsSer otherContactsSer;
    @Autowired
    private QQGroupSer qqGroupSer;
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
    @Autowired
    private UserDetailAPI userDetailAPI;


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceContactsBO save(CommerceContactsTO to) throws SerException {
        if (!Validator.isEmail(to.getCusEmail())) {
            throw new SerException("输入的邮箱格式不正确");
        }

        CommerceContacts entity = BeanTransform.copyProperties(to, CommerceContacts.class);
        CommerceContactsDTO dto = new CommerceContactsDTO();
        dto.getConditions().add(Restrict.eq("customerNum", to.getCustomerNum()));
        if (super.count(dto) != 0)
            throw new SerException(to.getCustomerNum() + ":该编号以已存在");
        super.save(entity);
        if (to.isSend()) {
            //发送邮件进行公示
            send(to);
        }
        return BeanTransform.copyProperties(entity, CommerceContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceContactsBO update(CommerceContactsTO to) throws SerException {
        CommerceContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不村在");
        if (!to.getCustomerNum().equals(entity.getCustomerNum())) {
            CommerceContactsDTO dto = new CommerceContactsDTO();
            dto.getConditions().add(Restrict.eq("customerNum", to.getCustomerNum()));
            if (super.count(dto) != 0)
                throw new SerException(to.getCustomerNum() + ":该编号以已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        if (to.isSend()) {
            //发送邮件进行公示
            send(to);
        }
        return BeanTransform.copyProperties(entity, CommerceContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(CommerceContactsTO to) throws SerException {
        CommerceContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不村在");
        super.remove(entity);
    }

    @Override
    public List<CommerceContactsBO> maps(CommerceContactsDTO dto) throws SerException {
        List<CommerceContacts> list = super.findByPage(dto);
        return BeanTransform.copyProperties(super.findByPage(dto), CommerceContactsBO.class);
    }

    @Override
    public CommerceContactsBO getById(String id) throws SerException {
        CommerceContacts entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不村在");
        return BeanTransform.copyProperties(entity, CommerceContactsBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        CommerceContactsDTO dto = new CommerceContactsDTO();
        return super.count(dto);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("commercecontacts");
        obj.setDescribesion("商务通讯录");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = internalContactsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("internalcontacts");
        obj.setDescribesion("内部通讯录");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = externalContactsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("externalcontacts");
        obj.setDescribesion("外部通讯录");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = commerceMemberSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("commercemember");
        obj.setDescribesion("商务会员卡");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = commonalitySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("commonalityserimpl");
        obj.setDescribesion("公共邮箱管理");
        if (flagSeeBase) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagOther = otherContactsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("othercontacts");
        obj.setDescribesion("其他通讯录");
        if (flagOther) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagQQ = qqGroupSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("qqgroup");
        obj.setDescribesion("QQ群管理");
        if (flagQQ) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
        return flag;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<CommerceContactsTemplateExport> commerceContactsExports = new ArrayList<>();

        CommerceContactsTemplateExport excel = new CommerceContactsTemplateExport();
        excel.setCustomerNum("移动通信类");
        excel.setCustomerName("test");
        excel.setArea("dsa");
        excel.setCustomerSex(CustomerSex.MAN);
        excel.setCustomerType(CustomerType.COOPERATOR);
        excel.setCustomerStatus(CustomerStatus.COMPLETEPROJECT);
        excel.setRelation(1.2);
        excel.setCustomerLevelName("dsa");
        excel.setOrigin("ds");
        excel.setIntroducer("dsa");
        excel.setCusEmail("已dsa签订");
        excel.setTel("框架d合同");
        excel.setPhone("已立项");
        excel.setWeChart("test");
        excel.setQq("test");
        excel.setWorkPosition("test");
        excel.setWorkLevel("ds");
        excel.setWorkRight("fgh");
        commerceContactsExports.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(commerceContactsExports, exce);
        return bytes;
    }

    @Override
    public List<MobileCommerceContactsBO> mobileList(CommerceContactsDTO dto) throws SerException {
        searchMobileCondition(dto);
        List<CommerceContacts> list = super.findByCis(dto);
        List<CommerceContactsBO> commerceContactsBOs = BeanTransform.copyProperties(super.findByPage(dto), CommerceContactsBO.class);
        if (!CollectionUtils.isEmpty(commerceContactsBOs)) {
            List<MobileCommerceContactsBO> bos = BeanTransform.copyProperties(commerceContactsBOs, MobileCommerceContactsBO.class, "headSculpture");
            for (MobileCommerceContactsBO bo : bos) {
                UserDTO userDTO = new UserDTO();
                userDTO.getConditions().add(Restrict.eq("username", bo.getCustomerName()));
                UserBO userBO = userAPI.findOne(userDTO);
                if (null != userBO) {
                    bo.setHeadSculpture(userBO.getHeadSculpture());
                }
            }
            return bos;
        }
        return null;
    }

    @Override
    public Long getMobileTotal(CommerceContactsDTO dto) throws SerException {
        searchMobileCondition(dto);
        return super.count(dto);
    }

    @Override
    public MobileCommerceContactsBO findByMobileID(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        CommerceContactsDTO dto = new CommerceContactsDTO();
        dto.getConditions().add(Restrict.eq("id", id));
        CommerceContacts entity = super.findOne(dto);
        CommerceContactsBO bo = BeanTransform.copyProperties(entity, CommerceContactsBO.class);
        if (null != bo) {
            MobileCommerceContactsBO mobileCommerceContactsBO = BeanTransform.copyProperties(bo, MobileCommerceContactsBO.class, "headSculpture");
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.eq("username", mobileCommerceContactsBO.getCustomerName()));
            UserBO userBO = userAPI.findOne(userDTO);
            if (null != userBO) {
                mobileCommerceContactsBO.setHeadSculpture(userBO.getHeadSculpture());
            }
            return mobileCommerceContactsBO;
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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    private void send(CommerceContactsTO to) throws SerException {
        //获得综合资源部,商务发展部,总经办的邮箱
        String[] allEmails = null;
        //从公共邮箱中得到部门的邮箱
        CommonalityDTO commonalityDTO = new CommonalityDTO();
        List<CommonalityBO> commonalityBOList = commonalityAPI.findAll();
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
        messageTO.setTitle("商务通讯录");
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setContent(content);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);
        messageTO.setReceivers(allEmails);
        messageAPI.send(messageTO);
    }

    private String html(CommerceContactsTO to) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>商务通讯录</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>客户信息编号</td>");
        sb.append("<td>客户姓名</td>");
        sb.append("<td>地区</td>");
        sb.append("<td>性别</td>");
        sb.append("<td>客户类别</td>");
        sb.append("<td>客户状态</td>");
        sb.append("<td>关系程度</td>");
        sb.append("<td>客户级别</td>");
        sb.append("<td>客户来源</td>");
        sb.append("<td>介绍人</td>");
        sb.append("<td>邮箱</td>");
        sb.append("<td>手机号</td>");
        sb.append("<td>座机</td>");
        sb.append("<td>微信</td>");
        sb.append("<td>QQ号</td>");
        sb.append("<td>岗位</td>");
        sb.append("<td>职级</td>");
        sb.append("<td>职权</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + to.getCustomerNum() + "</td>");
        sb.append("<td>" + to.getCustomerName() + "</td>");
        sb.append("<td>" + to.getArea() + "</td>");
        sb.append("<td>" + to.getCustomerSex() + "</td>");
        sb.append("<td>" + to.getCustomerType() + "</td>");
        sb.append("<td>" + to.getCustomerStatus() + "</td>");
        sb.append("<td>" + to.getRelation() + "</td>");
        sb.append("<td>" + to.getCustomerLevelName() + "</td>");
        sb.append("<td>" + to.getOrigin() + "</td>");
        sb.append("<td>" + to.getIntroducer() + "</td>");
        sb.append("<td>" + to.getCusEmail() + "</td>");
        sb.append("<td>" + to.getTel() + "</td>");
        sb.append("<td>" + to.getPhone() + "</td>");
        sb.append("<td>" + to.getWeChart() + "</td>");
        sb.append("<td>" + to.getQq() + "</td>");
        sb.append("<td>" + to.getWorkPosition() + "</td>");
        sb.append("<td>" + to.getWorkLevel() + "</td>");
        sb.append("<td>" + to.getWorkRight() + "</td>");
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
        if (null != departmentDetailBOList && departmentDetailBOList.size() > 0) {
            String departmentId = departmentDetailBOList.get(0).getId();
            return departmentId;
        }
        return null;
    }

    private void searchMobileCondition(CommerceContactsDTO dto) throws SerException {
        /**
         * 用户名
         */
        if (StringUtils.isNotBlank(dto.getCustomerName())) {
            dto.getConditions().add(Restrict.like("customerName", dto.getCustomerName()));
        }
    }

}
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
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.bo.ExternalContactsBO;
import com.bjike.goddess.contacts.bo.MobileExternalContactsBO;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.entity.ExternalContacts;
import com.bjike.goddess.contacts.enums.GuideAddrStatus;
import com.bjike.goddess.contacts.excel.ExternalContactsTemplateExport;
import com.bjike.goddess.contacts.to.ExternalContactsTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.util.ChineseCharToEn;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.enums.SexType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

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
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExternalContactsBO save(ExternalContactsTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getEmail())) {
            if (!Validator.isEmail(to.getEmail())) {
                throw new SerException("输入的邮箱格式不正确");
            }
        }
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
        search(dto);
        List<ExternalContacts> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ExternalContactsBO.class);
    }

    private List<ExternalContactsBO> search(ExternalContactsDTO dto) throws SerException {
        //姓名
        if (StringUtils.isNotBlank(dto.getUserName())) {
            dto.getConditions().add(Restrict.like("username", dto.getUserName()));
        }
        //地区
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.like("area", dto.getArea()));
        }
        //部门/项目组
        if (StringUtils.isNotBlank(dto.getProject())) {
            dto.getConditions().add(Restrict.like("project", dto.getProject()));
        }
        List<ExternalContacts> externalContacts = super.findByCis(dto);
        List<ExternalContactsBO> externalContactsBOS = BeanTransform.copyProperties(externalContacts, ExternalContactsBO.class);
        return externalContactsBOS;
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
    public ExternalContactsBO importExcel(List<ExternalContactsTO> externalContactsTO) throws SerException {
        if (null != externalContactsTO && externalContactsTO.size() > 0) {
            for (ExternalContactsTO to : externalContactsTO) {
                if (!Validator.isEmail(to.getEmail())) {
                    throw new SerException("导入的邮箱格式不正确");
                }
                if (!Validator.isPhone(to.getPhone())) {
                    throw new SerException("导入的电话号码格式不正确");
                }
            }
        }
        List<ExternalContacts> externalContacts = BeanTransform.copyProperties(externalContactsTO, ExternalContacts.class, true);
        externalContacts.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(externalContacts);

        ExternalContactsBO externalContactsBO = BeanTransform.copyProperties(new ExternalContacts(), ExternalContactsBO.class);
        return externalContactsBO;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ExternalContactsTemplateExport> commerceContactsExports = new ArrayList<>();

        ExternalContactsTemplateExport excel = new ExternalContactsTemplateExport();
        excel.setArea("移动通信类");
        excel.setProject("test");
        excel.setUsername("jkj");
        excel.setUnit("jkj");
        excel.setPosition("jkj");
        excel.setPhone("jkj");
        excel.setEmail("jkj");
        excel.setResponsible("jkj");
        excel.setOther("jkj");
        excel.setExternal("jkj");
        excel.setFrequency("jkj");
        excel.setWriter("jkj");
        excel.setWriteNumber("jkj");
        excel.setWriteTime(LocalDateTime.now());
        excel.setRemark("jkj");
        commerceContactsExports.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(commerceContactsExports, exce);
        return bytes;
    }
    private static List<MobileExternalContactsBO> sort(List<MobileExternalContactsBO> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
//        // Collator 类是用来执行区分语言环境的 String 比较的，这里选择使用CHINA
//        Comparator<Object> comparator = Collator.getInstance(java.util.Locale.CHINA);
//        // 使根据指定比较器产生的顺序对指定对象数组进行排序。
//        Arrays.sort(data, comparator);
        TreeSet<MobileExternalContactsBO> treeSet = new TreeSet<>(new Comparator<MobileExternalContactsBO>() {
            @Override
            public int compare(MobileExternalContactsBO o1, MobileExternalContactsBO o2) {
                return ChineseCharToEn.getFirstLetter(o1.getUsername()).compareTo(ChineseCharToEn.getFirstLetter(o2.getUsername()));
            }
        });
        for (MobileExternalContactsBO m : data) {
            treeSet.add(m);
        }
        return new ArrayList<>(treeSet);
    }
    @Override
    public List<MobileExternalContactsBO> mobileList(ExternalContactsDTO dto) throws SerException {
//        dto.getSorts().add("writeTime=desc");
        searchMobileCondition(dto);
        List<ExternalContacts> list = super.findByCis(dto);
        List<ExternalContactsBO> externalContactsBOs = BeanTransform.copyProperties(list, ExternalContactsBO.class, false);
        if (!CollectionUtils.isEmpty(externalContactsBOs)) {
            List<MobileExternalContactsBO> bos = BeanTransform.copyProperties(externalContactsBOs, MobileExternalContactsBO.class, "sex", "headSculpture", "number");
            for (MobileExternalContactsBO bo : bos) {
                UserDTO userDTO = new UserDTO();
                userDTO.getConditions().add(Restrict.eq("username", bo.getUsername()));
                UserBO userBO = userAPI.findOne(userDTO);
                if (null != userBO) {
                    bo.setHeadSculpture(userBO.getHeadSculpture());
                    UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
                    if (null != userDetailBO) {
                        bo.setSex(userDetailBO.getSex());
                        if ("男".equals(entryRegisterAPI.getGender(bo.getUsername()))) {
                            bo.setSex(SexType.MAN);
                        } else if ("女".equals(entryRegisterAPI.getGender(bo.getUsername()))) {
                            bo.setSex(SexType.WOMAN);
                        } else {
                            bo.setSex(SexType.NONE);
                        }
                    }

                }
            }
            return sort(bos);
        }
        return null;
    }

    @Override
    public Long getMobileTotal(ExternalContactsDTO dto) throws SerException {
        searchMobileCondition(dto);
        return super.count(dto);
    }

    @Override
    public MobileExternalContactsBO findByMobileID(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        ExternalContactsDTO dto = new ExternalContactsDTO();
        dto.getConditions().add(Restrict.eq("id", id));
        ExternalContacts entity = super.findOne(dto);
        ExternalContactsBO bo = BeanTransform.copyProperties(entity, ExternalContactsBO.class, false);
        if (null != bo) {
            MobileExternalContactsBO mobileExternalContactsBO = BeanTransform.copyProperties(bo, MobileExternalContactsBO.class, "sex", "headSculpture", "number");
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.eq("username", mobileExternalContactsBO.getUsername()));
            UserBO userBO = userAPI.findOne(userDTO);
            if (null != userBO) {
                mobileExternalContactsBO.setHeadSculpture(userBO.getHeadSculpture());
//                UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
//                if (null != userDetailBO) {
//                    mobileExternalContactsBO.setSex(userDetailBO.getSex());
//                }
                if ("男".equals(entryRegisterAPI.getGender(bo.getUsername()))) {
                    mobileExternalContactsBO.setSex(SexType.MAN);
                } else if ("女".equals(entryRegisterAPI.getGender(bo.getUsername()))) {
                    mobileExternalContactsBO.setSex(SexType.WOMAN);
                } else {
                    mobileExternalContactsBO.setSex(SexType.NONE);
                }
            }
            return mobileExternalContactsBO;
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

    /**
     * 增加或更新时发送邮件
     */
    private void send(ExternalContactsTO to) throws SerException {
        //获得综合资源部,商务发展部,总经办的邮箱
        String[] allEmails = null;
        //从公共邮箱中得到部门的邮箱
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

    private void searchMobileCondition(ExternalContactsDTO dto) throws SerException {
        /**
         * 用户名
         */
        if (StringUtils.isNotBlank(dto.getUserName())) {
            dto.getConditions().add(Restrict.like("username", dto.getUserName()));
        }
    }
}
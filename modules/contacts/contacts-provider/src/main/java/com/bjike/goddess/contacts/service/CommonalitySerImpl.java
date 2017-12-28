package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.entity.Commonality;
import com.bjike.goddess.contacts.enums.GuideAddrStatus;
import com.bjike.goddess.contacts.enums.Status;
import com.bjike.goddess.contacts.excel.CommonalityTemplateExport;
import com.bjike.goddess.contacts.to.CommonalityTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公共邮箱管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class CommonalitySerImpl extends ServiceImpl<Commonality, CommonalityDTO> implements CommonalitySer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ContactPermissionSer cusPermissionSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private MessageAPI messageAPI;


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO save(CommonalityTO to) throws SerException {
        if (!Validator.isEmail(to.getEmail())) {
            throw new SerException("输入的邮箱格式不正确");
        }
//        //从组织结构中得到部门的id
//        String departmentId1 = getDepartment(to.getDepartment());
//        if (StringUtils.isEmpty(departmentId1)) {
//            //组织结构中没有对应的项目组/部门
////            throw new SerException("组织结构中没有对应的项目组/部门,请先在组织结构中添加该部门");
//            departmentId1 = to.getDepartment();
//        }
        //查询数据库中是否有该数据
        List<Commonality> list = super.findAll();
        if (null != list && list.size() > 0) {
            for (Commonality entity : list) {
                if (to.getDepartmentId().equals(entity.getDepartment())) {
                    throw new SerException("该名称已存在");
                }
            }
        }
//        to.setDepartmentId(departmentId1);
        Commonality entity = BeanTransform.copyProperties(to, Commonality.class, true);
        entity.setDepartment(to.getDepartmentId());
        entity.setStatus(Status.THAW);
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
                        List<CommonalityBO> commonalityBOList = this.findAlls();
                        for (CommonalityBO commonalityBO : commonalityBOList) {
                            if (departmentId.equals(commonalityBO.getDepartmentId())) {
                                email = commonalityBO.getEmail();
                                String content = html(entity);
                                String[] email1 = new String[1];
                                email1[0] = email;
                                //调用发送邮箱接口
                                MessageTO messageTO = new MessageTO();
                                messageTO.setTitle("公共邮箱");
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

        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    private String html(Commonality entity) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>公共邮箱新增</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>更新时间</td>");
        sb.append("<td>地区</td>");
        sb.append("<td>项目组/部门</td>");
        sb.append("<td>邮箱地址</td>");
        sb.append("<td>状态</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
//        String pdID = getDepartmentName(entity.getDepartmentId());
//        if (StringUtils.isEmpty(pdID)) {
//            pdID = entity.getDepartmentId();
//        }
        sb.append("<td>" + entity.getUpdateTime() + "</td>");
        sb.append("<td>" + entity.getArea() + "</td>");
        sb.append("<td>" + entity.getDepartment() + "</td>");
        sb.append("<td>" + entity.getEmail() + "</td>");
        sb.append("<td>" + entity.getStatus() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO update(CommonalityTO to) throws SerException {
        Commonality entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setStatus(Status.THAW);
//        String dpID = getDepartment(to.getDepartmentId());
//        if (StringUtils.isEmpty(dpID)) {
//            dpID = to.getDepartmentId();
//        }
//        entity.setDepartmentId(dpID);
        entity.setDepartment(to.getDepartmentId());
        super.update(entity);
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
                        List<CommonalityBO> commonalityBOList = this.findAlls();
                        for (CommonalityBO commonalityBO : commonalityBOList) {
                            if (departmentId.equals(commonalityBO.getDepartmentId())) {
                                email = commonalityBO.getEmail();
                                String content = html1(entity);
                                String[] email1 = new String[1];
                                email1[0] = email;
                                //调用发送邮箱接口
                                MessageTO messageTO = new MessageTO();
                                messageTO.setTitle("公共邮箱");
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


        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    private String html1(Commonality entity) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>公共邮箱更改</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>项目组/部门ID</td>");
        sb.append("<td>邮箱地址</td>");
        sb.append("<td>状态</td>");
        sb.append("<tr>");

        //拼body部分
        sb.append("<tr>");
//        String pdID = getDepartmentName(entity.getDepartmentId());
//        if (StringUtils.isEmpty(pdID)) {
//            pdID = entity.getDepartmentId();
//        }
        sb.append("<td>" + entity.getDepartment() + "</td>");
        sb.append("<td>" + entity.getEmail() + "</td>");
        sb.append("<td>" + entity.getStatus() + "</td>");
        sb.append("<tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO delete(CommonalityTO to) throws SerException {
        Commonality entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO congeal(CommonalityTO to) throws SerException {
        Commonality entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO thaw(CommonalityTO to) throws SerException {
        Commonality entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Override
    public List<CommonalityBO> findThaw() throws SerException {
        CommonalityDTO dto = new CommonalityDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Commonality> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, CommonalityBO.class);
    }

    @Override
    public List<CommonalityBO> maps(CommonalityDTO dto) throws SerException {
        search(dto);
        List<CommonalityBO> boList = new ArrayList<>(0);
        List<Commonality> list = super.findByPage(dto);
        if (null != list && list.size() > 0) {
            for (Commonality entity : list) {
                //将部门ｉｄ转换为部门
                String name = entity.getDepartment();
                CommonalityBO bo = BeanTransform.copyProperties(entity, CommonalityBO.class);
                bo.setDepartmentId(name);
                boList.add(bo);
            }
        }
        return boList;
    }

    private List<CommonalityBO> search(CommonalityDTO dto) throws SerException {
        //地区
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.like("area", dto.getArea()));
        }
        //项目组/部门
        if (StringUtils.isNotBlank(dto.getDepartment())) {
            dto.getConditions().add(Restrict.like("department", dto.getDepartment()));
        }
        //状态
        if (null != dto.getStatus()) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        List<Commonality> commonalities = super.findByCis(dto);
        List<CommonalityBO> commonalityBOS = BeanTransform.copyProperties(commonalities, CommonalityBO.class);
        return commonalityBOS;
    }

    @Override
    public List<CommonalityBO> findAlls() throws SerException {
        List<Commonality> list = super.findAll();
        return BeanTransform.copyProperties(list, CommonalityBO.class);
    }

    @Override
    public CommonalityBO findByDepartment(String department) throws SerException {
        if (StringUtils.isBlank(department)) {
            return null;
        }
        CommonalityDTO dto = new CommonalityDTO();
        dto.getConditions().add(Restrict.eq("department", department));
        Commonality entity = super.findOne(dto);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Override
    public CommonalityBO getById(String id) throws SerException {
        Commonality entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
//        String name = getDepartmentName(entity.getDepartmentId());
//        if (StringUtils.isEmpty(name)) {
//            name = entity.getDepartmentId();
//        }
//        entity.setDepartmentId(name);
        CommonalityBO bo = BeanTransform.copyProperties(entity, CommonalityBO.class);
        bo.setDepartmentId(entity.getDepartment());
        return bo;
    }

    @Override
    public Long getTotal() throws SerException {
        CommonalityDTO dto = new CommonalityDTO();
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
    public void importExcel(List<CommonalityTO> commonalityTO) throws SerException {
        //对导入的数据进行判断是否已在数据库中存在
        if (null != commonalityTO && commonalityTO.size() > 0) {
            for (CommonalityTO to : commonalityTO) {

                if (Status.CONGEAL == to.getStatus() || Status.DELETE == to.getStatus()) {
                    throw new SerException("不能导入状态为删除或冻结的数据");
                }
                if (null == to.getStatus()) {
                    to.setStatus(Status.THAW);
                }

                CommonalityDTO dto = new CommonalityDTO();
//                String dpID = getDepartment(to.getDepartmentId());
//                if (StringUtils.isEmpty(dpID)) {
//                    dpID = to.getDepartmentId();
//                }
                dto.getConditions().add(Restrict.eq("department", to.getDepartmentId()));
                List<Commonality> list = super.findByCis(dto);
                if (null != list && list.size() > 0) {
                    throw new SerException("部门已存在");
                }
//                to.setDepartmentId(dpID);
            }
        }
        List<Commonality> commonality = BeanTransform.copyProperties(commonalityTO, Commonality.class, true, "isSend", "sendObject");

        commonality.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(commonality);
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<CommonalityTemplateExport> commerceContactsExports = new ArrayList<>();

        CommonalityTemplateExport excel = new CommonalityTemplateExport();
        excel.setDepartment("研发部");
        excel.setEmail("110120119@qq.com");
        excel.setStatus(Status.THAW);
        commerceContactsExports.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(commerceContactsExports, exce);
        return bytes;
    }

    @Override
    public List<String> getEmails() throws SerException {
        CommonalityDTO dto = new CommonalityDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Commonality> list = super.findByCis(dto);
        if (null != list && list.size() > 0) {
            return list.stream().map(Commonality::getEmail).distinct().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<String> findDepartment() throws SerException {
        //从组织结构中查询所有的部门
        return departmentDetailAPI.findAllDepartment();
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

    public List<CommonalityBO> list(CommonalityDTO dto) throws SerException {
        List<Commonality> commonalityList = super.findByCis(dto);
        List<CommonalityBO> list = BeanTransform.copyProperties(commonalityList, CommonalityBO.class);
        return list;
    }

    //得到对应部门的id号
    private String getDepartment1(String department) throws SerException {
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

}
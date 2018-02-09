package com.bjike.goddess.rotation.service;

import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.rotation.bo.CoverRotationBO;
import com.bjike.goddess.rotation.bo.RecommendRotationBO;
import com.bjike.goddess.rotation.dto.RecommendRotationDTO;
import com.bjike.goddess.rotation.entity.CoverRotation;
import com.bjike.goddess.rotation.entity.RecommendRotation;
import com.bjike.goddess.rotation.enums.AuditType;
import com.bjike.goddess.rotation.enums.GuideAddrStatus;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.RecommendRotationTO;
import com.bjike.goddess.rotation.to.RotationRecordTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

//import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;

/**
 * 岗位轮换推荐业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:28 ]
 * @Description: [ 岗位轮换推荐业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class RecommendRotationSerImpl extends ServiceImpl<RecommendRotation, RecommendRotationDTO> implements RecommendRotationSer {

//    @Autowired
//    private EntryBasicInfoAPI entryBasicInfoAPI;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private RegularizationAPI regularizationAPI;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private SubsidyStandardSer subsidyStandardSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private StaffRecordsAPI staffRecordsAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    RotationRecordSer rotationRecordSer;

    private RecommendRotationBO transformBO(RecommendRotation entity) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(userToken);
        RecommendRotationBO bo = BeanTransform.copyProperties(entity, RecommendRotationBO.class);
        StaffRecordsBO staffRecordsBO = null;
        if (moduleAPI.isCheck("archive")) {
            staffRecordsBO = staffRecordsAPI.findByName(entity.getUsername());
        }
        String time = null;
        if (moduleAPI.isCheck("regularization")) {
            time = regularizationAPI.getTime(entity.getUsername());
        }
        RpcTransmit.transmitUserToken(userToken);
        if (null != staffRecordsBO) {
            bo.setEntryTime(staffRecordsBO.getEntryTime());
        }
        if (null != time) {
            bo.setRegularTime(time);
        }
        if (null != entity.getApplyLevel()) {
            bo.setApplyLevelId(entity.getApplyLevel().getId());
            bo.setApplyLevelArrangement(entity.getApplyLevel().getArrangement());
        }
        if (null != entity.getRotationLevel()) {
            bo.setRotationLevelId(entity.getRotationLevel().getId());
            bo.setRotationLevelArrangement(entity.getRotationLevel().getArrangement());
        }
        return bo;
    }

    private List<RecommendRotationBO> transformBOList(List<RecommendRotation> list) throws SerException {
        List<RecommendRotationBO> bos = new ArrayList<>(0);
        for (RecommendRotation entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecommendRotationBO save(RecommendRotationTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        //user = userAPI.findByUsername(to.getUsername());


        RecommendRotation entity = BeanTransform.copyProperties(to, RecommendRotation.class, true);

        RpcTransmit.transmitUserToken(userToken);
        if (moduleAPI.isCheck("organize")) {
            List<PositionDetailBO> bos = positionDetailUserAPI.findPositionByUser(userBO
                    .getId()).stream()
                    .sorted(Comparator.comparing(PositionDetailBO::getArea)
                    .thenComparing(PositionDetailBO::getDepartmentId))
                    .collect(Collectors.toList());
            RpcTransmit.transmitUserToken(userToken);
            StringBuilder area = new StringBuilder(), department = new StringBuilder(), position = new StringBuilder(), arrangement = new StringBuilder();
            String tempArea = "", tempDepartment = "", tempArrangement = "";
            for (PositionDetailBO positionDetailBO : bos) {
                if (!tempArea.equals(positionDetailBO.getArea())) {
                    tempArea = positionDetailBO.getArea();
                    area.append(tempArea + ",");
                }
                if (!tempDepartment.equals(positionDetailBO.getDepartmentName())) {
                    tempDepartment = positionDetailBO.getDepartmentName();
                    department.append(tempDepartment + ",");
                }
                position.append(positionDetailBO.getPosition());
            }
            for (String s : bos.stream()
                    .sorted(Comparator.comparing(PositionDetailBO::getArrangementName))
                    .map(PositionDetailBO::getArrangementName).collect(Collectors.toList()))
                if (!tempArrangement.equals(s)) {
                    tempArrangement = s;
                    arrangement.append(s);
                }
            entity.setArea(area.toString());
            entity.setPosition(position.toString());
            entity.setArrangement(arrangement.toString());
            entity.setDepartment(department.toString());
        }
        entity.setRecommend(userBO.getUsername());
        entity.setRecommendTime(LocalDate.now());
        entity.setAudit(AuditType.NONE);
        entity.setApplyLevel(subsidyStandardSer.findById(to.getApplyLevelId()));
        StaffRecordsBO bo = staffRecordsAPI.findByName(entity.getUsername());
        entity.setEntryTime(bo == null ? null : bo.getEntryTime());
        entity.setRegularTime(regularizationAPI.getTime(entity.getUsername()));
        if (null == entity.getApplyLevel())
            throw new SerException("推荐的层级不存在");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecommendRotationBO update(RecommendRotationTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO user = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        RecommendRotation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        if (!user.getUsername().equals(entity.getRecommend()))
            throw new SerException("不能修改他人的轮换推荐");
        BeanTransform.copyProperties(to, entity, true);
        if (moduleAPI.isCheck("organize")) {

            List<PositionDetailBO> bos = positionDetailUserAPI.findPositionByUser(userAPI.findByUsername(to.getUsername()).getId()).stream()
                    .sorted(Comparator.comparing(PositionDetailBO::getArea)
                            .thenComparing(PositionDetailBO::getDepartmentId))
                    .collect(Collectors.toList());
            StringBuilder area = new StringBuilder(), department = new StringBuilder(), position = new StringBuilder(), arrangement = new StringBuilder();
            String tempArea = "", tempDepartment = "", tempArrangement = "";
            for (PositionDetailBO positionDetailBO : bos) {
                if (!tempArea.equals(positionDetailBO.getArea())) {
                    tempArea = positionDetailBO.getArea();
                    area.append(tempArea + ",");
                }
                if (!tempDepartment.equals(positionDetailBO.getDepartmentName())) {
                    tempDepartment = positionDetailBO.getDepartmentName();
                    department.append(tempDepartment + ",");
                }
                position.append(positionDetailBO.getPosition());
            }
            for (String s : bos.stream()
                    .sorted(Comparator.comparing(PositionDetailBO::getArrangementName))
                    .map(PositionDetailBO::getArrangementName).collect(Collectors.toList()))
                if (!tempArrangement.equals(s)) {
                    tempArrangement = s;
                    arrangement.append(s);
                }

            entity.setArea(area.toString());
            entity.setPosition(position.toString());
            entity.setArrangement(arrangement.toString());
            entity.setDepartment(department.toString());
        }
        entity.setRecommend(user.getUsername());
        entity.setRecommendTime(LocalDate.now());
        entity.setModifyTime(LocalDateTime.now());
        entity.setApplyLevel(subsidyStandardSer.findById(to.getApplyLevelId()));
        if (null == entity.getApplyLevel())
            throw new SerException("推荐的层级不存在");
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecommendRotationBO delete(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO user = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        RecommendRotation entity = super.findById(id);
        if (!user.getUsername().equals(entity.getRecommend()))
            throw new SerException("不能删除他人的轮换推荐");
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecommendRotationBO opinion(RecommendRotationTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO user = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        RecommendRotation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        if (entity.getAudit() != AuditType.NONE)
            throw new SerException("该数据已被评价");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setGeneral(user.getUsername());
        entity.setAudit(to.getPass() ? AuditType.ALLOWED : AuditType.DENIED);
        entity.setRotationLevel(subsidyStandardSer.findById(to.getRotationLevelId()));
        if (to.getPass() && null == entity.getRotationLevel())
            throw new SerException("选择的层级不存在");
        entity.setHadNotify(false);
        super.update(entity);

        CoverRotationBO coverRotationBO = BeanTransform.copyProperties(entity, CoverRotationBO.class);
        coverRotationBO.setApplyTime(entity.getCreateTime().toString());
        coverRotationBO.setApplyLevelArrangement(entity.getApplyLevel().getArrangement());
        coverRotationBO.setRotationLevelArrangement(entity.getRotationLevel().getArrangement());
        List<CoverRotationBO> coverRotationBOS = new ArrayList<>();
        coverRotationBOS.add(coverRotationBO);
        //通过则保存到记录表
        if (to.getPass()) {
            //发送邮件
            if (!StringUtils.isEmpty(user.getEmail())) {
                Email email = new Email("岗位轮换申请结果通知", getCRTable(coverRotationBOS), new String[] {user.getEmail()}, entity.getId(), null);
                new Thread(email).start();
            }

            RotationRecordTO rotationRecord = new RotationRecordTO();
            rotationRecord.setRotationType("推荐");
            rotationRecord.setRecommendRotation(entity);
            rotationRecord.setCoverRotation(null);
            rotationRecordSer.add(rotationRecord);
        }

        return this.transformBO(entity);
    }

    @Override
    public RecommendRotationBO getById(String id) throws SerException {
        RecommendRotation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public List<RecommendRotationBO> maps(RecommendRotationDTO dto) throws SerException {
        dto.getSorts().add("audit=asc");
        dto.getSorts().add("rotationDate=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Long getTotal() throws SerException {
        RecommendRotationDTO dto = new RecommendRotationDTO();
        return super.count(dto);
    }

    @Override
    public List<RecommendRotationBO> findByUserArrangement(String username, String arrangementId) throws SerException {
        RecommendRotationDTO dto = new RecommendRotationDTO();
        dto.getConditions().add(Restrict.eq(USERNAME, username));
        dto.getConditions().add(Restrict.eq("audit", AuditType.ALLOWED.getValue()));
        dto.getConditions().add(Restrict.eq("rotationLevel.id", arrangementId));
        dto.getSorts().add("rotationDate=desc");
        return this.transformBOList(super.findByCis(dto));
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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

    private String getCRTable(List<CoverRotationBO> list) {
        StringBuffer sb = new StringBuffer("");
        if (list != null && list.size() > 0) {
            sb = new StringBuffer("<h4>岗位轮换推荐结果:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            sb.append("<tr>");
            sb.append("<td>姓名</td>");
            sb.append("<td>地区</td>");
            sb.append("<td>项目组/部门</td>");
            sb.append("<td>岗位</td>");
            sb.append("<td>入职时间</td>");
            sb.append("<td>转正时间</td>");
            sb.append("<td>目前岗位层级</td>");
            sb.append("<td>获得时间</td>");
            sb.append("<td>申请轮换等级</td>");
            sb.append("<td>申请时间</td>");
            sb.append("<td>申请轮换原因</td>");
            sb.append("<td>轮换后岗位等级</td>");
            sb.append("<td>模块负责人</td>");
            sb.append("<td>意见</td>");
            sb.append("<td>项目经理</td>");
            sb.append("<td>意见</td>");
            sb.append("<td>总经办</td>");
            sb.append("<td>意见</td>");
            sb.append("<td>轮换时间</td>");
            sb.append("<tr>");
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                CoverRotationBO bo = (CoverRotationBO)var3.next();
                sb.append("<tr>");
                sb.append("<td>" + bo.getUsername() + "</td>");
                sb.append("<td>" + bo.getArea() + "</td>");
                sb.append("<td>" + bo.getDepartment() + "</td>");
                sb.append("<td>" + bo.getPosition() + "</td>");
                sb.append("<td>" + bo.getEntryTime() == null ? "" : bo.getEntryTime() + "</td>");
                sb.append("<td>" + bo.getRegularTime() == null ? "" : bo.getRegularTime() + "</td>");
                sb.append("<td>" + bo.getArrangement() + "</td>");
                sb.append("<td>" + bo.getGetTime() + "</td>");
                sb.append("<td>" + bo.getApplyLevelArrangement() + "</td>");
                sb.append("<td>" + bo.getApplyTime() + "</td>");
                sb.append("<td>" + bo.getReason() + "</td>");
                sb.append("<td>" + bo.getRotationLevelArrangement() + "</td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
                sb.append("<td></td>");
                sb.append("<td>" + bo.getGeneral() + "</td>");
                sb.append("<td>" + bo.getOpinion() + "</td>");
                sb.append("<td>" + bo.getRotationDate() + "</td>");
                sb.append("<tr>");
            }

            sb.append("</table>");
        }

        return sb.toString();
    }

    /**
     * 更新是否已发送结果
     * @param id
     * @throws SerException
     */
    void updateNotify(String id) throws SerException {
        RecommendRotation entity = super.findById(id);
        if (null == entity) {
            throw new SerException("更新实体不存在");
        }
        entity.setHadNotify(true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    //定义内部线程类，用于异步发送邮件
    public class Email implements Runnable {

        private String title;

        private String content;

        private String[] receivers;

        private String id;

        private String type;

        Email(String title, String content, String[] receivers, String id, String type) {
            this.title = title;
            this.content = content;
            this.receivers = receivers;
            this.id = id;
            this.type = type;

        }
        @Override
        public void run() {
            try {
                MessageTO messageTO = new MessageTO();
                messageTO.setContent(this.content );
                messageTO.setTitle(this.title);
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                messageTO.setReceivers(this.receivers);
//                Thread.sleep(5000);
                System.out.println("开始发送邮件");
                messageAPI.send(messageTO);

                updateNotify(this.id);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
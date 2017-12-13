package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.entity.InterviewInfor;
import com.bjike.goddess.recruit.excel.InterviewInforExport;
import com.bjike.goddess.recruit.excel.InterviewInforTemplateExcel;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.IdeaTO;
import com.bjike.goddess.recruit.to.InterviewInforTO;
import com.bjike.goddess.recruit.type.Gender;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class InterviewInforSerImpl extends ServiceImpl<InterviewInfor, InterviewInforDTO> implements InterviewInforSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private FirstPhoneRecordSer firstPhoneRecordSer;

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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.getCusPermission("2",null);
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
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
     * 分页查询面试信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<InterviewInforBO> list(InterviewInforDTO dto) throws SerException {
        checkSeeIdentity();
        search(dto);
        FirstPhoneRecordDTO firstPhoneRecordDTO = new FirstPhoneRecordDTO();
        List<FirstPhoneRecord> firstPhoneRecords = firstPhoneRecordSer.findByCis(firstPhoneRecordDTO,true);
        InterviewInfor interviewInfor = new InterviewInfor();
        for (FirstPhoneRecord record : firstPhoneRecords) {
            if (Boolean.TRUE.equals(record.getWhetherFirstInterview()) && record.getStatus() == null) {
                interviewInfor.setDate(record.getDate());//日期
                interviewInfor.setResumeResource(record.getResumeResource());//简历来源
                interviewInfor.setPosition(record.getPosition());//岗位
                interviewInfor.setName(record.getName());//姓名
                interviewInfor.setGender(record.getGender());//性别
                interviewInfor.setPhone(record.getTelephone());//联系方式
                interviewInfor.setWhetherPass(record.getWhetherPass());//简历筛选是否通过
                interviewInfor.setEmail(record.getEmail());//电子邮箱
                interviewInfor.setWorkingExperience(record.getWhetherWorkExperience());//是否有相关工作经验
                interviewInfor.setFirstPhoneSituation(record.getFirstSituation());//第一次电访了解到的情况
                interviewInfor.setFirstTestTime(record.getFirstInterviewTime());//邀约初试时间
                interviewInfor.setFirstTestPrincipal(record.getFirstInterviewPrincipal());//初试负责人
                interviewInfor.setWhetherFaceTest(record.getWhetherFirstInterview());//是否初试
                interviewInfor.setNotFirstCase(record.getDenyFirViewReason());//未应约初试原因
                interviewInfor.setArea(record.getArea());//地区
                interviewInfor.setDepartment(record.getProjectGroup());//应聘部门/项目组
                interviewInfor.setFirstPlace(record.getFirstPlace());//初试地点
                interviewInfor.setWhetherNeedSecondTest(record.getRetrial());//是否需要复试
                interviewInfor.setSecondTestTime(record.getRetrialTime());//复试时间
                interviewInfor.setSecondTestPrincipal(record.getRetrialOfficer());//复试负责人

                interviewInfor.setCreateTime(LocalDateTime.now());
                interviewInfor.setModifyTime(LocalDateTime.now());
                record.setStatus(true);
                firstPhoneRecordSer.update(record);
                super.save(interviewInfor);
            }
        }
        List<InterviewInfor> list = super.findByPage(dto);
        List<InterviewInforBO> listBO = BeanTransform.copyProperties(list, InterviewInforBO.class);
        return listBO;
    }

    private List<InterviewInforBO> search(InterviewInforDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.like("name", dto.getName()));
        }
        if (StringUtils.isNotBlank(dto.getPosition())) {
            dto.getConditions().add(Restrict.like("position", dto.getPosition()));
        }
        if (StringUtils.isNotBlank(dto.getFirstTestPrincipal())) {
            dto.getConditions().add(Restrict.like("firstTestPrincipal", dto.getFirstTestPrincipal()));
        }
        if (StringUtils.isNotBlank(dto.getSecondTestPrincipal())) {
            dto.getConditions().add(Restrict.like("secondTestPrincipal", dto.getSecondTestPrincipal()));
        }
        if (StringUtils.isNotBlank(dto.getStartDate()) && StringUtils.isNotBlank(dto.getEndDate())) {
            dto.getConditions().add(Restrict.between("date", new String[]{dto.getStartDate(), dto.getEndDate()}));
        }
        if (null != dto.getWhetherEntry()) {
            dto.getConditions().add(Restrict.eq("whetherEntry", dto.getWhetherEntry()));
        }
        List<InterviewInfor> list = super.findByPage(dto);
        List<InterviewInforBO> listBO = BeanTransform.copyProperties(list, InterviewInforBO.class);
        return listBO;
    }

    /**
     * 保存面试信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public InterviewInforBO save(InterviewInforTO to) throws SerException {
        checkAddIdentity();
        InterviewInfor failFirstInterviewReason = BeanTransform.copyProperties(to, InterviewInfor.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        InterviewInforBO bo = BeanTransform.copyProperties(failFirstInterviewReason, InterviewInforBO.class);
        return bo;
    }

    /**
     * 更新面试信息
     *
     * @param to 面试信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InterviewInforTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            InterviewInfor model = super.findById(to.getId());
            if (model != null) {
                updateInterviewInfor(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void firstIdea(IdeaTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            InterviewInfor interviewInfor = super.findById(to.getId());
//            LocalDateTime createTime = interviewInfor.getCreateTime();
            BeanTransform.copyProperties(to, interviewInfor, true);
//            interviewInfor.setCreateTime(createTime);
            interviewInfor.setModifyTime(LocalDateTime.now());
            super.update(interviewInfor);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void reexamineIdea(IdeaTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            InterviewInfor interviewInfor = super.findById(to.getId());
            BeanTransform.copyProperties(to, interviewInfor, true);
            interviewInfor.setModifyTime(LocalDateTime.now());
            super.update(interviewInfor);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void wagesIdea(IdeaTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            InterviewInfor interviewInfor = super.findById(to.getId());
            BeanTransform.copyProperties(to, interviewInfor, true);
            interviewInfor.setModifyTime(LocalDateTime.now());
            super.update(interviewInfor);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void zjbAudit(IdeaTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            InterviewInfor interviewInfor = super.findById(to.getId());
            BeanTransform.copyProperties(to, interviewInfor, true);
            interviewInfor.setModifyTime(LocalDateTime.now());
            super.update(interviewInfor);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void staffEntryInfo(IdeaTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            InterviewInfor interviewInfor = super.findById(to.getId());
            BeanTransform.copyProperties(to, interviewInfor, true);
            interviewInfor.setModifyTime(LocalDateTime.now());
            super.update(interviewInfor);
        } else {
            throw new SerException("id不能为空");
        }
    }


    /**
     * 更新面试信息
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInterviewInfor(InterviewInforTO to, InterviewInfor model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除面试信息
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(InterviewInfor entity) throws SerException {
        checkAddIdentity();
        super.remove(entity);
    }

    @Override
    public List<InterviewInforBO> findInterview() throws SerException {
        List<InterviewInfor> interviewInfors = super.findAll();
        List<InterviewInforBO> boList = BeanTransform.copyProperties(interviewInfors, InterviewInforBO.class, false);
        return boList;
    }

    @Override
    public InterviewInforBO findByName(String name) throws SerException {
        InterviewInforDTO dto = new InterviewInforDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        InterviewInfor interviewInfor = super.findOne(dto);
        InterviewInforBO bo = BeanTransform.copyProperties(interviewInfor, InterviewInforBO.class);
        return bo;
    }

    @Override
    public InterviewInforBO importExcel(List<InterviewInforTO> interviewInforTOS) throws SerException {
        List<InterviewInfor> interviewInfors = new ArrayList<>(interviewInforTOS.size());
        for (InterviewInforTO to : interviewInforTOS) {
            InterviewInfor interviewInfor = BeanTransform.copyProperties(to, InterviewInfor.class, true);
            interviewInfors.add(interviewInfor);
        }
        super.save(interviewInfors);
        InterviewInforBO bo = BeanTransform.copyProperties(new InterviewInfor(), InterviewInforBO.class);
        return bo;
    }

    @Override
    public byte[] exportExcel(InterviewInforDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getStartDate()) && StringUtils.isNotBlank(dto.getEndDate())) {
            dto.getConditions().add(Restrict.between("date", new String[]{dto.getStartDate(), dto.getEndDate()}));
        }
        List<InterviewInfor> list = super.findByCis(dto);
        List<InterviewInforExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            InterviewInforExport export = BeanTransform.copyProperties(str, InterviewInforExport.class,
                    "whetherPass", "workingExperience", "whetherFirstQuestionCorrect",
                    "whetherFaceTest", "whetherFirstTestPass", "whetherNeedSecondTest", "whetherSecondTestPass",
                    "agreedEmployed", "whetherAcceptAdmit", "whetherAccommodation", "whetherUseFirmPC",
                    "whetherEntry", "gender");
            //简历筛选是否通过
            if(null != str.getWhetherPass()){
                if (str.getWhetherPass().equals(0)) {
                    export.setWhetherPass("否");
                } else {
                    export.setWhetherPass("是");
                }

            }
            //是否有相关工作经验
            if(null != str.getWorkingExperience()){
                if (str.getWorkingExperience().equals(0)) {
                    export.setWorkingExperience("否");
                } else {
                    export.setWorkingExperience("是");
                }

            }
            //求职考试第一题是否正确
            if(null != str.getWhetherFirstQuestionCorrect()){
                if (str.getWhetherFirstQuestionCorrect().equals(0)) {
                    export.setWhetherFirstQuestionCorrect("否");
                } else {
                    export.setWhetherFirstQuestionCorrect("是");
                }
            }
            //是否初试
            if(null != str.getWhetherFaceTest()){
                if (str.getWhetherFaceTest().equals(0)) {
                    export.setWhetherFaceTest("否");
                } else {
                    export.setWhetherFaceTest("是");
                }
            }
            //初试是否通过
            if(null != str.getWhetherFirstTestPass()){
                if (str.getWhetherFirstTestPass().equals(0)) {
                    export.setWhetherFirstTestPass("否");
                } else {
                    export.setWhetherFirstTestPass("是");
                }

            }
            //是否需要复试
            if(null != str.getWhetherNeedSecondTest()){
                if (str.getWhetherNeedSecondTest().equals(0)) {
                    export.setWhetherNeedSecondTest("否");
                } else {
                    export.setWhetherNeedSecondTest("是");
                }

            }
            //复试是否通过
            if(null != str.getWhetherSecondTestPass()){
                if (str.getWhetherSecondTestPass().equals(0)) {
                    export.setWhetherSecondTestPass("否");
                } else {
                    export.setWhetherSecondTestPass("是");
                }

            }
            //是否同意录用
            if(null != str.getAgreedEmployed()){
                if (str.getAgreedEmployed().equals(0)) {
                    export.setAgreedEmployed("否");
                } else {
                    export.setAgreedEmployed("是");
                }
            }
            //是否接受录取
            if(null != str.getWhetherAcceptAdmit()){
                if (str.getWhetherAcceptAdmit().equals(0)) {
                    export.setWhetherAcceptAdmit("否");
                } else {
                    export.setWhetherAcceptAdmit("是");
                }

            }
            //是否住宿
            if(null != str.getWhetherAccommodation()){
                if (str.getWhetherAccommodation().equals(0)) {
                    export.setWhetherAccommodation("否");
                } else {
                    export.setWhetherAccommodation("是");
                }

            }
            //是否使用公司电脑
            if(null != str.getWhetherUseFirmPC()){
                if (str.getWhetherUseFirmPC().equals(0)) {
                    export.setWhetherUseFirmPC("否");
                } else {
                    export.setWhetherUseFirmPC("是");
                }
            }
            //是否入职
            if(null != str.getWhetherEntry()){
                if (str.getWhetherEntry().equals(0)) {
                    export.setWhetherEntry("否");
                } else {
                    export.setWhetherEntry("是");
                }

            }
            export.setGender(Gender.exportStrConvert(str.getGender()));
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<InterviewInforTemplateExcel> templateExcels = new ArrayList<>();
        InterviewInforTemplateExcel templateExcel = new InterviewInforTemplateExcel();
        templateExcel.setDate(LocalDate.now());
        templateExcel.setResumeResource("智联招聘");
        templateExcel.setPosition("test");
        templateExcel.setArea("test");
        templateExcel.setDepartment("test");
        templateExcel.setName("test");
        templateExcel.setGender("男");
        templateExcel.setPhone("test");
        templateExcel.setWhetherPass("是");
        templateExcel.setEmail("test");
        templateExcel.setWorkingExperience("是");
        templateExcel.setFirstPhoneSituation("test");
        templateExcel.setWhetherFirstQuestionCorrect("是");
        templateExcel.setFirstTestTime(LocalDateTime.now());
        templateExcel.setFirstTestPrincipal("test");
        templateExcel.setFirstPlace("test");
        templateExcel.setFirstTestAdvice("test");
        templateExcel.setWhetherFaceTest("是");
        templateExcel.setNotFirstCase("test");
        templateExcel.setWhetherFirstTestPass("是");
        templateExcel.setWhetherNeedSecondTest("是");
        templateExcel.setSecondTestTime(LocalDateTime.now());
        templateExcel.setSecondTestPrincipal("test");
        templateExcel.setSecondTestAdvice("test");
        templateExcel.setWhetherSecondTestPass("是");
        templateExcel.setSalaryFaceTime(LocalDateTime.now());
        templateExcel.setSalaryFacePrincipal("test");
        templateExcel.setFaceAdvice("test");
        templateExcel.setBoss("test");
        templateExcel.setBossAdvice("test");
        templateExcel.setAgreedEmployed("是");
        templateExcel.setAuditTime(LocalDate.now());
        templateExcel.setWhetherAcceptAdmit("是");
        templateExcel.setDenyAdmitReason("test");
        templateExcel.setEntryTime(LocalDate.now());
        templateExcel.setWhetherAccommodation("是");
        templateExcel.setWhetherUseFirmPC("是");
        templateExcel.setEntryAddress("test");
        templateExcel.setWhetherEntry("是");
        templateExcel.setDenyEntryReason("test");
        templateExcel.setComment("test");

        templateExcels.add(templateExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels, excel);
        return bytes;
    }
}

package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.entity.InterviewInfor;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.IdeaTO;
import com.bjike.goddess.recruit.to.InterviewInforTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        FirstPhoneRecordDTO firstPhoneRecordDTO = new FirstPhoneRecordDTO();
        List<FirstPhoneRecord> firstPhoneRecords = firstPhoneRecordSer.findByCis(firstPhoneRecordDTO);
        InterviewInfor interviewInfor = new InterviewInfor();
        for (FirstPhoneRecord record:firstPhoneRecords){
            if(record.getWhetherFaceTest().equals(1) && record.getStatus()==null){
                interviewInfor.setDate(record.getDate());//日期
                interviewInfor.setResumeResource(record.getResumeResource());//简历来源
                interviewInfor.setPosition(record.getPosition());//岗位
                interviewInfor.setName(record.getName());//姓名
                interviewInfor.setGender(record.getGender());//性别
                interviewInfor.setPhone(record.getTelephone());//联系方式
                interviewInfor.setWhetherPass(record.getWhetherPass());//简历筛选是否通过
                interviewInfor.setEmail(record.getEmail());
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
            InterviewInfor interviewInfor = BeanTransform.copyProperties(to, InterviewInfor.class, true);
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
            InterviewInfor interviewInfor = BeanTransform.copyProperties(to, InterviewInfor.class, true);
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
            InterviewInfor interviewInfor = BeanTransform.copyProperties(to, InterviewInfor.class, true);
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
            InterviewInfor interviewInfor = BeanTransform.copyProperties(to, InterviewInfor.class, true);
            interviewInfor.setModifyTime(LocalDateTime.now());
            super.update(interviewInfor);
        } else {
            throw new SerException("id不能为空");
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void staffEntryInfo(IdeaTO to) throws SerException{
        if (StringUtils.isNotBlank(to.getId())) {
            InterviewInfor interviewInfor = BeanTransform.copyProperties(to, InterviewInfor.class, true);
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
}

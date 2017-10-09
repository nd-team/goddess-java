package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.entity.PositionDetailUser;
import com.bjike.goddess.recruit.api.InterviewInforAPI;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.salarymanage.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.salarymanage.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.entity.SalaryConfirmRecord;
import com.bjike.goddess.salarymanage.enums.GuideAddrStatus;
import com.bjike.goddess.salarymanage.enums.Probation;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryConfirmRecordTO;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.api.StaffEntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * 招聘面谈薪资确认记录业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-09-15 02:20 ]
 * @Description: [ 招聘面谈薪资确认记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "salarymanageSerCache")
@Service
public class SalaryConfirmRecordSerImpl extends ServiceImpl<SalaryConfirmRecord, SalaryConfirmRecordDTO> implements SalaryConfirmRecordSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private StaffEntryRegisterAPI entryBasicInfoAPI;

    @Autowired
    private InterviewInforAPI interviewInforAPI;

    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private ModuleAPI moduleAPI;

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

    @Override
    public void add(SalaryConfirmRecordTO to) throws SerException {
        SalaryConfirmRecord model = BeanTransform.copyProperties(to, SalaryConfirmRecord.class);
        String userToken = RpcTransmit.getUserToken();
        UserBO user = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        InterviewInforBO interviewInforBO = new InterviewInforBO();
        if (moduleAPI.isCheck("recruit")) {
            interviewInforBO = interviewInforAPI.findByName(user.getUsername());
        } else {
            throw new SerException("请去关联模块管理设置模块关联");
        }
        if ("".equals(interviewInforBO) && interviewInforBO != null) {
            model.setUserName(interviewInforBO.getName());
            model.setArea(interviewInforBO.getArea());
            model.setPosition(interviewInforBO.getPosition());
            model.setDepartment(interviewInforBO.getDepartment());
            List<EntryRegisterBO> boList = entryRegisterAPI.getEntryRegisterByName(user.getUsername());
            if (boList != null && boList.size() > 0) {
                LocalDateTime nowTime = LocalDateTime.now();
                LocalDate entryDate = DateUtil.parseDate(boList.get(0).getInductionDate());
                LocalDateTime entryTime = DateUtil.parseDateTime(entryDate + " 00:00:00");
                long time = nowTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                        - entryTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                if (time > 0) {
                    model.setIsEntry(true);
                } else {
                    model.setIsEntry(false);
                }
                model.setEmployeeID(boList.get(0).getEmpNumber());
                EntryRegisterBO entryRegisterBO = new EntryRegisterBO();
                if (moduleAPI.isCheck("staffentry")) {
                    entryRegisterBO = entryRegisterAPI.getByNumber(boList.get(0).getEmpNumber());
                } else {
                    throw new SerException("请去模块关联管理设置模块关联");
                }
                model.setNativePlace(entryRegisterBO.getNativePlace());
                model.setEntryDate(DateUtil.parseDate(boList.get(0).getInductionDate()));
                model.setIfconfirm(false);
            } else {
                throw new SerException("面试模块没有获取到数据");
            }
        } else {
            throw new SerException("登录用户不是公司成员");
        }
    }

    @Override
    public void delete(String id) throws SerException {
        if (!"".equals(id) && id != null) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public void edit(SalaryConfirmRecordTO to) throws SerException {
        SalaryConfirmRecord model = super.findById(to.getId());
        BeanTransform.copyProperties(to, model, "userName", "position", "area", "department", "businessDirection", "workAge", "isEntry", "employeeID", "nativePlace", "entryDate");
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    public List<SalaryConfirmRecordBO> pageList(SalaryConfirmRecordDTO dto) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        List<PositionDetailBO> positionDetailUser = positionDetailUserAPI.findPositionByUser(userBO.getId());
        List<SalaryConfirmRecord> list = new ArrayList<>();
        if (positionDetailUser.get(0).getPosition().equals("综合资源部规划模块负责人")) {
            list = super.findByPage(dto);
        } else {
            dto.getConditions().add(Restrict.eq("userName", userBO.getUsername()));
            list = super.findByPage(dto);
        }
        List<SalaryConfirmRecordBO> boList = BeanTransform.copyProperties(list, SalaryConfirmRecordBO.class);
        return boList;
    }

    @Override
    public Long count(SalaryConfirmRecordDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<UserBO> findUserList() throws SerException {
        List<UserBO> boList = positionDetailUserAPI.findUserList();
        return boList;
    }

    @Override
    public SalaryConfirmRecordBO findOne(String id) throws SerException {
        SalaryConfirmRecord model = super.findById(id);
        SalaryConfirmRecordBO bo = BeanTransform.copyProperties(model, SalaryConfirmRecordBO.class);
        return bo;
    }

    @Override
    public void confirm(String id, Boolean ifConfirm) throws SerException {
        SalaryConfirmRecord salaryConfirmRecord = super.findById(id);
        salaryConfirmRecord.setIfconfirm(ifConfirm);
        super.update(salaryConfirmRecord);
    }

    @Override
    public Probation findProbationById(String employeeID) throws SerException {
        SalaryConfirmRecordDTO salaryConfirmRecordDTO = new SalaryConfirmRecordDTO();
        salaryConfirmRecordDTO.getConditions().add(Restrict.eq("employeeID", employeeID));
        SalaryConfirmRecord salaryConfirmRecord = super.findOne(salaryConfirmRecordDTO);
        if (null != salaryConfirmRecord) {
            return salaryConfirmRecord.getProbation();
        }
        return null;
    }
}
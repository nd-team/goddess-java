package com.bjike.goddess.staffmove.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.staffmove.bo.StaffMoveIntendCaseBO;
import com.bjike.goddess.staffmove.dto.StaffMoveIntendCaseDTO;
import com.bjike.goddess.staffmove.entity.StaffMoveIntendCase;
import com.bjike.goddess.staffmove.enums.GuideAddrStatus;
import com.bjike.goddess.staffmove.excel.StaffMoveIntendCaseExport;
import com.bjike.goddess.staffmove.excel.StaffMoveIntendCaseTemplateExcel;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveIntendCaseTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.nio.cs.ext.GB18030;

import java.awt.image.RescaleOp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 人员调动意愿情况业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmoveSerCache")
@Service
public class StaffMoveIntendCaseSerImpl extends ServiceImpl<StaffMoveIntendCase, StaffMoveIntendCaseDTO> implements StaffMoveIntendCaseSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long count(StaffMoveIntendCaseDTO dto) throws SerException {
        Long count =super.count(dto);
        return count;
    }

    @Override
    public StaffMoveIntendCaseBO getOne(String id) throws SerException {
        StaffMoveIntendCase staffMoveIntendCase = super.findById(id);
        StaffMoveIntendCaseBO bo = BeanTransform.copyProperties(staffMoveIntendCase,StaffMoveIntendCaseBO.class);
        return bo;
    }

    @Override
    public List<StaffMoveIntendCaseBO> list(StaffMoveIntendCaseDTO dto) throws SerException {
        checkSeeIdentity();
        List<StaffMoveIntendCase> staffMoveIntendCases = super.findByCis(dto);
        List<StaffMoveIntendCaseBO> staffMoveIntendCaseBOS = BeanTransform.copyProperties(staffMoveIntendCases,StaffMoveIntendCaseBO.class);
        return staffMoveIntendCaseBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StaffMoveIntendCaseBO insert(StaffMoveIntendCaseTO to) throws SerException {
        checkAddIdentity();
        StaffMoveIntendCase staffMoveIntendCase = BeanTransform.copyProperties(to,StaffMoveIntendCase.class,true);
        staffMoveIntendCase.setCreateTime(LocalDateTime.now());
        super.save(staffMoveIntendCase);
        StaffMoveIntendCaseBO bo = BeanTransform.copyProperties(staffMoveIntendCase,StaffMoveIntendCaseBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StaffMoveIntendCaseBO edit(StaffMoveIntendCaseTO to) throws SerException {
        checkAddIdentity();
        if(StringUtils.isNotBlank(to.getId())){
            StaffMoveIntendCase staffMoveIntendCase = super.findById(to.getId());
            LocalDateTime createTime =staffMoveIntendCase.getCreateTime();
            staffMoveIntendCase = BeanTransform.copyProperties(to,StaffMoveIntendCase.class,true);
            staffMoveIntendCase.setCreateTime(createTime);
            staffMoveIntendCase.setModifyTime(LocalDateTime.now());
            super.update(staffMoveIntendCase);
            StaffMoveIntendCaseBO bo = BeanTransform.copyProperties(staffMoveIntendCase,StaffMoveIntendCaseBO.class);
            return bo;
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if(StringUtils.isNotBlank(id)){
            super.remove(id);
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<String> getName() throws SerException {
        Set<String> set = new HashSet<>();
        List<StaffMoveIntendCase> list = super.findAll();
        for (StaffMoveIntendCase intendCase:list){
            set.add(intendCase.getName());
        }
        return new ArrayList<>(set);
    }

    @Override
    public StaffMoveIntendCaseBO importExcel(List<StaffMoveIntendCaseTO> caseTOS) throws SerException {
        List<StaffMoveIntendCase> list = new ArrayList<>(caseTOS.size());
        for (StaffMoveIntendCaseTO to:caseTOS){
            StaffMoveIntendCase intendCase = BeanTransform.copyProperties(to,StaffMoveIntendCase.class,true);
            list.add(intendCase);
        }
        super.save(list);
        StaffMoveIntendCaseBO bo = BeanTransform.copyProperties(new StaffMoveIntendCase(),StaffMoveIntendCaseBO.class);
        return bo;
    }

    @Override
    public byte[] exportExcel(StaffMoveIntendCaseDTO dto) throws SerException {
        if(null != dto.getName()){
            dto.getConditions().add(Restrict.eq("name",dto.getName()));
        }
        List<StaffMoveIntendCase> list = super.findByCis(dto);
        List<StaffMoveIntendCaseExport> exports = new ArrayList<>();
        list.stream().forEach(str->{
            StaffMoveIntendCaseExport export = BeanTransform.copyProperties(str,StaffMoveIntendCaseExport.class,"askWorkArea","obeyPlan");
            //是否对工作地区有要求
            if(str.getAskWorkArea().equals(false)){
                export.setAskWorkArea("否");
            }else {
                export.setAskWorkArea("是");
            }
            //是否服从全国范围内调动安排
            if(str.getObeyPlan().equals(false)){
                export.setObeyPlan("否");
            }else {
                export.setObeyPlan("是");
            }
            exports.add(export);
        });
        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports,excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<StaffMoveIntendCaseTemplateExcel> templateExcels = new ArrayList<>();
        StaffMoveIntendCaseTemplateExcel templateExcel = new StaffMoveIntendCaseTemplateExcel();
        templateExcel.setName("test");
        templateExcel.setPosition("test");
        templateExcel.setArea("test");
        templateExcel.setProjectGroup("test");
        templateExcel.setBusinessDirection("test");
        templateExcel.setSkill("test");
        templateExcel.setEntryTime(LocalDate.now());
        templateExcel.setLiveArea("test");
        templateExcel.setAskWorkArea("是");
        templateExcel.setObeyPlan("是");
        templateExcel.setObeyStaffArea("test");
        templateExcel.setMasterSkillA("test");
        templateExcel.setMasterSkillB("test");
        templateExcel.setMasterSkillC("test");
        templateExcel.setMasterSkillD("test");
        templateExcel.setMasterSkillE("test");
        templateExcel.setMasterSkillF("test");

        templateExcels.add(templateExcel);
        Excel excel = new Excel(0,2);
        byte[] bytes= ExcelUtil.clazzToExcel(templateExcels,excel);
        return bytes;
    }
}
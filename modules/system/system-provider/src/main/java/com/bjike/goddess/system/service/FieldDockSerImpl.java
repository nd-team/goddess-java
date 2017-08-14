package com.bjike.goddess.system.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.system.bo.FieldDockBO;
import com.bjike.goddess.system.dto.FieldDockDTO;
import com.bjike.goddess.system.entity.CusPermission;
import com.bjike.goddess.system.entity.FieldDock;
import com.bjike.goddess.system.enums.GuideAddrStatus;
import com.bjike.goddess.system.excel.FieldDockExport;
import com.bjike.goddess.system.excel.FieldDockTemplateExcel;
import com.bjike.goddess.system.to.FieldDockTO;
import com.bjike.goddess.system.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 字段对接业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 11:43 ]
 * @Description: [ 字段对接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "systemSerCache")
@Service
public class FieldDockSerImpl extends ServiceImpl<FieldDock, FieldDockDTO> implements FieldDockSer {
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
        return flag;
    }
    @Override
    public Long count(FieldDockDTO dto) throws SerException {
        search(dto);
        Long count = super.count(dto);
        return count;
    }

    @Override
    public FieldDockBO getOne(String id) throws SerException {
        FieldDock fieldDock = super.findById(id);
        return BeanTransform.copyProperties(fieldDock, FieldDockBO.class);
    }

    @Override
    public List<FieldDockBO> list(FieldDockDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        search(dto);
        List<FieldDock> fieldDocks = super.findByCis(dto);
        List<FieldDockBO> fieldDockBOS = BeanTransform.copyProperties(fieldDocks, FieldDockBO.class);
        return fieldDockBOS;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public FieldDockBO insert(FieldDockTO to) throws SerException {
        FieldDock fieldDock = BeanTransform.copyProperties(to, FieldDock.class, true);
        fieldDock.setCreateTime(LocalDateTime.now());
        verify(to);
        super.save(fieldDock);
        return BeanTransform.copyProperties(fieldDock, FieldDockBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FieldDockBO edit(FieldDockTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            FieldDock fieldDock = super.findById(to.getId());
            BeanTransform.copyProperties(to, fieldDock, true);
            fieldDock.setModifyTime(LocalDateTime.now());
            verify(to);
            super.update(fieldDock);
            return BeanTransform.copyProperties(fieldDock, FieldDockBO.class);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }


    @Override
    public FieldDockBO importExcel(List<FieldDockTO> fieldDockTOS) throws SerException {
        List<FieldDock> fieldDocks = new ArrayList<>(fieldDockTOS.size());
        for (FieldDockTO fieldDockTO : fieldDockTOS) {
            FieldDock fieldDock = BeanTransform.copyProperties(fieldDockTO, FieldDock.class, true);
            fieldDocks.add(fieldDock);
        }
        super.save(fieldDocks);
        FieldDockBO fieldDockBO = BeanTransform.copyProperties(new FieldDock(), FieldDockBO.class);
        return fieldDockBO;
    }

    @Override
    public byte[] exportExcel(FieldDockDTO dto) throws SerException {
        if (null != dto.getProjectName()) {
            dto.getConditions().add(Restrict.in("projectName", dto.getProjectName()));
        }
        List<FieldDock> list = super.findByCis(dto);
        List<FieldDockExport> fieldDockExports = new ArrayList<>();
        list.stream().forEach(str -> {
            FieldDockExport export = BeanTransform.copyProperties(str, FieldDockExport.class);
            fieldDockExports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(fieldDockExports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<FieldDockTemplateExcel> fieldDockTemplateExcels = new ArrayList<>();
        FieldDockTemplateExcel fieldDockTemplateExcel = new FieldDockTemplateExcel();
        fieldDockTemplateExcel.setBusinessMarketPlatform(1);
        fieldDockTemplateExcel.setCapitalMarketPlatform(1);
        fieldDockTemplateExcel.setProjectMarketPlatform(1);
        fieldDockTemplateExcel.setEmployeeMarketPlatform(1);
        fieldDockTemplateExcel.setLifeValuePlatform(1);
        fieldDockTemplateExcel.setCreativePlatform(1);
        fieldDockTemplateExcel.setSkillPlatform(1);
        fieldDockTemplateExcel.setNodeUpdateTime(LocalDate.now());
        fieldDockTemplateExcel.setProjectNum("test");
        fieldDockTemplateExcel.setProjectName("test");
        fieldDockTemplateExcel.setNewNodeUpdateTime(LocalDate.now());
        fieldDockTemplateExcel.setNewNodeNum("test");
        fieldDockTemplateExcel.setNewNode("test");
        fieldDockTemplateExcel.setFieldUpdateTime(LocalDate.now());
        fieldDockTemplateExcel.setNewFieldNum("test");
        fieldDockTemplateExcel.setNewField("test");
        fieldDockTemplateExcel.setFieldRepetition("test");
        fieldDockTemplateExcel.setChargeField("test");
        fieldDockTemplateExcel.setCharge("test");
        fieldDockTemplateExcel.setChargeNewField("test");
        fieldDockTemplateExcel.setRepetition("test");
        fieldDockTemplateExcel.setSummaryField("test");
        fieldDockTemplateExcel.setPlan(1);
        fieldDockTemplateExcel.setPlanProjectName("test");
        fieldDockTemplateExcel.setPlanNode("test");
        fieldDockTemplateExcel.setPlanField("test");
        fieldDockTemplateExcel.setPlanFieldNum("test");
        fieldDockTemplateExcel.setWelfare(1);
        fieldDockTemplateExcel.setWelfareProjectName("test");
        fieldDockTemplateExcel.setWelfareNode("test");
        fieldDockTemplateExcel.setWelfareField("test");
        fieldDockTemplateExcel.setWelfareFieldNum("test");
        fieldDockTemplateExcel.setLiteracy(1);
        fieldDockTemplateExcel.setLiteracyProjectName("test");
        fieldDockTemplateExcel.setLiteracyNode("test");
        fieldDockTemplateExcel.setLiteracyField("test");
        fieldDockTemplateExcel.setLiteracyFieldNum("test");
        fieldDockTemplateExcel.setAccount(1);
        fieldDockTemplateExcel.setAccountProjectName("test");
        fieldDockTemplateExcel.setAccountNode("test");
        fieldDockTemplateExcel.setAccountField("test");
        fieldDockTemplateExcel.setAccountFieldNum("test");
        fieldDockTemplateExcel.setMoney(1);
        fieldDockTemplateExcel.setMoneyProjectName("test");
        fieldDockTemplateExcel.setMoneyNode("test");
        fieldDockTemplateExcel.setMoneyField("test");
        fieldDockTemplateExcel.setMoneyFieldNum("test");
        fieldDockTemplateExcel.setBudget(1);
        fieldDockTemplateExcel.setBudgetProjectName("test");
        fieldDockTemplateExcel.setBudgetNode("test");
        fieldDockTemplateExcel.setBudgetField("test");
        fieldDockTemplateExcel.setBudgetFieldNum("test");
        fieldDockTemplateExcel.setCustomer(1);
        fieldDockTemplateExcel.setCustomerProjectName("test");
        fieldDockTemplateExcel.setCustomerNode("test");
        fieldDockTemplateExcel.setCustomerField("test");
        fieldDockTemplateExcel.setCustomerFieldNum("test");
        fieldDockTemplateExcel.setBusiness(1);
        fieldDockTemplateExcel.setBusinessProjectName("test");
        fieldDockTemplateExcel.setBusinessNode("test");
        fieldDockTemplateExcel.setBusinessField("test");
        fieldDockTemplateExcel.setBusinessFieldNum("test");
        fieldDockTemplateExcel.setProgress(1);
        fieldDockTemplateExcel.setProgressProjectName("test");
        fieldDockTemplateExcel.setProgressNode("test");
        fieldDockTemplateExcel.setProgressField("test");
        fieldDockTemplateExcel.setProgressFieldNum("test");
        fieldDockTemplateExcels.add(fieldDockTemplateExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(fieldDockTemplateExcels, excel);
        return bytes;
    }
    private void search(FieldDockDTO dto) throws SerException {
        if (null != dto.getBusinessMarketPlatform()) {
            dto.getConditions().add(Restrict.eq("businessMarketPlatform", dto.getBusinessMarketPlatform()));
        }
        if (null != dto.getCapitalMarketPlatform()) {
            dto.getConditions().add(Restrict.eq("capitalMarketPlatform",dto.getCapitalMarketPlatform()));
        }
        if (null != dto.getProjectMarketPlatform()) {
            dto.getConditions().add(Restrict.eq("projectMarketPlatform",dto.getProjectMarketPlatform()));
        }
        if (null != dto.getEmployeeMarketPlatform()) {
            dto.getConditions().add(Restrict.eq("employeeMarketPlatform",dto.getEmployeeMarketPlatform()));
        }
        if (null != dto.getLifeValuePlatform()) {
            dto.getConditions().add(Restrict.eq("lifeValuePlatform",dto.getLifeValuePlatform()));
        }
        if (null != dto.getCreativePlatform()) {
            dto.getConditions().add(Restrict.eq("creativePlatform",dto.getCreativePlatform()));
        }
        if (null != dto.getSkillPlatform()) {
            dto.getConditions().add(Restrict.eq("skillPlatform",dto.getSkillPlatform()));
        }
        if (null != dto.getPlan()) {
            dto.getConditions().add(Restrict.eq("plan",dto.getPlan()));
        }
        if (null != dto.getWelfare()) {
            dto.getConditions().add(Restrict.eq("welfare",dto.getWelfare()));
        }
        if (null != dto.getLiteracy()) {
            dto.getConditions().add(Restrict.eq("literacy",dto.getLiteracy()));
        }
        if (null != dto.getAccount()) {
            dto.getConditions().add(Restrict.eq("account",dto.getAccount()));
        }
        if (null != dto.getMoney()) {
            dto.getConditions().add(Restrict.eq("money",dto.getMoney()));
        }
        if (null != dto.getBudget()) {
            dto.getConditions().add(Restrict.eq("budget",dto.getBudget()));
        }
        if (null != dto.getCustomer()) {
            dto.getConditions().add(Restrict.eq("custome",dto.getCustomer()));
        }
        if (null != dto.getBusiness()) {
            dto.getConditions().add(Restrict.eq("business",dto.getBusiness()));
        }
        if (null != dto.getProgress()) {
            dto.getConditions().add(Restrict.eq("progress",dto.getProgress()));
        }
    }
    private FieldDockBO verify(FieldDockTO to) throws SerException {
        FieldDock fieldDock = BeanTransform.copyProperties(to, FieldDock.class, true);
        fieldDock.setCreateTime(LocalDateTime.now());
        if (null == to.getBusinessMarketPlatform() || to.getBusinessMarketPlatform().intValue() == 1) {
            fieldDock.setBusinessMarketPlatform(to.getBusinessMarketPlatform());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getCapitalMarketPlatform() || to.getCapitalMarketPlatform().intValue() == 1) {
            fieldDock.setCapitalMarketPlatform(to.getCapitalMarketPlatform());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getProjectMarketPlatform() || to.getProjectMarketPlatform().intValue() == 1) {
            fieldDock.setProjectMarketPlatform(to.getProjectMarketPlatform());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getEmployeeMarketPlatform() || to.getEmployeeMarketPlatform().intValue() == 1) {
            fieldDock.setEmployeeMarketPlatform(to.getEmployeeMarketPlatform());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getLifeValuePlatform() || to.getLifeValuePlatform().intValue() == 1) {
            fieldDock.setLifeValuePlatform(to.getLifeValuePlatform());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getCreativePlatform() || to.getCreativePlatform().intValue() == 1) {
            fieldDock.setCreativePlatform(to.getCreativePlatform());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getSkillPlatform() || to.getSkillPlatform().intValue() == 1) {
            fieldDock.setSkillPlatform(to.getSkillPlatform());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }

        if (null == to.getPlan() || to.getPlan().intValue() == 1) {
            fieldDock.setPlan(to.getPlan());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getWelfare() || to.getWelfare().intValue() == 1) {
            fieldDock.setWelfare(to.getWelfare());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getLiteracy() || to.getLiteracy().intValue() == 1) {
            fieldDock.setLiteracy(to.getLiteracy());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getAccount() || to.getAccount().intValue() == 1) {
            fieldDock.setAccount(to.getAccount());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getMoney() || to.getMoney().intValue() == 1) {
            fieldDock.setMoney(to.getMoney());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getBudget() || to.getBudget().intValue() == 1) {
            fieldDock.setBudget(to.getBudget());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }

        if (null == to.getCustomer() || to.getCustomer().intValue() == 1) {
            fieldDock.setCustomer(to.getCustomer());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getBusiness() || to.getBusiness().intValue() == 1) {
            fieldDock.setBusiness(to.getBusiness());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        if (null == to.getProgress() || to.getProgress().intValue() == 1) {
            fieldDock.setProgress(to.getProgress());
        } else {
            throw new SerException("这个只能填1或者不填哦");
        }
        return BeanTransform.copyProperties(fieldDock, FieldDockBO.class);
    }

}
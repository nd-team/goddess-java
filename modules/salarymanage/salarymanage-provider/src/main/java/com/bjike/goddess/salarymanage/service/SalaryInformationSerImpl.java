package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.assistance.api.AgeAssistAPI;
import com.bjike.goddess.assistance.api.ComputerAssistAPI;
import com.bjike.goddess.assistance.api.HotAssistAPI;
import com.bjike.goddess.assistance.api.HouseAssistAPI;
import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.bo.ComputerAssistBO;
import com.bjike.goddess.assistance.bo.HotAssistBO;
import com.bjike.goddess.assistance.bo.HouseAssistBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.managementpromotion.api.LevelShowAPI;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.managepromotion.api.OverviewSkillLevelAPI;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.salaryconfirm.api.SalaryconfirmAPI;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.entity.SalaryInformation;
import com.bjike.goddess.salarymanage.enums.GuideAddrStatus;
import com.bjike.goddess.salarymanage.excel.SalaryInformationSetExcel;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import com.bjike.goddess.secure.api.AttachedAPI;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 薪资管理业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-31 01:45 ]
 * @Description: [ 薪资管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "salarymanageSerCache")
@Service
public class SalaryInformationSerImpl extends ServiceImpl<SalaryInformation, SalaryInformationDTO> implements SalaryInformationSer {
    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private LevelShowAPI levelShowAPI;

    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    @Autowired
    private HotAssistAPI hotAssistAPI;

    @Autowired
    private HouseAssistAPI houseAssistAPI;

    @Autowired
    private ComputerAssistAPI computerAssistAPI;

    @Autowired
    private AgeAssistAPI ageAssistAPI;

    @Autowired
    private ModuleAPI moduleAPI;

    @Autowired
    private StaffRecordsAPI staffRecordsAPI;

    @Autowired
    private RegularizationAPI regularizationAPI;

    @Autowired
    private OverviewSkillLevelAPI overviewSkillLevelAPI;

    @Autowired
    private SalaryconfirmAPI salaryconfirmAPI;

    @Autowired
    private AttachedAPI attachedAPI;

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
    public List<SalaryInformationBO> pageList(SalaryInformationDTO dto) throws SerException {
        //规划人能查看所有的 还能根据名称来查找,默认是查找所有的
        if (guideAddIdentity() == true) {
            if (dto.getPayStarTime() != null && dto.getPayEndTime() != null) {
                LocalDate[] localDates = new LocalDate[]{DateUtil.parseDate(dto.getPayStarTime()), DateUtil.parseDate(dto.getPayEndTime())};
                dto.getConditions().add(Restrict.between("payStarTime", localDates));
                dto.getConditions().add(Restrict.between("payEndTime", localDates));
            }
            if (dto.getPayStarTime() != null && dto.getPayEndTime() == null) {
                LocalDate endTime = LocalDate.now();
                LocalDate[] localDates = new LocalDate[]{DateUtil.parseDate(dto.getPayStarTime()), endTime};
                dto.getConditions().add(Restrict.between("payStarTime", localDates));
                dto.getConditions().add(Restrict.between("payEndTime", localDates));
            }
            if (dto.getPayStarTime() == null && dto.getPayEndTime() != null) {
                dto.getConditions().add(Restrict.lt_eq("payEndTime", dto.getPayEndTime()));
            }
            if (dto.getEmployeeName() != null) {
                dto.getConditions().add(Restrict.eq("employeeName", dto.getEmployeeName()));
            }
        } else {
            UserBO userBO = userAPI.currentUser();
            dto.getConditions().add(Restrict.eq("employeeId", userBO.getEmployeeNumber()));
        }
        List<SalaryInformation> list = super.findByCis(dto);
        List<SalaryInformationBO> salaryInformationBOS = BeanTransform.copyProperties(list, SalaryInformationBO.class);
        return salaryInformationBOS;
    }

    @Override
    public SalaryInformationBO addSalaryInformation(SalaryInformationTO to) throws SerException {
        isExit(to);
        SalaryInformation salaryInformation = BeanTransform.copyProperties(to, SalaryInformation.class, true);
        salaryInformation.setCreateTime(LocalDateTime.now());
        super.save(salaryInformation);
        SalaryInformationBO salaryInformationBO = BeanTransform.copyProperties(salaryInformation, SalaryInformationBO.class);
        return salaryInformationBO;
    }

    //判断添加之前数据库是否已经存在该数据（判断规则是根据员工编号id和计薪周期)
    private void isExit(SalaryInformationTO to) throws SerException {
        String id = to.getEmployeeId();
        SalaryInformationDTO dto = new SalaryInformationDTO();
        dto.getConditions().add(Restrict.eq("employeeId", id));
        dto.getConditions().add(Restrict.eq("payStartTime", to.getPayStartTime()));
        dto.getConditions().add(Restrict.eq("payEndTime", to.getPayEndTime()));
        List<SalaryInformation> list = super.findByCis(dto);
        if (list.size() > 0) {
            throw new SerException("该数据已经存在,不可重复添加");
        }
    }


    @Override
    public SalaryInformationBO editSalaryInformation(SalaryInformationTO to) throws SerException {
        SalaryInformation temp = super.findById(to.getId());
        if (temp != null) {
            try {
                DateUtil.parseDate(to.getPayStartTime());
                DateUtil.parseDate(to.getPayEndTime());
            } catch (Exception e) {
                throw new SerException("输入的日期格式不对");
            }
            SalaryInformation salaryInformation = BeanTransform.copyProperties(to, SalaryInformation.class, true);
            BeanUtils.copyProperties(salaryInformation, temp, "id", "createTime");
            temp.setModifyTime(LocalDateTime.now());
            super.update(temp);
        } else {
            throw new SerException("你要修改的数据不存在！");
        }
        SalaryInformationBO salaryInformationBO = BeanTransform.copyProperties(temp, SalaryInformationBO.class);
        return salaryInformationBO;
    }

    @Override
    public void deleteSalaryInformation(String id) throws SerException {
        super.remove(id);
    }


    @Override
    public void leadExcel(List<SalaryInformationTO> toList) throws SerException {
        //只有模块规划负责人才有导入权限
        checkAddIdentity();
        UserBO userBO = userAPI.currentUser();
        List<SalaryInformation> list = BeanTransform.copyProperties(toList, SalaryInformation.class, true);
        list.stream().forEach(str -> {
            str.setModifyTime(LocalDateTime.now());
            str.setCreateTime(LocalDateTime.now());
        });
        super.save(list);

    }

    @Override
    public byte[] exportExcel(ExportSalaryInformationTO to) throws SerException {
//        checkAddIdentity();
        SalaryInformationDTO dto = new SalaryInformationDTO();
        //根据计薪开始时间和计薪结束时间来导出excel
        if (StringUtils.isNotBlank(to.getPayStarTime()) && StringUtils.isNotBlank(to.getPayEndTime())) {
            LocalDate[] localDates = new LocalDate[]{DateUtil.parseDate(dto.getPayStarTime()), DateUtil.parseDate(dto.getPayEndTime())};
            dto.getConditions().add(Restrict.between("payStarTime", localDates));
            dto.getConditions().add(Restrict.between("payEndTime", localDates));
        }

        List<SalaryInformation> list = super.findByCis(dto);
        List<SalaryInformationSetExcel> toList = new ArrayList<SalaryInformationSetExcel>();
        for (SalaryInformation model : list) {
            SalaryInformationSetExcel excel = new SalaryInformationSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public List<String> findTime() throws SerException {
        SalaryInformationDTO dto = new SalaryInformationDTO();
        List<SalaryInformationBO> list = this.pageList(dto);
        List<String> timeList = new ArrayList<>();
        for (SalaryInformationBO salaryInformationBO : list) {
            timeList.add(salaryInformationBO.getPayStartTime());
            timeList.add(salaryInformationBO.getPayEndTime());
        }
        return timeList;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<SalaryInformationSetExcel> salaryInformationSetExcels = new ArrayList<>();

        SalaryInformationSetExcel excel = new SalaryInformationSetExcel();

        excel.setPayStartTime("2017-08-09");
        excel.setPayEndTime("2017-08-09");
        excel.setArea("地区");
        excel.setEmployeeId("员工编号");
        excel.setEmployeeName("姓名");
        excel.setSystem("体系");
        excel.setSection("部门/项目组");
        excel.setStation("岗位");
        excel.setStationLevel("岗位层级");
        excel.setManageLevel("管理层级");
        excel.setSkill("技能项");
        excel.setProSkills("技能专业");
        excel.setSkillLevel("技能级别");
        excel.setHiredate("2017-01-01");
        excel.setPositiveTime("2017-01-02");
        excel.setWorkingTime("2");
        excel.setBasicSalary(10d);
        excel.setPostSalary(10d);
        excel.setManagePay(10d);
        excel.setSkillSubsidies(10d);
        excel.setManageSubsidies(10d);
        excel.setSeniorityLevSubsidies(10d);
        excel.setAllSubsidies(10d);
        excel.setProjectBenefits(10d);
        excel.setWage(10d);
        excel.setSalary(10d);
        excel.setSkillPay(10d);
        excel.setComputerSubsidies(10d);
        excel.setAccommodationSubsidies(10d);
        excel.setSenioritySubsidies(10d);
        excel.setHyperthermiaSubsidies(10d);
        excel.setAllSalary(10d);
        excel.setJinpoCost(10d);
        excel.setJinpoSubsidies(10d);
        excel.setUtilities(10d);
        excel.setPersonTax(10d);
        excel.setAllRewardScore(10d);
        excel.setAllRewardCost(10d);
        excel.setAttendanceDay(10d);
        excel.setVacateDay(10d);
        excel.setAbsenteeismDay(10d);
        excel.setUnfinishedTime(10d);
        excel.setNormalOvertimeDay(10d);
        excel.setLegalRestDay(10d);
        excel.setLegalOvertimeDay(10d);
        excel.setNormalRestDay(10d);
        excel.setRestOvertimeDay(10d);
        excel.setSurplusOvertimeDay(10d);
        excel.setOffsetOvertime(10d);
        excel.setEffectiveOvertime(10d);
        excel.setWeekDays(10d);
        excel.setPaidDay(10d);
        excel.setRemark("备注");
        salaryInformationSetExcels.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(salaryInformationSetExcels, exce);
        return bytes;
    }

    @Override
    public LevelShow findByEmployeeId(String employeeId) throws SerException {
        if (null != employeeId) {
            LevelShow levelShow = levelShowAPI.findByEmployeeId(employeeId);
            return levelShow;
        } else {
            throw new SerException("员工编号不能为空");
        }
    }

    @Override
    public List<EntryBasicInfoBO> getByEmpNumber(String employeeId) throws SerException {
        List<EntryBasicInfoBO> bo = entryBasicInfoAPI.getByEmpNumber(employeeId);
        return bo;
    }

    @Override
    public Long count(SalaryInformationDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public HotAssistBO findHotAssist(SalaryInformationDTO dto) throws SerException {
        HotAssistBO bo = hotAssistAPI.findHot(dto.getPayStarTime(), dto.getPayEndTime());
        return bo;
    }

    @Override
    public HouseAssistBO findHouseAssist(SalaryInformationDTO dto) throws SerException {
        HouseAssistBO bo = houseAssistAPI.findHouse(dto.getPayStarTime(), dto.getPayEndTime());
        return bo;
    }

    @Override
    public ComputerAssistBO findComputerAssist(SalaryInformationDTO dto) throws SerException {
        ComputerAssistBO bo = computerAssistAPI.findComputer(dto.getPayStarTime(), dto.getPayEndTime());
        return bo;
    }

    @Override
    public AgeAssistBO findAgeAssist(SalaryInformationDTO dto) throws SerException {
//        AgeAssistBO bo = ageAssistAPI.findAge(dto.getPayStarTime(), dto.getPayEndTime());
//        return bo;
        return null;
    }


    @Override
    public SalaryInformationBO findOne(String id) throws SerException {
        SalaryInformation salaryInformation = super.findById(id);
        SalaryInformationBO salaryInformationBO = BeanTransform.copyProperties(salaryInformation, SalaryInformationBO.class);
        return salaryInformationBO;
    }

    @Override
    public StaffRecordsBO findStaff(String employeeNumber) throws SerException {
        StaffRecordsBO bo = new StaffRecordsBO();
        if (moduleAPI.isCheck("archive")) {
            bo = staffRecordsAPI.findByNumber(employeeNumber);
        }
        return bo;
    }

    @Override
    public String findPositiveDate(String employeeId) throws SerException {
        String time = regularizationAPI.time(employeeId);
        return time;
    }

    @Override
    public OverviewSkillLevelBO findSkill(String employeeName) throws SerException {
        OverviewSkillLevelBO bo = overviewSkillLevelAPI.findByName(employeeName);
        return bo;
    }

    @Override
    public SalaryconfirmBO findSalaryConfirm(SalaryInformationDTO dto) throws SerException {
        SalaryconfirmBO bo = salaryconfirmAPI.findSalary(dto.getPayStarTime(), dto.getPayEndTime(), dto.getEmployeeName());
        return bo;
    }

    @Override
    public AttachedBO findAttached(SalaryInformationDTO dto) throws SerException {
        AttachedBO bo = attachedAPI.findAttached(dto.getEmployeeName());
        return bo;
    }

    @Override
    //chenjunhao
    public SalaryInformationBO findByName(String name) throws SerException {
        SalaryInformationDTO dto = new SalaryInformationDTO();
        dto.getConditions().add(Restrict.eq("employeeName", name));
        List<SalaryInformation> list = super.findByCis(dto);
        if (null != list && !list.isEmpty()) {
            return BeanTransform.copyProperties(list.get(0), SalaryInformationBO.class);
        }
        return null;
    }
}
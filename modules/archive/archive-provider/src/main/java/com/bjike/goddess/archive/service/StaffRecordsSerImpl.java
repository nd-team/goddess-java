package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.*;
import com.bjike.goddess.archive.dto.ForeignStaffingSetDTO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.ForeignStaffingSet;
import com.bjike.goddess.archive.entity.StaffRecords;
import com.bjike.goddess.archive.entity.StaffRecords1Excel;
import com.bjike.goddess.archive.entity.StaffRecordsExcel;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.StaffRecords1ExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsTO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.entity.DimissionInfo;
import com.bjike.goddess.dimission.enums.DimissionStatus;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 员工档案业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class StaffRecordsSerImpl extends ServiceImpl<StaffRecords, StaffRecordsDTO> implements StaffRecordsSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private RotainCusPermissionSer cusPermissionSer;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ForeignStaffingSetSer foreignStaffingSetSer;
    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;

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
            flag = cusPermissionSer.getRotainCusPermission("1");
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
            flag = cusPermissionSer.getRotainCusPermission("2");
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
            flag = cusPermissionSer.getRotainCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 员工档案列表层级查看权限（层级级别）
     */
    private Boolean guideSeeLevelIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busRotainCusPermission(flagId);
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
            flag = cusPermissionSer.getRotainCusPermission("2");
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void upload(List<StaffRecordsExcelTO> toList) throws SerException {
        for (int i = 1; i <= toList.size(); i++) {
            this.isExist(toList.get(i - 1), i);
        }
        List<StaffRecords> list = BeanTransform.copyProperties(toList, StaffRecords.class, true);
        for (StaffRecords staffRecords : list) {
            staffRecords.setStatus(Status.THAW);
        }
        super.save(list);
    }

    private void isExist(StaffRecordsExcelTO to, Integer row) throws SerException {
        if (this.findByName(to.getUsername()) != null)
            throw new SerException(String.format("第%d行的姓名已存在", row));
        if (this.findByNumber(to.getSerialNumber()) != null)
            throw new SerException(String.format("第%d行的员工编号已存在", row));

    }

    private void isExist(StaffRecords1ExcelTO to, Integer row) throws SerException {
        if (this.findByName(to.getUsername()) != null)
            throw new SerException(String.format("第%d行的姓名已存在", row));
        if (this.findByNumber(to.getSerialNumber()) != null)
            throw new SerException(String.format("第%d行的员工编号已存在", row));

    }

    @Override
    public StaffRecordsBO findByName(String username) throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        dto.getConditions().add(Restrict.eq("username", username));
        StaffRecords entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return BeanTransform.copyProperties(super.findOne(dto), StaffRecordsBO.class);
    }

    @Override
    public StaffRecordsBO findByNumber(String serialNumber) throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        dto.getConditions().add(Restrict.eq("serialNumber", serialNumber));
        StaffRecords entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return BeanTransform.copyProperties(entity, StaffRecordsBO.class);
    }

    @Override
    public List<StaffRecordsBO> maps(StaffRecordsDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        dto.getSorts().add("serialNumber=desc");
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        return BeanTransform.copyProperties(super.findByPage(dto), StaffRecordsBO.class);
    }

    @Override
    public StaffRecordsBO getById(String id) throws SerException {
        StaffRecords entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, StaffRecordsBO.class);
    }

    @Override
    public Long getTotal(StaffRecordsDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        return super.count(dto);
    }

    @Override
    public List<StaffNameBO> getName() throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        List<StaffRecordsBO> list = maps(dto);
        List<StaffNameBO> nameBOs = new ArrayList<>();
        if (null != list && list.size() > 0) {
            for (StaffRecordsBO bo : list) {
                StaffNameBO staffNameBO = new StaffNameBO();
                staffNameBO.setId(bo.getSerialNumber());
                staffNameBO.setName(bo.getUsername());
                nameBOs.add(staffNameBO);
            }
        }
        return nameBOs;
    }

    @Override
    public List<PerBO> getPerBO(String name) throws SerException {
        List<PerBO> list = new ArrayList<>();
        List<EntryRegisterBO> entryBasicInfoBOs = entryRegisterAPI.getEntryRegisterByName(name);
        if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
            EntryRegisterBO entryBasicInfoBO = entryBasicInfoBOs.get(0);
            String id = entryBasicInfoBO.getId();
            StaffRecordsBO staffRecordsBO = getById(id);
            if (null != staffRecordsBO) {
                PerBO perBO = new PerBO();
                perBO.setPerid(staffRecordsBO.getIdentityCard());
                perBO.setPhone(staffRecordsBO.getTelephone());
                list.add(perBO);
            }
        }
        return list;
    }

    @Override
    public List<StaffRecordsBO> listEmployee() throws SerException {
        List<EntryRegisterBO> entryBasicInfoBOList = entryRegisterAPI.list();
        if (null != entryBasicInfoBOList && entryBasicInfoBOList.size() > 0) {
            for (EntryRegisterBO entryBasicInfoBO : entryBasicInfoBOList) {
                StaffRecordsBO staffRecordsBO = new StaffRecordsBO();
                staffRecordsBO.setUsername(entryBasicInfoBO.getUsername());
                staffRecordsBO.setSerialNumber(entryBasicInfoBO.getEmpNumber());
                staffRecordsBO.setProject(entryBasicInfoBO.getDepartment());
                staffRecordsBO.setPosition(entryBasicInfoBO.getPosition());
                if (moduleAPI.isCheck("staffentry")) {
                    EntryRegisterBO entryRegister = entryRegisterAPI.getByNumber(entryBasicInfoBO.getEmpNumber());
                    if (null != entryRegister) {
                        staffRecordsBO.setEducation(entryRegister.getEducation());
                        staffRecordsBO.setSchool(entryRegister.getSchoolTag());
                        staffRecordsBO.setGraduate(entryRegister.getGraduationDate().toString());
                        staffRecordsBO.setBirth(entryRegister.getBirthday().toString());
                        staffRecordsBO.setAddress(entryRegister.getRegisteredAddress());
                        staffRecordsBO.setIdentityCard(entryRegister.getIdCard());
                    }
                }
                staffRecordsBO.setMajor(entryBasicInfoBO.getProfession());
                staffRecordsBO.setEntryTime(entryBasicInfoBO.getInductionDate());
                staffRecordsBO.setSeniority(getMonthSpace(entryBasicInfoBO.getInductionDate(), LocalDateTime.now().toString()));
                staffRecordsBO.setTelephone(entryBasicInfoBO.getPhone());
                //银行卡账号和开户行
//                staffRecordsBO.setBankCard(entryBasicInfoBO.getBankCardID());
//                staffRecordsBO.setBank(entryBasicInfoBO.getBankOfDeposit());
                staffRecordsBO.setEmail(entryBasicInfoBO.getEmail());
//                staffRecordsBO.setStatus();
            }
        }
        return null;
    }

    public int getMonthSpace(String date1, String date2) throws SerException {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));
            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        } catch (Exception e) {
            throw new SerException("");
        }
        return result == 0 ? 1 : Math.abs(result);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        List<StaffRecordsExcel> toList = new ArrayList<StaffRecordsExcel>();
        StaffRecordsExcel staffRecordsExcel = new StaffRecordsExcel();
        toList.add(staffRecordsExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public void dimissionUpload(List<StaffRecords1ExcelTO> toList) throws SerException {
        for (int i = 1; i <= toList.size(); i++) {
            this.isExist(toList.get(i - 1), i);
        }
        List<StaffRecords> list = BeanTransform.copyProperties(toList, StaffRecords.class, true);
        if (null != list && list.size() > 0) {
            for (StaffRecords entity : list) {
                entity.setStatus(Status.CONGEAL);
                if (moduleAPI.isCheck("staffentry")) {
                    EntryRegisterBO entryRegister = entryRegisterAPI.getByNumber(entity.getSerialNumber());
                    if (null != entryRegister) {
                        entity.setGraduate(DateUtil.parseDate(entryRegister.getGraduationDate()));
                    }
                }
                if (moduleAPI.isCheck("organize")) {
                    List<String> stringList = positionDetailUserAPI.getPosition(entity.getUsername());
                    if (!CollectionUtils.isEmpty(stringList)) {
                        entity.setPosition(stringList.get(0));
                    }
                }
            }
        }
        super.save(list);
    }

    @Override
    public List<StaffRecords1BO> dimissionMaps(StaffRecordsDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        dto.getSorts().add("serialNumber=desc");
        dto.getConditions().add(Restrict.eq("status", Status.CONGEAL));
        return BeanTransform.copyProperties(super.findByPage(dto), StaffRecords1BO.class);
    }

    @Override
    public Long count(StaffRecordsDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        dto.getConditions().add(Restrict.eq("status", Status.CONGEAL));
        return super.count(dto);
    }

    @Override
    public byte[] templateDimissionExcel() throws SerException {
        List<StaffRecords1Excel> toList = new ArrayList<>(0);
        StaffRecords1Excel staffRecordsExcel = new StaffRecords1Excel();
        toList.add(staffRecordsExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

//    private void isExist(StaffRecordsExcelTO to, Integer row) throws SerException {
//        if (this.findByName(to.getUsername()) != null)
//            throw new SerException(String.format("第%d行的姓名已存在", row));
//        if (this.findByNumber(to.getSerialNumber()) != null)
//            throw new SerException(String.format("第%d行的员工编号已存在", row));
//
//    }


    @Override
    public List<StaffRecordsBO> findByMonth(Integer month) throws SerException {
        String[] staff = new String[]{"id", "createTime", "modifyTime", "address", "bank", "bankCard", "birth", "dimissionTime", "education", "email", "entryTime", "graduate", "identityCard", "major", "position", "project", "school", "seniority", "serialNumber", "status", "telephone", "username"};
        String sql = "select * from archive_staff_records where month(birth) = '" + month + "'";
        List<StaffRecords> list = super.findBySql(sql, StaffRecords.class, staff);
        List<StaffRecordsBO> boList = BeanTransform.copyProperties(list, StaffRecordsBO.class, false);
        return boList;
    }

    @Override
    //chenjunhao
    public StaffRecordsBO getByName(String name) throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        dto.getConditions().add(Restrict.eq("username", name));
        List<StaffRecords> list = super.findByCis(dto);
        if (!list.isEmpty()) {
            return BeanTransform.copyProperties(list.get(0), StaffRecordsBO.class);
        }
        return null;
    }

    @Override
    public CurrentMessageBO findCurrentMessage() throws SerException {
        UserBO userBO = userAPI.currentUser();
        userBO.getEmployeeNumber();
        StaffRecordsDTO staffRecordsDTO = new StaffRecordsDTO();
        staffRecordsDTO.getConditions().add(Restrict.eq("serialNumber", userBO.getEmployeeNumber()));
        List<StaffRecords> staffRecordses = super.findByCis(staffRecordsDTO);
        if (null != staffRecordses && staffRecordses.size() > 0) {
            StaffRecords entity = staffRecordses.get(0);
            CurrentMessageBO currentMessageBO = new CurrentMessageBO();
            currentMessageBO.setHeadAdrees(userBO.getHeadSculpture());
            currentMessageBO.setUsername(userBO.getUsername());
            currentMessageBO.setSerialNumber(userBO.getEmployeeNumber());
            currentMessageBO.setDimissionTime(DateUtil.dateToString(entity.getDimissionTime()));
            currentMessageBO.setEntryTime(DateUtil.dateToString(entity.getEntryTime()));
            currentMessageBO.setBirth(DateUtil.dateToString(entity.getBirth()));
            currentMessageBO.setEmail(entity.getEmail());
            //如果离职时间不存在,证明还在职
            if (null == entity.getDimissionTime()) {
                currentMessageBO.setIncumbency(true);
            } else {
                currentMessageBO.setIncumbency(false);
            }
            return currentMessageBO;
        }
        return null;
    }

    @Override
    public void add(StaffRecordsTO to) throws SerException {
        StaffRecords entity = BeanTransform.copyProperties(to, StaffRecords.class, true);
        entity.setStatus(Status.THAW);
        super.save(entity);
    }

    @Override
    public void edit(StaffRecordsTO to) throws SerException {
        StaffRecords entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据队形不能为空");
        }
        BeanUtils.copyProperties(to, entity, "createTime", "id");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public StaffRecordsBO findEntity(String id) throws SerException {
        StaffRecords entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据队形不能为空");
        }
        StaffRecordsBO bo = BeanTransform.copyProperties(entity, StaffRecordsBO.class, false);
        return bo;
    }

    @Override
    public void delete(String id) throws SerException {
        StaffRecords entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据队形不能为空");
        }
        super.remove(entity);
    }

    @Override
    public byte[] exportExcel(StaffRecordsDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        List<StaffRecords> list = super.findByCis(dto);
        Excel excel = new Excel(0, 2);

        List<StaffRecordsExcel> staffRecordsExcels = new ArrayList<>(0);
        if (null != list && list.size() > 0) {
            list.stream().forEach(obj -> {
                StaffRecordsExcel staffRecordsExcel = BeanTransform.copyProperties(obj, StaffRecordsExcel.class, false);
                staffRecordsExcels.add(staffRecordsExcel);
            });
        }

        byte[] bytes = ExcelUtil.clazzToExcel(staffRecordsExcels, excel);
        return bytes;
    }

    @Override
    public void freeze(String id) throws SerException {
        StaffRecords entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据队形不能为空");
        }
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thaw(String id) throws SerException {
        StaffRecords entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据队形不能为空");
        }
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<StaffRecordsCollectBO> dayCollect(String day) throws SerException {
        String startTime = "";
        String endTime = "";
        if (StringUtils.isBlank(day)) {
            day = DateUtil.dateToString(LocalDate.now());
        }
        if (StringUtils.isNotBlank(day)) {
            startTime = day;
            endTime = getAfterDay(day);
        }
        List<StaffRecordsCollectBO> list = findCollect(startTime, endTime, true);
        return list;
    }

    @Override
    public List<StaffRecordsCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startTime = date[0];
        String endTime = date[1];
        List<StaffRecordsCollectBO> list = findCollect(startTime, endTime, true);
        return list;
    }

    @Override
    public List<StaffRecordsCollectBO> monthCollect(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        List<StaffRecordsCollectBO> list = findCollect(startTime, endTime, true);
        return list;
    }

    @Override
    public List<StaffRecordsCollectBO> totalCollect() throws SerException {
        String startTime = "";
        String endTime = "";
        List<StaffRecordsCollectBO> list = findCollect(startTime, endTime, true);
        return list;
    }

    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        if (StringUtils.isBlank(day)) {
            day = DateUtil.dateToString(LocalDate.now());
        }
        String startTime = day;
        String endTime = getAfterDay(day);
        //获得部门汇总数据
        List<StaffRecordsCollectBO> staffRecordsCollectBOs = findCollect(startTime, endTime, false);
        List<StaffRecordsGraphicalBO> list = BeanTransform.copyProperties(staffRecordsCollectBOs, StaffRecordsGraphicalBO.class);

        String text_1 = "员工信息管理日汇总" + "(" + startTime + "-" + endTime + ")";
        return getOptionBO(text_1, list);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startTime = date[0];
        String endTime = date[1];

        //获得部门汇总数据
        List<StaffRecordsCollectBO> staffRecordsCollectBOs = findCollect(startTime, endTime, false);
        List<StaffRecordsGraphicalBO> list = BeanTransform.copyProperties(staffRecordsCollectBOs, StaffRecordsGraphicalBO.class);

        String text_1 = "员工信息管理周汇总柱状图" + "(" + startTime + "-" + endTime + ")";
        return getOptionBO(text_1, list);
    }

    @Override
    public OptionBO figureShowMonth(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        //获得部门汇总数据
        List<StaffRecordsCollectBO> staffRecordsCollectBOs = findCollect(startTime, endTime, false);
        List<StaffRecordsGraphicalBO> list = BeanTransform.copyProperties(staffRecordsCollectBOs, StaffRecordsGraphicalBO.class);

        String text_1 = "员工信息管理月汇总图形化" + "(" + startTime + "-" + endTime + ")";
        return getOptionBO(text_1, list);
    }

    @Override
    public OptionBO figureShowAll() throws SerException {
        String startTime = "";
        String endTime = "";

        //获得部门汇总数据
        List<StaffRecordsCollectBO> staffRecordsCollectBOs = findCollect(startTime, endTime, false);
        List<StaffRecordsGraphicalBO> list = BeanTransform.copyProperties(staffRecordsCollectBOs, StaffRecordsGraphicalBO.class);

        String text_1 = "员工信息管理累计汇总柱状图";
        return getOptionBO(text_1, list);
    }

    @Override
    public StaffRecordsDataBO findDataByName(String name) throws SerException {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (moduleAPI.isCheck("staffentry")) {
            List<EntryRegisterBO> entryRegisterBOs = entryRegisterAPI.getEntryRegisterByName(name);
            if (null != entryRegisterBOs && entryRegisterBOs.size() > 0) {
                EntryRegisterBO entryRegisterBO = entryRegisterBOs.get(0);
                StaffRecordsDataBO bo = BeanTransform.copyProperties(entryRegisterBO, StaffRecordsBO.class);
                bo.setSerialNumber(entryRegisterBO.getEmpNumber());
                bo.setProject(entryRegisterBO.getDepartment());
                bo.setMajor(entryRegisterBO.getProfession());
                bo.setSchool(entryRegisterBO.getSchoolTag());
                bo.setGraduate(entryRegisterBO.getGraduationDate());
                bo.setTelephone(entryRegisterBO.getPhone());
                bo.setEntryTime(entryRegisterBO.getInductionDate());
                bo.setSeniority(findSeniority(bo.getEntryTime(), bo.getUsername()));
                bo.setBirth(entryRegisterBO.getBirthday());
                bo.setAddress(entryRegisterBO.getRegisteredAddress());
                bo.setIdentityCard(entryRegisterBO.getIdCard());
                bo.setNowAddress(entryRegisterBO.getLocation());
                return bo;
            }
        }
        return null;
    }

    //tar false:柱状图
    private List<StaffRecordsCollectBO> findCollect(String startTime, String endTime, Boolean tar) throws SerException {
        List<StaffRecordsConditionBO> list = this.findAreaAndProject(tar);
        List<StaffRecordsCollectBO> staffRecordsCollectBOs = new ArrayList<>(0);
        if (null != list && list.size() > 0) {
            for (StaffRecordsConditionBO bo : list) {
                StaffRecordsCollectBO staffRecordsCollectBO = BeanTransform.copyProperties(bo, StaffRecordsCollectBO.class);
                staffRecordsCollectBO.setEntryNum(getData(startTime, endTime, "entryTime", bo, false, tar));
                staffRecordsCollectBO.setStaffFilesNum(getData(startTime, endTime, "modifyTime", bo, false, tar));
                staffRecordsCollectBO.setInformationNum(getData(startTime, endTime, "modifyTime", bo, false, tar));
                staffRecordsCollectBO.setDimissionNum(getData(startTime, endTime, "dimissionTime", bo, false, tar));
                staffRecordsCollectBO.setFreeNum(getData(startTime, endTime, "dimissionTime", bo, true, tar));
                staffRecordsCollectBO.setBiddingNum(getForeignData(startTime, endTime, "招投标", bo, tar));
                staffRecordsCollectBO.setExternalNum(getForeignData(startTime, endTime, "对外宣传", bo, tar));
                staffRecordsCollectBO.setProjectNum(getForeignData(startTime, endTime, "项目人员信息", bo, tar));
                staffRecordsCollectBOs.add(staffRecordsCollectBO);
            }
        }
        return staffRecordsCollectBOs;
    }

    //tar1 false:柱状图
    private Integer getData(String startTime, String endTime, String str, StaffRecordsConditionBO bo, Boolean tar, Boolean tar1) throws SerException {
        String[] files = new String[]{"entryNum"};
        String area = bo.getArea();
        String project = bo.getProject();
        StringBuilder sql = new StringBuilder("select count(" + str + ") as entryNum ");
        sql.append(" from archive_staff_records ");
        sql.append(" where project = '" + project + "' ");
        if (tar1) {
            sql.append(" and area = '" + area + "' ");
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" and " + str + " between '" + startTime + "' ");
            sql.append(" and '" + endTime + "'");
        }
        if (tar) {
            sql.append(" and status = 1 ");
        }
        List<StaffRecordsCollectBO> bos = super.findBySql(sql.toString(), StaffRecordsCollectBO.class, files);
        if (null != bos && bos.size() > 0) {
            return bos.get(0).getEntryNum();
        }
        return 0;
    }

    //tar false:柱状图
    private Integer getForeignData(String startTime, String endTime, String str, StaffRecordsConditionBO bo, Boolean tar) throws SerException {
        String[] files = new String[]{"biddingNum"};
        String type_id = findTypeId(str);
        if (StringUtils.isBlank(type_id)) {
            return 0;
        }
        String area = bo.getArea();
        String project = bo.getProject();
        StringBuilder sql = new StringBuilder("select count(modifyTime) as biddingNum ");
        sql.append(" from archive_foreign_staffing ");
        sql.append(" where project = '" + project + "' ");
        if (tar) {
            sql.append(" and area = '" + area + "' ");
        }
        sql.append(" and type_id = '" + type_id + "' ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" and modifyTime between '" + startTime + "' ");
            sql.append(" and '" + endTime + "'");
        }
        List<StaffRecordsCollectBO> bos = super.findBySql(sql.toString(), StaffRecordsCollectBO.class, files);
        if (null != bos && bos.size() > 0) {
            return bos.get(0).getBiddingNum();
        }
        return 0;
    }

    private String findTypeId(String str) throws SerException {
        ForeignStaffingSetDTO foreignStaffingSetDTO = new ForeignStaffingSetDTO();
        foreignStaffingSetDTO.getConditions().add(Restrict.eq("name", str));
        List<ForeignStaffingSet> list = foreignStaffingSetSer.findByCis(foreignStaffingSetDTO);
        if (null != list && list.size() > 0) {
            return list.get(0).getId();
        }
        return null;
    }

    //tar false:柱状图
    private List<StaffRecordsConditionBO> findAreaAndProject(Boolean tar) throws SerException {
        String[] files = null;
        if (tar) {
            files = new String[]{"project", "area"};
        } else {
            files = new String[]{"project"};
        }
        StringBuilder sql = new StringBuilder("select project as project ");
        if (tar) {
            sql.append(" , area as area ");
        }
        sql.append(" from archive_staff_records order by project,area asc ");
        List<StaffRecordsConditionBO> list = super.findBySql(sql.toString(), StaffRecordsConditionBO.class, files);

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (((null == list.get(i).getProject() && null == list.get(j).getProject()) || list.get(i).getProject().equals(list.get(j).getProject())) && ((null == list.get(i).getArea() && null == list.get(j).getArea()) || list.get(i).getArea().equals(list.get(j).getArea()))) {
                    list.remove(list.get(j));
                }
            }
        }

        Set<StaffRecordsConditionBO> treeSet = filter();
        treeSet.addAll(list);

        List<StaffRecordsConditionBO> staffRecordsConditionBOs = new ArrayList<>(0);
        staffRecordsConditionBOs.addAll(treeSet);
        return staffRecordsConditionBOs;
    }

    private <T> TreeSet<T> filter() throws SerException {
        TreeSet<T> treeSet = new TreeSet<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Field[] field = o1.getClass().getDeclaredFields();//获取实体类的所有属性，返回field数组
                int num = 0;   //用于识别属性相同的个数
                int sum = 0;    //用于识别该对象除了集合的属性值个数
                for (Field f : field) {//遍历所有的属性
                    String type = f.getGenericType().toString();//获取属性的类型
                    if (type.indexOf("java.util.List") < 0) {
                        sum++;
                        String name = f.getName(); // 获取属性的名字
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字符大写，方便构造get，set方法
                        try {
                            Method m = o1.getClass().getMethod("get" + name);
                            Object value = m.invoke(o1);// 调用getter方法获取属性值
                            Method m1 = o2.getClass().getMethod("get" + name);
                            Object value1 = m1.invoke(o2);
                            if (value.equals(value1)) {    //判断该属性值是否相同
                                num++;
                            }
                        } catch (Exception e) {

                        }
                    }
                }
                if (num == sum) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return treeSet;
    }


    private void searchCondition(StaffRecordsDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getUsername())) {
            dto.getConditions().add(Restrict.eq("username", dto.getUsername()));
        }
        if (StringUtils.isNotBlank(dto.getProject())) {
            dto.getConditions().add(Restrict.eq("project", dto.getProject()));
        }
    }

    //获取指定日期的后一天
    private String getAfterDay(String day) throws SerException {
        Calendar c = Calendar.getInstance();
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
            c.setTime(date);
            int day1 = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day1 + 1);
            String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
            return dayAfter;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    //获取某一个月的第一天
    private String findFirstDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(firstDayOfMonth);
        return startTime;
    }

    //获取某一个月的最后一天
    private String findEndDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf.format(lastDayOfMonth);
        return endTime;
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }

    //柱状图数据
    private OptionBO getOptionBO(String text_1, List<StaffRecordsGraphicalBO> list) throws SerException {
        List<String> projectList = list.stream().map(StaffRecordsGraphicalBO::getProject).distinct().collect(Collectors.toList());
        String[] text_2 = projectList.toArray(new String[projectList.size()]);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list3 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_3 = new String[]{"入职人数", "建立员工档案数",
                "录入员工信息数", "离职人数", "冻结员工信息数", "更新招投标人员信息数", "更新对外宣传人员信息数", "更新项目人员信息数"};
        text_list3 = Arrays.stream(text_2).collect(Collectors.toList());
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (String str : text_list3) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(str);
                seriesBO.setType("bar");
                List<Integer> number = new ArrayList<>(0);
                for (StaffRecordsGraphicalBO bo : list) {
                    Integer i = 0;
                    if (str.equals(bo.getProject())) {
                        number.add(bo.getEntryNum());
                        number.add(bo.getStaffFilesNum());
                        number.add(bo.getInformationNum());
                        number.add(bo.getDimissionNum());
                        number.add(bo.getFreeNum());
                        number.add(bo.getBiddingNum());
                        number.add(bo.getExternalNum());
                        number.add(bo.getProjectNum());
                    }
                }
                //柱状图数据
                Integer[] numbers = number.toArray(new Integer[number.size()]);
                seriesBO.setData(numbers);
                seriesBOList.add(seriesBO);
            }
        }
        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        TooltipBO tooltipBO = new TooltipBO();
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    /**
     * 得到在职时间(月)
     */
    private Integer findSeniority(String time, String name) throws SerException {
        try {
            if (StringUtils.isBlank(time)) {
                return 0;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Calendar c1 = Calendar.getInstance();
            c.setTime(sdf.parse(time));

            String time1 = DateUtil.dateToString(LocalDate.now());
            //判断员工是否离职
            if (moduleAPI.isCheck("dimission")) {
                DimissionInfoDTO dimissionInfoDTO = new DimissionInfoDTO();
                List<DimissionInfo> dimissionInfos = dimissionInfoAPI.findByName(name);
                if (null != dimissionInfos && dimissionInfos.size() > 0) {
                    DimissionInfo dimissionInfo = dimissionInfos.get(0);
                    if (DimissionStatus.SUCCESS.equals(dimissionInfo.getDimission())) {
                        time1 = DateUtil.dateToString(dimissionInfo.getDimissionDate());
                    }
                }
            }
            c1.setTime(sdf.parse(time1));
            int result = c1.get(Calendar.MONTH) - c.get(Calendar.MONTH);
            return result == 0 ? 1 : Math.abs(result);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    /**
     * 是否有权限查看所有人的信息(岗位级别)
     */
    private Boolean guideSeePositionIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.guideSeePositionIdentity();
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据岗位查看所有信息或个人信息
     */
    private StaffRecordsDTO findData(StaffRecordsDTO dto) throws SerException {
        if (!guideSeePositionIdentity()) {
            dto = new StaffRecordsDTO();
            String userToken = RpcTransmit.getUserToken();
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            dto.getConditions().add(Restrict.eq("username", userBO.getUsername()));
        }
        return dto;
    }

}
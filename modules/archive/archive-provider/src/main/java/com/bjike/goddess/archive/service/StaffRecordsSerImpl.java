package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.PerBO;
import com.bjike.goddess.archive.bo.StaffNameBO;
import com.bjike.goddess.archive.bo.StaffRecords1BO;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.StaffRecords;
import com.bjike.goddess.archive.entity.StaffRecords1Excel;
import com.bjike.goddess.archive.entity.StaffRecordsExcel;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.StaffRecords1ExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
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
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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
            flag = cusPermissionSer.busRotainCusPermission("2");
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
            flag = cusPermissionSer.busRotainCusPermission("2");
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
            flag = cusPermissionSer.getRotainCusPermission("1");
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
    public Long getTotal() throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
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
        List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.getEntryBasicInfoByName(name);
        if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
            EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoAPI.getEntryBasicInfoByName(name).get(0);
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
        List<EntryBasicInfoBO> entryBasicInfoBOList = entryBasicInfoAPI.listEntryBasicInfo();
        if (null != entryBasicInfoBOList && entryBasicInfoBOList.size() > 0) {
            for (EntryBasicInfoBO entryBasicInfoBO : entryBasicInfoBOList) {
                StaffRecordsBO staffRecordsBO = new StaffRecordsBO();
                staffRecordsBO.setUsername(entryBasicInfoBO.getName());
                staffRecordsBO.setSerialNumber(entryBasicInfoBO.getEmployeeID());
                staffRecordsBO.setProject(entryBasicInfoBO.getProjectGroup());
                staffRecordsBO.setPosition(entryBasicInfoBO.getPosition());
                if (moduleAPI.isCheck("staffentry")) {
                    EntryRegisterBO entryRegister = entryRegisterAPI.getByNumber(entryBasicInfoBO.getEmployeeID());
                    staffRecordsBO.setEducation(entryRegister.getEducation());
                    staffRecordsBO.setSchool(entryRegister.getSchoolTag());
                    staffRecordsBO.setGraduate(entryRegister.getGraduationDate().toString());
                    staffRecordsBO.setBirth(entryRegister.getBirthday().toString());
                    staffRecordsBO.setAddress(entryRegister.getRegisteredAddress());
                    staffRecordsBO.setIdentityCard(entryRegister.getIdCard());
                }
                staffRecordsBO.setMajor(entryBasicInfoBO.getProfession());
                staffRecordsBO.setEntryTime(entryBasicInfoBO.getEntryTime());
                staffRecordsBO.setSeniority(getMonthSpace(entryBasicInfoBO.getEntryTime(), LocalDateTime.now().toString()));
                staffRecordsBO.setTelephone(entryBasicInfoBO.getPhone());
                staffRecordsBO.setBankCard(entryBasicInfoBO.getBankCardID());
                staffRecordsBO.setBank(entryBasicInfoBO.getBankOfDeposit());
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
        dto.getSorts().add("serialNumber=desc");
        dto.getConditions().add(Restrict.eq("status", Status.CONGEAL));
        return BeanTransform.copyProperties(super.findByPage(dto), StaffRecords1BO.class);
    }

    @Override
    public Long count() throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
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
}
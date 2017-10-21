package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.TemperatureSubsidiesBO;
import com.bjike.goddess.assistance.dto.TemperatureSubsidiesDTO;
import com.bjike.goddess.assistance.entity.TemperatureSubsidies;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.excel.TemperatureSubsidiesImport;
import com.bjike.goddess.assistance.excel.TemperatureSubsidiesImportTemple;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.TemperatureSubsidiesExcelTO;
import com.bjike.goddess.assistance.to.TemperatureSubsidiesTO;
import com.bjike.goddess.assistance.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.enums.StaffStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 高温补助业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class TemperatureSubsidiesSerImpl extends ServiceImpl<TemperatureSubsidies, TemperatureSubsidiesDTO> implements TemperatureSubsidiesSer {
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ComputerSubsidiesSer computerSubsidiesSer;
    @Autowired
    private SenioritySubsidiesSer senioritySubsidiesSer;
    @Autowired
    private SenioritySubsidiesStandardSer senioritySubsidiesStandardSer;
    @Autowired
    private SubsidySchemeSer subsidySchemeSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private void checkAduitIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private Boolean guideAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    private Boolean allTrueIdentity() throws SerException {
        return true;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAuditIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean allTrue = allTrueIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("temperaturesubsidies");
        obj.setDescribesion("高温补助");
        if (flagSeeSign || flagAddSign || allTrue) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeComputer = computerSubsidiesSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("computersubsidies");
        obj.setDescribesion("电脑补助");
        if (flagSeeComputer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeSeniorit = senioritySubsidiesSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("senioritysubsidies");
        obj.setDescribesion("工龄补助");
        if (flagSeeSeniorit) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeStandar = senioritySubsidiesStandardSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("senioritysubsidiesstandard");
        obj.setDescribesion("工龄补助标准");
        if (flagSeeStandar) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeScheme = subsidySchemeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("subsidyscheme");
        obj.setDescribesion("公司补助方案");
        if (flagSeeScheme) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case MANAGEAUDIT:
                flag = guideAuditIdentity();
                break;
            case CONFIRM:
                flag = allTrueIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countTempera(TemperatureSubsidiesDTO temperatureSubsidiesDTO) throws SerException {
        seachCondition(temperatureSubsidiesDTO);
        Long count = super.count(temperatureSubsidiesDTO);
        return count;
    }

    @Override
    public TemperatureSubsidiesBO getOneById(String id) throws SerException {
        TemperatureSubsidies temperatureSubsidies = super.findById(id);
        return BeanTransform.copyProperties(temperatureSubsidies, TemperatureSubsidiesBO.class);
    }

    @Override
    public List<TemperatureSubsidiesBO> listTempera(TemperatureSubsidiesDTO temperatureSubsidiesDTO) throws SerException {
        checkSeeIdentity();
        seachCondition(temperatureSubsidiesDTO);
        List<TemperatureSubsidies> temperatureSubsidies = super.findByPage(temperatureSubsidiesDTO);
        List<TemperatureSubsidiesBO> temperatureSubsidiesBOS = BeanTransform.copyProperties(temperatureSubsidies, TemperatureSubsidiesBO.class);
        if(temperatureSubsidiesBOS!=null&&temperatureSubsidiesBOS.size()>0){
            for (TemperatureSubsidiesBO temperatureSubsidiesBO : temperatureSubsidiesBOS) {
                StaffStatus staffStatus = positionDetailUserAPI.statusByName(temperatureSubsidiesBO.getName());//查看员工状态
                if (staffStatus == null) {
                    temperatureSubsidiesBO.setStaffStatus("未获取到数据");
                }else{

                    temperatureSubsidiesBO.setStaffStatus(staffStatus.toString());
                }
            }
        }
        return temperatureSubsidiesBOS;
    }

    public void seachCondition(TemperatureSubsidiesDTO temperatureSubsidiesDTO) throws SerException {
        if (StringUtils.isNotBlank(temperatureSubsidiesDTO.getName())) {
            temperatureSubsidiesDTO.getConditions().add(Restrict.eq("name", temperatureSubsidiesDTO.getName()));
        }
    }

    @Override
    public void saveTempera(TemperatureSubsidiesTO temperatureSubsidiesTO) throws SerException {
        checkSeeIdentity();
        TemperatureSubsidies temperatureSubsidies = BeanTransform.copyProperties(temperatureSubsidiesTO, TemperatureSubsidies.class, true);
        temperatureSubsidies.setCreateTime(LocalDateTime.now());
        temperatureSubsidies.setSubsidiesAmount(temperatureSubsidiesTO.getDays() * temperatureSubsidiesTO.getSubsidiesPrice());
        super.save(temperatureSubsidies);
    }

    @Override
    public void editTempera(TemperatureSubsidiesTO temperatureSubsidiesTO) throws SerException {
        checkSeeIdentity();
        TemperatureSubsidies temperatureSubsidies = super.findById(temperatureSubsidiesTO.getId());
        if (!temperatureSubsidies.getConfirm()) {
            BeanTransform.copyProperties(temperatureSubsidies, temperatureSubsidies, true);
            temperatureSubsidies.setCreateTime(LocalDateTime.now());
            temperatureSubsidies.setSubsidiesAmount(temperatureSubsidiesTO.getDays() * temperatureSubsidiesTO.getSubsidiesPrice());
            super.update(temperatureSubsidies);
        } else {
            throw new SerException("已经被确认了就不能被修改了");
        }
    }

    @Override
    public void deleteTemp(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        checkSeeIdentity();
        List<TemperatureSubsidies> list = super.findAll();
        List<TemperatureSubsidiesImport> temperatureSubsidiesImports = new ArrayList<>();

        for (TemperatureSubsidies temperatureSubsidies : list){
            TemperatureSubsidiesImport excel = BeanTransform.copyProperties(temperatureSubsidies, TemperatureSubsidiesImport.class);
            StaffStatus staffStatus = positionDetailUserAPI.statusByName(excel.getName());//查看员工状态
            if (staffStatus == null) {
                excel.setStaffStatus("未获取到数据");
            }else{

                excel.setStaffStatus(staffStatus.toString());
            }
            temperatureSubsidiesImports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(temperatureSubsidiesImports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<TemperatureSubsidiesImportTemple> temperatureSubsidiesImportTemples = new ArrayList<>();
        TemperatureSubsidiesImportTemple excel = new TemperatureSubsidiesImportTemple();
        excel.setArea("广州");
        excel.setDepartment("研发部");
        excel.setName("张三");
        excel.setEntryDate("2017-09-12");
        excel.setSalaryStartDate("2017-09-12");
        excel.setSalaryEndDate("2017-12-12");
        excel.setOutdoorWorkDate("2017-12-12");
        excel.setDays(10);
        excel.setSubsidiesPrice(100d);
        excel.setSubsidiesAmount(1000d);
        excel.setConfirm("是");
        excel.setConfirmDate("2017-12-12");
        excel.setSubsidiesStatus("在补助");
        temperatureSubsidiesImportTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(temperatureSubsidiesImportTemples, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<TemperatureSubsidiesExcelTO> temperatureSubsidiesExcelTOS) throws SerException {
        checkSeeIdentity();
        List<TemperatureSubsidies> senioritySubsidies = BeanTransform.copyProperties(temperatureSubsidiesExcelTOS, TemperatureSubsidies.class, true);
        senioritySubsidies.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(senioritySubsidies);
    }

    @Override
    public void remindingConfirm(String id) throws SerException {
        TemperatureSubsidies temperatureSubsidies = super.findById(id);
        String email = internalContactsAPI.getEmail(temperatureSubsidies.getName());
        //如果该员工邮箱存在发送邮件,否则就不发
        if (StringUtils.isNotBlank(email)) {
            StringBuffer content = new StringBuffer();
            content.append(temperatureSubsidies.getName() + "你好! 请确认: " + temperatureSubsidies.getSalaryStartDate() + temperatureSubsidies.getSalaryEndDate() + "的高温补助.");
            MessageTO messageTO = new MessageTO();
            messageTO.setContent(content.toString());
            messageTO.setTitle("确认提醒");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);

            messageTO.setReceivers(new String[]{email});
            messageAPI.send(messageTO);
        }
    }

    @Override
    public void confirm(String id, Boolean confirm) throws SerException {
        TemperatureSubsidies temperatureSubsidies = super.findById(id);
        temperatureSubsidies.setConfirm(confirm);
        temperatureSubsidies.setConfirmDate(LocalDate.now());
        temperatureSubsidies.setCreateTime(LocalDateTime.now());
        super.update(temperatureSubsidies);
    }

    @Override
    public TemperatureSubsidiesBO findTemperature(String paytStartTime, String payEndTime) throws SerException {
        TemperatureSubsidiesDTO dto = new TemperatureSubsidiesDTO();
        LocalDate startTime = DateUtil.parseDate(paytStartTime);
        LocalDate endTime = DateUtil.parseDate(payEndTime);
        LocalDate[] time = new LocalDate[]{startTime, endTime};
        dto.getConditions().add(Restrict.between("entryDate", time));
        TemperatureSubsidies temperatureSubsidies = super.findOne(dto);
        TemperatureSubsidiesBO temperatureSubsidiesBO = BeanTransform.copyProperties(temperatureSubsidies, TemperatureSubsidiesBO.class, false);
        return temperatureSubsidiesBO;
    }
}